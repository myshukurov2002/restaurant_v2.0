package com.company.food;

import com.company.food.dto.FoodCr;
import com.company.food.dto.FoodUpd;
import com.company.base.ApiResponse;
import com.company.food.dto.FoodResp;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/food")
public class FoodController {

    private final FoodService foodService;

    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('STAFF', 'CHEF')")
    public ResponseEntity<ApiResponse<FoodResp>> create(
            @Valid @RequestBody FoodCr foodCr) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(foodService.create(foodCr));
    }

    @PutMapping("/update")
    @PreAuthorize("hasAnyRole('STAFF', 'CHEF')")
    public ResponseEntity<ApiResponse<FoodResp>> update(
            @RequestParam UUID id,
            @Valid @RequestBody FoodUpd foodUpd) {
        return ResponseEntity
                .ok(foodService.update(id, foodUpd));
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasAnyRole('STAFF', 'CHEF')")
    public ResponseEntity<ApiResponse<?>> delete(
            @RequestParam UUID id) {
        return ResponseEntity
                .ok(foodService.delete(id));
    }

    @GetMapping("/open/byId/{id}")
    public ResponseEntity<ApiResponse<FoodResp>> getById(
            @PathVariable UUID id) {
        return ResponseEntity
                .ok(foodService.getById(id));
    }

    @GetMapping("/open/byName/{name}")
    public ResponseEntity<ApiResponse<FoodResp>> getByName(
            @PathVariable String name){
        return ResponseEntity
                .ok(foodService.getByName(name));
    }

    @GetMapping("/open/all")
    public ResponseEntity<ApiResponse<Page<FoodResp>>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "8") int size) {
        return ResponseEntity
                .ok(foodService.getAll(page, size));
    }
}
