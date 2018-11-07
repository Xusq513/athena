DROP TABLE
IF EXISTS `t_user`;

CREATE TABLE `t_user` (
    `user_name` VARCHAR(50)      COMMENT 'userName' ,    `password` VARCHAR(50)      COMMENT 'password' ,    `phone` VARCHAR(50)      COMMENT 'phone' ,    `id` INT    PRIMARY KEY   AUTO_INCREMENT   COMMENT 'id' ,    `operate_user` VARCHAR(50)      COMMENT 'operateUser' ,    `operate_time` DATETIME       COMMENT 'operateTime' ,    `create_time` DATETIME       COMMENT 'createTime'     ) COMMENT = '用户信息' ENGINE = InnoDB DEFAULT CHARSET = utf8;