package com.example.demosecurity.web;

import com.example.demosecurity.utils.VerifyUtil;
import com.example.demosecurity.web.errors.ErrorCode;
import com.example.demosecurity.web.errors.MyException;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 工具类接口
 * @author wangyi
 */
@Log4j2
@Controller
@RequestMapping("/util")
public class UtilResource {

    @RequestMapping("/createVerifyCode")
    public void createImg(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            //设置相应类型,告诉浏览器输出的内容为图片
            response.setContentType("image/jpeg");
            //设置响应头信息，告诉浏览器不要缓存此内容
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expire", 0);
            VerifyUtil randomValidateCode = new VerifyUtil();
            //输出验证码图片
            randomValidateCode.getRandcode(request, response);
                //将生成的随机验证码存放到redis中
    //            redisService.setForValue("RANDOMVALIDATECODEKEY",(String)request.getSession().getAttribute("
    //                    RANDOMREDISKEY
    //                    "),Long.parseLong("60000")); } catch (Exception e) { logger.error("获取验证码异常：", e); }"
    //                            }
        }catch (Exception e){
            log.error("生成验证码失败");
            MyException.buildAndThrow(ErrorCode.CREATE_VERIFY_CODE_ERROR);
        }
    }
}
