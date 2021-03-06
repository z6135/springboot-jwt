package com.zmz.springbootjjwt.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.zmz.springbootjjwt.entity.User;
import org.springframework.stereotype.Service;


/**
 * @author zhangshengrui
 * @date 2022-01-06 21:04
 */
@Service("TokenService")
public class TokenService {
    public String getToken(User user) {
        String token="zsrservice";
        token= JWT.create().withAudience(user.getId())// 将 user id 保存到 token 里面
                .sign(Algorithm.HMAC256(user.getPassword()));// 以 password 作为 token 的密钥
        return token;
    }
}
