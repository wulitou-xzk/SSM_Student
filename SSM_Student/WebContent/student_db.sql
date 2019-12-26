/*
 Navicat Premium Data Transfer

 Source Server         : docker-mysql02
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : 192.168.181.160:3307
 Source Schema         : student_db

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 08/12/2019 18:44:07
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for colleges
-- ----------------------------
DROP TABLE IF EXISTS `colleges`;
CREATE TABLE `colleges`  (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `college` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of colleges
-- ----------------------------
INSERT INTO `colleges` VALUES (1, '学院-1');
INSERT INTO `colleges` VALUES (2, '学院-2');
INSERT INTO `colleges` VALUES (3, '学院-3');
INSERT INTO `colleges` VALUES (4, '学院-4');
INSERT INTO `colleges` VALUES (5, '学院-5');
INSERT INTO `colleges` VALUES (6, '学院-6');
INSERT INTO `colleges` VALUES (7, '学院-7');

-- ----------------------------
-- Table structure for courses
-- ----------------------------
DROP TABLE IF EXISTS `courses`;
CREATE TABLE `courses`  (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `cname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `clock` int(2) DEFAULT NULL COMMENT '课程锁 （0/1 -> 开/关）',
  `coId` int(5) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `coId`(`coId`) USING BTREE,
  CONSTRAINT `coId` FOREIGN KEY (`coId`) REFERENCES `colleges` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of courses
-- ----------------------------
INSERT INTO `courses` VALUES (1, 'course-01', 0, 1);
INSERT INTO `courses` VALUES (2, 'course-02', 0, 2);
INSERT INTO `courses` VALUES (3, 'course-03', 0, 3);
INSERT INTO `courses` VALUES (4, 'course-04', 0, 4);
INSERT INTO `courses` VALUES (5, 'course-05', 0, 5);
INSERT INTO `courses` VALUES (6, 'course-06', 0, 6);
INSERT INTO `courses` VALUES (7, 'course-07', 0, 7);
INSERT INTO `courses` VALUES (8, 'course-08', 0, 5);
INSERT INTO `courses` VALUES (9, 'course-09', 0, 2);
INSERT INTO `courses` VALUES (10, 'course-10', 0, 1);
INSERT INTO `courses` VALUES (11, 'course-11', 0, 3);

-- ----------------------------
-- Table structure for majors
-- ----------------------------
DROP TABLE IF EXISTS `majors`;
CREATE TABLE `majors`  (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `major` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `collegeId` int(5) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `collegeId`(`collegeId`) USING BTREE,
  CONSTRAINT `collegeId` FOREIGN KEY (`collegeId`) REFERENCES `colleges` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of majors
-- ----------------------------
INSERT INTO `majors` VALUES (1, '专业-1.1', 1);
INSERT INTO `majors` VALUES (2, '专业-1.2', 1);
INSERT INTO `majors` VALUES (3, '专业-1.3', 1);
INSERT INTO `majors` VALUES (4, '专业-1.4', 1);
INSERT INTO `majors` VALUES (5, '专业-1.5', 1);
INSERT INTO `majors` VALUES (6, '专业-2.1', 2);
INSERT INTO `majors` VALUES (7, '专业-2.2', 2);
INSERT INTO `majors` VALUES (8, '专业-2.3', 2);
INSERT INTO `majors` VALUES (9, '专业-3.1', 3);
INSERT INTO `majors` VALUES (10, '专业-3.2', 3);
INSERT INTO `majors` VALUES (11, '专业-3.3', 3);
INSERT INTO `majors` VALUES (12, '专业-3.4', 3);
INSERT INTO `majors` VALUES (13, '专业-3.5', 3);
INSERT INTO `majors` VALUES (14, '专业-3.6', 3);
INSERT INTO `majors` VALUES (15, '专业-4.1', 4);
INSERT INTO `majors` VALUES (16, '专业-4.2', 4);
INSERT INTO `majors` VALUES (17, '专业-4.3', 4);
INSERT INTO `majors` VALUES (18, '专业-5.1', 5);
INSERT INTO `majors` VALUES (19, '专业-5.2', 5);
INSERT INTO `majors` VALUES (20, '专业-5.3', 5);
INSERT INTO `majors` VALUES (21, '专业-5.4', 5);
INSERT INTO `majors` VALUES (22, '专业-5.5', 5);
INSERT INTO `majors` VALUES (23, '专业-6.1', 6);
INSERT INTO `majors` VALUES (24, '专业-6.2', 6);
INSERT INTO `majors` VALUES (25, '专业-6.3', 6);
INSERT INTO `majors` VALUES (26, '专业-7.1', 7);
INSERT INTO `majors` VALUES (27, '专业-7.2', 7);
INSERT INTO `majors` VALUES (28, '专业-7.3', 7);
INSERT INTO `majors` VALUES (29, '专业-7.4', 7);
INSERT INTO `majors` VALUES (30, '专业-7.5', 7);

-- ----------------------------
-- Table structure for stu_col_maj
-- ----------------------------
DROP TABLE IF EXISTS `stu_col_maj`;
CREATE TABLE `stu_col_maj`  (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `colId` int(5) DEFAULT NULL,
  `majorId` int(5) DEFAULT NULL,
  `stuNumber` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of stu_col_maj
-- ----------------------------
INSERT INTO `stu_col_maj` VALUES (1, 1, 1, '16091001');
INSERT INTO `stu_col_maj` VALUES (2, 1, 2, '16091002');
INSERT INTO `stu_col_maj` VALUES (3, 1, 3, '16091003');
INSERT INTO `stu_col_maj` VALUES (4, 1, 4, '16091004');
INSERT INTO `stu_col_maj` VALUES (5, 1, 5, '16091005');
INSERT INTO `stu_col_maj` VALUES (6, 2, 6, '16091006');
INSERT INTO `stu_col_maj` VALUES (7, 3, 9, '16091007');
INSERT INTO `stu_col_maj` VALUES (8, 2, 8, '16091008');
INSERT INTO `stu_col_maj` VALUES (9, 3, 10, '16091009');
INSERT INTO `stu_col_maj` VALUES (10, 3, 11, '16091010');
INSERT INTO `stu_col_maj` VALUES (11, 3, 12, '16091011');
INSERT INTO `stu_col_maj` VALUES (12, 3, 13, '16091012');
INSERT INTO `stu_col_maj` VALUES (13, 3, 14, '16091013');
INSERT INTO `stu_col_maj` VALUES (14, 4, 15, '16091014');
INSERT INTO `stu_col_maj` VALUES (15, 4, 16, '16091015');
INSERT INTO `stu_col_maj` VALUES (16, 4, 17, '16091016');
INSERT INTO `stu_col_maj` VALUES (17, 5, 18, '16091017');
INSERT INTO `stu_col_maj` VALUES (18, 5, 19, '16091018');
INSERT INTO `stu_col_maj` VALUES (19, 5, 20, '16091019');
INSERT INTO `stu_col_maj` VALUES (20, 5, 21, '16091020');
INSERT INTO `stu_col_maj` VALUES (21, 5, 22, '16091021');
INSERT INTO `stu_col_maj` VALUES (22, 5, 23, '16091022');
INSERT INTO `stu_col_maj` VALUES (23, 6, 24, '16091023');
INSERT INTO `stu_col_maj` VALUES (24, 6, 25, '16091024');
INSERT INTO `stu_col_maj` VALUES (25, 6, 26, '16091025');
INSERT INTO `stu_col_maj` VALUES (26, 7, 27, '16091026');
INSERT INTO `stu_col_maj` VALUES (27, 7, 28, '16091027');
INSERT INTO `stu_col_maj` VALUES (28, 7, 29, '16091028');
INSERT INTO `stu_col_maj` VALUES (29, 7, 30, '16091029');
INSERT INTO `stu_col_maj` VALUES (30, 2, 7, '16091030');

-- ----------------------------
-- Table structure for stu_course
-- ----------------------------
DROP TABLE IF EXISTS `stu_course`;
CREATE TABLE `stu_course`  (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `sid` int(5) DEFAULT NULL,
  `cid` int(5) DEFAULT NULL,
  `tid` int(5) DEFAULT NULL,
  `score` double(10, 0) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of stu_course
-- ----------------------------
INSERT INTO `stu_course` VALUES (1, 1, 1, 1, 0);

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `sname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `number` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `gender` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `birth` date DEFAULT NULL,
  `grader` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES (1, 'student-01', '16091001', '男', '1997-07-17', '大四');
INSERT INTO `student` VALUES (2, 'student-02', '16091002', '女', '1997-07-17', '大四');
INSERT INTO `student` VALUES (3, 'student-03', '16091003', '女', '2010-10-14', '大四');
INSERT INTO `student` VALUES (4, 'student-04', '16091004', '女', '2007-04-05', '大二');
INSERT INTO `student` VALUES (5, 'student-05', '16091005', '女', '1991-07-11', '大三');
INSERT INTO `student` VALUES (6, 'student-06', '16091006', '女', '1991-07-11', '大三');
INSERT INTO `student` VALUES (7, 'student-07', '16091007', '男', '1991-07-11', '大三');
INSERT INTO `student` VALUES (8, 'student-08', '16091008', '女', '1991-07-11', '大三');
INSERT INTO `student` VALUES (9, 'student-09', '16091009', '男', '1991-07-11', '大二');
INSERT INTO `student` VALUES (10, 'student-10', '16091010', '女', '1997-07-17', '大二');
INSERT INTO `student` VALUES (11, 'student-11', '16091011', '男', '1997-07-17', '大三');
INSERT INTO `student` VALUES (12, 'student-12', '16091012', '女', '1997-07-17', '大二');
INSERT INTO `student` VALUES (13, 'student-13', '16091013', '男', '1997-07-17', '大二');
INSERT INTO `student` VALUES (14, 'student-14', '16091014', '女', '1997-07-17', '大三');
INSERT INTO `student` VALUES (15, 'student-15', '16091015', '男', '1997-07-17', '大二');
INSERT INTO `student` VALUES (16, 'student-16', '16091016', '男', '1997-07-17', '大二');
INSERT INTO `student` VALUES (17, 'student-17', '16091017', '女', '1997-07-17', '大三');
INSERT INTO `student` VALUES (18, 'student-18', '16091018', '女', '1997-07-17', '大二');
INSERT INTO `student` VALUES (19, 'student-19', '16091019', '男', '1997-07-17', '大二');
INSERT INTO `student` VALUES (20, 'student-20', '16091020', '男', '1997-07-17', '大三');
INSERT INTO `student` VALUES (21, 'student-21', '16091021', '女', '1997-07-17', '大三');
INSERT INTO `student` VALUES (22, 'student-22', '16091022', '男', '1997-07-17', '大三');
INSERT INTO `student` VALUES (23, 'student-23', '16091023', '女', '1997-07-17', '大三');
INSERT INTO `student` VALUES (24, 'student-24', '16091024', '女', '1997-07-17', '大四');
INSERT INTO `student` VALUES (25, 'student-25', '16091025', '女', '1991-07-11', '大四');
INSERT INTO `student` VALUES (26, 'student-26', '16091026', '男', '1991-07-11', '大四');
INSERT INTO `student` VALUES (27, 'student-27', '16091027', '男', '1991-07-11', '大四');
INSERT INTO `student` VALUES (28, 'student-28', '16091028', '女', '1991-07-11', '大四');
INSERT INTO `student` VALUES (29, 'student-29', '16091029', '男', '1991-07-11', '大四');
INSERT INTO `student` VALUES (30, 'student-30', '16091030', '男', '1991-07-11', '大四');

-- ----------------------------
-- Table structure for teach_course
-- ----------------------------
DROP TABLE IF EXISTS `teach_course`;
CREATE TABLE `teach_course`  (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `teachId` int(5) DEFAULT NULL,
  `courseId` int(5) DEFAULT NULL,
  `number` int(5) DEFAULT NULL,
  `maxnumber` int(5) DEFAULT NULL,
  `place` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `day` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `time` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `week` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of teach_course
-- ----------------------------
INSERT INTO `teach_course` VALUES (1, 1, 1, 1, 50, 'room-001', 'Mon', '1-2', '1-16');
INSERT INTO `teach_course` VALUES (2, 2, 2, 0, 50, 'room-002', 'Tues', '3-4', '1-16');
INSERT INTO `teach_course` VALUES (3, 3, 3, 0, 50, 'room-003', 'Thrus', '1-2', '1-16');
INSERT INTO `teach_course` VALUES (4, 4, 4, 0, 50, 'room-004', 'Fri', '5-6', '1-16');
INSERT INTO `teach_course` VALUES (5, 5, 5, 0, 50, 'room-005', 'Sun', '8-9', '1-16');
INSERT INTO `teach_course` VALUES (6, 6, 6, 0, 50, 'room-006', 'Satur', '1-2', '1-16');
INSERT INTO `teach_course` VALUES (7, 7, 7, 0, 50, 'room-007', 'Wednes', '3-4', '1-16');
INSERT INTO `teach_course` VALUES (8, 5, 8, 0, 50, 'room-008', 'Fri', '3-4', '1-16');
INSERT INTO `teach_course` VALUES (9, 2, 9, 0, 50, 'room-009', 'Mon', '5-6', '1-16');
INSERT INTO `teach_course` VALUES (10, 1, 10, 0, 50, 'room-010', 'Fri', '8-8', '1-16');
INSERT INTO `teach_course` VALUES (11, 3, 11, 0, 50, 'room-011', 'Sun', '1-2', '1-16');

-- ----------------------------
-- Table structure for teachers
-- ----------------------------
DROP TABLE IF EXISTS `teachers`;
CREATE TABLE `teachers`  (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `t_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `t_gender` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `t_number` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `t_phone` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `collId` int(11) DEFAULT NULL COMMENT '教师所在学院 Id（外键）',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `collId`(`collId`) USING BTREE,
  CONSTRAINT `collId` FOREIGN KEY (`collId`) REFERENCES `colleges` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of teachers
-- ----------------------------
INSERT INTO `teachers` VALUES (1, 'teacher-01', '男', 'T001', '18279658251', 1);
INSERT INTO `teachers` VALUES (2, 'teacher-02', '男', 'T002', '18279658252', 2);
INSERT INTO `teachers` VALUES (3, 'teacher-03', '女', 'T003', '18279658253', 3);
INSERT INTO `teachers` VALUES (4, 'teacher-04', '男', 'T004', '18279658254', 4);
INSERT INTO `teachers` VALUES (5, 'teacher-05', '女', 'T005', '18279658255', 5);
INSERT INTO `teachers` VALUES (6, 'teacher-06', '女', 'T006', '18279658256', 6);
INSERT INTO `teachers` VALUES (7, 'teacher-07', '女', 'T007', '18279658257', 7);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `type` int(5) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', 'eihVqXm7rN', 2);
INSERT INTO `user` VALUES (2, '16091001', '5p:[4l=K5h4M4Q5o', -1);
INSERT INTO `user` VALUES (3, '16091002', '5n:x4J=|5P4u4s6q', -1);
INSERT INTO `user` VALUES (4, '16091003', '56:L4==5U4=4F7o', -1);
INSERT INTO `user` VALUES (5, '16091004', '5V:h4f=j5z454{8{', -1);
INSERT INTO `user` VALUES (6, '16091005', '5H:|4w=55Y464x9s', -1);
INSERT INTO `user` VALUES (7, '16091006', '5V:P4R=Z5M4M4i:5', -1);
INSERT INTO `user` VALUES (8, '16091007', '5k:q4G=K5J4|4;6', -1);
INSERT INTO `user` VALUES (9, '16091008', '5J:I4H=95Y474V<R', -1);
INSERT INTO `user` VALUES (10, '16091009', '5::g4[=l5{4R4x={', -1);
INSERT INTO `user` VALUES (11, '16091010', '5E:t4k=65s4=5S4P', -1);
INSERT INTO `user` VALUES (12, '16091011', '5k:F4[=95K4h565Y', -1);
INSERT INTO `user` VALUES (13, '16091012', '5r:o4V=}5z495u68', -1);
INSERT INTO `user` VALUES (14, '16091013', '5y:z4[=T5I4H5U7O', -1);
INSERT INTO `user` VALUES (15, '16091014', '5q:X4:=m5{4h5N8K', -1);
INSERT INTO `user` VALUES (16, '16091015', '5g:p4L=W5<4S5p9=', -1);
INSERT INTO `user` VALUES (17, '16091016', '5n:E4j=[5|4t5f:q', -1);
INSERT INTO `user` VALUES (18, '16091017', '5S:m4T=n564^5W;o', -1);
INSERT INTO `user` VALUES (19, '16091018', '5u:r4q=s56475Z<5', -1);
INSERT INTO `user` VALUES (20, '16091019', '59:g4h={5s4w5G=N', -1);
INSERT INTO `user` VALUES (21, '16091020', '5p:R4<=E5o4p6j4<', -1);
INSERT INTO `user` VALUES (22, '16091021', '5[:94|=;5Q4q6K5G', -1);
INSERT INTO `user` VALUES (23, '16091022', '5E:V4^=g5}4N6l6p', -1);
INSERT INTO `user` VALUES (24, '16091023', '5n:E4F=h5X4N6l7:', -1);
INSERT INTO `user` VALUES (25, '16091024', '5p:g4}=K5i4y6O8E', -1);
INSERT INTO `user` VALUES (26, '16091025', '5x:o4M=n5O4Z6G9S', -1);
INSERT INTO `user` VALUES (27, '16091026', '5f:t4W=y5^4k6W:9', -1);
INSERT INTO `user` VALUES (28, '16091027', '5m:X4:=F5i4p6:;t', -1);
INSERT INTO `user` VALUES (29, '16091028', '5m:e45=T564N6Y<=', -1);
INSERT INTO `user` VALUES (30, '16091029', '5f:~4z=u5R476R=}', -1);
INSERT INTO `user` VALUES (31, '16091030', '5v:J46=;5Q4G754w', -1);
INSERT INTO `user` VALUES (33, 'T001', 'XY4|4o5v', 1);
INSERT INTO `user` VALUES (34, 'T002', 'XY4y4X6G', 1);
INSERT INTO `user` VALUES (35, 'T003', 'XE4x4|7Z', 1);
INSERT INTO `user` VALUES (36, 'T004', 'X;4t4{8u', 1);
INSERT INTO `user` VALUES (37, 'T005', 'Xl4z4[9z', 1);
INSERT INTO `user` VALUES (38, 'T006', 'X|4{4p:l', 1);
INSERT INTO `user` VALUES (39, 'T007', 'X|4Z4V;J', 1);

SET FOREIGN_KEY_CHECKS = 1;
