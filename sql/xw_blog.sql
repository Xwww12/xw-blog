/*
SQLyog Ultimate v12.14 (64 bit)
MySQL - 8.0.29 : Database - xw_blog
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`xw_blog` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `xw_blog`;

/*Table structure for table `sys_menu` */

DROP TABLE IF EXISTS `sys_menu`;

CREATE TABLE `sys_menu` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `menu_name` varchar(50) NOT NULL COMMENT '菜单名称',
  `parent_id` bigint DEFAULT '0' COMMENT '父菜单ID',
  `order_num` int DEFAULT '0' COMMENT '显示顺序',
  `path` varchar(200) DEFAULT '' COMMENT '路由地址',
  `component` varchar(255) DEFAULT NULL COMMENT '组件路径',
  `is_frame` int DEFAULT '1' COMMENT '是否为外链（0是 1否）',
  `menu_type` char(1) DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `visible` char(1) DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
  `status` char(1) DEFAULT '0' COMMENT '菜单状态（0正常 1停用）',
  `perms` varchar(100) DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(100) DEFAULT '#' COMMENT '菜单图标',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  `del_flag` char(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2037 DEFAULT CHARSET=utf8mb3 COMMENT='菜单权限表';

/*Data for the table `sys_menu` */

insert  into `sys_menu`(`id`,`menu_name`,`parent_id`,`order_num`,`path`,`component`,`is_frame`,`menu_type`,`visible`,`status`,`perms`,`icon`,`create_by`,`create_time`,`update_by`,`update_time`,`remark`,`del_flag`) values 
(1,'系统管理',0,1,'system',NULL,1,'M','0','0','','system',0,'2021-11-12 10:46:19',0,NULL,'系统管理目录','0'),
(100,'用户管理',1,1,'user','system/user/index',1,'C','0','0','system:user:list','user',0,'2021-11-12 10:46:19',1,'2022-07-31 15:47:58','用户管理菜单','0'),
(101,'角色管理',1,2,'role','system/role/index',1,'C','0','0','system:role:list','peoples',0,'2021-11-12 10:46:19',0,NULL,'角色管理菜单','0'),
(102,'菜单管理',1,3,'menu','system/menu/index',1,'C','0','0','system:menu:list','tree-table',0,'2021-11-12 10:46:19',0,NULL,'菜单管理菜单','0'),
(1001,'用户查询',100,1,'','',1,'F','0','0','system:user:query','#',0,'2021-11-12 10:46:19',0,NULL,'','0'),
(1002,'用户新增',100,2,'','',1,'F','0','0','system:user:add','#',0,'2021-11-12 10:46:19',0,NULL,'','0'),
(1003,'用户修改',100,3,'','',1,'F','0','0','system:user:edit','#',0,'2021-11-12 10:46:19',0,NULL,'','0'),
(1004,'用户删除',100,4,'','',1,'F','0','0','system:user:remove','#',0,'2021-11-12 10:46:19',0,NULL,'','0'),
(1005,'用户导出',100,5,'','',1,'F','0','0','system:user:export','#',0,'2021-11-12 10:46:19',0,NULL,'','0'),
(1006,'用户导入',100,6,'','',1,'F','0','0','system:user:import','#',0,'2021-11-12 10:46:19',0,NULL,'','0'),
(1007,'重置密码',100,7,'','',1,'F','0','0','system:user:resetPwd','#',0,'2021-11-12 10:46:19',0,NULL,'','0'),
(1008,'角色查询',101,1,'','',1,'F','0','0','system:role:query','#',0,'2021-11-12 10:46:19',0,NULL,'','0'),
(1009,'角色新增',101,2,'','',1,'F','0','0','system:role:add','#',0,'2021-11-12 10:46:19',0,NULL,'','0'),
(1010,'角色修改',101,3,'','',1,'F','0','0','system:role:edit','#',0,'2021-11-12 10:46:19',0,NULL,'','0'),
(1011,'角色删除',101,4,'','',1,'F','0','0','system:role:remove','#',0,'2021-11-12 10:46:19',0,NULL,'','0'),
(1012,'角色导出',101,5,'','',1,'F','0','0','system:role:export','#',0,'2021-11-12 10:46:19',0,NULL,'','0'),
(1013,'菜单查询',102,1,'','',1,'F','0','0','system:menu:query','#',0,'2021-11-12 10:46:19',0,NULL,'','0'),
(1014,'菜单新增',102,2,'','',1,'F','0','0','system:menu:add','#',0,'2021-11-12 10:46:19',0,NULL,'','0'),
(1015,'菜单修改',102,3,'','',1,'F','0','0','system:menu:edit','#',0,'2021-11-12 10:46:19',0,NULL,'','0'),
(1016,'菜单删除',102,4,'','',1,'F','0','0','system:menu:remove','#',0,'2021-11-12 10:46:19',0,NULL,'','0'),
(2017,'内容管理',0,4,'content',NULL,1,'M','0','0',NULL,'table',NULL,'2022-01-08 02:44:38',1,'2022-07-31 12:34:23','','0'),
(2018,'分类管理',2017,1,'category','content/category/index',1,'C','0','0','content:category:list','example',NULL,'2022-01-08 02:51:45',NULL,'2022-01-08 02:51:45','','0'),
(2019,'文章管理',2017,0,'article','content/article/index',1,'C','0','0','content:article:list','build',NULL,'2022-01-08 02:53:10',NULL,'2022-01-08 02:53:10','','0'),
(2021,'标签管理',2017,6,'tag','content/tag/index',1,'C','0','0','content:tag:index','button',NULL,'2022-01-08 02:55:37',NULL,'2022-01-08 02:55:50','','0'),
(2022,'友链管理',2017,4,'link','content/link/index',1,'C','0','0','content:link:list','404',NULL,'2022-01-08 02:56:50',NULL,'2022-01-08 02:56:50','','0'),
(2023,'写博文',0,0,'write','content/article/write/index',1,'C','0','0','content:article:writer','build',NULL,'2022-01-08 03:39:58',1,'2022-07-31 22:07:05','','0'),
(2024,'友链新增',2022,0,'',NULL,1,'F','0','0','content:link:add','#',NULL,'2022-01-16 07:59:17',NULL,'2022-01-16 07:59:17','','0'),
(2025,'友链修改',2022,1,'',NULL,1,'F','0','0','content:link:edit','#',NULL,'2022-01-16 07:59:44',NULL,'2022-01-16 07:59:44','','0'),
(2026,'友链删除',2022,1,'',NULL,1,'F','0','0','content:link:remove','#',NULL,'2022-01-16 08:00:05',NULL,'2022-01-16 08:00:05','','0'),
(2027,'友链查询',2022,2,'',NULL,1,'F','0','0','content:link:query','#',NULL,'2022-01-16 08:04:09',NULL,'2022-01-16 08:04:09','','0'),
(2028,'导出分类',2018,1,'',NULL,1,'F','0','0','content:category:export','#',NULL,'2022-01-21 07:06:59',NULL,'2022-01-21 07:06:59','','0'),
(2034,'test',0,1,'test123',NULL,1,'M','0','0',NULL,'404',NULL,NULL,NULL,NULL,'','1'),
(2035,'test',0,1,'test',NULL,1,'M','0','0',NULL,'bug',NULL,NULL,NULL,NULL,'','1'),
(2036,'test2',2035,1,'qwreqr',NULL,1,'M','0','0',NULL,'404',NULL,NULL,NULL,NULL,'','1');

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(30) NOT NULL COMMENT '角色名称',
  `role_key` varchar(100) NOT NULL COMMENT '角色权限字符串',
  `role_sort` int NOT NULL COMMENT '显示顺序',
  `status` char(1) NOT NULL COMMENT '角色状态（0正常 1停用）',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb3 COMMENT='角色信息表';

/*Data for the table `sys_role` */

insert  into `sys_role`(`id`,`role_name`,`role_key`,`role_sort`,`status`,`del_flag`,`create_by`,`create_time`,`update_by`,`update_time`,`remark`) values 
(1,'超级管理员','admin',1,'0','0',0,'2021-11-12 10:46:19',0,NULL,'超级管理员'),
(2,'普通角色','common',2,'0','0',0,'2021-11-12 10:46:19',0,'2022-01-01 22:32:58','普通角色'),
(11,'嘎嘎嘎','aggag',5,'0','0',NULL,'2022-01-06 14:07:40',NULL,'2022-01-07 03:48:48','嘎嘎嘎'),
(12,'友链审核员','link',1,'0','0',NULL,'2022-01-16 06:49:30',NULL,'2022-01-16 08:05:09',NULL),
(13,'test123','wds',0,'0','1',NULL,NULL,NULL,NULL,'备注'),
(14,'test','asdasd',0,'0','0',1,'2023-03-23 22:19:54',1,'2023-03-23 22:19:54','asd'),
(15,'asd','asd',0,'0','1',1,'2023-03-24 15:15:34',1,'2023-03-24 15:15:34',NULL);

/*Table structure for table `sys_role_menu` */

DROP TABLE IF EXISTS `sys_role_menu`;

CREATE TABLE `sys_role_menu` (
  `role_id` bigint NOT NULL COMMENT '角色ID',
  `menu_id` bigint NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='角色和菜单关联表';

/*Data for the table `sys_role_menu` */

insert  into `sys_role_menu`(`role_id`,`menu_id`) values 
(0,0),
(2,1),
(2,102),
(2,1013),
(2,1014),
(2,1015),
(2,1016),
(2,2000),
(3,2),
(3,3),
(3,4),
(3,100),
(3,101),
(3,103),
(3,104),
(3,105),
(3,106),
(3,107),
(3,108),
(3,109),
(3,110),
(3,111),
(3,112),
(3,113),
(3,114),
(3,115),
(3,116),
(3,500),
(3,501),
(3,1001),
(3,1002),
(3,1003),
(3,1004),
(3,1005),
(3,1006),
(3,1007),
(3,1008),
(3,1009),
(3,1010),
(3,1011),
(3,1012),
(3,1017),
(3,1018),
(3,1019),
(3,1020),
(3,1021),
(3,1022),
(3,1023),
(3,1024),
(3,1025),
(3,1026),
(3,1027),
(3,1028),
(3,1029),
(3,1030),
(3,1031),
(3,1032),
(3,1033),
(3,1034),
(3,1035),
(3,1036),
(3,1037),
(3,1038),
(3,1039),
(3,1040),
(3,1041),
(3,1042),
(3,1043),
(3,1044),
(3,1045),
(3,1046),
(3,1047),
(3,1048),
(3,1049),
(3,1050),
(3,1051),
(3,1052),
(3,1053),
(3,1054),
(3,1055),
(3,1056),
(3,1057),
(3,1058),
(3,1059),
(3,1060),
(3,2000),
(11,1),
(11,100),
(11,101),
(11,102),
(11,103),
(11,104),
(11,105),
(11,106),
(11,107),
(11,108),
(11,500),
(11,501),
(11,1001),
(11,1002),
(11,1003),
(11,1004),
(11,1005),
(11,1006),
(11,1007),
(11,1008),
(11,1009),
(11,1010),
(11,1011),
(11,1012),
(11,1013),
(11,1014),
(11,1015),
(11,1016),
(11,1017),
(11,1018),
(11,1019),
(11,1020),
(11,1021),
(11,1022),
(11,1023),
(11,1024),
(11,1025),
(11,1026),
(11,1027),
(11,1028),
(11,1029),
(11,1030),
(11,1031),
(11,1032),
(11,1033),
(11,1034),
(11,1035),
(11,1036),
(11,1037),
(11,1038),
(11,1039),
(11,1040),
(11,1041),
(11,1042),
(11,1043),
(11,1044),
(11,1045),
(11,2000),
(11,2003),
(11,2004),
(11,2005),
(11,2006),
(11,2007),
(11,2008),
(11,2009),
(11,2010),
(11,2011),
(11,2012),
(11,2013),
(11,2014),
(12,2017),
(12,2022),
(12,2024),
(12,2025),
(12,2026),
(12,2027),
(14,1),
(14,100),
(14,101),
(14,102),
(14,2017),
(14,2018),
(14,2019),
(14,2021),
(14,2022),
(14,2023),
(14,2035);

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_name` varchar(64) NOT NULL DEFAULT 'NULL' COMMENT '用户名',
  `nick_name` varchar(64) NOT NULL DEFAULT 'NULL' COMMENT '昵称',
  `password` varchar(64) NOT NULL DEFAULT 'NULL' COMMENT '密码',
  `type` char(1) DEFAULT '0' COMMENT '用户类型：0代表普通用户，1代表管理员',
  `status` char(1) DEFAULT '0' COMMENT '账号状态（0正常 1停用）',
  `email` varchar(64) DEFAULT NULL COMMENT '邮箱',
  `phonenumber` varchar(32) DEFAULT NULL COMMENT '手机号',
  `sex` char(1) DEFAULT NULL COMMENT '用户性别（0男，1女，2未知）',
  `avatar` varchar(128) DEFAULT NULL COMMENT '头像',
  `create_by` bigint DEFAULT NULL COMMENT '创建人的用户id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` int DEFAULT '0' COMMENT '删除标志（0代表未删除，1代表已删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14787164048667 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表';

/*Data for the table `sys_user` */

insert  into `sys_user`(`id`,`user_name`,`nick_name`,`password`,`type`,`status`,`email`,`phonenumber`,`sex`,`avatar`,`create_by`,`create_time`,`update_by`,`update_time`,`del_flag`) values 
(1,'sg','sg333','$2a$10$Jnq31rRkNV3RNzXe0REsEOSKaYK8UgVZZqlNlNXqn.JeVcj2NdeZy','1','0','23412332@qq.com','18888888888','0','http://rro56zvtn.hn-bkt.clouddn.com/2023/03/18/c529dcb2b8bb4a4baeacbf9f0b6ba2bd.png',NULL,'2022-01-05 09:01:56',1,'2022-01-30 15:37:03',0),
(3,'sg3','weqe','$2a$10$ydv3rLkteFnRx9xelQ7elOiVhFvXOooA98xCqk/omh7G94R.K/E3O','1','0',NULL,NULL,'0',NULL,NULL,'2022-01-05 13:28:43',NULL,'2022-01-05 13:28:43',0),
(4,'sg2','dsadd','$2a$10$kY4T3SN7i4muBccZppd2OOkhxMN6yt8tND1sF89hXOaFylhY2T3he','1','0','23412332@qq.com','19098790742','0',NULL,NULL,NULL,NULL,NULL,0),
(5,'sg2233','tteqe','','1','0',NULL,'18246845873','1',NULL,NULL,'2022-01-06 03:51:13',NULL,'2022-01-06 07:00:50',0),
(6,'sangeng','sangeng','$2a$10$Jnq31rRkNV3RNzXe0REsEOSKaYK8UgVZZqlNlNXqn.JeVcj2NdeZy','1','0','2312321','17777777777','0',NULL,NULL,'2022-01-16 06:54:26',NULL,'2022-01-16 07:06:34',0),
(14787164048662,'weixin','weixin','$2a$10$y3k3fnMZsBNihsVLXWfI8uMNueVXBI08k.LzWYaKsW8CW7xXy18wC','0','0','weixin@qq.com',NULL,NULL,NULL,-1,'2022-01-30 17:18:44',-1,'2022-01-30 17:18:44',0),
(14787164048663,'xw','xw1112','$2a$10$KvbicCJ5c0MCwwmTkbN0d..sjMzjPKG/V2M3lSQyUwmQapppJIt1.','0','0','1811498080@qq.com',NULL,'0','http://rro56zvtn.hn-bkt.clouddn.com/2023/03/18/fea3fb7267d2405587a146e47b8e9f5e.png',NULL,NULL,NULL,NULL,1),
(14787164048665,'test','test123','$2a$10$sv1HwcVt7x9ZWYA6F.sluOF4gZAuDpDu4YYCgjMd8xKxmmUOdbJfK','0','0','131312@qq.com','13456789001','0',NULL,NULL,NULL,1,'2023-03-24 15:15:03',1),
(14787164048666,'xw','xw','$2a$10$J7Y8bgSYdUySfbsf3cMkSu31bl.yAvrxGU7ILjrTDMGODqS0U2jO.','0','0','111@qq.com','13456789002','0',NULL,1,'2023-03-24 16:13:38',1,'2023-03-24 16:13:38',0);

/*Table structure for table `sys_user_role` */

DROP TABLE IF EXISTS `sys_user_role`;

CREATE TABLE `sys_user_role` (
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `role_id` bigint NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='用户和角色关联表';

/*Data for the table `sys_user_role` */

insert  into `sys_user_role`(`user_id`,`role_id`) values 
(1,1),
(2,2),
(5,2),
(6,12),
(14787164048666,1);

/*Table structure for table `xw_article` */

DROP TABLE IF EXISTS `xw_article`;

CREATE TABLE `xw_article` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `title` varchar(256) DEFAULT NULL COMMENT '标题',
  `content` longtext COMMENT '文章内容',
  `summary` varchar(1024) DEFAULT NULL COMMENT '文章摘要',
  `category_id` bigint DEFAULT NULL COMMENT '所属分类id',
  `thumbnail` varchar(256) DEFAULT NULL COMMENT '缩略图',
  `is_top` char(1) DEFAULT '0' COMMENT '是否置顶（0否，1是）',
  `status` char(1) DEFAULT '1' COMMENT '状态（0已发布，1草稿）',
  `view_count` bigint DEFAULT '0' COMMENT '访问量',
  `is_comment` char(1) DEFAULT '1' COMMENT '是否允许评论 1是，0否',
  `create_by` bigint DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` bigint DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `del_flag` int DEFAULT '0' COMMENT '删除标志（0代表未删除，1代表已删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='文章表';

/*Data for the table `xw_article` */

insert  into `xw_article`(`id`,`title`,`content`,`summary`,`category_id`,`thumbnail`,`is_top`,`status`,`view_count`,`is_comment`,`create_by`,`create_time`,`update_by`,`update_time`,`del_flag`) values 
(1,'SpringSecurity从入门到精通','## 课程介绍\n![image20211219121555979.png](https://sg-blog-oss.oss-cn-beijing.aliyuncs.com/2022/01/31/e7131718e9e64faeaf3fe16404186eb4.png)\n\n## 0. 简介1\n\n​	**Spring Security** 是 Spring 家族中的一个安全管理框架。相比与另外一个安全框架**Shiro**，它提供了更丰富的功能，社区资源也比Shiro丰富。\n\n​	一般来说中大型的项目都是使用**SpringSecurity** 来做安全框架。小项目有Shiro的比较多，因为相比与SpringSecurity，Shiro的上手更加的简单。\n\n​	 一般Web应用的需要进行**认证**和**授权**。\n\n​		**认证：验证当前访问系统的是不是本系统的用户，并且要确认具体是哪个用户**\n\n​		**授权：经过认证后判断当前用户是否有权限进行某个操作**\n\n​	而认证和授权也是SpringSecurity作为安全框架的核心功能。\n\n\n\n## 1. 快速入门\n\n### 1.1 准备工作\n\n​	我们先要搭建一个简单的SpringBoot工程\n\n① 设置父工程 添加依赖\n\n~~~~\n    <parent>\n        <groupId>org.springframework.boot</groupId>\n        <artifactId>spring-boot-starter-parent</artifactId>\n        <version>2.5.0</version>\n    </parent>\n    <dependencies>\n        <dependency>\n            <groupId>org.springframework.boot</groupId>\n            <artifactId>spring-boot-starter-web</artifactId>\n        </dependency>\n        <dependency>\n            <groupId>org.projectlombok</groupId>\n            <artifactId>lombok</artifactId>\n            <optional>true</optional>\n        </dependency>\n    </dependencies>\n~~~~\n\n② 创建启动类\n\n~~~~\n@SpringBootApplication\npublic class SecurityApplication {\n\n    public static void main(String[] args) {\n        SpringApplication.run(SecurityApplication.class,args);\n    }\n}\n\n~~~~\n\n③ 创建Controller\n\n~~~~java\n\nimport org.springframework.web.bind.annotation.RequestMapping;\nimport org.springframework.web.bind.annotation.RestController;\n\n@RestController\npublic class HelloController {\n\n    @RequestMapping(\"/hello\")\n    public String hello(){\n        return \"hello\";\n    }\n}\n\n~~~~\n\n\n\n### 1.2 引入SpringSecurity\n\n​	在SpringBoot项目中使用SpringSecurity我们只需要引入依赖即可实现入门案例。\n\n~~~~xml\n        <dependency>\n            <groupId>org.springframework.boot</groupId>\n            <artifactId>spring-boot-starter-security</artifactId>\n        </dependency>\n~~~~\n\n​	引入依赖后我们在尝试去访问之前的接口就会自动跳转到一个SpringSecurity的默认登陆页面，默认用户名是user,密码会输出在控制台。\n\n​	必须登陆之后才能对接口进行访问。\n\n\n\n## 2. 认证\n\n### 2.1 登陆校验流程\n![image20211215094003288.png](https://sg-blog-oss.oss-cn-beijing.aliyuncs.com/2022/01/31/414a87eeed344828b5b00ffa80178958.png)','SpringSecurity框架教程-Spring Security+JWT实现项目级前端分离认证授权',1,'https://sg-blog-oss.oss-cn-beijing.aliyuncs.com/2022/01/31/948597e164614902ab1662ba8452e106.png','1','0',107,'0',NULL,'2022-01-23 23:20:11',NULL,NULL,0),
(2,'weq','adadaeqe','adad',2,'https://sg-blog-oss.oss-cn-beijing.aliyuncs.com/2022/01/15/fd2e9460c58a4af3bbeae5d9ed581688.png','1','0',22,'0',NULL,'2022-01-21 14:58:30',NULL,NULL,1),
(3,'dad','asdasda','sadad',1,'https://sg-blog-oss.oss-cn-beijing.aliyuncs.com/2022/01/15/737a0ed0b8ea430d8700a12e76aa1cd1.png','1','0',33,'0',NULL,'2022-01-18 14:58:34',NULL,NULL,1),
(5,'sdad','![Snipaste_20220115_165812.png](https://sg-blog-oss.oss-cn-beijing.aliyuncs.com/2022/01/15/1d9d283f5d874b468078b183e4b98b71.png)\r\n\r\n## sda \r\n\r\n222\r\n### sdasd newnewnew',NULL,2,'','1','0',44,'0',NULL,'2022-01-17 14:58:37',NULL,NULL,0),
(8,'test','# test','文章摘要',1,'http://rro56zvtn.hn-bkt.clouddn.com/2023/03/22/bc6946c1ff574b5a8f2c5823e4b9032e.png','1','1',0,'0',1,'2023-03-22 22:49:19',1,'2023-03-22 22:49:19',0),
(9,'test','# test','文章摘要12312312312323',17,'http://rro56zvtn.hn-bkt.clouddn.com/2023/03/22/bc6946c1ff574b5a8f2c5823e4b9032e.png','1','0',0,'0',1,'2023-03-22 22:53:02',1,'2023-03-24 16:12:34',0),
(10,'deleteTest','','asdasdasd',1,'','1','0',0,'0',1,'2023-03-23 16:41:55',1,'2023-03-23 16:41:55',1),
(11,'asdasdasdasdasd','asdasdasdasdasd','asddasd',17,'','1','0',0,'0',1,'2023-03-24 16:16:08',1,'2023-03-24 16:16:08',0);

/*Table structure for table `xw_article_tag` */

DROP TABLE IF EXISTS `xw_article_tag`;

CREATE TABLE `xw_article_tag` (
  `article_id` bigint NOT NULL AUTO_INCREMENT COMMENT '文章id',
  `tag_id` bigint NOT NULL DEFAULT '0' COMMENT '标签id',
  PRIMARY KEY (`article_id`,`tag_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='文章标签关联表';

/*Data for the table `xw_article_tag` */

insert  into `xw_article_tag`(`article_id`,`tag_id`) values 
(1,4),
(2,1),
(2,4),
(3,4),
(3,5),
(8,1),
(8,4),
(9,1),
(10,1),
(11,1),
(11,4);

/*Table structure for table `xw_category` */

DROP TABLE IF EXISTS `xw_category`;

CREATE TABLE `xw_category` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(128) DEFAULT NULL COMMENT '分类名',
  `pid` bigint DEFAULT '-1' COMMENT '父分类id，如果没有父分类为-1',
  `description` varchar(512) DEFAULT NULL COMMENT '描述',
  `status` char(1) DEFAULT '0' COMMENT '状态0:正常,1禁用',
  `create_by` bigint DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` bigint DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `del_flag` int DEFAULT '0' COMMENT '删除标志（0代表未删除，1代表已删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='分类表';

/*Data for the table `xw_category` */

insert  into `xw_category`(`id`,`name`,`pid`,`description`,`status`,`create_by`,`create_time`,`update_by`,`update_time`,`del_flag`) values 
(1,'java',-1,'wsd','0',NULL,NULL,NULL,NULL,0),
(2,'PHP',-1,'wsd','0',NULL,NULL,NULL,NULL,0),
(16,'python',-1,'qwe333','0',1,'2023-03-24 15:33:29',1,'2023-03-24 15:40:58',1),
(17,'python',-1,'123','0',1,'2023-03-24 16:12:15',1,'2023-03-24 16:12:15',0);

/*Table structure for table `xw_comment` */

DROP TABLE IF EXISTS `xw_comment`;

CREATE TABLE `xw_comment` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `type` char(1) DEFAULT '0' COMMENT '评论类型（0代表文章评论，1代表友链评论）',
  `article_id` bigint DEFAULT NULL COMMENT '文章id',
  `root_id` bigint DEFAULT '-1' COMMENT '根评论id',
  `content` varchar(512) DEFAULT NULL COMMENT '评论内容',
  `to_comment_user_id` bigint DEFAULT '-1' COMMENT '所回复的目标评论的userid',
  `to_comment_id` bigint DEFAULT '-1' COMMENT '回复目标评论id',
  `create_by` bigint DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` bigint DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `del_flag` int DEFAULT '0' COMMENT '删除标志（0代表未删除，1代表已删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='评论表';

/*Data for the table `xw_comment` */

insert  into `xw_comment`(`id`,`type`,`article_id`,`root_id`,`content`,`to_comment_user_id`,`to_comment_id`,`create_by`,`create_time`,`update_by`,`update_time`,`del_flag`) values 
(1,'0',1,-1,'asS',-1,-1,1,'2022-01-29 07:59:22',1,'2022-01-29 07:59:22',0),
(2,'0',1,-1,'[哈哈]SDAS',-1,-1,1,'2022-01-29 08:01:24',1,'2022-01-29 08:01:24',0),
(3,'0',1,-1,'是大多数',-1,-1,1,'2022-01-29 16:07:24',1,'2022-01-29 16:07:24',0),
(4,'0',1,-1,'撒大声地',-1,-1,1,'2022-01-29 16:12:09',1,'2022-01-29 16:12:09',0),
(5,'0',1,-1,'你再说什么',-1,-1,1,'2022-01-29 18:19:56',1,'2022-01-29 18:19:56',0),
(6,'0',1,-1,'hffd',-1,-1,1,'2022-01-29 22:13:52',1,'2022-01-29 22:13:52',0),
(9,'0',1,2,'你说什么',1,2,1,'2022-01-29 22:18:40',1,'2022-01-29 22:18:40',0),
(10,'0',1,2,'哈哈哈哈[哈哈]',1,9,1,'2022-01-29 22:29:15',1,'2022-01-29 22:29:15',0),
(11,'0',1,2,'we全文',1,10,3,'2022-01-29 22:29:55',1,'2022-01-29 22:29:55',0),
(12,'0',1,-1,'王企鹅',-1,-1,1,'2022-01-29 22:30:20',1,'2022-01-29 22:30:20',0),
(13,'0',1,-1,'什么阿是',-1,-1,1,'2022-01-29 22:30:56',1,'2022-01-29 22:30:56',0),
(14,'0',1,-1,'新平顶山',-1,-1,1,'2022-01-29 22:32:51',1,'2022-01-29 22:32:51',0),
(15,'0',1,-1,'2222',-1,-1,1,'2022-01-29 22:34:38',1,'2022-01-29 22:34:38',0),
(16,'0',1,2,'3333',1,11,1,'2022-01-29 22:34:47',1,'2022-01-29 22:34:47',0),
(17,'0',1,2,'回复weqedadsd',3,11,1,'2022-01-29 22:38:00',1,'2022-01-29 22:38:00',0),
(18,'0',1,-1,'sdasd',-1,-1,1,'2022-01-29 23:18:19',1,'2022-01-29 23:18:19',0),
(19,'0',1,-1,'111',-1,-1,1,'2022-01-29 23:22:23',1,'2022-01-29 23:22:23',0),
(20,'0',1,1,'你说啥？',1,1,1,'2022-01-30 10:06:21',1,'2022-01-30 10:06:21',0),
(21,'0',1,-1,'友链添加个呗',-1,-1,1,'2022-01-30 10:06:50',1,'2022-01-30 10:06:50',0),
(22,'1',1,-1,'友链评论2',-1,-1,1,'2022-01-30 10:08:28',1,'2022-01-30 10:08:28',0),
(23,'1',1,22,'回复友链评论3',1,22,1,'2022-01-30 10:08:50',1,'2022-01-30 10:08:50',0),
(24,'1',1,-1,'友链评论4444',-1,-1,1,'2022-01-30 10:09:03',1,'2022-01-30 10:09:03',0),
(25,'1',1,22,'收到的',1,22,1,'2022-01-30 10:13:28',1,'2022-01-30 10:13:28',0),
(26,'0',1,-1,'sda',-1,-1,1,'2022-01-30 10:39:05',1,'2022-01-30 10:39:05',0),
(27,'0',1,1,'说你咋地',1,20,14787164048662,'2022-01-30 17:19:30',14787164048662,'2022-01-30 17:19:30',0),
(28,'0',1,1,'sdad',1,1,14787164048662,'2022-01-31 11:11:20',14787164048662,'2022-01-31 11:11:20',0),
(29,'0',1,-1,'你说是的ad',-1,-1,14787164048662,'2022-01-31 14:10:11',14787164048662,'2022-01-31 14:10:11',0),
(30,'0',1,1,'撒大声地',1,1,14787164048662,'2022-01-31 20:19:18',14787164048662,'2022-01-31 20:19:18',0),
(31,'0',1,-1,'test',-1,-1,1,'2023-03-17 21:20:59',1,'2023-03-17 21:20:59',0),
(32,'0',1,31,'回复测试[微笑]',1,31,1,'2023-03-17 21:21:20',1,'2023-03-17 21:21:20',0),
(33,'1',1,-1,'123',-1,-1,1,'2023-03-17 21:28:32',1,'2023-03-17 21:28:32',0);

/*Table structure for table `xw_link` */

DROP TABLE IF EXISTS `xw_link`;

CREATE TABLE `xw_link` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(256) DEFAULT NULL,
  `logo` varchar(256) DEFAULT NULL,
  `description` varchar(512) DEFAULT NULL,
  `address` varchar(128) DEFAULT NULL COMMENT '网站地址',
  `status` char(1) DEFAULT '2' COMMENT '审核状态 (0代表审核通过，1代表审核未通过，2代表未审核)',
  `create_by` bigint DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` bigint DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `del_flag` int DEFAULT '0' COMMENT '删除标志（0代表未删除，1代表已删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='友链';

/*Data for the table `xw_link` */

insert  into `xw_link`(`id`,`name`,`logo`,`description`,`address`,`status`,`create_by`,`create_time`,`update_by`,`update_time`,`del_flag`) values 
(1,'sda','https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fn1.itc.cn%2Fimg8%2Fwb%2Frecom%2F2016%2F05%2F10%2F146286696706220328.PNG&refer=http%3A%2F%2Fn1.itc.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1646205529&t=f942665181eb9b0685db7a6f59d59975','sda','https://www.baidu.com','0',NULL,'2022-01-13 08:25:47',NULL,'2022-01-13 08:36:14',0),
(2,'sda','https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fn1.itc.cn%2Fimg8%2Fwb%2Frecom%2F2016%2F05%2F10%2F146286696706220328.PNG&refer=http%3A%2F%2Fn1.itc.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1646205529&t=f942665181eb9b0685db7a6f59d59975','dada','https://www.qq.com','0',NULL,'2022-01-13 09:06:10',NULL,'2022-01-13 09:07:09',0),
(3,'sa','https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fn1.itc.cn%2Fimg8%2Fwb%2Frecom%2F2016%2F05%2F10%2F146286696706220328.PNG&refer=http%3A%2F%2Fn1.itc.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1646205529&t=f942665181eb9b0685db7a6f59d59975','da','https://www.taobao.com','1',NULL,'2022-01-13 09:23:01',NULL,'2022-01-13 09:23:01',0),
(4,'asd123','asd123','asd123','asd123','0',1,'2023-03-24 16:01:51',1,'2023-03-24 16:05:15',1),
(5,'asdasd','asdsad','asdasd','asdasd','0',1,'2023-03-24 16:16:36',1,'2023-03-24 16:16:36',1);

/*Table structure for table `xw_tag` */

DROP TABLE IF EXISTS `xw_tag`;

CREATE TABLE `xw_tag` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(128) DEFAULT NULL COMMENT '标签名',
  `create_by` bigint DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` bigint DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `del_flag` int DEFAULT '0' COMMENT '删除标志（0代表未删除，1代表已删除）',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='标签';

/*Data for the table `xw_tag` */

insert  into `xw_tag`(`id`,`name`,`create_by`,`create_time`,`update_by`,`update_time`,`del_flag`,`remark`) values 
(1,'Mybatis',NULL,NULL,NULL,'2022-01-11 09:20:50',0,'weqwe'),
(2,'asdas',NULL,'2022-01-11 09:20:55',NULL,'2022-01-11 09:20:55',1,'weqw'),
(3,'weqw',NULL,'2022-01-11 09:21:07',NULL,'2022-01-11 09:21:07',1,'qweqwe'),
(4,'Java',NULL,'2022-01-13 15:22:43',NULL,'2022-01-13 15:22:43',0,'sdad'),
(5,'WAD',NULL,'2022-01-13 15:22:47',NULL,'2022-01-13 15:22:47',0,'ASDAD'),
(7,'test',1,'2023-03-22 21:33:47',1,'2023-03-22 21:33:47',1,'321'),
(8,'test2',1,'2023-03-22 21:45:01',1,'2023-03-22 21:49:14',0,'123');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
