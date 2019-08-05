package com.gf.oauth2.hi.controller;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.Principal;

@RestController
public class TestEndPointController {
    private String signKey="123456789";
    Logger log=LoggerFactory.getLogger(TestEndPointController.class);

    @GetMapping("/product/{id}")
    public String getProduct(@PathVariable String id){
        return "product id:"+id;
    }

    @GetMapping("/order/{id}")
    public String getOrder(@PathVariable String id){
        return "order Id:"+id;
    }

    @GetMapping("/getPrinciple")
    public OAuth2Authentication getPrinciple(OAuth2Authentication oAuth2Authentication,
                                             Principal principal, Authentication authentication,
                                             HttpServletRequest request) throws UnsupportedEncodingException {
        log.info(oAuth2Authentication.getUserAuthentication().getAuthorities().toString());
        log.info(oAuth2Authentication.toString());
        log.info("-----------principal.toString()-----------"+principal.toString());
        log.info("-----------principal.getName()-----------"+principal.getName());
        log.info("-----------authentication----------------:"+authentication);
//        解析jwtToken
        String header=request.getHeader("Authorization");
        String token=StringUtils.substringAfter(header,"Bearer ");
        Claims body = Jwts.parser().setSigningKey(signKey.getBytes("UTF-8")).parseClaimsJws(token).getBody();
        String company= (String) body.get("company");

        System.out.println("--------------company----"+company);
        return oAuth2Authentication;
    }
}
