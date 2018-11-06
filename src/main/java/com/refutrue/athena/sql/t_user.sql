DROP TABLE
IF EXISTS `t_user`;

CREATE TABLE `t_user` (
    `id` INTEGER      COMMENT 'id' ,    
    `user_name` VARCHAR(50)      COMMENT 'userName' ,    
    `password` VARCHAR(50)      COMMENT 'password' ,    
    `phone` VARCHAR(50)      COMMENT 'phone' ,    
    `serial_version_u_i_d` BIGINT      COMMENT 'serialVersionUID'     
    ) COMMENT = 'User' ENGINE = InnoDB DEFAULT CHARSET = utf8;