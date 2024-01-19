package com.company.menu;

import com.company.base.ApiResponse;
import com.company.configs.security.utils.SecurityUtil;
import com.company.expection.exp.ItemNotFoundException;
import com.company.food.FoodService;
import com.company.food.dto.FoodResp;
import com.company.menu.dto.MenuCr;
import com.company.menu.dto.MenuResp;
import com.company.menu.entity.MenuEntity;
import com.company.order.OrderDtoMapper;
import com.company.order.OrderService;
import com.company.order.dto.OrderCr;
import com.company.order.dto.OrderResp;
import com.company.table.TableService;
import com.company.table.dto.TableResp;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;

    private final TableService tableService;
    private final FoodService foodService;
    private final OrderService orderService;
    private final OrderDtoMapper orderDtoMapper;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public ApiResponse<MenuResp> create(MenuCr menuCr) {

        MenuEntity menu = toEntity(menuCr);

        MenuEntity savedMenu = menuRepository.save(menu);

        for (String name : menuCr.foodNames()) {

            FoodResp foodResp = foodService
                    .getByName(name)
                    .data();

            OrderCr orderCr = OrderCr.builder()
                    .foodId(foodResp.id())
                    .menuId(savedMenu.getId())
                    .price(foodResp.price())
                    .build();

            orderService.create(orderCr);
        }

        MenuEntity entity = get(savedMenu.getId());

        return new ApiResponse<>(true, toDto(entity));
    }

    @Override
    public ApiResponse<MenuResp> getById(UUID id) {
        return new ApiResponse<>(true, toDto(get(id)));
    }

    private MenuEntity get(UUID id) {

        return menuRepository
                .findById(id)
                .orElseThrow(ItemNotFoundException::new);
    }

    private MenuResp toDto(MenuEntity entity) {

        List<OrderResp> orderRespList = entity
                .getOrderEntities()
                .stream()
                .map(orderDtoMapper)
                .toList();

        return MenuResp.builder()
                .orders(orderRespList)
                .tableNumber(tableService.getById(entity.getId()).data().number())
                .id(entity.getId())
                .build();
    }

    private MenuEntity toEntity(MenuCr menuCr) {

        TableResp tableResp = tableService
                .getByNumber(menuCr.tableNumber())
                .data();

        return MenuEntity.builder()
                .ownerId(SecurityUtil.getCurrentProfileId())
                .tableId(tableResp.id())
                .isCanceled(Boolean.FALSE)
                .build();
    }
}
