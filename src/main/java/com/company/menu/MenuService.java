package com.company.menu;

import com.company.menu.dto.MenuCr;
import com.company.base.ApiResponse;
import com.company.menu.dto.MenuResp;
import io.micrometer.core.instrument.binder.db.MetricsDSLContext;

import java.util.UUID;

public interface MenuService {
    ApiResponse<MenuResp> create(MenuCr menuCr);

    ApiResponse<MenuResp> getById(UUID uuid);
}
