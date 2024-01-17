package com.company.service.impl;

import com.company.config.security.details.SecurityUtil;
import com.company.entity.BronEntity;
import com.company.entity.TableEntity;
import com.company.expection.exp.AppBadRequestException;
import com.company.model.dto.request.BronCr;
import com.company.model.dto.response.ApiResponse;
import com.company.model.dto.response.BronResp;
import com.company.model.dto.response.TableResp;
import com.company.repository.BronRepository;
import com.company.repository.TableRepository;
import com.company.service.BronService;
import com.company.service.TableService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BronServiceImpl implements BronService {

    private final BronRepository bronRepository;
    private final TableService tableService;

    public BronEntity toEntity(BronCr bronCr) {

        return BronEntity.builder()
                .status(Boolean.TRUE)
                .bronDate(bronCr.bronDate())
                .profileId(SecurityUtil.getCurrentProfileId())
                .build();
    }

    @Override
    @Transactional
    public ApiResponse<BronResp> bron(UUID tableId, BronCr bronCr) {

        bronRepository
                .findBronWithinOneHour(tableId, bronCr.bronDate())
                .ifPresent(bron -> {
                    throw new AppBadRequestException("table.busy.at.this.time");
                });

        TableResp table = tableService
                .getByNumber(bronCr.tableNumber())
                .data();

        //TODO continue
        return null;
    }
}
