package by.it_academy.MDK29522.dao.memory;

import by.it_academy.MDK29522.dao.api.IGenreDao;
import by.it_academy.MDK29522.core.dto.GenreDTO;
import by.it_academy.MDK29522.core.dto.GenreID;

import java.util.LinkedList;
import java.util.List;

public class GenreDaoMemory implements IGenreDao {
    private List<GenreID> genres;
    private int id;

    public GenreDaoMemory() {
        genres = new LinkedList<>();
        id = 1;
    }

    public GenreDaoMemory(List<GenreID> genres) {
        this.genres = genres;
        this.id = genres.size()+1;
    }

    @Override
    public void save(GenreDTO genreDTO) {
        genres.add(new GenreID(id,genreDTO));
        id++;
    }

    @Override
    public List<GenreID> getAll() {
        return genres;
    }

    @Override
    public GenreID getById(long id) {
        for(GenreID genreID : genres){
            if(genreID.getId()==id){
                return genreID;
            }
        }
        throw new IllegalArgumentException("There if no genre by that id");
    }

    @Override
    public GenreID getByName(String name) {
        for(GenreID genreID : genres){
            if(genreID.getGenre().getName().equals(name)){
                return genreID;
            }
        }
        throw new IllegalArgumentException("There is bo genre by that name");
    }

    @Override
    public boolean exist(String name) {
        for(GenreID genreID : genres){
            if(genreID.getGenre().getName().equals(name)){
                return true;
            }
        }
        return false;
    }

    @Override
    public void edit(long id, String name) {
        for(GenreID genre : genres){
            if(genre.getId()==id){
                genre.getGenre().setName(name);
                break;
            }
        }
    }

    @Override
    public void delete(long id) {
        for(GenreID genre : genres){
            if(genre.getId()==id){
                genres.remove(genre);
                break;
            }
        }
    }
}
