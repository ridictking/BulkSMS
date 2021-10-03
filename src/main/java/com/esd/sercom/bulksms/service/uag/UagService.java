package com.esd.sercom.bulksms.service.uag;

import com.esd.sercom.bulksms.dao.BulkSmsPricingRepo;
import com.esd.sercom.bulksms.dao.UagTransactionRepo;
import com.esd.sercom.bulksms.exceptions.BadRequestException;
import com.esd.sercom.bulksms.exceptions.NotFoundException;
import com.esd.sercom.bulksms.model.DTO.UagTransactionModificationDTO;
import com.esd.sercom.bulksms.model.entity.BulkSmsPricing;
import com.esd.sercom.bulksms.model.entity.UagTransactionEntity;
import com.esd.sercom.bulksms.service.telcoapiaproxy.TelcoApiProxyClient;
import com.esd.sercom.bulksms.util.Keyword;
import com.esd.sercom.bulksms.util.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UagService {

    private final UagTransactionRepo uagTransactionRepo;
    private final UagClient uagClient;
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final TelcoApiProxyClient proxyClient;
    private final BulkSmsPricingRepo smsPricingRepo;

    @Autowired
    public UagService(UagTransactionRepo uagTransactionRepo, UagClient uagClient, TelcoApiProxyClient proxyClient, BulkSmsPricingRepo smsPricingRepo) {
        this.uagTransactionRepo = uagTransactionRepo;
        this.uagClient = uagClient;
        this.proxyClient = proxyClient;
        this.smsPricingRepo = smsPricingRepo;
    }

    public UagTransactionModificationDTO modify(UagTransactionModificationDTO dto){
        logger.info("Modify data :"+ dto.toString());
        List<UagTransactionEntity> transactions = uagTransactionRepo.findByAccountNameOrderByIdDesc(dto.getAccountName());
        UagTransactionEntity entity = new UagTransactionEntity();
        UagTransactionEntity transactionEntity = entity.of(dto);
        if(!transactions.isEmpty()){
            UagTransactionEntity uagTransactionEntity = transactions.get(0);
            double threshold = uagTransactionEntity.getAmount() * 0.25;
            if(dto.getAmount() >= Math.round(threshold)
                    && uagTransactionEntity.getSubscriptionDate()
                    .plusDays(uagTransactionEntity.getValidityDuration())
                    .isBefore(transactionEntity.getSubscriptionDate())){
                transactionEntity.setRollOver(true);
                dto.setRollOverFlag(1);
                transactionEntity.setNumberOfSms(uagTransactionEntity.getNumberOfSms()+ dto.getNumberOfSms());
            }else{
                dto.setRollOverFlag(0);
            }
        }
        int noOfSms = calculateNumberOfSms(dto) == 0 ? 20 : calculateNumberOfSms(dto);
        //ResponseEntity<UagTransactionModificationDTO> modify = uagClient.modify(dto);
        transactionEntity.setStatus(Status.SUCCESSFUL);
        transactionEntity.setNumberOfSms(noOfSms);
        logger.info("Modify data to be persisted: "+ transactionEntity);
        UagTransactionEntity save = uagTransactionRepo.save(transactionEntity);
        dto.setCorrelationId(save.getCorrelationId());
        notifyStakeholder();
        return dto;
    }

    //Todo Calculate no of sms
    private int calculateNumberOfSms(UagTransactionModificationDTO dto) {

        return 0;
    }


    @Cacheable("unit")
    public BulkSmsPricing getPricingInfo(long boundUnit){
        Optional<BulkSmsPricing> priceInfo = smsPricingRepo.findByLowerBoundUnitLessThanEqualAndUpperBoundUnitGreaterThanEqual(boundUnit,boundUnit);
         if(!priceInfo.isPresent()) throw new NotFoundException("Pricing doesnt not exist");
         return priceInfo.get();
    }
    @CacheEvict(value = "unit", allEntries = true)
    public void emptyCache(){

    }
    public void addPricingInfo(BulkSmsPricing pricing){
        if(pricing == null) throw new BadRequestException("");
        smsPricingRepo.save(pricing);
    }

    public List<BulkSmsPricing> getAllPricing(){
        return smsPricingRepo.findAll();
    }

    public List<UagTransactionModificationDTO> query(String accountName){
        //ResponseEntity<UagTransactionModificationDTO> query = uagClient.query(accountName, Keyword.QUERY.name());
        //return query.getBody();
        List<UagTransactionEntity> uagTransactions = uagTransactionRepo.findByAccountNameOrderByIdDesc(accountName);
        List<UagTransactionModificationDTO> collect = uagTransactions.stream().map(x -> {
            UagTransactionModificationDTO dto = new UagTransactionModificationDTO();
            dto.setCorrelationId(x.getCorrelationId());
            dto.setAmount(x.getAmount());
            dto.setAccountName(x.getAccountName());
            dto.setNumberOfSms(x.getNumberOfSms());
            dto.setValidity(x.getValidityDuration());
            dto.setDateCreated(x.getSubscriptionDate());
            return dto;
        }).collect(Collectors.toList());
        return collect;
    }

    public UagTransactionModificationDTO verify(String accountName){
        ResponseEntity<UagTransactionModificationDTO> verify = uagClient.query(accountName, Keyword.VERIFY.name());
        return verify.getBody();
    }

    private void notifyStakeholder(){
        //Send notification to clients
    }

    public void updatePricingInfo(String rank, BulkSmsPricing pricing) {
        Optional<BulkSmsPricing> byRank =  smsPricingRepo.findByRank(rank);
        if(!byRank.isPresent()) throw new NotFoundException("");
        BulkSmsPricing bulkSmsPricing = byRank.get();
        bulkSmsPricing.setLowerBoundUnit(pricing.getLowerBoundUnit());
        bulkSmsPricing.setUpperBoundUnit(pricing.getUpperBoundUnit());
        bulkSmsPricing.setPromotionalPricePerSms(pricing.getPromotionalPricePerSms());
        bulkSmsPricing.setTransactionalPricePerSms(pricing.getTransactionalPricePerSms());
        smsPricingRepo.save(bulkSmsPricing);
    }

    //Todo endpoint to update and reload cache pricing unit
    //Todo endpoint to add and reload cache unit
}
