package com.zara.pricesbackapp.repositories;

import com.zara.pricesbackapp.models.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {

    @Query("SELECT price FROM Price price " +
            "WHERE :date BETWEEN price.startDate AND price.endDate " +
            "AND price.product.id = :productId " +
            "AND price.brand.id = :brandId")
    List<Price> findAllByDateBetweenAndProductIdAndBrandId(LocalDateTime date, Long productId, Long brandId);

}
