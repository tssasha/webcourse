CREATE DATABASE forum;

CREATE TABLE IF NOT EXISTS users (
    user_login text PRIMARY KEY,
    user_password text CHECK (char_length(user_password) >= 8),
    reg_date date DEFAULT CURRENT_DATE,
    rights text DEFAULT 'Пользователь',
    description text
);

CREATE TABLE IF NOT EXISTS topics (
    topic_no SERIAL PRIMARY KEY,
    topic_name text,
    section_name text NOT NULL
);

CREATE TABLE IF NOT EXISTS messages (
    message_no SERIAL PRIMARY KEY,
    topic_no int REFERENCES topics,
    message text NOT NULL,
    user_login text REFERENCES users,
    time_stamp date DEFAULT CURRENT_DATE
);

CREATE TABLE IF NOT EXISTS files (
    file text PRIMARY KEY,  --path to file
    message_no integer REFERENCES messages
);

DELETE from messages where message_no = 27;

DELETE from files where file = 'path';

DELETE from messages where message_no = 184;
DELETE from topics where topic_name = 'Тестовая тема';
DELETE from users where user_login = 'testUser';
