create table question
(
	ID int AUTO_INCREMENT PRIMARY KEY,
	title varchar(50),
	description text,
	gmt_create bigint,
	gmt_modified bigint,
	creator int default 0,
	comment_count int default 0,
	view_count int default 0,
	like_count int default 0,
	tag varchar(256)
);

