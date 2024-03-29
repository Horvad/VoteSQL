package by.it_academy.MDK29522.core.dto;

import java.util.Objects;

public class GenreID {
    private final long id;
    private GenreDTO genre;

    public GenreID(long id, GenreDTO genre) {
        this.id = id;
        this.genre = genre;
    }

    public GenreID(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public GenreDTO getGenre() {
        return genre;
    }

    public void setGenre(GenreDTO genre) {
        this.genre = genre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenreID genreID = (GenreID) o;
        return id == genreID.id && Objects.equals(genre, genreID.genre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, genre);
    }
}
