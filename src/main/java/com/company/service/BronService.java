package com.company.service;

import com.company.model.dto.request.BronCr;
import com.company.model.dto.response.ApiResponse;
import com.company.model.dto.response.BronResp;

import java.util.UUID;

public interface BronService {
    ApiResponse<BronResp> bron(UUID tableId, BronCr bronCr);
}
