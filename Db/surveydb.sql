CREATE TABLE user_roles
(
  user_name character varying(50) NOT NULL,
  role_name character varying(50) NOT NULL,
  CONSTRAINT pk_user_roles PRIMARY KEY (user_name, role_name)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE user_roles
  OWNER TO postgres;

CREATE TABLE users
(
  user_name character varying(50) NOT NULL,
  user_pass character varying(50) NOT NULL,
  CONSTRAINT pk_users PRIMARY KEY (user_name)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE users
  OWNER TO postgres;

insert into users (user_name, user_pass) values('hc105',md5('1qazXSW@@'));

insert into users (user_name, user_pass) values ('postgres',md5('1qazXSW@@'));
	
insert into user_roles (user_name, role_name) values('hc105','SurveyAdmin');

insert into user_roles (user_name, role_name) values('hc105','SurveyMember');
	
insert into user_roles (user_name, role_name) values('postgres','SurveyAdmin');