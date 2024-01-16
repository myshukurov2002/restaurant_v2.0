package com.company.service.impl;

import com.company.config.i18n.MessageService;
import com.company.config.security.details.SecurityUtil;
import com.company.entity.TableEntity;
import com.company.expection.exp.DuplicateItemException;
import com.company.expection.exp.ItemNotFoundException;
import com.company.model.dto.TableResp;
import com.company.model.dto.TableUpdStatus;
import com.company.model.dto.request.TableCr;
import com.company.model.dto.request.TableUpd;
import com.company.model.dto.response.ApiResponse;
import com.company.repository.TableRepository;
import com.company.service.TableService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class TableServiceImpl implements TableService {

    private final TableRepository tableRepository;
    private final MessageService messageService;

    @Override
    public ApiResponse<TableResp> create(TableCr tableCr) {

        tableRepository
                .findByNumberAndVisibilityTrue(tableCr.number())
                .ifPresent(table -> {
                    throw new DuplicateItemException();
                });

        TableEntity table = toEntity(tableCr);
        TableEntity savedTable = tableRepository.save(table);

        log.info("table created: " + table.getId());

        return new ApiResponse<>(true, getMessage("success.created"), toDto(savedTable));
    }

    @Override
    public ApiResponse<TableResp> update(UUID id, TableUpd tableCr) {

        TableEntity table = get(id);
        table.setType(tableCr.type());
        table.setNumber(tableCr.number());

        TableEntity updatedTable = tableRepository.save(table);

        log.info("table updated: " + table.getId());

        return new ApiResponse<>(true, getMessage("success.updated"), toDto(updatedTable));
    }

    @Override
    public ApiResponse<TableResp> changeStatus(UUID id, TableUpdStatus tableUpdStatus) {

        TableEntity table = get(id);
        table.setIsBusy(tableUpdStatus.isBusy());

        TableEntity changedTable = tableRepository.save(table);

        log.info("table changed status: " + table.getId() + " " + tableUpdStatus.isBusy());

        return new ApiResponse<>(true, "success.changed.status", toDto(changedTable));
    }

    @Override
    public ApiResponse<?> delete(UUID id) {

        TableEntity table = get(id);
        table.setVisibility(Boolean.FALSE);

        tableRepository.save(table);

        log.warn("table deleted: " + table.getId() + " ");

        return new ApiResponse<>(true, "success.deleted");
    }

    private TableEntity get(UUID id) {

        return tableRepository
                .findByIdAndVisibilityTrue(id)
                .orElseThrow(ItemNotFoundException::new);
    }

    private TableResp toDto(TableEntity savedTable) {

        return TableResp.builder()
                .id(savedTable.getId())
                .isBusy(savedTable.getIsBusy())
                .number(savedTable.getNumber())
                .type(savedTable.getType()).build();
    }

    private String getMessage(String msg) {

        return messageService.getMessage(msg, LocaleContextHolder.getLocale());
    }

    private TableEntity toEntity(TableCr tableCr) {

        return TableEntity.builder()
                .number(tableCr.number())
                .type(tableCr.type())
                .isBusy(Boolean.FALSE)
                .ownerId(SecurityUtil.getCurrentProfileId()).build();
    }
}
