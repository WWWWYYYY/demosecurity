package com.example.demosecurity.web;

import com.example.demosecurity.common.Constants;
import com.example.demosecurity.utils.VerifyUtil;
import com.example.demosecurity.web.errors.ErrorCode;
import com.example.demosecurity.web.errors.MyException;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

/**
 * 工具类接口
 *
 * @author wangyi
 */
@Log4j2
@Controller
@RequestMapping("/util")
public class UtilResource {

    @Autowired
    private DefaultKaptcha captchaProducer;

    //    @RequestMapping("/createVerifyCode")
//    public void createImg(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        try {
//            //设置相应类型,告诉浏览器输出的内容为图片
//            response.setContentType("image/jpeg");
//            //设置响应头信息，告诉浏览器不要缓存此内容
//            response.setHeader("Pragma", "No-cache");
//            response.setHeader("Cache-Control", "no-cache");
//            response.setDateHeader("Expire", 0);
//            VerifyUtil randomValidateCode = new VerifyUtil();
//            //输出验证码图片
//            randomValidateCode.getRandcode(request, response);
//                //将生成的随机验证码存放到redis中
//    //            redisService.setForValue("RANDOMVALIDATECODEKEY",(String)request.getSession().getAttribute("
//    //                    RANDOMREDISKEY
//    //                    "),Long.parseLong("60000")); } catch (Exception e) { logger.error("获取验证码异常：", e); }"
//    //                            }
//        }catch (Exception e){
//            log.error("生成验证码失败");
//            MyException.buildAndThrow(ErrorCode.CREATE_VERIFY_CODE_ERROR);
//        }
//    }
    @RequestMapping("/createVerifyCode")
    public void createImg(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        byte[] captchaChallengeAsJpeg = null;
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        try {
            //生产验证码字符串并保存到session中
            String createText = captchaProducer.createText();
            httpServletRequest.getSession().setAttribute(Constants.RANDOMCODEKEY, createText);
            //使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
            BufferedImage challenge = captchaProducer.createImage(createText);
            ImageIO.write(challenge, "jpg", jpegOutputStream);
        } catch (IllegalArgumentException e) {
            httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        //定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
        captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
        httpServletResponse.setHeader("Cache-Control", "no-store");
        httpServletResponse.setHeader("Pragma", "no-cache");
        httpServletResponse.setDateHeader("Expires", 0);
        httpServletResponse.setContentType("image/jpeg");
        ServletOutputStream responseOutputStream =
                httpServletResponse.getOutputStream();
        responseOutputStream.write(captchaChallengeAsJpeg);
        responseOutputStream.flush();
        responseOutputStream.close();
    }
}
