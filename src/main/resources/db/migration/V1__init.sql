CREATE TABLE `district_trade_info` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '流水 id',
  `trade_date` DATE NOT NULL COMMENT '日期',
  `district` VARCHAR(45) NOT NULL COMMENT '行政区划，如 南宁市青秀区',
  `number` INT NOT NULL DEFAULT 0 COMMENT '套数',
  `area` FLOAT NOT NULL DEFAULT 0 COMMENT '面积',
  `category` VARCHAR(45) NOT NULL COMMENT '类型 （新建/存量）',
  PRIMARY KEY (`id`))
COMMENT = '按地区统计每日交易数据';

CREATE TABLE `type_trade_info` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '流水 id',
  `trade_date` DATE NOT NULL COMMENT '日期',
  `city` VARCHAR(45) NOT NULL COMMENT '城市，如 南宁市',
  `type` VARCHAR(45) NOT NULL COMMENT '用途',
  `number` INT NOT NULL DEFAULT 0 COMMENT '套数',
  `area` FLOAT NOT NULL DEFAULT 0 COMMENT '面积',
  `category` VARCHAR(45) NOT NULL COMMENT '类型 （新建/存量）',
  PRIMARY KEY (`id`))
COMMENT = '按用途统计每日交易数据';