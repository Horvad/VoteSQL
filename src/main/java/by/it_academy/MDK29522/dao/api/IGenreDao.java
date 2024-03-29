package by.it_academy.MDK29522.dao.api;

import by.it_academy.MDK29522.core.dto.GenreDTO;
import by.it_academy.MDK29522.core.dto.GenreID;

import java.util.List;

public interface IGenreDao {
    void save(GenreDTO genreDTO);
    List<GenreID> getAll();
    GenreID getById(long id);
    GenreID getByName(String name);
    boolean exist(String name);
    void edit(long id, String name);

    void delete(long id);
}
