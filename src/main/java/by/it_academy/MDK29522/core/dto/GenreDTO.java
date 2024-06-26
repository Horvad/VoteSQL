package by.it_academy.MDK29522.core.dto;

import java.util.Objects;

public class GenreDTO {
    private String name;

    public GenreDTO(String name) {
        this.name = name;
    }

    public GenreDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenreDTO genreDTO = (GenreDTO) o;
        return Objects.equals(name, genreDTO.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
