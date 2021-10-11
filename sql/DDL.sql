DROP TABLE member cascade constraint;
DROP TABLE errand cascade constraint;
DROP TABLE apply cascade constraint;
DROP TABLE errand_apply cascade constraint;

DROP SEQUENCE member_seq;
DROP SEQUENCE errand_seq;
DROP SEQUENCE apply_seq;
DROP SEQUENCE errand_apply_seq;

CREATE SEQUENCE member_seq;
CREATE TABLE member (
       member_id		NUMBER(10)  PRIMARY KEY,
       eamil			VARCHAR2(50) NOT NULL unique,
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
       member_status 	CHAR DEFAULT 0       
);

CREATE SEQUENCE errand_seq;
CREATE TABLE errand (
	   errand_id		NUMBER(10) PRIMARY KEY,
	   writer 			NUMBER(10) NOT NULL,
	   created_at		DATE NOT NULL,
	   title			VARCHAR2(100) NOT NULL,
	   content 			VARCHAR2(1000) NOT NULL,	  
	   category			VARCHAR2(50) NOT NULL,
	   req_location		VARCHAR2(100) NOT NULL,
	   req_time			DATE NOT NULL,
	   errand_status	NUMBER(5)
);

CREATE SEQUENCE apply_seq;
CREATE TABLE apply (	
	   apply_id 		NUMBER(10) PRIMARY KEY,
	   applicant		NUMBER(10) NOT NULL,
	   message 			VARCHAR2(500) NOT NULL 
);

CREATE SEQUENCE errand_apply_seq;
CREATE TABLE errand_apply (
		errand_apply_id	NUMBER(10),
		errand_id		NUMBER(10),
		apply_id		NUMBER(10),
		matching		CHAR
);

ALTER TABLE errand ADD FOREIGN KEY (writer) REFERENCES MEMBER (member_id);
ALTER TABLE errand_apply ADD FOREIGN KEY (errand_id) REFERENCES errand (errand_id);
ALTER TABLE errand_apply ADD FOREIGN KEY (apply_id) REFERENCES apply (apply_id);
ALTER TABLE apply ADD FOREIGN KEY (applicant) REFERENCES MEMBER (member_id);
