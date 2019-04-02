/*
 Navicat Premium Data Transfer

 Source Server         : mySql
 Source Server Type    : MySQL
 Source Server Version : 50725
 Source Schema         : docmanager

 Target Server Type    : MySQL
 Target Server Version : 50725
 File Encoding         : 65001

 Date: 01/04/2019 22:35:41
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for college
-- ----------------------------
DROP TABLE IF EXISTS `college`;
CREATE TABLE `college` (
  `college_id` int(11) NOT NULL,
  `college_name` varchar(255) DEFAULT NULL COMMENT '课程名',
  PRIMARY KEY (`college_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of college
-- ----------------------------
BEGIN;
INSERT INTO `college` VALUES (1, '计算机系');
INSERT INTO `college` VALUES (2, '设计系');
INSERT INTO `college` VALUES (3, '财经系');
COMMIT;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `course_id` int(11) NOT NULL AUTO_INCREMENT,
  `course_name` varchar(255) DEFAULT NULL COMMENT '课程名称',
  `teacher_id` int(11) DEFAULT NULL,
  `course_time` varchar(255) DEFAULT NULL COMMENT '开课时间',
  `class_room` varchar(255) DEFAULT NULL COMMENT '开课地点',
  `course_week` int(11) DEFAULT NULL COMMENT '学时',
  `course_type` varchar(255) DEFAULT NULL COMMENT '课程类型',
  `college_id` int(11) DEFAULT NULL COMMENT '所属院系',
  `score` int(11) DEFAULT NULL COMMENT '学分',
  PRIMARY KEY (`course_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of course
-- ----------------------------
BEGIN;
INSERT INTO `course` VALUES (1, 'C语言程序设计', 1002, '周二', '科401', 19, '必修课', 1, 3);
INSERT INTO `course` VALUES (2, 'Python爬虫技巧', 1001, '周四', 'X402', 18, '必修课', 1, 3);
INSERT INTO `course` VALUES (3, '数据结构', 1003, '周四', '科401', 18, '必修课', 1, 2);
INSERT INTO `course` VALUES (4, 'Java程序设计', 1001, '周五', '科401', 18, '必修课', 3, 2);
INSERT INTO `course` VALUES (5, '英语', 1002, '周四', 'X302', 18, '必修课', 2, 2);
INSERT INTO `course` VALUES (6, '服装设计', 1003, '周一', '科401', 18, '选修课', 2, 2);
INSERT INTO `course` VALUES (10, '测试课程1', 11, '周五', '教室1', 18, '必修课', 1, 23);
INSERT INTO `course` VALUES (11, '测试课程1', 11, '周五', '教室1', 18, '必修课', 1, 23);
INSERT INTO `course` VALUES (12, '测试课程1', 11, '周五', '教室1', 18, '必修课', 1, 23);
INSERT INTO `course` VALUES (14, '计算机网络', 1002, '周一', 'X444', 18, '必修课', 1, 3);
INSERT INTO `course` VALUES (15, '计算机组成原理', 1003, '周一', 'X444', 12, '必修课', 1, 3);
COMMIT;

-- ----------------------------
-- Table structure for course_doc
-- ----------------------------
DROP TABLE IF EXISTS `course_doc`;
CREATE TABLE `course_doc` (
  `file_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `student_id` int(11) DEFAULT NULL,
  `course_id` int(11) DEFAULT NULL,
  `file_name` varchar(255) DEFAULT NULL,
  `file_url` varchar(500) DEFAULT NULL,
  `last_time` datetime DEFAULT NULL,
  `subject_id` int(11) DEFAULT NULL,
  `bak` int(11) DEFAULT '0',
  `is_delete` int(11) DEFAULT '0',
  PRIMARY KEY (`file_id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COMMENT='课程文档表';

-- ----------------------------
-- Records of course_doc
-- ----------------------------
BEGIN;
INSERT INTO `course_doc` VALUES (4, 10001, 1, '2.html', '/2019/03/30/15539322896596272.html', '2019-03-30 15:51:30', 4, 1, 1);
INSERT INTO `course_doc` VALUES (7, 10001, 1, '2 (1).txt', '/2019/03/30/15539415557082398.txt', '2019-03-30 18:25:56', 4, 1, 1);
INSERT INTO `course_doc` VALUES (17, 10001, 2, '2015.txt', '/2019/03/30/15539420499575535.txt', '2019-03-30 18:34:10', 9, 0, 0);
INSERT INTO `course_doc` VALUES (20, 10001, 2, '2015.txt', '/2019/03/30/15539421719443670.txt', '2019-03-30 18:36:12', 9, 0, 0);
INSERT INTO `course_doc` VALUES (21, 10001, 1, 'Eclipse快捷键.png', '/2019/03/30/15539422512655833.png', '2019-03-30 18:37:31', 4, 1, 1);
INSERT INTO `course_doc` VALUES (22, 10001, 1, 'A}CCC9}J}V)IM3RO$YIN}`Q.jpg', '/2019/03/30/15539422759071870.jpg', '2019-03-30 18:37:56', 4, 0, 1);
INSERT INTO `course_doc` VALUES (24, 10001, 2, '2015.txt', '/2019/03/30/15539424430883141.txt', '2019-03-30 18:40:43', 9, 0, 0);
INSERT INTO `course_doc` VALUES (26, 10001, 1, '444.txt', '/2019/03/30/15539468794278738.txt', '2019-03-30 19:54:39', 4, 0, 1);
INSERT INTO `course_doc` VALUES (27, 10001, 1, '2015.txt', '/2019/03/30/15539485604724418.txt', '2019-03-30 20:22:40', 4, 1, 0);
INSERT INTO `course_doc` VALUES (31, 10001, 1, '123.docx', '/2019/03/30/15539512036557450.docx', '2019-03-30 21:06:44', 4, 0, 1);
INSERT INTO `course_doc` VALUES (32, 10001, 2, '1.html', '/2019/03/30/15539521961779207.html', '2019-03-30 21:23:16', 9, 0, 1);
INSERT INTO `course_doc` VALUES (33, 10001, 1, 'Eclipse快捷键.png', '/2019/03/30/15539526298819791.png', '2019-03-30 21:30:30', 4, 1, 0);
INSERT INTO `course_doc` VALUES (34, 10001, 1, '2015.txt', '/2019/04/01/15541282437180674.txt', '2019-04-01 22:17:24', 4, 0, 1);
COMMIT;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `role_id` int(11) NOT NULL,
  `role_name` varchar(255) DEFAULT NULL,
  `permissions` varchar(255) DEFAULT NULL COMMENT '权限',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of role
-- ----------------------------
BEGIN;
INSERT INTO `role` VALUES (0, 'admin', '12');
INSERT INTO `role` VALUES (1, 'teacher', '23');
INSERT INTO `role` VALUES (2, 'student', '43');
COMMIT;

-- ----------------------------
-- Table structure for selected_course
-- ----------------------------
DROP TABLE IF EXISTS `selected_course`;
CREATE TABLE `selected_course` (
  `course_id` int(11) NOT NULL,
  `student_id` int(11) NOT NULL,
  `mark` int(11) DEFAULT NULL,
  PRIMARY KEY (`course_id`,`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of selected_course
-- ----------------------------
BEGIN;
INSERT INTO `selected_course` VALUES (1, 10001, 55);
INSERT INTO `selected_course` VALUES (2, 123, 1);
INSERT INTO `selected_course` VALUES (2, 10001, 64);
INSERT INTO `selected_course` VALUES (2, 10006, 88);
INSERT INTO `selected_course` VALUES (4, 10001, NULL);
INSERT INTO `selected_course` VALUES (5, 10001, NULL);
COMMIT;

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `birth_year` date DEFAULT NULL COMMENT '出生日期',
  `grade` date DEFAULT NULL COMMENT '入学时间',
  `college_id` int(11) DEFAULT NULL COMMENT '院系id',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10008 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of student
-- ----------------------------
BEGIN;
INSERT INTO `student` VALUES (10001, '小黄', '男', '1996-09-13', '2019-09-02', 2);
INSERT INTO `student` VALUES (10002, '小米', '女', '1995-10-14', '2015-09-02', 3);
INSERT INTO `student` VALUES (10003, '小陈', '女', '1996-09-02', '2015-09-02', 1);
INSERT INTO `student` VALUES (10004, '小华', '男', '1996-09-02', '2015-09-02', 2);
INSERT INTO `student` VALUES (10005, '小左', '女', '1996-09-02', '2015-09-02', 2);
INSERT INTO `student` VALUES (10006, '小拉', '五', NULL, NULL, 1);
COMMIT;

-- ----------------------------
-- Table structure for subject
-- ----------------------------
DROP TABLE IF EXISTS `subject`;
CREATE TABLE `subject` (
  `subject_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `teacher_id` int(11) DEFAULT NULL,
  `course_id` int(11) DEFAULT NULL,
  `subject_desc` varchar(500) DEFAULT NULL,
  `subject_name` varchar(255) DEFAULT NULL,
  `file_name` varchar(255) DEFAULT NULL,
  `file_url` varchar(500) DEFAULT NULL,
  `last_time` datetime DEFAULT NULL,
  `bak` int(11) DEFAULT '0',
  `is_delete` int(11) DEFAULT '0',
  PRIMARY KEY (`subject_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COMMENT='课件表';

-- ----------------------------
-- Records of subject
-- ----------------------------
BEGIN;
INSERT INTO `subject` VALUES (4, 1001, 1, '24343434343', '这是一个测试选题22222', 'TemplateService.java', '/2019/03/30/15539296405968612.java', '2019-03-30 15:07:21', 1, 0);
INSERT INTO `subject` VALUES (8, 1001, 2, '爬个人感兴趣的网站，如：网易云评论，知乎评论等。', '学习爬虫技术', 'API.md', '/2019/03/30/15539323648251132.md', '2019-03-30 15:52:45', 0, 0);
INSERT INTO `subject` VALUES (9, 1001, 2, '123', 'wwww', '444.txt', '/2019/03/30/15539348897569407.txt', '2019-03-30 16:34:50', 0, 1);
INSERT INTO `subject` VALUES (10, 1002, 1, '123123', 'ces', 'Eclipse快捷键.png', '/2019/03/30/15539518971489090.png', '2019-03-30 21:18:17', 1, 0);
INSERT INTO `subject` VALUES (11, 1001, 4, '123', '这是一个选题', '2015.txt', '/2019/03/30/15539521014575584.txt', '2019-03-30 21:21:41', 0, 1);
INSERT INTO `subject` VALUES (12, 1001, 2, '111111', '1111', 'idea快捷键.txt', '/2019/04/01/15541266157985019.txt', '2019-04-01 21:50:16', 0, 1);
COMMIT;

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `birth_year` date DEFAULT NULL,
  `degree` varchar(255) DEFAULT NULL COMMENT '学历',
  `title` varchar(255) DEFAULT NULL COMMENT '职称',
  `grade` date DEFAULT NULL COMMENT '入职时间',
  `college_id` int(11) DEFAULT NULL COMMENT '院系',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1006 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of teacher
-- ----------------------------
BEGIN;
INSERT INTO `teacher` VALUES (1001, '刘老师', '男', '1990-03-08', '硕士', '副教授', '2015-09-02', 2);
INSERT INTO `teacher` VALUES (1002, '张老师', '女', '1996-09-02', '博士', '教授', '2015-09-02', 3);
INSERT INTO `teacher` VALUES (1003, '软老师', '男', '1996-09-02', '硕士', '助教', '2017-07-07', 1);
COMMIT;

-- ----------------------------
-- Table structure for user_login
-- ----------------------------
DROP TABLE IF EXISTS `user_login`;
CREATE TABLE `user_login` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL COMMENT '对应角色表Id',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user_login
-- ----------------------------
BEGIN;
INSERT INTO `user_login` VALUES (1, 'admin', '123', 0);
INSERT INTO `user_login` VALUES (8, '10001', '123', 2);
INSERT INTO `user_login` VALUES (9, '10002', '123', 2);
INSERT INTO `user_login` VALUES (10, '10003', '123', 2);
INSERT INTO `user_login` VALUES (11, '10005', '123', 2);
INSERT INTO `user_login` VALUES (12, '10004', '123', 2);
INSERT INTO `user_login` VALUES (13, '10006', '1244', 2);
INSERT INTO `user_login` VALUES (14, '1001', '123', 1);
INSERT INTO `user_login` VALUES (15, '1002', '123', 1);
INSERT INTO `user_login` VALUES (16, '1003', '123', 1);
INSERT INTO `user_login` VALUES (21, '10007', '123', 2);
INSERT INTO `user_login` VALUES (22, '1004', '123', 1);
INSERT INTO `user_login` VALUES (23, '1005', '123', 1);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
