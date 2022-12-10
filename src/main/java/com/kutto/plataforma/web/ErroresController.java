package com.kutto.plataforma.web;

import com.kutto.plataforma.util.StringUtil;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ErroresController implements ErrorController {

    @GetMapping("/error")
    public String error(HttpServletRequest request, Model model) throws Exception {

        if(is404(request)) {
            return "errornotfound";
        }

        return "error";
    }

    private boolean is404(HttpServletRequest request)  {

        String codeHTTPNotFound = StringUtil.toStr(HttpStatus.NOT_FOUND.value());
        String codeStatusRequest = StringUtil.toStr((Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE));
        return StringUtil.equiv(codeStatusRequest, codeHTTPNotFound) ;
    }

}
