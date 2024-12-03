package com.kapasiya.em.controller;

import com.kapasiya.em.dto.RequestDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/status")
public class StatusController {

    @PostMapping
    public ResponseEntity<String> updateStatus(@RequestBody @Valid RequestDto statusRequest) {
        return ResponseEntity.ok("Status :" + statusRequest.getStatus());
    }
}