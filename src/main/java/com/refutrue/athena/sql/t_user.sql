DROP TABLE
IF EXISTS `t_user`;

CREATE TABLE `t_user` (
    `id` INTEGER      COMMENT 'id' ,    `user_name` VARCHAR(50)      COMMENT 'userName' ,    `password` VARCHAR(50)      COMMENT 'password' ,    `phone` VARCHAR(50)      COMMENT 'phone'     ) COMMENT = '用户信息' ENGINE = InnoDB DEFAULT CHARSET = utf8;