package by.it_academy.MDK29522.dao.memory;

import by.it_academy.MDK29522.dao.api.IArtistDao;
import by.it_academy.MDK29522.core.dto.ArtistDTO;
import by.it_academy.MDK29522.core.dto.ArtistID;

import java.util.LinkedList;
import java.util.List;

public class ArtistDaoMemory implements IArtistDao {
    private List<ArtistID> artists;
    private int id;

    public ArtistDaoMemory(List<ArtistID> artists) {
        this.artists = artists;
        this.id = artists.size()+1;
    }

    public ArtistDaoMemory() {
        artists = new LinkedList<>();
        id = 1;
    }

    @Override
    public void save(ArtistDTO artistDTO) {
        artists.add(new ArtistID(id,artistDTO));
        id++;
    }

    @Override
    public List<ArtistID> getAll() {
        return artists;
    }

    @Override
    public ArtistID getById(long id) {
        for(ArtistID artistID : artists){
            if(artistID.getId()==id){
                return artistID;
            }
        }
        throw new IllegalArgumentException("There is no artist by that id");
    }

    @Override
    public ArtistID getByName(String name) {
        for(ArtistID artistID : artists){
            if(artistID.getArtist().getName().equals(name)){
                return artistID;
            }
        }
        throw new IllegalArgumentException("There is no artist by that name");
    }

    @Override
    public boolean exist(String name) {
        for(ArtistID artistID : artists){
            if(artistID.getArtist().getName().equals(name)){
                return true;
            }
        }
        return false;
    }

    @Override
    public void edit(long id, String name) {
        for(ArtistID artist : artists){
            if(artist.getId()==id){
                artist.getArtist().setName(name);
                break;
            }
        }
    }

    @Override
    public void delete(long id) {
        for(ArtistID artist : artists){
            if(artist.getId()==id){
                artists.remove(artist);
                break;
            }
        }
    }
}
