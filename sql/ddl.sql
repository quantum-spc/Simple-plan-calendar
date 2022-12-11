CREATE TABLE `plan_calendar` (
     `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '시퀀스 번호',
     `regdate` DATETIME NULL DEFAULT NULL COMMENT '등록 날짜',
     `delflag` VARCHAR(2) NULL DEFAULT 'N' COMMENT '삭제 여부',
     `user_id` BIGINT(20) NULL DEFAULT NULL COMMENT '유저 번호',
     `start` DATE NULL DEFAULT NULL COMMENT '시작 날짜',
     `end` DATE NULL DEFAULT NULL COMMENT '종료 날짜',
     `title` VARCHAR(255) NULL DEFAULT NULL COMMENT '제목',
     `color` VARCHAR(20) NULL DEFAULT NULL COMMENT '배경 색깔',
     `moddate` DATETIME NULL DEFAULT NULL,
     PRIMARY KEY (`id`) USING BTREE
)
;

CREATE TABLE `plan_user` (
     `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '시퀀스 번호',
     `regdate` DATETIME NULL DEFAULT NULL COMMENT '등록 날짜',
     `delflag` VARCHAR(2) NULL DEFAULT 'N' COMMENT '삭제 여부',
     `memberid` VARCHAR(50) NULL DEFAULT NULL,
     `memberpw` VARCHAR(100) NULL DEFAULT NULL,
     `moddate` DATETIME NULL DEFAULT NULL,
     `last_login_date` DATETIME NULL DEFAULT NULL,
     PRIMARY KEY (`id`) USING BTREE
)
;
