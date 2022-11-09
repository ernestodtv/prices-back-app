package com.zara.pricesbackapp.services;

import com.zara.pricesbackapp.dtos.PriceDTO;
import com.zara.pricesbackapp.models.Brand;
import com.zara.pricesbackapp.models.Price;
import com.zara.pricesbackapp.models.Product;
import com.zara.pricesbackapp.models.Tariff;
import com.zara.pricesbackapp.repositories.PriceRepository;
import com.zara.pricesbackapp.services.mappers.PriceMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PriceServiceImplTest {

    @Mock
    private PriceRepository priceRepository;

    @Mock
    private PriceMapper priceMapper;

    private PriceService priceService;

    @BeforeEach
    void setUp() {
        priceService = new PriceServiceImpl(priceRepository, priceMapper);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/test-data.csv")
    void testGetPriceByFiltersWithASinglePriceResult(Long priceId, Long brandId, String brandName, Long tariffId, Long productId,
                                    LocalDateTime startDate, LocalDateTime endDate, Double pvp, Integer priority, LocalDateTime date) {

        Price price = getPriceWithSpecificData(priceId, brandId, brandName, tariffId, productId, startDate, endDate, pvp, priority);
        when(priceRepository.findAllByDateBetweenAndProductIdAndBrandId(any(), anyLong(), anyLong()))
                .thenReturn(List.of(price));

        PriceDTO expectedPriceDTO = getPriceDTOWithSpecificData(priceId, productId, brandId, tariffId, startDate, endDate, pvp);
        when(priceMapper.mapPriceToPriceDTO(price))
                .thenReturn(expectedPriceDTO);

        PriceDTO actualPriceDTO = priceService.getPriceByFilters(date, productId, brandId);

        assertEquals(expectedPriceDTO, actualPriceDTO);
    }

    @Test
    void testGetPriceByFiltersWithTwoPriceResults() {
        Long brandId = 1L;
        String brandName = "ZARA";
        Long productId = 35455L;

        Long firstPriceId = 1L;
        Long firstTariffId = 1L;
        LocalDateTime firstStartDate = LocalDateTime.of(2020, 6, 14, 0, 0, 0);
        LocalDateTime firstEndDate = LocalDateTime.of(2020, 12, 31, 23, 59, 59);
        Double firstPvp = 35.5;
        Integer firstPriority = 0;
        Price firstPrice = getPriceWithSpecificData(firstPriceId, brandId, brandName, firstTariffId, productId,
                firstStartDate, firstEndDate, firstPvp, firstPriority);

        Long secondPriceId = 2L;
        Long secondTariffId = 2L;
        LocalDateTime secondStartDate = LocalDateTime.of(2020, 6, 14, 15, 0, 0);
        LocalDateTime secondEndDate = LocalDateTime.of(2020, 6, 14, 18, 30, 0);
        Double secondPvp = 25.45;
        Integer secondPriority = 1;
        Price secondPrice = getPriceWithSpecificData(secondPriceId, brandId, brandName, secondTariffId, productId,
                secondStartDate, secondEndDate, secondPvp, secondPriority);

        List<Price> prices = List.of(firstPrice, secondPrice);
        when(priceRepository.findAllByDateBetweenAndProductIdAndBrandId(any(), anyLong(), anyLong()))
                .thenReturn(prices);

        PriceDTO expectedPriceDTO = getPriceDTOWithSpecificData(secondPriceId, productId, brandId, secondTariffId,
                secondStartDate, secondEndDate, secondPvp);
        when(priceMapper.mapPriceToPriceDTO(secondPrice))
                .thenReturn(expectedPriceDTO);

        LocalDateTime date = LocalDateTime.of(2020, 6, 14, 16, 0, 0);
        PriceDTO actualPriceDTO = priceService.getPriceByFilters(date, productId, brandId);

        assertEquals(expectedPriceDTO, actualPriceDTO);
    }

    private Price getPriceWithSpecificData(Long priceId, Long brandId, String brandName, Long tariffId, Long productId,
                                           LocalDateTime startDate, LocalDateTime endDate, Double pvp, Integer priority) {
        Brand brand = new Brand(brandId, brandName);
        Tariff tariff = new Tariff(tariffId);
        Product product = new Product(productId);

        return Price.builder()
                .id(priceId)
                .brand(brand)
                .tariff(tariff)
                .product(product)
                .startDate(startDate)
                .endDate(endDate)
                .pvp(pvp)
                .priority(priority)
                .build();
    }

    private PriceDTO getPriceDTOWithSpecificData(Long priceId, Long productId, Long brandId, Long tariffId,
                                                 LocalDateTime startDate, LocalDateTime endDate, Double pvp) {
        return PriceDTO.builder()
                .id(priceId)
                .productId(productId)
                .brandId(brandId)
                .tariffId(tariffId)
                .startDate(startDate)
                .endDate(endDate)
                .pvp(pvp)
                .build();
    }

}
