package com.lkyl.oceanframework.security.handler;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.lkyl.oceanframework.common.utils.constant.CommonCode;
import com.lkyl.oceanframework.common.utils.constant.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * TODO
 *
 * @version 1.0
 * @author: nicholas
 * @createTime: 2022年05月03日 18:48
 */
@Slf4j
public class OceanAccessDeniedHandler implements AccessDeniedHandler{

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        log.error("error: ", accessDeniedException);
        CommonResult result = new CommonResult().setCode(CommonCode.NO_AUTH).setMsg(CommonCode.AUTH_FAILED_MSG);
        try {
            ObjectMapper mapper = new ObjectMapper();
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            mapper.writeValue(response.getOutputStream(), result);
        } catch (Exception e) {
            log.error("error: ", e);
        }
    }
}