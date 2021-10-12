DROP TABLE member cascade constraint;
DROP TABLE errand cascade constraint;
DROP TABLE apply cascade constraint;

DROP SEQUENCE member_seq;
DROP SEQUENCE errand_seq;
DROP SEQUENCE apply_seq;

CREATE SEQUENCE member_seq;
CREATE TABLE member (
       member_id		NUMBER(10)  PRIMARY KEY,
       email			VARCHAR2(50) NOT NULL unique,
       password     	VARCHAR2(20) NOT NULL,
       password2    	VARCHAR2(20) NOT NULL,
       nickname			VARCHAR2(20) NOT NULL UNIQUE,
       age				NUMBER(10) NOT NULL,
       gender			CHAR NOT NULL,
       phone			VARCHAR2(20) NOT NULL UNIQUE,
       address			VARCHAR2(100) NOT NULL,
       specialty1		VARCHAR2(50) NOT NULL,
       specialty2		VARCHAR2(50), 
       specialty3		VARCHAR2(50),
       picture			VARCHAR2(50),
       score			NUMBER(10) DEFAULT 0,
       member_status 	NUMBER(2) DEFAULT 0       
);

CREATE SEQUENCE errand_seq;
CREATE TABLE errand (
	   errand_id		NUMBER(10) PRIMARY KEY,
	   writer 			NUMBER(10) NOT NULL,
<<<<<<< HEAD
=======
	   pay				NUMBER(10) NOT NULL,
>>>>>>> c2c33aaa3ae721d4cda296d3dd4b658192974d3b
	   created_at		DATE NOT NULL,
	   title			VARCHAR2(100) NOT NULL,
	   content 			VARCHAR2(1000) NOT NULL,	  
	   category			VARCHAR2(50) NOT NULL,
	   req_location		VARCHAR2(100) NOT NULL,
	   req_date			DATE NOT NULL,
	   errand_status	NUMBER(2) DEFAULT 0
);

CREATE SEQUENCE apply_seq;
CREATE TABLE apply (	
	   apply_id 		NUMBER(10) PRIMARY KEY,
	   errand			NUMBER(10) NOT NULL,	   
	   applicant		NUMBER(10) NOT NULL,
	   message 			VARCHAR2(500) NOT NULL,
	   created_at		DATE NOT NULL, 
	   match_status		NUMBER(2) DEFAULT 0	   
);

ALTER TABLE errand ADD FOREIGN KEY (writer) REFERENCES MEMBER (member_id);
ALTER TABLE apply ADD FOREIGN KEY (applicant) REFERENCES MEMBER (member_id);
ALTER TABLE apply ADD FOREIGN KEY (errand) REFERENCES errand (errand_id);

