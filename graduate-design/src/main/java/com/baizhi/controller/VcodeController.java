package com.baizhi.controller;

import com.baizhi.conf.CreateValidateCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("vcode")
public class VcodeController {

    @RequestMapping("validateImage")
    public void validateImage(HttpServletResponse response, HttpServletRequest request) throws IOException {
        CreateValidateCode validate = new CreateValidateCode();
        String code = validate.getCode();
        HttpSession session = request.getSession();
        session.setAttribute("validateImage", code);
        validate.write(response.getOutputStream());
    }
}
