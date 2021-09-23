package com.msb.dongbao.common.base.result;

import com.msb.dongbao.common.base.enums.StateCodeEnum;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

//返回值
@Data
@Builder
public class ResultWrapper<T> implements Serializable {
    //状态码
    private int code;
    //提示信息
    private String message;
    //返回数据
    private T data;

    /**
     * 返回成功的包装
     * @return
     */
    public static ResultWrapper.ResultWrapperBuilder getSuccessBuilder(){
        return ResultWrapper.builder().code(StateCodeEnum.SUCCESS.getCode()).message(StateCodeEnum.SUCCESS.getMessage());
    }

    public static ResultWrapper.ResultWrapperBuilder getFailBuilder(){
        return ResultWrapper.builder().code(StateCodeEnum.FAIL.getCode()).message(StateCodeEnum.FAIL.getMessage());
    }
}
