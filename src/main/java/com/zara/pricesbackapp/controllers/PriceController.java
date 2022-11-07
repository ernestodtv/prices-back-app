package com.zara.pricesbackapp.controllers;

import com.zara.pricesbackapp.dtos.PriceDTO;
import com.zara.pricesbackapp.services.PriceService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping(path = "/prices")
@AllArgsConstructor
public class PriceController {

    private PriceService priceService;

    @GetMapping
    public ResponseEntity<PriceDTO> getPriceByFilters(
            @RequestParam
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
                    LocalDateTime date,
            @RequestParam Long productId,
            @RequestParam Long brandId) {
        PriceDTO price = priceService.getPriceByFilters(date, productId, brandId);
        return ResponseEntity.ok(price);
    }

}
