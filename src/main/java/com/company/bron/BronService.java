package com.company.bron;

import com.company.bron.dto.BronCr;
import com.company.base.ApiResponse;
import com.company.bron.dto.BronResp;

import java.util.UUID;

public interface BronService {
    ApiResponse<BronResp> bron(UUID tableId, BronCr bronCr);
}
