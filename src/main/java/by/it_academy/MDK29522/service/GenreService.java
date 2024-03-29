package by.it_academy.MDK29522.service;

import by.it_academy.MDK29522.dao.api.IGenreDao;
import by.it_academy.MDK29522.core.dto.GenreDTO;
import by.it_academy.MDK29522.core.dto.GenreID;
import by.it_academy.MDK29522.service.api.IGenreService;

import java.util.List;

public class GenreService implements IGenreService {
    private IGenreDao genreDao;
    public GenreService(IGenreDao genreDao) {
        this.genreDao = genreDao;
    }

    @Override
    public void save(GenreDTO genreDTO) {
        if(validation(genreDTO.getName())){
            genreDao.save(genreDTO);
        }
    }

    @Override
    public void save(String name) {
        if(validation(name)){
            genreDao.save(new GenreDTO(name));
        }
    }

    @Override
    public List<GenreID> getGenres() {
        return genreDao.getAll();
    }

    @Override
    public boolean exist(String name) {
        return genreDao.exist(name);
    }

    @Override
    public GenreID getNameForId(long id) {
        return genreDao.getById(id);
    }

    @Override
    public GenreID getIdForName(String name) {
        return genreDao.getByName(name);
    }

    @Override
    public void edit(long id, String name) {
        validation(name);
        genreDao.edit(id,name);
    }

    @Override
    public void delete(long id) {
        genreDao.delete(id);
    }

    private boolean validation(String name){
        if(name.isBlank()){
            throw new IllegalArgumentException("Жанр не может быть пустым");
        }
        if(genreDao.exist(name)){
            throw new IllegalArgumentException("Жанр "+name+" не должен повторяться");
        }
        return true;
    }
}
