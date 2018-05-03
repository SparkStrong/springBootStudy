package com.zw.springBootStudy;

import com.zw.springBootStudy.authorization.TokenManager;
import com.zw.springBootStudy.util.TokenProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Nack on 2018/5/2.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestTokenManager {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TokenManager tokenManager;

    @Autowired
    private TokenProducer tokenProducer;

    @Test
    public void testGenerateAndSaveToken() {
        String userName = "zhouwang";

        tokenManager.generateAndSaveToken(userName);
    }

    @Test
    public void testCheckToken() {
        logger.debug("test check token!");
        String userName = "zhouwang";

        String token = tokenProducer.generateToken(userName, false);

        if (tokenManager.checkToken(token)) {
            System.out.println("token already exist!");
        } else {
            System.out.println("token not exist!");
        }
    }

    @Test
    public void testLogger() {
        this.logger.debug("debug...");
        this.logger.info("info.....");
        logger.warn("日志输出 warn");
        logger.error("日志输出 error");
    }
}
