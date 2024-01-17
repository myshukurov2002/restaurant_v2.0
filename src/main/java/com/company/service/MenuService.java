package com.company.service;

import com.company.model.dto.request.MenuCr;
import com.company.model.dto.response.ApiResponse;
import com.company.model.dto.response.MenuResp;

public interface MenuService {
    ApiResponse<MenuResp> create(MenuCr menuCr);
}
