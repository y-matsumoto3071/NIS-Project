-- --------------------------------------------------------
-- ホスト:                          127.0.0.1
-- サーバーのバージョン:                   5.7.30 - MySQL Community Server (GPL)
-- サーバー OS:                      Win64
-- HeidiSQL バージョン:               11.0.0.5919
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- nis のデータベース構造をダンプしています
DROP DATABASE IF EXISTS `nis`;
CREATE DATABASE IF NOT EXISTS `nis` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `nis`;

--  テーブル nis.certification の構造をダンプしています
DROP TABLE IF EXISTS `certification`;
CREATE TABLE IF NOT EXISTS `certification` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `username` varchar(50) NOT NULL DEFAULT '' COMMENT 'username',
  `password` varchar(100) NOT NULL COMMENT 'password',
  `enabled` tinyint(4) NOT NULL COMMENT 'enabled',
  `admin` tinyint(4) NOT NULL COMMENT 'admin',
  `PERSON_ID` varchar(40) NOT NULL DEFAULT '' COMMENT '人ID',
  `PASSWORD_UPDATE` char(10) NOT NULL DEFAULT '' COMMENT 'パスワード更新日',
  PRIMARY KEY (`id`,`PERSON_ID`),
  UNIQUE KEY `account_IX2` (`id`),
  UNIQUE KEY `username` (`username`),
  KEY `PERSON_ID` (`PERSON_ID`),
  CONSTRAINT `certification_ibfk_1` FOREIGN KEY (`PERSON_ID`) REFERENCES `m_person` (`PERSON_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='認証情報(CERTIFICATION))';

-- テーブル nis.certification: ~2 rows (約) のデータをダンプしています
DELETE FROM `certification`;
/*!40000 ALTER TABLE `certification` DISABLE KEYS */;
INSERT INTO `certification` (`id`, `username`, `password`, `enabled`, `admin`, `PERSON_ID`, `PASSWORD_UPDATE`) VALUES
	(1, 'admin', '$2a$10$35nbsM22xscZJG9SgSz2hewJdU/f29us/poT7VPY.tSv.6fbddBtu', 1, 1, '1', ''),
	(2, 'user1', '$2a$10$9pCPq6E9jW9a6RstTcnRJuGrAVKaU45xU/8s/qGsj8N6/acCl/kse', 1, 0, '2', '');
/*!40000 ALTER TABLE `certification` ENABLE KEYS */;

--  テーブル nis.client の構造をダンプしています
DROP TABLE IF EXISTS `client`;
CREATE TABLE IF NOT EXISTS `client` (
  `client_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '顧客ID',
  `client_name` varchar(50) NOT NULL COMMENT '顧客社名',
  `deleteflg` int(1) NOT NULL DEFAULT '1' COMMENT '削除フラグ',
  `createdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '作成日付',
  `updatedate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日付',
  PRIMARY KEY (`client_id`),
  UNIQUE KEY `client_name` (`client_name`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- テーブル nis.client: ~16 rows (約) のデータをダンプしています
DELETE FROM `client`;
/*!40000 ALTER TABLE `client` DISABLE KEYS */;
INSERT INTO `client` (`client_id`, `client_name`, `deleteflg`, `createdate`, `updatedate`) VALUES
	(1, '株式会社NTTデータ', 1, '2020-12-16 14:25:28', '2020-12-16 14:25:28'),
	(2, '楽天', 1, '2021-01-18 18:57:56', '2021-01-18 18:57:56'),
	(3, '株式会社サイバーエージェント', 1, '2021-01-18 18:58:10', '2021-01-18 18:58:10'),
	(4, '日本アイ・ビー・エム株式会社', 1, '2020-12-16 14:21:12', '2020-12-16 14:21:12'),
	(5, 'NTT・コミュニケーションズ株式会社', 1, '2020-12-16 14:21:15', '2020-12-16 14:21:15'),
	(6, 'USEN／USEN-NEXT GROUP', 1, '2020-12-16 14:21:16', '2020-12-16 14:21:16'),
	(7, '株式会社 オービック', 1, '2020-12-16 14:21:19', '2020-12-16 14:21:19'),
	(8, 'ウイングアーク1st', 1, '2021-01-18 18:56:43', '2021-01-18 18:56:43'),
	(9, '株式会社Works Human Intelligence', 1, '2020-12-16 14:21:22', '2020-12-16 14:21:22'),
	(10, 'Sun Asterisk', 1, '2020-12-24 14:26:16', '2020-12-24 14:26:16'),
	(11, 'アマゾンジャパン', 1, '2020-12-16 14:21:26', '2020-12-16 14:21:26'),
	(12, '電通国際情報サービス', 1, '2020-12-16 14:21:28', '2020-12-16 14:21:28'),
	(13, 'ユーキャスサービス【廃止】', 0, '2021-01-01 22:53:39', '2021-01-01 22:53:39'),
	(14, 'HSC【廃止】', 0, '2021-01-01 22:53:39', '2021-01-01 22:53:39'),
	(15, '日本データスキル【廃止】', 0, '2021-01-01 22:53:04', '2021-01-01 22:53:04'),
	(18, 'おおしまテック【廃止】', 0, '2021-01-01 21:24:10', '2021-01-01 21:24:10');
/*!40000 ALTER TABLE `client` ENABLE KEYS */;

--  テーブル nis.employee の構造をダンプしています
DROP TABLE IF EXISTS `employee`;
CREATE TABLE IF NOT EXISTS `employee` (
  `employee_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '社員ID',
  `employee_area` int(11) DEFAULT NULL,
  `employee_group` int(11) DEFAULT NULL,
  `employee_category` varchar(5) NOT NULL COMMENT 'カテゴリ',
  `employee_name` varchar(20) NOT NULL COMMENT '社員名',
  `deleteflg` int(1) NOT NULL DEFAULT '1' COMMENT '削除フラグ',
  `createdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '作成日付',
  `updatedate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日付',
  PRIMARY KEY (`employee_id`),
  UNIQUE KEY `employee_name` (`employee_name`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- テーブル nis.employee: ~14 rows (約) のデータをダンプしています
DELETE FROM `employee`;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` (`employee_id`, `employee_area`, `employee_group`, `employee_category`, `employee_name`, `deleteflg`, `createdate`, `updatedate`) VALUES
	(1, NULL, NULL, 'ccgt0', '松雪 晃子', 1, '2020-11-27 13:48:57', '2020-11-27 13:48:57'),
	(2, NULL, NULL, 'ccgt0', '島田 菜々子', 1, '2020-11-27 13:48:59', '2020-11-27 13:48:59'),
	(3, NULL, NULL, 'ccgt0', '江渕 太郎', 1, '2020-11-27 13:49:00', '2020-11-27 13:49:00'),
	(4, NULL, NULL, 'ccgt0', '廣田 有都', 1, '2020-11-27 13:49:00', '2020-11-27 13:49:00'),
	(5, NULL, NULL, 'ccgt0', '今田 智子', 1, '2020-11-27 13:49:01', '2020-11-27 13:49:01'),
	(6, NULL, NULL, 'ccgt0', '宮下 太一', 1, '2020-11-27 13:49:02', '2021-12-15 15:00:11'),
	(7, NULL, NULL, 'scgt6', '島村 公博', 1, '2021-01-03 00:13:27', '2021-12-15 15:00:29'),
	(8, NULL, NULL, 'scgt1', '松本 雄樹', 1, '2020-12-24 14:25:50', '2020-12-24 14:25:50'),
	(9, NULL, NULL, 'scgt2', '針貝 将矢', 1, '2020-11-27 13:45:12', '2020-11-27 13:45:12'),
	(10, NULL, NULL, 'scgt3', '丸山 泰延', 1, '2020-11-27 13:45:16', '2020-11-27 13:45:16'),
	(11, NULL, NULL, 'scgt4', '木曾原 大将', 1, '2021-01-18 18:27:04', '2021-12-15 15:00:44'),
	(12, NULL, NULL, 'scgt5', '本間 洋平', 1, '2020-11-27 13:45:26', '2020-11-27 13:45:26'),
	(15, NULL, NULL, 'scgt1', 'テスト【廃止】', 0, '2021-01-03 00:13:34', '2021-01-03 00:13:34'),
	(16, NULL, NULL, 'scgt1', 'test1【廃止】', 0, '2020-12-31 19:45:20', '2020-12-31 19:45:20');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;

--  テーブル nis.event の構造をダンプしています
DROP TABLE IF EXISTS `event`;
CREATE TABLE IF NOT EXISTS `event` (
  `event_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '面談報告書ID',
  `event_client_id` int(11) NOT NULL DEFAULT '0' COMMENT '顧客ID',
  `event_contact` varchar(20) NOT NULL DEFAULT '' COMMENT '顧客担当者名',
  `event_date` date NOT NULL COMMENT '面談日',
  `event_start_time` time NOT NULL COMMENT '面談開始時間',
  `event_end_time` time NOT NULL COMMENT '面談終了時間',
  `event_location` varchar(50) NOT NULL DEFAULT '' COMMENT '面談場所',
  `event_member` varchar(50) NOT NULL COMMENT '参加者',
  `event_sales_employee_id` int(11) NOT NULL DEFAULT '0' COMMENT '営業担当ID',
  `event_entry_employee_id` int(11) NOT NULL DEFAULT '0' COMMENT '報告者名ID',
  `event_project` varchar(500) NOT NULL COMMENT '案件概要',
  `event_session` varchar(2000) NOT NULL COMMENT '質疑応答',
  `event_report` varchar(2000) NOT NULL COMMENT '考察',
  `event_feedback_byccg` varchar(2000) NOT NULL COMMENT 'CCG評価',
  `event_feedback_employee_id` int(11) DEFAULT NULL COMMENT '所見返信担当ID',
  `event_feedback_content` varchar(500) DEFAULT NULL COMMENT '所見',
  `event_status` int(1) NOT NULL DEFAULT '0' COMMENT '状態区分',
  `createdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '作成日付',
  `updatedate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日付',
  PRIMARY KEY (`event_id`),
  KEY `fk_client_id` (`event_client_id`),
  KEY `fk_sales_employee_id` (`event_sales_employee_id`),
  KEY `fk_entry_employee_id` (`event_entry_employee_id`),
  KEY `fk_feedback_employee_id` (`event_feedback_employee_id`),
  CONSTRAINT `fk_client_id` FOREIGN KEY (`event_client_id`) REFERENCES `client` (`client_id`),
  CONSTRAINT `fk_entry_employee_id` FOREIGN KEY (`event_entry_employee_id`) REFERENCES `employee` (`employee_id`),
  CONSTRAINT `fk_feedback_employee_id` FOREIGN KEY (`event_feedback_employee_id`) REFERENCES `employee` (`employee_id`),
  CONSTRAINT `fk_sales_employee_id` FOREIGN KEY (`event_sales_employee_id`) REFERENCES `employee` (`employee_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='面談報告書を管理するテーブル';

-- テーブル nis.event: ~13 rows (約) のデータをダンプしています
DELETE FROM `event`;
/*!40000 ALTER TABLE `event` DISABLE KEYS */;
INSERT INTO `event` (`event_id`, `event_client_id`, `event_contact`, `event_date`, `event_start_time`, `event_end_time`, `event_location`, `event_member`, `event_sales_employee_id`, `event_entry_employee_id`, `event_project`, `event_session`, `event_report`, `event_feedback_byccg`, `event_feedback_employee_id`, `event_feedback_content`, `event_status`, `createdate`, `updatedate`) VALUES
	(1, 3, '本間 洋', '2020-11-01', '13:00:00', '14:00:00', '池袋', '山口 翔平', 2, 8, '通信を管理するネットワークを構成', 'Linuxコマンドは使えるか\n単体テストは行った事があるか\nインフラの知識はあるか', '質問にスムーズに答えられて良かった', '全体的に良好', 1, 'なし', 1, '2020-11-30 00:00:00', '2021-01-10 01:14:00'),
	(2, 1, '三木谷 浩史', '2020-11-04', '11:00:00', '12:00:00', '新宿', '坂井 達郎', 1, 12, '現行システムの機能追加、改修作業', '詳細設計を作成する際何を作成したか\nテストをする際何に気を付けたか\nSQLはどのくらい書けるか', '自信をもって質問に答えられてよかった', 'ハキハキと話せていてとても良かった', 1, 'なし', 1, '2020-11-30 00:00:00', '2021-01-10 01:14:00'),
	(3, 2, '藤田 晋', '2020-11-04', '16:30:00', '17:30:00', '渋谷', '棚澤 貴之', 4, 10, '通販システムの機能追加', '基本設計書は書けるか\nJava以外の言語だが問題無いか\nリモートワークの環境は整っているか', '緊張してしまいうまく話せなかった', '声が小さいのではっきりと発する事', 1, 'なし', 1, '2020-11-30 00:00:00', '2020-12-19 14:40:00'),
	(4, 5, '山口明夫', '2020-11-06', '18:00:00', '18:30:00', '社内(Web面談)', '布矢 梓', 3, 7, 'ゲーム開発作業', 'C#での開発となるが問題無いか\n設計書を任せる可能性があるが大丈夫か\nコードレビューの経験はあるか', '質問された際に正面を向けなかった。', '視線が散って気になったのでもう少しカメラを意識する事', 1, 'なし', 1, '2020-11-30 00:00:00', '2020-12-19 14:40:00'),
	(5, 9, '丸岡 亨', '2020-11-10', '15:00:00', '16:30:00', '横浜', '大森 真亮', 6, 11, '会員制コミュニケーションサイト再構築', 'リグレッションテストを行ったか\nテストケースの洗い出しで工夫している事はあるか\nどういったテスト観点をケースに盛り込んだか', '話すことに夢中になってしまい前にのめり込み過ぎてしまった。', '姿勢が悪いので気をつけるよう', 1, 'なし', 1, '2020-11-30 00:00:00', '2020-12-19 14:40:00'),
	(6, 11, '宇野 康秀', '2020-11-14', '17:00:00', '17:45:00', '中野', '近藤 充朗', 5, 7, '先物取引、商品先物等を取り扱うサイト開発', '疎通テストを行ったか\n新規開発のhtmlではどんな事を書いたか上から説明して\n新規開発のcssではどんな事を書いたか上から説明して', '緊張しすぎて笑顔が出せなかった', '表情が硬い、もう少しリラックスしよう', 1, 'なし', 1, '2020-11-30 00:00:00', '2020-12-19 14:40:00'),
	(7, 4, '橘　昇一', '2020-11-17', '10:00:00', '11:15:00', '品川', '新垣 佳苗', 1, 6, '証券売買に関するシステムの機能追加', '障害対応はどんなことをしたか\nSpringの便利な特徴は何ですか。\n研修中で一番自分が成長できたと感じた期間はどこか', '話す内容を考える事を意識しすぎてしまい言葉が悪くなってしまった。', '言葉遣いに気をつける事', 1, 'なし', 1, '2020-11-30 00:00:00', '2020-12-19 14:41:00'),
	(8, 10, '田中　潤', '2020-11-20', '17:30:00', '18:10:00', '社内(Web面談)', '吉田 瑞樹', 3, 9, 'クレジットカード会社向け、システムの改修作業', 'この資格を持っているとどこまで出来るという認識をすればいいか\nSQLの外部結合と内部結合について説明して\n経歴で行ったJSではどういった事を行ったか', '事前に用意していた逆質問が先に説明されてしまい逆質問が出来なかった。', '逆質問はなるべくする事', 1, 'なし', 1, '2020-11-30 00:00:00', '2020-12-19 14:41:00'),
	(9, 7, '安斎 富太郎', '2020-11-24', '14:00:00', '15:30:00', '三鷹', '井上 直哉', 5, 9, '貸付、信用リスクに関する現行システムの改修（レベルアップ）作業', '今後なりたい人物像はあるか\n同値分割と境界分析はわかるか\nテスト仕様書は何を元に作成したか', '緊張で経歴説明の内容が飛んでしまった。', '緊張感が相手にも伝わっていたので、もう少しリラックスした方がいい', 1, 'なし', 1, '2020-11-30 00:00:00', '2020-12-19 14:41:00'),
	(10, 8, '小林泰平', '2020-11-28', '11:00:00', '11:45:00', '浜松町', '山口 瑠夏', 6, 12, '流通系クレジットカード会社向け、システム開発作業', '詳細設計は何をもとに作成したか\n業務外で学習している事はあるか\nテストにかけた期間はどのくらいか', '想定していない質問がきてしまい固まってしまった。', '練習不足のように感じた、事前準備をしっかりする事', 1, 'なし', 1, '2020-11-30 00:00:00', '2020-12-19 14:41:00'),
	(11, 6, 'ジャスパー・チャン', '2020-11-29', '10:00:00', '11:00:00', '有明', '野口 器', 2, 10, '不動産管理にかかわるパッケージの改修支援', 'ブラックボックステストとホワイトボックステストはわかるか\nこの1000stepにかけた時間はどのくらいか\nLinuxを扱ったことはあるか', '質問対策にばかり気がいってしまい服装を直さずに参加してしまった。', '身嗜みに気をつける事', 1, 'なし', 1, '2020-11-30 00:00:00', '2020-12-19 14:41:00'),
	(12, 12, '名和 亮一', '2020-11-30', '15:00:00', '16:00:00', '茅場町', '斎藤 友佑', 4, 11, 'オフコンで稼動中の旧システムをイントラネット化する作業', '行ったテストは結合テストだけか\nビジネスメールの作成をしたことはあるか\nVBAを扱えるか', '自分の話す内容ばかりに気が言ってしまい質問の趣旨を掴めなかった。', '相手の話はしっかり聞こう', 1, 'なし', 1, '2020-11-30 00:00:00', '2020-12-19 14:41:00'),
	(13, 1, 'a', '2022-01-01', '00:00:00', '00:01:00', 'a', 'a', 2, 3, 'a', 'a', 'a', 'a', NULL, NULL, 1, '2022-01-11 14:50:03', '2022-01-11 14:50:03');
/*!40000 ALTER TABLE `event` ENABLE KEYS */;

--  テーブル nis.message の構造をダンプしています
DROP TABLE IF EXISTS `message`;
CREATE TABLE IF NOT EXISTS `message` (
  `message_number` int(11) NOT NULL AUTO_INCREMENT,
  `message_id` varchar(9) NOT NULL DEFAULT '0' COMMENT 'メッセージ一覧表上のメッセージID',
  `message_type` varchar(1) NOT NULL COMMENT 'e:エラーメッセージ w:ワーニングメッセージ i:インフォメーション',
  `message_body` varchar(100) NOT NULL COMMENT 'メッセージ一覧表上のメッセージ内容',
  `startdate` date NOT NULL COMMENT 'エラーメッセージの適用開始日',
  `enddate` date NOT NULL COMMENT 'エラーメッセージの適用終了日',
  `createdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'レコード作成時',
  `updatedate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新時にUPDATEされる',
  PRIMARY KEY (`message_number`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- テーブル nis.message: ~20 rows (約) のデータをダンプしています
DELETE FROM `message`;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` (`message_number`, `message_id`, `message_type`, `message_body`, `startdate`, `enddate`, `createdate`, `updatedate`) VALUES
	(1, 'COM00E010', 'e', '未入力の項目があります。', '1900-01-01', '2999-12-31', '2021-06-01 17:13:44', '2021-06-01 17:13:44'),
	(2, 'COM00E020', 'i', 'パスワードを入力してください。', '1900-01-01', '2999-12-31', '2021-06-01 17:26:02', '2021-06-01 17:26:02'),
	(3, 'COM00E030', 'e', 'パスワードが間違っています。', '1900-01-01', '2999-12-31', '2021-06-01 17:26:02', '2021-06-01 17:26:02'),
	(4, 'COM00E040', 'e', '管理者ID又はパスワードが間違っています。', '1900-01-01', '2999-12-31', '2021-06-01 17:26:02', '2021-06-01 17:26:02'),
	(5, 'COM00E050', 'i', '名前を入力してください。', '1900-01-01', '2999-12-31', '2021-06-01 17:26:02', '2021-06-01 17:26:02'),
	(6, 'COM00E060', 'e', '該当のIDが存在しません。', '1900-01-01', '2999-12-31', '2021-06-01 17:26:02', '2021-06-01 17:26:02'),
	(7, 'COM00E070', 'e', '所見は500文字以内で入力してください。', '1900-01-01', '2999-12-31', '2021-06-01 17:26:02', '2021-06-01 17:26:02'),
	(8, 'COM00I010', 'i', '登録が完了しました。', '1900-01-01', '2999-12-31', '2021-06-01 17:26:02', '2021-06-01 17:26:02'),
	(9, 'COM00I020', 'i', '更新が完了しました。', '1900-01-01', '2999-12-31', '2021-06-01 17:26:02', '2021-06-01 17:26:02'),
	(10, 'COM00I030', 'i', '件削除しました。', '1900-01-01', '2999-12-31', '2021-06-01 17:26:02', '2021-06-11 17:28:49'),
	(11, 'RC010E010', 'e', '適切な数値を入力してください。', '1900-01-01', '2999-12-31', '2021-06-01 17:26:02', '2021-06-01 17:26:02'),
	(12, 'RD010E010', 'i', '削除する報告書を選択してください。', '1900-01-01', '2999-12-31', '2021-06-01 17:26:02', '2021-06-15 11:21:21'),
	(13, 'CC010E010', 'e', '社名が重複しています。', '1900-01-01', '2999-12-31', '2021-06-01 17:26:02', '2021-06-01 17:26:02'),
	(14, 'CD010E010', 'i', '削除する顧客を選択してください', '1900-01-01', '2999-12-31', '2021-06-01 17:26:02', '2021-06-01 17:26:02'),
	(15, 'EC010E010', 'i', '適切な組み合わせを選んでください。', '1900-01-01', '2999-12-31', '2021-06-01 17:26:02', '2021-06-01 17:26:02'),
	(16, 'EC010E020', 'e', '名前が重複しています。', '1900-01-01', '2999-12-31', '2021-06-01 17:26:02', '2021-06-01 17:26:02'),
	(17, 'ED010E010', 'i', '削除する社員を選択してください。', '1900-01-01', '2999-12-31', '2021-06-01 17:26:02', '2021-06-01 17:26:02'),
	(18, 'RS010E010', 'i', '検索結果：◯件', '1900-01-01', '2999-12-31', '2021-06-01 17:26:02', '2021-06-01 17:26:02'),
	(19, 'RS010E020', 'e', '該当する報告書はありません。', '1900-01-01', '2999-12-31', '2021-06-01 17:26:02', '2021-06-01 17:26:02'),
	(20, 'RS010E030', 'e', '検索条件が不正です。', '1900-01-01', '2999-12-31', '2021-06-01 17:26:02', '2021-06-01 17:26:02');
/*!40000 ALTER TABLE `message` ENABLE KEYS */;

--  テーブル nis.password の構造をダンプしています
DROP TABLE IF EXISTS `password`;
CREATE TABLE IF NOT EXISTS `password` (
  `password_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'パスワードID',
  `password_type` int(1) NOT NULL DEFAULT '1' COMMENT 'パスワード種別',
  `password_body` varchar(20) NOT NULL COMMENT 'パスワード',
  `startdate` date NOT NULL DEFAULT '1900-01-01' COMMENT '適用開始日付',
  `enddate` date NOT NULL DEFAULT '2999-12-31' COMMENT '適用終了日付',
  `createdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '作成日付',
  `updatedate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日付',
  PRIMARY KEY (`password_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- テーブル nis.password: ~0 rows (約) のデータをダンプしています
DELETE FROM `password`;
/*!40000 ALTER TABLE `password` DISABLE KEYS */;
INSERT INTO `password` (`password_id`, `password_type`, `password_body`, `startdate`, `enddate`, `createdate`, `updatedate`) VALUES
	(1, 1, '12345', '1900-01-01', '2999-12-31', '2021-01-26 14:15:56', '2022-01-27 15:03:32');
/*!40000 ALTER TABLE `password` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
