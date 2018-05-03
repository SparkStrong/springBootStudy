package com.zw.springBootStudy.authorization;

/**
 * Created by Nack on 2018/4/24.
 * 对Token进行操作的接口
 */
public interface TokenManager {

    /**
     * 生成这个用户的Token并保存到合适位置
     * @param userName
     * @return
     */
    public String generateAndSaveToken(String userName);

    /**
     * 检查token是否有效
     * @param token
     * @return 如果存在且有效返回true，否则false
     */
    public boolean checkToken(String token);

    /**
     * 根据token获取用户名
     * @param token 加密后字符串
     * @return
     */
//    public String getUserName(String token);

    /**
     * 清除token,一般用于退出登录等操作
     * @param token
     */
    public void deleteToken(String token);
}
