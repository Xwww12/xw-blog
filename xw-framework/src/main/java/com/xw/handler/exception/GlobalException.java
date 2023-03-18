package com.xw.handler.exception;

import com.xw.domain.ResponseResult;
import com.xw.enums.AppHttpCodeEnum;
import com.xw.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 */
@RestControllerAdvice
@Slf4j
public class GlobalException {

    /**
     * 处理自定义异常
     * @param e
     * @return
     */
    @ExceptionHandler(SystemException.class)
    public ResponseResult systemExceptionHandler(SystemException e) {
        e.printStackTrace();
//        log.error("出现了异常：{}", e.getMsg());
        return ResponseResult.errorResult(e.getCode(), e.getMsg());
    }

    /**
     * 处理其他异常
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ResponseResult exceptionHandler(Exception e) {
        e.printStackTrace();
//        log.error("出现了异常：{}", e.getMessage());
        return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
    }
}
