package com.company.service.impl;

import com.company.config.i18n.MessageService;
import com.company.config.security.details.SecurityUtil;
import com.company.entity.FoodEntity;
import com.company.expection.exp.DuplicateItemException;
import com.company.expection.exp.ItemNotFoundException;
import com.company.model.dto.response.FoodResp;
import com.company.model.dto.request.FoodCr;
import com.company.model.dto.request.FoodUpd;
import com.company.model.dto.response.ApiResponse;
import com.company.repository.FoodRepository;
import com.company.service.FoodService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class FoodServiceImpl implements FoodService {

    private final FoodRepository foodRepository;
    private final MessageService messageService;

    @Override
    public ApiResponse<FoodResp> create(FoodCr foodCr) {

        foodRepository
                .findByNameAndVisibilityTrue(foodCr.name())
                .ifPresent(food -> {
                    throw new DuplicateItemException();
                });

        FoodEntity food = toEntity(foodCr);
        FoodEntity savedFood = foodRepository.save(food);

        log.info("food created, id: " + savedFood.getId());

        return new ApiResponse<>(true, getMessage("success.created"), toDto(savedFood));
    }

    @Override
    public ApiResponse<FoodResp> update(UUID id, FoodUpd foodUpd) {

        FoodEntity food = get(id);
        food.setPrice(foodUpd.price());
        food.setName(foodUpd.name());

        FoodEntity updatedFood = foodRepository.save(food);

        log.info("food updated, id: " + updatedFood.getId());

        return new ApiResponse<>(true, getMessage("success.updated"), toDto(updatedFood));
    }

    @Override
    public ApiResponse<?> delete(UUID id) {

        FoodEntity food = get(id);
        food.setVisibility(Boolean.FALSE);

        foodRepository.save(food);

        log.warn("deleted food id: " + id);

        return new ApiResponse<>(true, getMessage("success.deleted"));
    }

    @Override
    public ApiResponse<FoodResp> getById(UUID id) {

        FoodEntity food = get(id);

        return new ApiResponse<>(true, toDto(food));
    }

    @Override
    public ApiResponse<Page<FoodResp>> getAll(int page, int size) {

        Pageable pageable = PageRequest
                .of(page, size, Sort.by("createdDate").descending());

        List<FoodResp> foodRespList = foodRepository
                .findAllByVisibilityTrue(pageable)
                .stream()
                .map(this::toDto)
                .toList();

        Page<FoodResp> foodRespPage = new PageImpl<>(foodRespList, pageable, foodRespList.size());

        return new ApiResponse<>(true, foodRespPage);
    }

    private FoodEntity get(UUID id) {

        return foodRepository
                .findByIdAndVisibilityTrue(id)
                .orElseThrow(ItemNotFoundException::new);
    }

    private FoodResp toDto(FoodEntity savedFood) {

        return FoodResp.builder().id(savedFood.getId()).name(savedFood.getName()).price(savedFood.getPrice()).build();
    }

    private FoodEntity toEntity(FoodCr foodCr) {

        return FoodEntity.builder().name(foodCr.name()).price(foodCr.price()).ownerId(SecurityUtil.getCurrentProfileId()).build();
    }

    private String getMessage(String msg) {

        return messageService.getMessage(msg, LocaleContextHolder.getLocale());
    }
}
