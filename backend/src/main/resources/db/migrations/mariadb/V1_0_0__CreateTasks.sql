CREATE TABLE tasks
(
    id          VARCHAR(36) not null,
    title       VARCHAR(100) not null,
    description VARCHAR(1000) not null,
    dueDate     TIMESTAMP,

    PRIMARY KEY (id)
);