package com.company.service;

import com.company.model.dto.request.BronCr;
import com.company.model.dto.request.TableUpdStatus;
import com.company.model.dto.request.TableCr;
import com.company.model.dto.response.BronResp;
import com.company.model.dto.response.TableResp;
import com.company.model.dto.request.TableUpd;
import com.company.model.dto.response.ApiResponse;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface TableService {
    ApiResponse<TableResp> create(TableCr tableCr);
    ApiResponse<TableResp> update(UUID id, TableUpd tableCr);

    ApiResponse<TableResp> changeStatus(UUID id, TableUpdStatus tableUpdStatus);

    ApiResponse<?> delete(UUID id);

    ApiResponse<TableResp> getById(UUID id);

    ApiResponse<TableResp> getByNumber(Integer number);

    ApiResponse<Page<TableResp>> getAll(int page, int size);

    ApiResponse<Page<TableResp>> getAllByStatus(Boolean status, int page, int size);
}
