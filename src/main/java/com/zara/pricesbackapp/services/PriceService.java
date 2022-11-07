package com.zara.pricesbackapp.services;

import com.zara.pricesbackapp.dtos.PriceDTO;

import java.time.LocalDateTime;

public interface PriceService {

    PriceDTO getPriceByFilters(LocalDateTime date, Long productId, Long brandId);

}
