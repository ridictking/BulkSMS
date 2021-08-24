package com.esd.sercom.bulksms.api;

import com.esd.sercom.bulksms.dao.BulkSmsPricingRepo;
import com.esd.sercom.bulksms.model.DTO.UagTransactionModificationDTO;
import com.esd.sercom.bulksms.model.base.Response;
import com.esd.sercom.bulksms.model.entity.BulkSmsPricing;
import com.esd.sercom.bulksms.service.uag.UagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("uag/api/v1")
public class TransactionController {

    private final UagService uagService;
    private final BulkSmsPricingRepo bulkSmsPricingRepo;

    @Autowired
    public TransactionController(UagService uagService, BulkSmsPricingRepo bulkSmsPricingRepo) {
        this.uagService = uagService;
        this.bulkSmsPricingRepo = bulkSmsPricingRepo;
    }

    @PostMapping("/modify")
    public ResponseEntity<UagTransactionModificationDTO> modify(@RequestBody UagTransactionModificationDTO dto){
        UagTransactionModificationDTO modify = uagService.modify(dto);
        return ResponseEntity.ok(modify);
    }

    @GetMapping("/verify")
    public ResponseEntity<UagTransactionModificationDTO> verify(@RequestParam String accountName){
        UagTransactionModificationDTO verify = uagService.verify(accountName);
        return ResponseEntity.ok(verify);
    }
    @GetMapping("/query")
    public ResponseEntity<List<UagTransactionModificationDTO>> query(@RequestParam String accountName){
        List<UagTransactionModificationDTO> query = uagService.query(accountName);
        return ResponseEntity.ok(query);
    }

    //Todo endpoint to get all pricing unit
    @GetMapping("/price-list")
    public ResponseEntity<List<BulkSmsPricing>> query(){
        List<BulkSmsPricing> all = uagService.getAllPricing();
        return ResponseEntity.ok(all);
    }
    //Todo endpoint to update and reload cache pricing unit
    //Todo endpoint to add and reload cache unit
    @PostMapping("/add-price")
    public ResponseEntity<Void> addBulkSmsPricing(@RequestBody BulkSmsPricing pricing){
        uagService.addPricingInfo(pricing);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/price-info/{unit}")
    public ResponseEntity<BulkSmsPricing> queryInfo(@PathVariable long unit){
        BulkSmsPricing all = uagService.getPricingInfo(unit);
        return ResponseEntity.ok(all);
    }
    @PostMapping("/update-price/{rank}")
    public ResponseEntity<Void> updateBulkSmsPricing(@PathVariable String rank, @RequestBody BulkSmsPricing pricing){
        uagService.updatePricingInfo(rank, pricing);
        uagService.emptyCache();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
