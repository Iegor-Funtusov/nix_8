create table if not exists students
(
    id         int auto_increment primary key,
    first_name varchar(255) null,
    last_name  varchar(255) null
);

create table if not exists courses
(
    id   int auto_increment primary key,
    name varchar(255) not null
);

create table if not exists students_courses
(
    student_id int not null,
    course_id int not null,
    primary key (student_id, course_id),
    foreign key (student_id) references students (id),
    foreign key (course_id) references courses (id)
);