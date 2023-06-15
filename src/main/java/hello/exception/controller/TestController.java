package hello.exception.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import static hello.exception.servlet.ErrorPageController.ERROR_EXCEPTION;
import static hello.exception.servlet.ErrorPageController.ERROR_STATUS_CODE;

@Slf4j
@Controller
public class TestController {

    @RequestMapping(value = "/main")
    public void main(HttpServletRequest request){

        Exception ex = (Exception) request.getAttribute(ERROR_EXCEPTION);
        request.getAttribute(ERROR_STATUS_CODE);

        throw new IllegalArgumentException("잘못된 입력값");
    }

    @ResponseBody
    @RequestMapping("/main1")
    public ResponseEntity<Map<String, Object>> errorPageTest(HttpServletRequest request,
                                                           HttpServletResponse response){

        Exception ex = (Exception) request.getAttribute(ERROR_EXCEPTION);
        Map<String, Object> map = new HashMap<>();
        map.put("status", request.getAttribute(ERROR_STATUS_CODE));
        map.put("message", ex.getMessage());

        Integer statusCode = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        return new ResponseEntity<>(map, HttpStatus.valueOf(ERROR_STATUS_CODE));
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "잘못된 요청")
    @RequestMapping(value = "/test1", produces = MediaType.APPLICATION_JSON_VALUE)
    public void errorTest(){

    }

}
