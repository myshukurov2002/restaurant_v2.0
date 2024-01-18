package com.company.table;

import com.company.table.dto.TableUpdStatus;
import com.company.table.dto.TableCr;
import com.company.table.dto.TableResp;
import com.company.table.dto.TableUpd;
import com.company.base.ApiResponse;
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
