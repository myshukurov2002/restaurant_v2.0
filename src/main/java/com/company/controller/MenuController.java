package com.company.controller;

import com.company.model.dto.request.MenuCr;
import com.company.model.dto.response.ApiResponse;
import com.company.model.dto.response.MenuResp;
import com.company.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/menu")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ApiResponse<MenuResp>> create(
            @Valid @RequestBody MenuCr menuCr){
        return ResponseEntity
                .ok(menuService.create(menuCr));
    }
}
