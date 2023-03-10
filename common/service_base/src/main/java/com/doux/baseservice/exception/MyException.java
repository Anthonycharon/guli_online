package com.doux.baseservice.exception;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author lenovo
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MyException extends RuntimeException {
    @ApiModelProperty(value = "状态码")
    private Integer code;
    private String msg;

}
