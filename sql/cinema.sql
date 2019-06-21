-- MySQL dump 10.13  Distrib 5.7.26, for Linux (x86_64)
--
-- Host: localhost    Database: cinema
-- ------------------------------------------------------
-- Server version	5.7.26-0ubuntu0.16.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `activity`
--

DROP TABLE IF EXISTS `activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `activity` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `activity_name` varchar(45) NOT NULL,
  `a_description` varchar(255) NOT NULL,
  `end_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `coupon_id` int(11) DEFAULT NULL,
  `start_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity`
--

LOCK TABLES `activity` WRITE;
/*!40000 ALTER TABLE `activity` DISABLE KEYS */;
INSERT INTO `activity` VALUES (2,'猪年迎新春','为迎接新年，NJUSE影院现推出猪年迎新春活动，在活动期间购买指定电影票即可获得5元优惠券一张，活动最终解释权归主办方所有','2019-02-04 16:00:00',5,'2019-02-28 15:59:59'),(3,'劳动节特惠','为庆祝五一劳动节，NJUSE影院现推出五一劳动节优惠活动，活动期间购买指定电影电影票，即可获赠减10元优惠券一张','2019-04-29 16:00:00',6,'2019-05-03 15:59:59'),(4,'母亲节特惠','“世上只有妈妈好”，NJUSE影院现于5月12日推出母亲节特惠活动，活动期间和母亲共同观看电影的观众将获赠一张电影票','2019-05-11 16:00:00',8,'2019-05-12 15:59:59'),(5,'儿童节特惠','“谁还不是父母眼中的孩子呢？”为庆祝六一儿童节，NJUSE影院现推出儿童节活动，在活动期间观看电影的观众可获赠优惠券一张，凭此优惠券可去影院前台兑换价值20元的爆米花一份','2019-05-31 16:00:00',9,'2019-06-01 15:59:59'),(6,'端午节特惠','恰逢端午节撞上高考，为庆祝中国传统节日端午节，同时也为庆祝各位高考考生顺利结束考试，NJUSE影院现推出端午节特惠活动，在活动期间购买电影的观众可获得价值10元的优惠券一张，凭高考准考证还可去前台领取小礼物一份','2019-06-06 16:00:00',10,'2019-06-16 15:59:59'),(7,'猪年迎新春','猪年迎新春','2019-02-27 16:00:00',11,'2019-02-04 16:00:00'),(8,'晕了','晕了','2019-06-11 15:36:04',12,'2019-06-11 15:36:04'),(9,'端午节特惠','端午节特惠','2019-06-19 16:00:00',NULL,'2019-06-08 16:00:00'),(10,'新春佳节','新春佳节','2019-06-14 16:00:00',13,'2019-06-12 16:00:00'),(11,'123','123','2019-06-13 16:00:00',14,'2019-06-05 16:00:00'),(12,'1','1','2019-06-14 16:00:00',15,'2019-06-13 16:00:00');
/*!40000 ALTER TABLE `activity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `activity_movie`
--

DROP TABLE IF EXISTS `activity_movie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `activity_movie` (
  `activity_id` int(11) DEFAULT NULL,
  `movie_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity_movie`
--

LOCK TABLES `activity_movie` WRITE;
/*!40000 ALTER TABLE `activity_movie` DISABLE KEYS */;
INSERT INTO `activity_movie` VALUES (2,10),(2,11),(2,16),(4,10),(5,10),(7,11),(7,12),(7,13),(7,15),(7,16),(7,10),(7,14),(9,13),(9,16),(9,10),(9,11),(9,12),(9,14),(9,15),(9,17),(11,13);
/*!40000 ALTER TABLE `activity_movie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `charge_order`
--

DROP TABLE IF EXISTS `charge_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `charge_order` (
  `user_id` int(11) NOT NULL,
  `fare` double NOT NULL,
  `real_fare` double NOT NULL,
  `card_id` int(11) NOT NULL,
  `status` int(11) NOT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `charge_order`
--

LOCK TABLES `charge_order` WRITE;
/*!40000 ALTER TABLE `charge_order` DISABLE KEYS */;
INSERT INTO `charge_order` VALUES (16,100,120,1,1,'2019-05-31 17:24:21'),(14,100,120,12,2,'2019-06-02 16:20:33');
/*!40000 ALTER TABLE `charge_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `coupon`
--

DROP TABLE IF EXISTS `coupon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `coupon` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `target_amount` float DEFAULT NULL,
  `discount_amount` float DEFAULT NULL,
  `start_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `end_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coupon`
--

LOCK TABLES `coupon` WRITE;
/*!40000 ALTER TABLE `coupon` DISABLE KEYS */;
INSERT INTO `coupon` VALUES (1,'测试优惠券','春季电影节',20,5,'2019-04-20 17:47:54','2019-04-23 17:47:59'),(5,'测试优惠券','品质联盟',30,4,'2019-04-20 21:14:46','2019-04-24 21:14:51'),(6,'春节电影节优惠券','电影节优惠券',50,10,'2019-04-20 21:15:11','2019-04-21 21:14:56'),(11,'新年优惠券','本优惠券适用于春节期间所有电影，活动最终解释权归主办方所有',20,5,'2019-02-04 16:00:00','2019-02-27 16:00:00'),(12,'','',0,0,'2019-06-11 15:36:04',NULL),(13,'dark礼包','新日暮里',1,0.5,'2019-06-12 16:00:00','2019-06-14 16:00:00'),(14,'2','2',2,2,'2019-06-05 16:00:00','2019-06-13 16:00:00'),(15,'2','2',2,2,'2019-06-13 16:00:00','2019-06-14 16:00:00');
/*!40000 ALTER TABLE `coupon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `coupon_user`
--

DROP TABLE IF EXISTS `coupon_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `coupon_user` (
  `coupon_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coupon_user`
--

LOCK TABLES `coupon_user` WRITE;
/*!40000 ALTER TABLE `coupon_user` DISABLE KEYS */;
INSERT INTO `coupon_user` VALUES (8,15),(5,15),(8,15),(6,15),(5,15),(8,15),(6,15),(9,16),(9,16),(9,16),(9,16),(9,16),(9,16),(9,16),(9,16),(9,16),(9,16),(9,16),(9,16),(9,16);
/*!40000 ALTER TABLE `coupon_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hall`
--

DROP TABLE IF EXISTS `hall`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hall` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `column_c` int(11) DEFAULT NULL,
  `row_r` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hall`
--

LOCK TABLES `hall` WRITE;
/*!40000 ALTER TABLE `hall` DISABLE KEYS */;
INSERT INTO `hall` VALUES (1,'1号厅',10,5),(2,'2号厅',12,8),(3,'3号厅',16,10),(4,'4号厅',9,7);
/*!40000 ALTER TABLE `hall` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `history`
--

DROP TABLE IF EXISTS `history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `history` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pay_type` int(11) DEFAULT NULL,
  `time` timestamp NULL DEFAULT NULL,
  `fare` double DEFAULT NULL,
  `type` varchar(20) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `history`
--

LOCK TABLES `history` WRITE;
/*!40000 ALTER TABLE `history` DISABLE KEYS */;
INSERT INTO `history` VALUES (1,1,'2019-06-06 13:54:08',100,'ticket',16),(2,1,'2019-06-06 14:13:43',100,'ticket',16),(3,1,'2019-06-06 14:16:15',100,'ticket',16),(4,1,'2019-06-06 14:42:00',200,'ticket',16),(5,1,'2019-06-06 14:52:15',300,'ticket',16),(6,1,'2019-06-08 02:31:55',300,'ticket',16),(7,1,'2019-06-08 02:35:11',250,'ticket',16),(8,1,'2019-06-08 02:35:48',250,'ticket',16),(9,1,'2019-06-08 02:39:10',2100,'ticket',16),(10,1,'2019-06-08 02:44:36',1500,'ticket',16),(11,0,'2019-06-12 03:18:00',35,'ticket',27),(12,0,'2019-06-12 03:22:18',35,'ticket',27),(13,0,'2019-06-12 03:24:57',35,'ticket',27),(14,0,'2019-06-12 03:26:12',35,'ticket',27),(15,0,'2019-06-12 06:57:17',25,'card',28),(16,0,'2019-06-12 06:57:26',100,'charge',28),(17,0,'2019-06-12 07:05:35',100,'charge',28),(18,0,'2019-06-12 07:39:34',3000,'charge',28),(19,0,'2019-06-12 07:39:52',25,'card',27),(20,0,'2019-06-12 07:40:13',100,'charge',27),(21,0,'2019-06-12 07:41:45',3000,'charge',28),(22,0,'2019-06-12 08:00:48',70,'ticket',29),(23,0,'2019-06-12 08:01:52',100,'charge',29),(24,0,'2019-06-12 09:31:37',100,'charge',29),(25,0,'2019-06-12 09:34:30',100,'charge',29),(26,0,'2019-06-12 13:08:33',3000,'charge',29),(27,0,'2019-06-12 13:09:09',25,'card',31),(28,0,'2019-06-12 13:09:30',3000,'charge',31),(29,0,'2019-06-12 13:10:01',100,'charge',31),(30,0,'2019-06-13 02:06:48',100,'charge',31),(31,0,'2019-06-13 02:07:21',100,'charge',31),(32,0,'2019-06-13 02:10:10',100,'charge',29),(33,0,'2019-06-13 02:11:35',100,'charge',29),(34,0,'2019-06-13 02:13:23',25,'card',36);
/*!40000 ALTER TABLE `history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `history_charge`
--

DROP TABLE IF EXISTS `history_charge`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `history_charge` (
  `history_id` int(11) DEFAULT NULL,
  `balance` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `history_charge`
--

LOCK TABLES `history_charge` WRITE;
/*!40000 ALTER TABLE `history_charge` DISABLE KEYS */;
INSERT INTO `history_charge` VALUES (16,100),(17,100),(18,3000),(20,100),(21,3000),(23,100),(24,100),(25,100),(26,3000),(28,3000),(29,100),(30,5240),(31,5360),(32,3510),(33,3610);
/*!40000 ALTER TABLE `history_charge` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `history_movie`
--

DROP TABLE IF EXISTS `history_movie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `history_movie` (
  `history_id` int(11) DEFAULT NULL,
  `movie_name` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci COMMENT '评论内容',
  `ticket_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `history_movie`
--

LOCK TABLES `history_movie` WRITE;
/*!40000 ALTER TABLE `history_movie` DISABLE KEYS */;
INSERT INTO `history_movie` VALUES (4,'夏目友人帐',NULL),(5,'夏目友人帐',NULL),(6,'夏目友人帐',NULL),(7,'夏目友人帐',NULL),(8,'夏目友人帐',NULL),(9,'夏目友人帐',NULL),(10,'夏目友人帐',NULL),(11,'?????????',4),(12,'?????????',5),(13,'?????????',6),(14,'?????????',7),(22,'?????????',10),(22,'?????????',11);
/*!40000 ALTER TABLE `history_movie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie`
--

DROP TABLE IF EXISTS `movie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `movie` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `poster_url` varchar(255) DEFAULT NULL,
  `director` varchar(255) DEFAULT NULL,
  `screen_writer` varchar(255) DEFAULT NULL,
  `starring` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `language` varchar(255) DEFAULT NULL,
  `length` int(11) NOT NULL,
  `start_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `name` varchar(255) NOT NULL,
  `description` text,
  `status` int(11) DEFAULT '0',
  `end_date` timestamp NULL DEFAULT NULL,
  `big_poster_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie`
--

LOCK TABLES `movie` WRITE;
/*!40000 ALTER TABLE `movie` DISABLE KEYS */;
INSERT INTO `movie` VALUES (10,'https://img3.doubanio.com/view/photo/m/public/p2548852590.webp','大森贵弘 / 伊藤秀树','绿川幸 / 村井贞之',' 神谷浩史 / 井上和彦 / 高良健吾','剧情 / 动画 / 奇幻',' 日本','日语',104,'2019-06-12 10:39:08','夏目友人帐','在人与妖怪之间过着忙碌日子的夏目，偶然与以前的同学结城重逢，由此回忆起了被妖怪缠身的苦涩记忆。此时，夏目认识了在归还名字的妖怪记忆中出现的女性·津村容莉枝。和玲子相识的她，现在和独子椋雄一同过着平稳的生活。夏目通过与他们的交流，心境也变得平和。但这对母子居住的城镇，却似乎潜伏着神秘的妖怪。在调查此事归来后，寄生于猫咪老师身体的“妖之种”，在藤原家的庭院中，一夜之间就长成树结出果实。而吃掉了与自己形状相似果实的猫咪老师，竟然分裂成了3个——！？',0,'2019-06-29 16:00:00','http://i2.hdslb.com/bfs/archive/29a4871997a906989981e8ee7f9cec11cca199a1.jpg'),(11,'https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2557157554.webp','章笛沙','章笛沙','陈飞宇 / 何蓝逗 / 惠英红 ','剧情 / 爱情','中国大陆','汉语普通话',110,'2019-06-12 10:39:08','最好的我们','每个人的心里大概都藏着一个念念不忘的人。一个偶然被提及的名字，让女摄影师耿耿（何蓝逗 饰）内心掀起万千波澜，触动了回忆的开关，那个撩人心动的少年余淮（陈飞宇 饰）再度闯进她的思绪。 \n　　那是记忆里最好的时光，“学渣”耿耿和“学霸”余淮成了同桌，还结识了简单（王初伊 饰）、贝塔（周楚濋 饰）、徐延亮（陈帅 饰）。校园里充盈着专属少男少女们的懵懂、青涩、怦然心动和勇敢，耿耿余淮也拥有了他们的约定。高考后，当耿耿满怀期待憧憬约定兑现之时，余淮却忽然消失不见了。七年后两人重逢，余淮当年未说出口的那句话、他不辞而别的秘密，耿耿能否得到解答？这段耿耿于怀的过往，让两人再度面临情感的抉择…… \n　　本片根据八月长安同名小说作品改编。',0,'2019-06-29 16:00:00','http://puui.qpic.cn/qqvideo_ori/0/a0864woaki3_496_280/0'),(12,'https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2555886490.webp',' 西蒙·金伯格','西蒙·金伯格 / 约翰·拜恩 ','苏菲·特纳 / 詹姆斯·麦卡沃伊 ','动作 / 科幻 / 冒险','美国','英语',114,'2019-06-12 10:39:08','X战警：黑凤凰 Dark Phoenix','影片剧情围绕X战警中最受欢迎成员之一的琴·葛蕾展开，讲述她逐渐转化为黑凤凰的故事。在一次危及生命的太空营救行动中，琴被神秘的宇宙力量击中，成为最强大的变种人。此后琴·葛蕾不仅要设法掌控日益增长、极不稳定的力量，更要与自己内心的恶魔抗争，她的失控让整个X战警大家庭分崩离析，也让整个星球陷入毁灭的威胁之中。《X战警：黑凤凰》是迄今为止气氛最紧张、情感最丰富的一部《X战警》电影，是《X战警》系列20年来的集大成之作，大家非常熟悉和热爱的变种人大家庭即将面对最为强大的敌人——而她恰恰还是他们中的一员。',0,'2019-06-29 16:00:00','http://puui.qpic.cn/vpic/0/g0871xmaqu8.png/0'),(13,'https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2549523952.webp','林孝谦','吕安弦',' 陈意涵 / 刘以豪 ','爱情','中国台湾','汉语普通话',105,'2019-06-12 10:39:07','比悲伤更悲伤的故事','唱片制作人张哲凯(刘以豪)和王牌作词人宋媛媛(陈意涵)相依为命，两人自幼身世坎坷只有彼此为伴，他们是亲人、是朋友，也彷佛是命中注定的另一半。父亲罹患遗传重症而被母亲抛弃的哲凯，深怕自己随时会发病不久人世，始终没有跨出友谊的界线对媛媛展露爱意。眼见哲凯的病情加重，他暗自决定用剩余的生命完成他们之间的终曲，再为媛媛找个可以托付一生的好男人。这时，事业有 成温柔体贴的医生(张书豪)适时的出现让他成为照顾媛媛的最佳人选，二人按部就班发展着关系。一切看似都在哲凯的计划下进行。然而，故事远比这里所写更要悲伤......',0,'2019-06-29 16:00:00','http://wx4.sinaimg.cn/large/006whaK6ly1g1518t4h7lj30rs0fm1kx.jpg'),(14,'https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2553992741.webp','盖·里奇','盖·里奇 / 约翰·奥古斯特','梅纳·玛索德 / 娜奥米·斯科特','爱情 / 奇幻 / 冒险','美国','英语',128,'2019-06-12 10:39:08','阿拉丁 Aladdin','在充满异域风情的古代阿拉伯王国，善良的穷小子阿拉丁（莫纳·马苏德 饰）和勇敢的茉莉公主（娜奥米·斯科特 饰）浪漫邂逅，在可以满足主人三个愿望的神灯精灵（威尔·史密斯 饰）的帮助下，两人踏上了一次寻找真爱和自我的魔幻冒险。',0,'2019-06-29 16:00:00','http://5b0988e595225.cdn.sohucs.com/images/20190531/53d3c45014da4f91a94a2402786a24d9.jpeg'),(15,'https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2554370800.webp','迈克尔·道赫蒂','迈克尔·道赫蒂 / 扎克·希尔兹 ','凯尔·钱德勒 / 维拉·法米加','动作 / 科幻 / 冒险 / 灾难','美国','英语',132,'2019-06-12 10:39:08','哥斯拉2：怪兽之王','随着《哥斯拉》和《金刚：骷髅岛》在全球范围内取得成功，传奇影业和华纳兄弟影业联手开启了怪兽宇宙系列电影的新篇章—一部史诗级动作冒险巨制。在这部电影中，哥斯拉将和众多大家在流行文化中所熟知的怪兽展开较量。全新故事中，研究神秘动物学的机构“帝王组织”将勇敢直面巨型怪兽，其中强大的哥斯拉也将和魔斯拉、拉顿和它的死对头——三头王者基多拉展开激烈对抗。当这些只存在于传说里的超级生物再度崛起时，它们将展开王者争霸，人类的命运岌岌可危……',0,'2019-06-29 16:00:00','http://5b0988e595225.cdn.sohucs.com/images/20190522/68e146932f1b456fbf0fbe231dcac30b.jpeg'),(16,'https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2558294190.webp','王晶 / 关智耀','王晶 / 吕冠南','梁家辉 / 古天乐','剧情 / 动作 / 犯罪','香港 / 中国大陆','粤语 / 汉语普通话',103,'2019-06-12 10:39:07','追龙Ⅱ 追龍2：贼王','悍匪龙志强，在香港回归前，趁香港英政府不作为，而屡犯巨案，先后绑架富豪利家及雷家之长子，勒索超过二十亿元，事主怕被报复, 交赎款后都不敢报警。中国公安部极为关注，与香港警方合力，派香港警员何天卧底潜入龙志强犯罪团伙，发现他正策划绑架澳门富豪贺不凡，最终陆港警察合力勇擒龙志强，救出贺不凡。',0,'2019-06-29 16:00:00','http://5b0988e595225.cdn.sohucs.com/images/20190517/865cd89dab774c9d93cb42af0e62d9ec.jpeg'),(17,'https://img3.doubanio.com/view/photo/l/public/p2557284230.webp',' 乔什·库雷','约翰·拉塞特 / 安德鲁·斯坦顿 / 彼特·道格特 / 李·昂克里奇 / 威尔·麦克马克 / 史黛芬妮·福尔松 / C·S·安德森','汤姆·汉克斯 / 蒂姆·艾伦 / 安妮·波茨 / 托尼·海尔 / 帕特丽夏·阿奎特 / 琼·库萨克 / 基努·里维斯 / 玛德琳·麦格劳 / 克里斯汀·沙尔 / 劳里·梅特卡夫 / 邦尼·亨特 / 裘蒂·班森 / 罗里·艾伦 / 杰夫·格尔林 / 布莱克·克拉克 / 巴德·乐凯 / 爱丝黛儿·哈里斯 / 杰夫·皮金','喜剧 / 动画 / 奇幻','美国','英语',100,'2019-06-29 16:00:00','玩具总动员4 Toy Story 4','《玩具总动员4》将是皮克斯的动画系列电影的第四部，主角是两个玩具：牛仔警长胡迪和太空骑警巴斯光年。 \n《玩具总动员4》的故事将延续上集，孩子们变成了青年，玩具的回归便显得更加尴尬。当然，不排除皮克斯开辟新的故事方向，将系列电影更好地发展下去。',0,'2019-07-29 16:00:00','https://img3.doubanio.com/view/photo/l/public/p2557284230.webp');
/*!40000 ALTER TABLE `movie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie_like`
--

DROP TABLE IF EXISTS `movie_like`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `movie_like` (
  `movie_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `like_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`movie_id`,`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie_like`
--

LOCK TABLES `movie_like` WRITE;
/*!40000 ALTER TABLE `movie_like` DISABLE KEYS */;
INSERT INTO `movie_like` VALUES (10,29,'2019-06-13 01:50:56'),(10,31,'2019-06-13 01:49:51'),(11,27,'2019-06-12 07:38:22'),(12,27,'2019-06-12 07:38:30'),(12,36,'2019-06-13 02:14:45'),(13,27,'2019-06-12 07:38:38'),(13,28,'2019-06-13 01:52:13'),(13,29,'2019-06-13 01:50:39'),(13,37,'2019-06-13 02:14:24'),(14,28,'2019-06-13 01:52:23'),(15,29,'2019-06-13 01:50:51'),(15,31,'2019-06-13 01:50:03'),(17,29,'2019-06-13 01:50:45');
/*!40000 ALTER TABLE `movie_like` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_ticket`
--

DROP TABLE IF EXISTS `order_ticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_ticket` (
  `order_id` int(11) DEFAULT NULL,
  `ticket_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_ticket`
--

LOCK TABLES `order_ticket` WRITE;
/*!40000 ALTER TABLE `order_ticket` DISABLE KEYS */;
INSERT INTO `order_ticket` VALUES (1,1),(2,1),(3,2),(4,3),(5,3),(6,4),(7,4),(8,5),(9,6),(10,7),(11,8),(11,9),(12,10),(12,11);
/*!40000 ALTER TABLE `order_ticket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8mb4 DEFAULT NULL,
  `content` varchar(45) DEFAULT NULL,
  `price` float DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,'比悲伤更悲伤的故事','ticket',35,'0'),(2,'比悲伤更悲伤的故事','ticket',35,'0'),(3,'比悲伤更悲伤的故事','ticket',35,'0'),(4,'比悲伤更悲伤的故事','ticket',35,'0'),(5,'比悲伤更悲伤的故事','ticket',35,'0'),(6,'比悲伤更悲伤的故事','ticket',35,'0'),(7,'比悲伤更悲伤的故事','ticket',35,'1'),(8,'比悲伤更悲伤的故事','ticket',35,'1'),(9,'比悲伤更悲伤的故事','ticket',35,'1'),(10,'比悲伤更悲伤的故事','ticket',35,'1'),(11,'比悲伤更悲伤的故事','ticket',70,'0'),(12,'比悲伤更悲伤的故事','ticket',70,'1'),(13,'充值','charge',100,'0'),(14,'充值','charge',100,'0'),(15,'充值','charge',100,'0'),(16,'充值','charge',100,'0'),(17,'购买会员卡','buyCard',25,'0');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `refund_strg`
--

DROP TABLE IF EXISTS `refund_strg`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `refund_strg` (
  `end_minute` int(11) NOT NULL,
  `percent` double NOT NULL,
  PRIMARY KEY (`end_minute`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `refund_strg`
--

LOCK TABLES `refund_strg` WRITE;
/*!40000 ALTER TABLE `refund_strg` DISABLE KEYS */;
/*!40000 ALTER TABLE `refund_strg` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `schedule`
--

DROP TABLE IF EXISTS `schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `schedule` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `hall_id` int(11) NOT NULL,
  `movie_id` int(11) NOT NULL,
  `start_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `end_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `fare` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `schedule`
--

LOCK TABLES `schedule` WRITE;
/*!40000 ALTER TABLE `schedule` DISABLE KEYS */;
INSERT INTO `schedule` VALUES (1,2,13,'2019-06-15 12:00:00','2019-06-15 14:00:00',35);
/*!40000 ALTER TABLE `schedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket`
--

DROP TABLE IF EXISTS `ticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ticket` (
  `user_id` int(11) DEFAULT NULL,
  `schedule_id` int(11) DEFAULT NULL,
  `column_index` int(11) DEFAULT NULL,
  `row_index` int(11) DEFAULT NULL,
  `state` tinyint(4) DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `ticketing_state` int(11) NOT NULL,
  `use_coupon` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket`
--

LOCK TABLES `ticket` WRITE;
/*!40000 ALTER TABLE `ticket` DISABLE KEYS */;
INSERT INTO `ticket` VALUES (27,1,5,3,2,1,'2019-06-12 02:23:20',0,0),(27,1,6,3,2,2,'2019-06-12 02:37:33',0,0),(27,1,6,4,2,3,'2019-06-12 02:45:22',0,0),(27,1,5,4,1,4,'2019-06-12 03:10:03',0,0),(27,1,4,4,1,5,'2019-06-12 03:21:34',0,0),(27,1,5,3,1,6,'2019-06-12 03:24:41',0,0),(27,1,4,3,1,7,'2019-06-12 03:25:53',0,0),(29,1,6,3,3,10,'2019-06-12 07:59:35',0,0),(29,1,6,4,3,11,'2019-06-12 07:59:35',0,0),(27,1,9,3,2,12,'2019-06-12 15:16:02',0,0);
/*!40000 ALTER TABLE `ticket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `userType` int(11) NOT NULL,
  `photoURL` varchar(1000) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id_uindex` (`id`),
  UNIQUE KEY `user_username_uindex` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (3,'root','123456',0,'https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3397512707,4005967310&fm=27&gp=0.jpg','123456@qq.com'),(27,'hahaha','123456',2,'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1560332018832&di=42cd57a6edc11ad1826a07a27ffa5457&imgtype=0&src=http%3A%2F%2Fi5.72g.com%2Fupload%2F201408%2F201408041006053007.JPG',NULL),(28,'Kevin','rwkdccc',2,'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1560332018832&di=42cd57a6edc11ad1826a07a27ffa5457&imgtype=0&src=http%3A%2F%2Fi5.72g.com%2Fupload%2F201408%2F201408041006053007.JPG',NULL),(29,'Jolin','llbnxrr',2,'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1560332018832&di=42cd57a6edc11ad1826a07a27ffa5457&imgtype=0&src=http%3A%2F%2Fi5.72g.com%2Fupload%2F201408%2F201408041006053007.JPG',NULL),(30,'罗哥工作室','lmssjwsl',2,'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1560332018832&di=42cd57a6edc11ad1826a07a27ffa5457&imgtype=0&src=http%3A%2F%2Fi5.72g.com%2Fupload%2F201408%2F201408041006053007.JPG',NULL),(31,'Gliscor','123456',2,'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1560332018832&di=42cd57a6edc11ad1826a07a27ffa5457&imgtype=0&src=http%3A%2F%2Fi5.72g.com%2Fupload%2F201408%2F201408041006053007.JPG',NULL),(32,'AdminLuo','glylms',0,'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1560332018832&di=42cd57a6edc11ad1826a07a27ffa5457&imgtype=0&src=http%3A%2F%2Fi5.72g.com%2Fupload%2F201408%2F201408041006053007.JPG',NULL),(33,'AdminFan','glyfyxs',0,'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1560332018832&di=42cd57a6edc11ad1826a07a27ffa5457&imgtype=0&src=http%3A%2F%2Fi5.72g.com%2Fupload%2F201408%2F201408041006053007.JPG',NULL),(34,'AdminZhou','glyzykxs',0,'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1560332018832&di=42cd57a6edc11ad1826a07a27ffa5457&imgtype=0&src=http%3A%2F%2Fi5.72g.com%2Fupload%2F201408%2F201408041006053007.JPG',NULL),(35,'StaffAn','dgzahxs',2,'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1560332018832&di=42cd57a6edc11ad1826a07a27ffa5457&imgtype=0&src=http%3A%2F%2Fi5.72g.com%2Fupload%2F201408%2F201408041006053007.JPG',NULL),(36,'Pikachu','123456',2,'https://www.baidu.com/',NULL),(37,'Scizor','123456',2,'https://www.baidu.com/',NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `view`
--

DROP TABLE IF EXISTS `view`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `view` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `day` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `view`
--

LOCK TABLES `view` WRITE;
/*!40000 ALTER TABLE `view` DISABLE KEYS */;
INSERT INTO `view` VALUES (1,7);
/*!40000 ALTER TABLE `view` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vip_card`
--

DROP TABLE IF EXISTS `vip_card`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vip_card` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `balance` float NOT NULL,
  `join_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `vip_type` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `vip_card_user_id_uindex` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vip_card`
--

LOCK TABLES `vip_card` WRITE;
/*!40000 ALTER TABLE `vip_card` DISABLE KEYS */;
INSERT INTO `vip_card` VALUES (1,28,5000,'2019-06-12 07:41:45',1),(2,27,100,'2019-06-12 07:40:13',1),(3,29,3610,'2019-06-13 02:11:34',0),(4,31,5360,'2019-06-13 02:07:21',1),(5,36,0,'2019-06-13 02:13:23',1);
/*!40000 ALTER TABLE `vip_card` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vip_order`
--

DROP TABLE IF EXISTS `vip_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vip_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `fare` double DEFAULT NULL,
  `real_fare` double DEFAULT NULL,
  `time` timestamp NULL DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vip_order`
--

LOCK TABLES `vip_order` WRITE;
/*!40000 ALTER TABLE `vip_order` DISABLE KEYS */;
/*!40000 ALTER TABLE `vip_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vip_strg`
--

DROP TABLE IF EXISTS `vip_strg`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vip_strg` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `target` float NOT NULL,
  `add` float NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vip_strg`
--

LOCK TABLES `vip_strg` WRITE;
/*!40000 ALTER TABLE `vip_strg` DISABLE KEYS */;
INSERT INTO `vip_strg` VALUES (4,100,20),(5,200,50),(6,500,200),(7,1000,500),(8,3000,2000);
/*!40000 ALTER TABLE `vip_strg` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-06-13 10:46:28
