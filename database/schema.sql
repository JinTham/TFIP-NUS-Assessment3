use railway;

create table user (
	user_id 	varchar(8) not null,
    CONSTRAINT CHECK (CHARINDEX(' ',user_id ) < 1),
    username 	varchar(100) not null,
    name 		varchar(100),
    constraint user_pk primary key (user_id)
);

create table task (
	task_id 		int not null auto_increment,
    description 	varchar(255),
    priority 		int not null,
    due_date		date,
    user_id			varchar(8) not null,
    constraint task_pk primary key (task_id),
    constraint task_user_fk foreign key (user_id) references user (user_id),
    constraint check (priority between 1 and 3)
);



