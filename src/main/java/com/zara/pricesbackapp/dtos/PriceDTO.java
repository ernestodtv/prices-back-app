package com.zara.pricesbackapp.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PriceDTO {

    private Long id;

    private Long productId;

    private Long brandId;

    private Long tariffId;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private Double pvp;

}
