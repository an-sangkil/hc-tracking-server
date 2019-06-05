CREATE DATABASE IF NOT EXISTS ad_server;
CREATE SCHEMA IF NOT EXISTS `hc_tracking` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE SCHEMA IF NOT EXISTS `ae_report` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

ALTER SCHEMA `ad_server`  DEFAULT COLLATE utf8mb4_unicode_ci ;
ALTER SCHEMA `hc_tracking`  DEFAULT COLLATE utf8mb4_unicode_ci ;

create user 'aereport_user'@'%' identified by 'Mezzo12!@';
create user 'hctracking_user'@'%' identified by 'Mezzo12!@';

grant all privileges on hc_tracking.* to hctracking_user@'%';
flush privileges;

use hc_tracking;

-- 사용자 정보
CREATE TABLE users
(
	-- 사용자 고유 식별 키
	uuid varchar(64) NOT NULL COMMENT '사용자 고유 식별 키',
	-- 사용자 아이디
	user_id varchar(16) COMMENT '사용자 아이디 ',
	email varchar(32) COMMENT '이메일 정보',
	-- 사용자명
	user_name varchar(32) COMMENT '사용자명',
	-- 마지막 접속 시간
	last_access_time datetime COMMENT '마지막 접속 시간',
	-- 계정 상태
	account_status bit(1) COMMENT '계정 상태',
	-- 최상위 관리자 여부(Y/N)
	is_admin bit(1) NOT NULL COMMENT '최상위 관리자 여부(Y/N)',
	-- 생성시간
	created_time datetime COMMENT '생성시간',
	-- 수정시간
	modifyed_time datetime COMMENT '수정시간',
	PRIMARY KEY (uuid),
	UNIQUE (user_id)
) COMMENT = '사용자 정보';
