package com.company.menu;

import com.company.menu.dto.MenuCr;
import com.company.base.ApiResponse;
import com.company.menu.dto.MenuResp;

public interface MenuService {
    ApiResponse<MenuResp> create(MenuCr menuCr);
}
