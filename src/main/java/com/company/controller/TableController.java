package com.company.controller;

import com.company.model.dto.TableResp;
import com.company.model.dto.TableUpdStatus;
import com.company.model.dto.request.TableCr;
import com.company.model.dto.request.TableUpd;
import com.company.model.dto.response.ApiResponse;
import com.company.service.TableService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/table")
@RequiredArgsConstructor

public class TableController {

    private final TableService tableService;

    @PostMapping
    @PreAuthorize("hasAnyRole('STAFF')")
    public ResponseEntity<ApiResponse<TableResp>> create(@Valid @RequestBody TableCr tableCr) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(tableService.create(tableCr));
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('STAFF')")
    public ResponseEntity<ApiResponse<TableResp>> update(@RequestParam UUID id,
                                                         @Valid @RequestBody TableUpd tableUpd) {
        return ResponseEntity
                .ok(tableService.update(id, tableUpd));
    }

    @PutMapping("/change")
    @PreAuthorize("hasAnyRole('STAFF')")
    public ResponseEntity<ApiResponse<TableResp>> changeStatus(@RequestParam UUID id,
                                                               @Valid @RequestBody TableUpdStatus tableUpdStatus) {
        return ResponseEntity
                .ok(tableService.changeStatus(id, tableUpdStatus));
    }

    @DeleteMapping
    @PreAuthorize("hasAnyRole('STAFF')")
    public ResponseEntity<ApiResponse<?>> delete(@RequestParam UUID id) {
        return ResponseEntity
                .ok(tableService.delete(id));
    }
}
