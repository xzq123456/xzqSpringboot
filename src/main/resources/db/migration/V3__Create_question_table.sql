create table question
(
	id int auto_increment,
	title varchar(255),
	description Text,
	get_create bigint,
	get_modified bigint,
	creater int,
	comment_count int default 0,
	view_count int default 0,
	like_count int default 0,
	tag varchar(255),
	constraint question_pk
		primary key (id)
);