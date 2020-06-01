package com.hongchai.catmall.product.exception;

import com.hongchai.common.exception.BizcodeEnum;
import com.hongchai.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Slf4j
//@ResponseBody
//@ControllerAdvice(basePackages = "com.hongchai.catmall.product.controller")
@RestControllerAdvice(basePackages = "com.hongchai.catmall.product.controller")
public class CatmallExceptionControllerAdvice {


    @ExceptionHandler(value = Exception.class)
    public R handleValidException(MethodArgumentNotValidException e){
      log.error("数据校验出现问题{}，异常类型{}",e.getMessage(),e.getCause());
        BindingResult result=e.getBindingResult();
        Map<String,String> errors=new HashMap<>();
        result.getFieldErrors().forEach(fieldError->{
            errors.put(fieldError.getField(),fieldError.getDefaultMessage());
        });
        return  R.error(BizcodeEnum.VALID_EXCEPTION.getCode(),BizcodeEnum.VALID_EXCEPTION.getMsg()).put("data",errors);

    }

    @ExceptionHandler(value = Throwable.class)
    public R  handleException(Throwable t){

        log.error("错误：",t);
        return  R.error(BizcodeEnum.UNKNOW_EXCEPTION.getCode(),BizcodeEnum.UNKNOW_EXCEPTION.getMsg());
    }
}
