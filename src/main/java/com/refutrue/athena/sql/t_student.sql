DROP TABLE
IF EXISTS `t_student`;

CREATE TABLE `t_student` (
    `name` VARCHAR(50)      COMMENT '姓名' ,    `age` INTEGER      COMMENT '年龄' ,    `gender` BOOLEAN      COMMENT '性别' ,    `birth_day` DATE      COMMENT '生日' ,    `address` VARCHAR(200)      COMMENT '住址' ,    `country` VARCHAR(50)      COMMENT '国家' ,    `hobby` VARCHAR(200)      COMMENT '爱好' ,    `grade_id` INTEGER      COMMENT 'gradeId' ,    `id` INT    PRIMARY KEY   AUTO_INCREMENT   COMMENT 'id' ,    `operate_user` VARCHAR(50)      COMMENT 'operateUser' ,    `operate_time` DATETIME       COMMENT 'operateTime' ,    `create_time` DATETIME       COMMENT 'createTime'     ) COMMENT = '学生信息' ENGINE = InnoDB DEFAULT CHARSET = utf8;