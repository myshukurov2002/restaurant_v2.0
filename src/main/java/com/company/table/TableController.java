package com.company.table;

import com.company.table.dto.TableCr;
import com.company.table.dto.TableUpd;
import com.company.table.dto.TableUpdStatus;
import com.company.base.ApiResponse;
import com.company.table.dto.TableResp;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
    @PreAuthorize("hasRole('STAFF')")
    public ResponseEntity<ApiResponse<TableResp>> create(
            @Valid @RequestBody TableCr tableCr) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(tableService.create(tableCr));
    }

    @PutMapping
    @PreAuthorize("hasRole('STAFF')")
    public ResponseEntity<ApiResponse<TableResp>> update(
            @RequestParam UUID id,
            @Valid @RequestBody TableUpd tableUpd) {
        return ResponseEntity
                .ok(tableService.update(id, tableUpd));
    }

    @PutMapping("/change")
    @PreAuthorize("hasRole('STAFF')")
    public ResponseEntity<ApiResponse<TableResp>> changeStatus(
            @RequestParam UUID id,
            @Valid @RequestBody TableUpdStatus tableUpdStatus) {
        return ResponseEntity
                .ok(tableService.changeStatus(id, tableUpdStatus));
    }

    @DeleteMapping
    @PreAuthorize("hasRole('STAFF')")
    public ResponseEntity<ApiResponse<?>> delete(
            @RequestParam UUID id) {
        return ResponseEntity
                .ok(tableService.delete(id));
    }

    @GetMapping("/open/get-by-id")
    public ResponseEntity<ApiResponse<TableResp>> getById(
            @RequestParam UUID id) {
        return ResponseEntity
                .ok(tableService.getById(id));
    }

    @GetMapping("/open/get-by-number")
    public ResponseEntity<ApiResponse<TableResp>> getByNumber(
            @RequestParam Integer number) {
        return ResponseEntity
                .ok(tableService.getByNumber(number));
    }


    @GetMapping("/open/get-all")
    public ResponseEntity<ApiResponse<Page<TableResp>>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity
                .ok(tableService.getAll(page, size));
    }

    @GetMapping("/open/get-all-by-status")
    public ResponseEntity<ApiResponse<Page<TableResp>>> getAllByStatus(
            @RequestParam(defaultValue = "false") Boolean status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity
                .ok(tableService.getAllByStatus(status, page, size));
    }
}
