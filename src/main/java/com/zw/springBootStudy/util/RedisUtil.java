package com.zw.springBootStudy.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * Created by Nack on 2018/5/2.
 * redis 使用的工具类
 */

@Component("redisUtil")
public class RedisUtil {
    // 不设置过期时长
    private final static long NOT_EXPIRE = -1;
    // 默认过期时长，单位：秒/s
    private final static long DEFAULT_EXPIRE = 60 * 60 * 24;

    @Autowired
    RedisTemplate<Object, Object> redisTemplate;

    @Resource(name = "redisTemplate")
    ValueOperations<Object, Object> objectOps;

    /**
     * 设置key和value，和生存时间
     * @param key
     * @param value
     * @param expire
     */
    public void set(Object key, Object value, long expire) {
        if (expire == NOT_EXPIRE) {
            objectOps.set(key, value);
        } else {
            objectOps.set(key, value, expire, TimeUnit.SECONDS);
        }
    }

    public void set(Object key, Object value) {
        set(key, value, NOT_EXPIRE);
    }

    /**
     * 判断key是否存在
     * @param key 键值或者键对象
     * @return
     */
    public boolean hasKey(Object key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 根据键获取String 类型的value，不刷新生存时长
     * @param key 键
     * @return 键对应的value，返回对象
     */
    public String get(Object key) {
        return get(key, NOT_EXPIRE);
    }

    /**
     * 获取String类型的值，并更新生存时间
     * @param key 键
     * @param expire 生存时间
     * @return 值
     */
    public String get(Object key, long expire) {
        String value = (String) objectOps.get(key);

        if (expire != NOT_EXPIRE) {
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }

        return value;
    }

    /**
     * 获取指定key对应的value值，并转换成目标类型，更新生存时长
     * @param key 键值
     * @param tClass 类型
     * @param expire 生存时长，-1 表示不更新
     * @param <T> 返回的类型，可以是任何Object类型
     * @return 获取到的value
     */
    public <T> T get(Object key, Class<T> tClass, long expire) {
        Object value = objectOps.get(key);
        if (expire != NOT_EXPIRE) {
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }

        return value == null ? null : (T) value;
    }

    /**
     * 获取指定类型的值，不刷新生存时间
     * @param key
     * @param tClass
     * @param <T>
     * @return
     */
    public <T> T get(Object key, Class<T> tClass) {
        return get(key, tClass, NOT_EXPIRE);
    }

    /**
     * 删除
     * @param key
     */
    public void delete(Object key) {
        redisTemplate.delete(key);
    }
}
