package com.xw.utils;

import com.xw.domain.entity.Article;
import com.xw.domain.vo.HotArticleVo;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class BeanCopyUtils {
    private BeanCopyUtils() {}

    /**
     * 拷贝单个对象
     * @param source
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T copyBean(Object source, Class<T> clazz) {
        // 创建目标对象
        T res = null;
        try {
            res = clazz.newInstance();
            // 实现属性copy
            BeanUtils.copyProperties(source, res);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 返回
        return res;
    }

    /**
     * 拷贝数组
     * @param source
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> copyBeanList(List<?> source, Class<T> clazz) {
        return source.stream()
                .map(o -> copyBean(o, clazz))
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        Article article = new Article();
        article.setId(11111L);
        HotArticleVo hotArticleVo = copyBean(article, HotArticleVo.class);
        System.out.println(hotArticleVo);
    }
}
