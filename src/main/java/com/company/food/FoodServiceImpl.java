package com.company.food;

import com.company.base.ApiResponse;
import com.company.configs.i18n.MessageService;
import com.company.configs.security.utils.SecurityUtil;
import com.company.expection.exp.DuplicateItemException;
import com.company.expection.exp.ItemNotFoundException;
import com.company.food.dto.FoodCr;
import com.company.food.dto.FoodResp;
import com.company.food.dto.FoodUpd;
import com.company.food.entity.FoodEntity;
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
    private final FoodDtoMapper foodDtoMapper;

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

        return new ApiResponse<>(true, getMessage("success.created"), foodDtoMapper.apply(savedFood));
    }

    @Override
    public ApiResponse<FoodResp> update(UUID id, FoodUpd foodUpd) {

        FoodEntity food = get(id);
        food.setPrice(foodUpd.price());
        food.setName(foodUpd.name());

        FoodEntity updatedFood = foodRepository.save(food);

        log.info("food updated, id: " + updatedFood.getId());

        return new ApiResponse<>(true, getMessage("success.updated"), foodDtoMapper.apply(updatedFood));
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

        return new ApiResponse<>(true, foodDtoMapper.apply(food));
    }

    @Override
    public ApiResponse<Page<FoodResp>> getAll(int page, int size) {

        Pageable pageable = PageRequest
                .of(page, size, Sort.by("updatedDate").descending());

        List<FoodResp> foodRespList = foodRepository
                .findAllByVisibilityTrue(pageable)
                .stream()
                .map(foodDtoMapper)
                .toList();

        return new ApiResponse<>(true, new PageImpl<>(foodRespList, pageable, foodRespList.size()));
    }

    @Override
    public ApiResponse<FoodResp> getByName(String name) {

        FoodEntity food = foodRepository
                .findByNameAndVisibilityTrue(name)
                .orElseThrow(ItemNotFoundException::new);

        return new ApiResponse<>(true, foodDtoMapper.apply(food));
    }

    private FoodEntity get(UUID id) {

        return foodRepository
                .findByIdAndVisibilityTrue(id)
                .orElseThrow(ItemNotFoundException::new);
    }

    private FoodEntity toEntity(FoodCr foodCr) {

        return FoodEntity.builder()
                .name(foodCr.name())
                .price(foodCr.price())
                .ownerId(SecurityUtil.getCurrentProfileId()).build();
    }

    private String getMessage(String msg) {
        return messageService.getMessage(msg, LocaleContextHolder.getLocale());
    }
}
