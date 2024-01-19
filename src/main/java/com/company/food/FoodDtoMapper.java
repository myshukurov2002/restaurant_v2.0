package com.company.food;

import com.company.food.dto.FoodResp;
import com.company.food.entity.FoodEntity;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class FoodDtoMapper implements Function<FoodEntity, FoodResp> {

    @Override
    public FoodResp apply(FoodEntity foodEntity) {

        return FoodResp.builder()
                .id(foodEntity.getId())
                .name(foodEntity.getName())
                .price(foodEntity.getPrice()).build();
    }
}
