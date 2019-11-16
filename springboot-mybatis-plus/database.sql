DROP TABLE IF EXISTS `tbl_user`;
CREATE TABLE `tbl_user` (
    `id` BIGINT(20) NOT NULL COMMENT '主键ID',
    `user_name` VARCHAR(30) NULL DEFAULT NULL COMMENT '名字',
    `user_age` INT(11)NULL DEFAULT NULL COMMENT '年龄',
    PRIMARY KEY (id)
) ENGINE = InnoDB CHARSET = utf8;