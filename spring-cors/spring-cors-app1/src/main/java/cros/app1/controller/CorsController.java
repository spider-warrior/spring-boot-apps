package cros.app1.controller;

import cn.t.util.web.RequestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

@RequestMapping("cors")
@RestController
public class CorsController {

    private static final Logger logger = LoggerFactory.getLogger(CorsController.class);

    @RequestMapping("test")
    public Object test(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        String oldTicket = RequestUtil.getCookie(request, "ticket");
        logger.info("old ticket: {}", oldTicket);
        String topDomain = RequestUtil.getTopDomain(request);
        RequestUtil.setCookie(response, topDomain, "ticket", "app1:" + String.valueOf(System.currentTimeMillis()), 10000000);
        return "访问app1跨域成功";
    }

    @RequestMapping("show_ticket")
    public Object showCurrentCookie(HttpServletRequest request) {
        return RequestUtil.getCookie(request, "ticket");
    }

}
