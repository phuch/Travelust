
create table Users
(
    user_id int not null primary key auto_increment,
    fname varchar(50) not null,
    lname varchar(50) not null,
    dateOfBirth date,
    email varchar(50) not null,
    validStartTime date,
    validEndTime date,
    user_status boolean,
    user_active boolean,
    salt varchar(255),
    user_hash varchar(255),
    profilepicture_path varchar(500),
    token varchar(255),
    unique(user_id)
);

create table Journal
(
    journal_id int not null primary key auto_increment,
    owner_id int,
    title varchar(30),
    location varchar(100),
    description varchar(255),
    coverpic_path varchar(255),
    constraint fk_ownerMapping foreign key (owner_id) 
    references Users(user_id),
    unique(journal_id)
);

create table Picture
(
    picture_id int not null primary key auto_increment,
    content_id int,
    filepath varchar(500) not null,
    unique(picture_id),
    constraint fk_contentMapping foreign key (content_id) 
    references Content(journal_id)
);


create table Content
(
    content_id int not null primary key auto_increment,
    journal_id int,
    text_description varchar(1000),
    content_type enum('TEXT', 'PICTURE', 'VIDEO', 'VOICE', 'COMMENT'),
    unique(content_id),
    constraint fk_journalMapping foreign key (journal_id) 
    references Journal(journal_id)
);


create table Subscribe
(
    id int not null primary key auto_increment,
    user_id int,
    journal_id int,
    unique(id),
    constraint fk_subscriber foreign key (user_id)
    references Users(user_id),
    constraint fk_journalSubbed foreign key (journal_id)
    references Journal(journal_id)
);


create table Likes
(
    id int not null primary key auto_increment,
    user_id int,
    content_id int,
    unique(id),
    constraint fk_liker foreign key (user_id)
    references Users(user_id),
    constraint fk_liked foreign key (content_id)
    references Content(content_id)
);
