package com.esd.sercom.bulksms.api;

import com.esd.sercom.bulksms.model.DTO.UagTransactionModificationDTO;
import com.esd.sercom.bulksms.service.uag.UagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("uag/api/v1")
public class TransactionController {

    private final UagService uagService;

    @Autowired
    public TransactionController(UagService uagService) {
        this.uagService = uagService;
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
}
