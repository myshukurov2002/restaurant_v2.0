package com.company.service.impl;

import com.company.config.i18n.MessageService;
import com.company.config.security.details.SecurityUtil;
import com.company.entity.TableEntity;
import com.company.expection.exp.AppBadRequestException;
import com.company.expection.exp.DuplicateItemException;
import com.company.expection.exp.ItemNotFoundException;
import com.company.model.dto.request.TableCr;
import com.company.model.dto.request.TableUpd;
import com.company.model.dto.request.TableUpdStatus;
import com.company.model.dto.response.ApiResponse;
import com.company.model.dto.response.TableResp;
import com.company.repository.TableRepository;
import com.company.service.TableService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
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

        TableEntity table = tableRepository
                .findByIdAndIsBusyFalseAndVisibilityTrue(id)
                .orElseThrow(() -> new AppBadRequestException("table.is.busy"));

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

        return new ApiResponse<>(true, getMessage("success.deleted"));
    }

    @Override
    public ApiResponse<TableResp> getById(UUID id) {

        return new ApiResponse<>(true, toDto(get(id)));
    }

    @Override
    public ApiResponse<TableResp> getByNumber(Integer number) {

        TableEntity table = tableRepository
                .findByNumberAndVisibilityTrue(number)
                .filter(t -> Objects.equals(t.getIsBusy(), Boolean.FALSE))
                .orElseThrow(ItemNotFoundException::new);

        return new ApiResponse<>(true, toDto(table));
    }
    @Override
    public ApiResponse<Page<TableResp>> getAll(int page, int size) {

        Pageable pageable = PageRequest
                .of(page, size, Sort.by("updatedDate").descending());

        List<TableResp> tableRespList = tableRepository
                .findAllByVisibilityTrue(pageable)
                .stream()
                .map(this::toDto)
                .toList();

        return new ApiResponse<>(true, new PageImpl<>(tableRespList, pageable, tableRespList.size()));
    }

    @Override
    public ApiResponse<Page<TableResp>> getAllByStatus(Boolean isBusy, int page, int size) {

        Pageable pageable = PageRequest
                .of(page, size, Sort.by("updatedDate"));

        List<TableResp> tableRespList = tableRepository
                .findAllByIsBusyAndVisibilityTrue(isBusy, pageable)
                .stream()
                .map(this::toDto)
                .toList();

        return new ApiResponse<>(true, new PageImpl<>(tableRespList, pageable, tableRespList.size()));
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
