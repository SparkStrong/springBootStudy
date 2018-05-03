package com.zw.springBootStudy.authorization;

import com.zw.springBootStudy.util.RedisUtil;
import com.zw.springBootStudy.util.TokenProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by Nack on 2018/4/25.
 */

@Component
public class RedisTokenManagerImp implements TokenManager {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final static long DEFAULT_EXPIRE = 60 * 60 * 24;

    @Autowired
    @Qualifier(value = "tokenProducer")
    private TokenProducer tokenProducer;

    @Resource(name = "redisUtil")
    private RedisUtil redisUtil;

    /**
     * 生成token并保存到redis中
     * @param userName
     * @return
     */
    public String generateAndSaveToken(String userName) {
        String token = tokenProducer.generateToken(userName, false);

        if (token == null) {
            this.logger.error("generate token fail!");

            return token;
        }

        // token 存在于redis中，表明该用户已经处于登录状态
        if (redisUtil.hasKey(token)) {
            redisUtil.get(token, DEFAULT_EXPIRE);  // 更新token的生存时间
            this.logger.info("update token survive time, userName: " + userName + " token: " + token);
        } else {
            redisUtil.set(token, userName, DEFAULT_EXPIRE);
            this.logger.info("first store token in redis, userName: " + userName + " token: " + token);
        }

        return token;
    }

    /**
     * 检查redis中是否含有该token
     * @param token
     * @return
     */
    public boolean checkToken(String token) {
        return redisUtil.hasKey(token);
    }

    /**
     * 从redis中删除token
     * @param token
     */
    public void deleteToken(String token) {
        redisUtil.delete(token);
    }
}
