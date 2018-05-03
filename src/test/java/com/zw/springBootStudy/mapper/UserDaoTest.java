package com.zw.springBootStudy.mapper;

import com.zw.springBootStudy.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by Nack on 2018/4/4.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void testQuery() {
        List<User> users = userDao.getAll();
        users.forEach(System.out::println);
    }

    @Test
    public void testQueryByUserNameAndPassword() {
        User user = userDao.getUserByUserNameAndPassword("zhouwang", "123");
        System.out.println(user.toString());
    }
}
