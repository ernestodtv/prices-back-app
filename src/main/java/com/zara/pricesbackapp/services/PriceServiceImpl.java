package com.zara.pricesbackapp.services;

import com.zara.pricesbackapp.dtos.PriceDTO;
import com.zara.pricesbackapp.models.Price;
import com.zara.pricesbackapp.repositories.PriceRepository;
import com.zara.pricesbackapp.services.exceptions.PriceNotFoundException;
import com.zara.pricesbackapp.services.mappers.PriceMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class PriceServiceImpl implements PriceService {

    private PriceRepository priceRepository;

    private PriceMapper priceMapper;

    @Override
    public PriceDTO getPriceByFilters(LocalDateTime date, Long productId, Long brandId) {
        List<Price> prices = priceRepository.findAllByDateBetweenAndProductIdAndBrandId(date, productId, brandId);
        if (prices.isEmpty()) {
            throw new PriceNotFoundException("The price was not found");
        }
        Price price;
        if(prices.size() > 1) {
            price = getPriceWithHighestPriority(prices);
        } else {
            price = prices.get(0);
        }
        return priceMapper.mapPriceToPriceDTO(price);
    }

    private Price getPriceWithHighestPriority(List<Price> prices) {
        return prices.stream()
                .max(Comparator.comparing(Price::getPriority))
                .orElseThrow(NoSuchElementException::new);
    }

}
