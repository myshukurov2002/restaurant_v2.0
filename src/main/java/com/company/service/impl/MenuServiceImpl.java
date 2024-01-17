package com.company.service.impl;

import com.company.model.dto.request.MenuCr;
import com.company.model.dto.response.ApiResponse;
import com.company.model.dto.response.MenuResp;
import com.company.model.dto.response.TableResp;
import com.company.repository.MenuRepository;
import com.company.service.FoodService;
import com.company.service.MenuService;
import com.company.service.OrderService;
import com.company.service.TableService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;

    private final TableService tableService;
    private final FoodService foodService;
    private final OrderService orderService;

    @Override
    public ApiResponse<MenuResp> create(MenuCr menuCr) {

        TableResp tableResp = tableService
                .getByNumber(menuCr.tableNumber())
                .data();

        for (String name : menuCr.foodNames()) {

        }
//        foodService.getByName(me)

        return null;
    }
}
