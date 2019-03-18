DROP TABLE
IF EXISTS `t_user`;

CREATE TABLE `t_user` (
    `login_id` VARCHAR(50)      COMMENT '登录账号' ,    `passwd` VARCHAR(50)      COMMENT '密码' ,    `id` INT    PRIMARY KEY   AUTO_INCREMENT   COMMENT 'id' ,    `operate_user` VARCHAR(50)      COMMENT 'operateUser' ,    `operate_time` DATETIME       COMMENT 'operateTime' ,    `create_time` DATETIME       COMMENT 'createTime'     ) COMMENT = '用户基本信息' ENGINE = InnoDB DEFAULT CHARSET = utf8;