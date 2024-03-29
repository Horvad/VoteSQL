CREATE TABLE IF NOT EXISTS app.vote_genre
(
    genre_id bigint,
    vote_id bigint,
    CONSTRAINT vote_genres_genre_id_vote_id_key UNIQUE (genre_id,vote_id)
        INCLUDE(vote_id),
    CONSTRAINT vote_genres_genre_id_fkey FOREIGN KEY (genre_id)
        REFERENCES app.genre (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT vote_genres_vote_id_fkey FOREIGN KEY (vote_id)
        REFERENCES app.vote (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)