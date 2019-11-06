package com.imooc.miaosha.controller;

import com.alibaba.druid.util.StringUtils;
import com.imooc.miaosha.domain.MiaoshaUser;
import com.imooc.miaosha.domain.User;
import com.imooc.miaosha.redis.RedisService;
import com.imooc.miaosha.redis.UserKey;
import com.imooc.miaosha.result.CodeMsg;
import com.imooc.miaosha.result.Result;
import com.imooc.miaosha.service.MiaoshaUserService;
import com.imooc.miaosha.service.UserService;
import com.imooc.miaosha.vo.LoginVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@RequestMapping(value="/goods")
public class GoodsController {

    private static Logger log = LoggerFactory.getLogger(GoodsController.class);//

    @Autowired
    UserService userService;

    @Autowired
    RedisService redisService;

    @Autowired
    MiaoshaUserService miaoshaUserService;


    /* 转到商品列表页面
    * @CookieValue()用于取到Cookie中的值，required = false表示可以Cookie不是必须的参数
    * @RequestParam用于兼容手机客户端以参数形式传递Cookie的name
    * 1.做一个取参数的优先级：先取@RequestParam中的参数，没有取到才去@CookieValue中取
    * 2.
    * 3.
    * */


    /* 不再使用如下代码，使用WebConfig配置Session，以提高代码复用性*/
    /**
    @RequestMapping("/to_list")
    public String toGoodsList(HttpServletResponse httpServletResponse, Model model, @CookieValue(value = MiaoshaUserService.COOKIE_NAME_TOKEN, required = false) String cookieToken,
                              @RequestParam(value = MiaoshaUserService.COOKIE_NAME_TOKEN, required = false)String paramToken,
                              MiaoshaUser miaoshaUserParam){
        //如果Cookie和Param中都没有值，则表示没有登录或者过期了，转去登录页面
        if(StringUtils.isEmpty(cookieToken) && StringUtils.isEmpty(paramToken)){
            return "login";
        }

        //此时，Cookie和Param中至少有一个有值，优先取paramToken的值
        String token = StringUtils.isEmpty(paramToken) ? cookieToken:paramToken;

        //获取该Cookie或者Param所对应的用户信息
        MiaoshaUser miaoshaUser = miaoshaUserService.getByToken(httpServletResponse, token);

        model.addAttribute("user", miaoshaUser);
        return "goods_list";
    }
     */


    @RequestMapping("/to_list")
    public String toGoodsList(Model model,MiaoshaUser miaoshaUser){
        model.addAttribute("user", miaoshaUser);
        System.out.println(miaoshaUser.getNickname());
        return "goods_list";
    }



    @RequestMapping("/to_detail")
    public String toGoodsDetail(HttpServletResponse httpServletResponse, Model model, @CookieValue(value = MiaoshaUserService.COOKIE_NAME_TOKEN, required = false) String cookieToken,
                              @RequestParam(value = MiaoshaUserService.COOKIE_NAME_TOKEN, required = false)String paramToken){

        return "goods_detail";
    }


}










