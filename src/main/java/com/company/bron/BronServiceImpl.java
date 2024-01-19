package com.company.bron;

import com.company.configs.security.utils.SecurityUtil;
import com.company.expection.exp.AppBadRequestException;
import com.company.bron.dto.BronCr;
import com.company.base.ApiResponse;
import com.company.bron.dto.BronResp;
import com.company.table.dto.TableResp;
import com.company.table.TableService;
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
