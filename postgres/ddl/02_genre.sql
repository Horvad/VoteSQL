CREATE TABLE IF NOT EXISTS app.genre
(
    id bigserial,
    name text NOT NULL,
    CONSTRAINT genres_pkey PRIMARY KEY (id)
)