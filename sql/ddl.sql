CREATE TABLE `plan_calendar` (
     `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '시퀀스 번호',
     `regdate` DATETIME NULL DEFAULT NULL COMMENT '등록 날짜',
     `delflag` VARCHAR(2) NULL DEFAULT 'N' COMMENT '삭제 여부' COLLATE 'utf8_general_ci',
     `user_id` BIGINT(20) NULL DEFAULT NULL COMMENT '유저 seq',
     `start` DATE NULL DEFAULT NULL COMMENT '시작 날짜',
     `end` DATE NULL DEFAULT NULL COMMENT '종료 날짜',
     `title` VARCHAR(255) NULL DEFAULT NULL COMMENT '제목' COLLATE 'utf8_general_ci',
     `color` VARCHAR(20) NULL DEFAULT NULL COMMENT '배경 색깔' COLLATE 'utf8_general_ci',
     `moddate` DATETIME NULL DEFAULT NULL,
     PRIMARY KEY (`id`) USING BTREE,
     INDEX `FK6noekxi6fr5or5e8dyhdjnpto` (`user_id`) USING BTREE
)
    COLLATE='euckr_korean_ci'
ENGINE=MyISAM
AUTO_INCREMENT=960
;

CREATE TABLE `plan_user` (
     `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '시퀀스 번호',
     `regdate` DATETIME NULL DEFAULT NULL COMMENT '등록 날짜',
     `delflag` VARCHAR(2) NULL DEFAULT 'N' COMMENT '삭제 여부' COLLATE 'utf8_general_ci',
     `memberid` VARCHAR(50) NULL DEFAULT NULL COLLATE 'utf8_general_ci',
     `memberpw` VARCHAR(100) NULL DEFAULT NULL COLLATE 'utf8_general_ci',
     `moddate` DATETIME NULL DEFAULT NULL,
     `last_login_date` DATETIME NULL DEFAULT NULL,
     PRIMARY KEY (`id`) USING BTREE
)
    COLLATE='euckr_korean_ci'
ENGINE=MyISAM
AUTO_INCREMENT=60
;
