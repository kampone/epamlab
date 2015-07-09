CREATE TABLE Authors
  (
    AUTHOR_ID NUMBER (20) NOT NULL ,
    AUTHOR_NAME NVARCHAR2 (30) NOT NULL ,
    EXPIRED TIMESTAMP WITH LOCAL TIME ZONE NOT NULL
  ) ;
ALTER TABLE Authors ADD CONSTRAINT Authors_PK PRIMARY KEY ( AUTHOR_ID ) ;

CREATE TABLE Comments
  (
    COMMNET_ID NUMBER (20) NOT NULL ,
    NEWS_ID    NUMBER (20) NOT NULL ,
    COMMENT_TEXT NVARCHAR2 (100) NOT NULL ,
    CREATION_DATE TIMESTAMP WITH LOCAL TIME ZONE NOT NULL
  ) ;
ALTER TABLE Comments ADD CONSTRAINT Comments_PK PRIMARY KEY ( COMMENT_ID ) ;

CREATE TABLE News
  (
    NEWS_ID NUMBER (20) NOT NULL ,
    TITLE NVARCHAR2 (30) NOT NULL ,
    SHORT_TEXT NVARCHAR2 (100) NOT NULL ,
    FULL_TEXT NVARCHAR2 (2000) NOT NULL ,
    CREATION_DATE     TIMESTAMP WITH LOCAL TIME ZONE NOT NULL ,
    MODIFICATION_DATE DATE NOT NULL
  ) ;
ALTER TABLE News ADD CONSTRAINT News_PK PRIMARY KEY ( NEWS_ID ) ;

CREATE TABLE News_Authors
  (
    NEWS_ID   NUMBER (20) NOT NULL ,
    AUTHOR_ID NUMBER (20) NOT NULL
  ) ;

CREATE TABLE News_Tags
  (
    NEWS_ID NUMBER (20) NOT NULL ,
    TAG_ID  NUMBER (20) NOT NULL
  ) ;

CREATE TABLE Roles
  (
    USER_ID   NUMBER (20) NOT NULL ,
    ROLE_NAME VARCHAR2 (50) NOT NULL
  ) ;

CREATE TABLE Tags
  (
    TAG_ID NUMBER (20) NOT NULL ,
    TAG_NAME NVARCHAR2 (30) NOT NULL
  ) ;
ALTER TABLE Tags ADD CONSTRAINT Tag_PK PRIMARY KEY ( TAG_ID ) ;

CREATE TABLE Users
  (
    USER_ID NUMBER (20) NOT NULL ,
    USER_NAME NVARCHAR2 (50) NOT NULL ,
    LOGIN    VARCHAR2 (30) NOT NULL ,
    PASSWORD VARCHAR2 (30) NOT NULL
  ) ;
ALTER TABLE Users ADD CONSTRAINT User_PK PRIMARY KEY ( USER_ID ) ;

ALTER TABLE Comments ADD CONSTRAINT Comments_News_FK FOREIGN KEY ( NEWS_ID ) REFERENCES News ( NEWS_ID ) ;

ALTER TABLE News_Authors ADD CONSTRAINT News_Authors_Authors_FK FOREIGN KEY ( AUTHOR_ID ) REFERENCES Authors ( AUTHOR_ID ) ;

ALTER TABLE News_Authors ADD CONSTRAINT News_Authors_News_FK FOREIGN KEY ( NEWS_ID ) REFERENCES News ( NEWS_ID );

ALTER TABLE News_Tags ADD CONSTRAINT News_Tag_News_FK FOREIGN KEY ( NEWS_ID ) REFERENCES News ( NEWS_ID );

ALTER TABLE News_Tags ADD CONSTRAINT News_Tag_Tag_FK FOREIGN KEY ( TAG_ID ) REFERENCES Tags ( TAG_ID ) ;

ALTER TABLE Roles ADD CONSTRAINT Roles_User_FK FOREIGN KEY ( USER_ID ) REFERENCES Users ( USER_ID ) ;

CREATE SEQUENCE Authors_AUTHOR_ID_SEQ START WITH 1 NOCACHE ORDER ;

CREATE SEQUENCE Comments_COMMNET_ID_SEQ START WITH 1 NOCACHE ORDER ;

CREATE SEQUENCE News_NEWS_ID_SEQ START WITH 1 NOCACHE ORDER ;

CREATE SEQUENCE Tags_TAG_ID_SEQ START WITH 1 NOCACHE ORDER ;

CREATE SEQUENCE Users_USER_ID_SEQ START WITH 1 NOCACHE ORDER ;