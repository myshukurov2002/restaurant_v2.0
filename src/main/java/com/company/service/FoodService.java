package com.company.service;

import com.company.model.dto.request.FoodUpd;
import com.company.model.dto.request.FoodCr;
import com.company.model.dto.FoodResp;
import com.company.model.dto.response.ApiResponse;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface FoodService {
    ApiResponse<FoodResp> create(FoodCr foodCr);

    ApiResponse<FoodResp> update(UUID id, FoodUpd foodUpd);

    ApiResponse<?> delete(UUID id);

    ApiResponse<FoodResp> getById(UUID id);

    ApiResponse<Page<FoodResp>> getAll(int page, int size);
}
