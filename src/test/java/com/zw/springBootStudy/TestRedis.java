package com.zw.springBootStudy;

import com.zw.springBootStudy.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nack on 2018/5/2.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRedis {

    @Autowired
    private RedisTemplate<Object, Object> template;

    private <T> T get(Object key, Class<T> tClass) {
        Object value = template.opsForValue().get(key);

        return value == null ? null : (T) value;
    }

    @Test
    public void testObject() throws Exception {
        User user = new User("userName", "email.com", "password", 1, "code");

        template.opsForValue().set(user.getUserName(), user);
        Thread.sleep(1000);

        boolean isExist = template.hasKey(user.getUserName());
        if (isExist) {
            System.out.println("key exist!");
//            User userResult = (User) template.opsForValue().get(user.getUserName());
            User userResult = get(user.getUserName(), User.class);
            System.out.println(userResult.getUserName());
        } else {
            System.out.println("key is not exist!");
        }
    }

    @Test
    public void testHash() {
        String userName = "zhouwang";
        List<String> articles = new ArrayList<>();
        articles.add("article 1");
        User user = new User("zhouwang", "email.com", "password", 1, "code");
        template.opsForHash().put(userName, "userInfo", user);
        template.opsForHash().put(userName, "articles", articles);

        User userResult = (User) template.opsForHash().get(userName, "userInfo");

        System.out.println("before: " + userResult.getUserName());

        user.setUserName("zhouwang 2");
        template.opsForHash().put(userName, "userInfo", user);

        System.out.println("after: " + ((User) template.opsForHash().get(userName, "userInfo")).getUserName());

        template.opsForHash().put(userName, "token", "12345678");

        System.out.println("token : " + template.opsForHash().get(userName, "token"));
    }
}
