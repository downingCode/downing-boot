package com.downing.boot.exception;

import com.downing.boot.entity.DowningResult;
import com.downing.boot.exception.LogicException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author downing
 * @desc
 * @date 2020/7/22 16:10
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(LogicException.class)
    @ResponseBody
    public DowningResult logicException(LogicException e) {
        return new DowningResult(e.getErrorCode(), e.getMessage(), null);
    }

}
