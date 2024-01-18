package com.company.menu;

import com.company.menu.dto.MenuCr;
import com.company.base.ApiResponse;
import com.company.menu.dto.MenuResp;
import com.company.table.dto.TableResp;
import com.company.food.FoodService;
import com.company.order.OrderService;
import com.company.table.TableService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
