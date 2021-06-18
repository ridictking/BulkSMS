package com.esd.sercom.bulksms.service.uag;

import com.esd.sercom.bulksms.dao.UagTransactionRepo;
import com.esd.sercom.bulksms.model.DTO.UagTransactionModificationDTO;
import com.esd.sercom.bulksms.model.entity.UagTransactionEntity;
import com.esd.sercom.bulksms.service.telcoapiaproxy.TelcoApiProxyClient;
import com.esd.sercom.bulksms.util.Keyword;
import com.esd.sercom.bulksms.util.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UagService {

    private final UagTransactionRepo uagTransactionRepo;
    private final UagClient uagClient;
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final TelcoApiProxyClient proxyClient;

    @Autowired
    public UagService(UagTransactionRepo uagTransactionRepo, UagClient uagClient, TelcoApiProxyClient proxyClient) {
        this.uagTransactionRepo = uagTransactionRepo;
        this.uagClient = uagClient;
        this.proxyClient = proxyClient;
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
        //ResponseEntity<UagTransactionModificationDTO> modify = uagClient.modify(dto);
        transactionEntity.setStatus(Status.SUCCESSFUL);
        logger.info("Modify data to be persisted: "+transactionEntity.toString());
        UagTransactionEntity save = uagTransactionRepo.save(transactionEntity);
        dto.setCorrelationId(save.getCorrelationId());
        notifyStakeholder();
        return dto;
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
}
