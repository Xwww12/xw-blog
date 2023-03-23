package com.xw.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueryArticleDto {
    // 文章标题
    private String title;

    //文章摘要
    private String summary;
}
