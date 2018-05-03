# DROP DATABASE IF EXISTS `spring_boot_study`;
CREATE DATABASE IF NOT EXISTS `spring_boot_study` CHARACTER SET UTF8;

USE spring_boot_study;

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user`(
    `id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '自增长id',
    `user_id` INT COMMENT '用户id',
    `user_name` VARCHAR(20) NOT NULL COMMENT '用户名',
    `email` VARCHAR(50) NOT NULL COMMENT '邮箱',
    `password` VARCHAR(30) NOT NULL COMMENT '密码',
    `activation_status` TINYINT NOT NULL COMMENT '用户激活状态 0 未完成激活，1 完成',
    `activation_code` CHAR(30) COMMENT '用户激活码，用来在邮件中添加用户标识'
) ENGINE = InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;