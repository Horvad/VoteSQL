package by.it_academy.MDK29522.dao.api;

import by.it_academy.MDK29522.core.dto.ArtistDTO;
import by.it_academy.MDK29522.core.dto.ArtistID;

import java.util.List;

public interface IArtistDao {
    void save(ArtistDTO artistDTO);
    List<ArtistID> getAll();
    ArtistID getById(long id);
    ArtistID getByName(String name);
    boolean exist(String name);

    void edit(long id, String name);

    void delete(long id);
}
