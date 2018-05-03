package com.zw.springBootStudy.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Nack on 2018/5/2.
 */

@Component("tokenProducer")
public class TokenProducer {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public String generateToken(String msg, boolean timeChange) {
        try {
            long currentTime = System.currentTimeMillis();
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(msg.getBytes());

            if (timeChange) {
                byte[] now = (new Long(currentTime)).toString().getBytes();
                md.update(now);
            }

            return toHex(md.digest());
        } catch (NoSuchAlgorithmException e) {
            this.logger.error("generate token exception, no md5 algorithm!");
            return null;
        }
    }

    private String toHex(byte buffer[]) {
        StringBuffer sb = new StringBuffer(buffer.length * 2);
        for (int i = 0; i < buffer.length; i++) {
            sb.append(Character.forDigit((buffer[i] & 240) >> 4, 16));
            sb.append(Character.forDigit(buffer[i] & 15, 16));
        }

        return sb.toString();
    }
}
