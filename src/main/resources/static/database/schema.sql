-- Database schema for paper submission system
DROP TABLE IF EXISTS paper_author;
DROP TABLE IF EXISTS account CASCADE;
DROP TABLE IF EXISTS paper;

CREATE TABLE account
(
    id                 VARCHAR NOT NULL PRIMARY KEY,
);

CREATE TABLE paper
(
    id                  VARCHAR NOT NULL PRIMARY KEY,
    title               VARCHAR,
    abstract_summary    VARCHAR,
    filepath            VARCHAR,
    previous_version_id VARCHAR REFERENCES paper
);

CREATE TABLE paper_author
(
    author_id VARCHAR REFERENCES account,
    paper_id  VARCHAR REFERENCES paper
);
