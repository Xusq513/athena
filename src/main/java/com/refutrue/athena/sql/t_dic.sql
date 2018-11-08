DROP TABLE
IF EXISTS `t_dic`;

CREATE TABLE `t_dic` (
    `code` VARCHAR(50)      COMMENT '编码值' ,    `name` VARCHAR(100)      COMMENT '显示值' ,    `type` VARCHAR(50)      COMMENT '类型' ,    `description` VARCHAR(500)      COMMENT '描述' ,    `bak1` VARCHAR(50)      COMMENT '备注字段一' ,    `bak2` VARCHAR(50)      COMMENT '备注字段二' ,    `bak3` VARCHAR(50)      COMMENT '备注字段三' ,    `dic_type_id` INT      COMMENT 'dicTypeId' ,    `id` INT    PRIMARY KEY   AUTO_INCREMENT   COMMENT 'id' ,    `operate_user` VARCHAR(50)      COMMENT 'operateUser' ,    `operate_time` DATETIME       COMMENT 'operateTime' ,    `create_time` DATETIME       COMMENT 'createTime'     ) COMMENT = '字典信息维护' ENGINE = InnoDB DEFAULT CHARSET = utf8;