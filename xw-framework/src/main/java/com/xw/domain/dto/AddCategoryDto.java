package com.xw.domain.dto;

import com.xw.domain.ResponseResult;
import lombok.Data;

@Data
public class AddCategoryDto {
    private String name;

    private String description;

    private String status;
}
