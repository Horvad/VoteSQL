CREATE TABLE IF NOT EXISTS app.vote
(
    id bigserial,
    about text NOT NULL,
    email text NOT NULL,
    date timestamp without time zone NOT NULL,
    CONSTRAINT vote_pkey PRIMARY KEY (id)
)