package com.company.service;

import com.company.model.dto.TableUpdStatus;
import com.company.model.dto.request.TableCr;
import com.company.model.dto.TableResp;
import com.company.model.dto.request.TableUpd;
import com.company.model.dto.response.ApiResponse;

import java.util.UUID;

public interface TableService {
    ApiResponse<TableResp> create(TableCr tableCr);
    ApiResponse<TableResp> update(UUID id, TableUpd tableCr);

    ApiResponse<TableResp> changeStatus(UUID id, TableUpdStatus tableUpdStatus);

    ApiResponse<?> delete(UUID id);
}
