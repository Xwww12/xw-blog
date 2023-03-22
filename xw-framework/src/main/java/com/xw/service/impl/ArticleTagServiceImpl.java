package com.xw.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xw.domain.entity.ArticleTag;
import com.xw.mapper.ArticleTagMapper;
import com.xw.service.ArticleTagService;
import org.springframework.stereotype.Service;

@Service
public class ArticleTagServiceImpl extends ServiceImpl<ArticleTagMapper, ArticleTag> implements ArticleTagService {
}
