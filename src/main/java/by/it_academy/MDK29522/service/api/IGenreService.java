package by.it_academy.MDK29522.service.api;

import by.it_academy.MDK29522.core.dto.GenreDTO;
import by.it_academy.MDK29522.core.dto.GenreID;

import java.util.List;

public interface IGenreService {
    void save(GenreDTO genreDTO);
    void save(String name);
    List<GenreID> getGenres();
    boolean exist(String name);
    GenreID getNameForId(long id);
    GenreID getIdForName(String name);
    void edit(long id, String name);

    void delete(long id);
}
