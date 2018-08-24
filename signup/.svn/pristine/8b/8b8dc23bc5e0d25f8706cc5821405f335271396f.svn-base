-- 活动
create table tb_event (
   id                   varchar(40) not null,
   name                 varchar(200),
   event_time           varchar(1000),
   register_start_time  datetime,
   register_end_time    datetime,
   country              varchar(100),
   province             varchar(100),
   city                 varchar(100),
   address              varchar(255),
   banner               varchar(100),
   introduce            text,
   color                varchar(10),
   org_id               int(11) NOT NULL,
   creator              varchar(40),
   create_time          datetime,
   editor               varchar(40),
   edit_time            datetime,
   logo                 varchar(100),
   acode                varchar(100),
   qrcode               varchar(100),
   layout text,
   ticket_prefix        varchar(10),
   ticket_length        int(8) NOT NULL,
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 创建log表  2018-05-29 13:45 by zhanghaonan
CREATE TABLE tb_log (
  id varchar(40) NOT NULL,
  user_id varchar(40) DEFAULT NULL,
  openid varchar(255) DEFAULT NULL,
  sessionkey varchar(1000) DEFAULT NULL,
  state int DEFAULT NULL,
  indate datetime DEFAULT NULL,
  outdate datetime DEFAULT NULL,
  invite_openid varchar(100) DEFAULT NULL,
  event_id varchar(40) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 创建用户表  2018-05-29 13:45 by zhanghaonan
CREATE TABLE tb_user (
  userid varchar(40) NOT NULL,
  nick_name varchar(100) CHARACTER SET utf8mb4 DEFAULT NULL,
  open_id varchar(255) DEFAULT NULL,
  head_imgurl varchar(255) DEFAULT NULL,
  head_img_circle varchar(255) DEFAULT NULL,
  sex varchar(10) DEFAULT NULL,
  create_time datetime DEFAULT NULL,
  tickets_num varchar(50) DEFAULT NULL,
  share_num int DEFAULT 0,
  restiger_status int DEFAULT NULL,
  qrcode_url varchar(255) DEFAULT NULL,
  event_id varchar(40) NOT NULL,
  PRIMARY KEY (userid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 创建报名表  2018-05-29 13:45 by zhanghaonan
CREATE TABLE tb_sign (
  id varchar(40) NOT NULL,
  event_id varchar(40) NOT NULL,
  open_id varchar(100) DEFAULT NULL,
  invite_openid varchar(100) DEFAULT NULL,
  invite_num int DEFAULT 0,
  invite_enter_times int DEFAULT 0, -- 邀请进入数, 即影响力指数
  create_time datetime ,
  name varchar(255) ,
  cellphone varchar(11) ,
  company varchar(255) ,
  department varchar(255) ,
  position varchar(255) ,
  address varchar(255) ,
  email varchar(255) ,
  telephone varchar(255) ,
  sex varchar(10) DEFAULT NULL,
  country varchar(255) ,
  province varchar(255) ,
  city varchar(255) ,
  photo varchar(255) ,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 创建邀请二维码信息表  2018-05-29 13:45 by zhanghaonan
CREATE TABLE tb_tickets (
  id varchar(40) NOT NULL,
  open_id varchar(100) DEFAULT NULL,
  ticket_num varchar(40) DEFAULT NULL,
  ticket_num_url varchar(255) DEFAULT NULL,
  create_time datetime DEFAULT NULL,
  event_id varchar(40) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 20180709 增加国家、省、市
CREATE TABLE tb_country (
  id varchar(40) NOT NULL,
  english varchar(255),
  name varchar(255),
  area varchar(255),
  abbreviation varchar(255),
  pinyin varchar(255),
  pinyin_short varchar(255),
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE tb_province (
  id varchar(40) NOT NULL,
  name varchar(255) ,
  english varchar(255) ,
  pinyin varchar(255) ,
  pinyin_short varchar(255) ,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE tb_city (
  id varchar(40) NOT NULL,
  name varchar(255) ,
  province_id varchar(40) ,
  english varchar(255) ,
  pinyin varchar(255) ,
  pinyin_short varchar(255) ,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 小程序码的场景
CREATE TABLE tb_scene (
  id varchar(40) NOT NULL,
  event_id varchar(40) NOT NULL,
  open_id varchar(100) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE tb_field (
  id varchar(40) NOT NULL,
  name varchar(255) ,
  type_id int(1) ,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE tb_form_field (
  id varchar(40) NOT NULL,
  event_id varchar(40) ,
  field_id varchar(40) ,
  name varchar(255) ,
  custom int(1) ,
  required int(1) ,
  dictionary varchar(2000) ,
  create_time varchar(32) ,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 分享日志, 供统计分析中的分享数用
CREATE TABLE tb_share_log (
  id varchar(32) NOT NULL,
  open_id varchar(32) DEFAULT NULL,
  event_id varchar(32) NOT NULL,
  share_time datetime DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 门票码的序列号
create table tb_sequence (
   event_id             varchar(40),
   seq                  int(8),
   primary key (event_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
