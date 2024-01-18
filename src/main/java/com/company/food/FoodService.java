package com.company.food;

import com.company.food.dto.FoodUpd;
import com.company.food.dto.FoodCr;
import com.company.food.dto.FoodResp;
import com.company.base.ApiResponse;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface FoodService {
    ApiResponse<FoodResp> create(FoodCr foodCr);

    ApiResponse<FoodResp> update(UUID id, FoodUpd foodUpd);

    ApiResponse<?> delete(UUID id);

    ApiResponse<FoodResp> getById(UUID id);

    ApiResponse<Page<FoodResp>> getAll(int page, int size);

    ApiResponse<FoodResp> getByName(String name);
}
