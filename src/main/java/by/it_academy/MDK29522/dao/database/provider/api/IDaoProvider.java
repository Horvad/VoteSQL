package by.it_academy.MDK29522.dao.database.provider.api;

import by.it_academy.MDK29522.dao.api.IArtistDao;
import by.it_academy.MDK29522.dao.api.IGenreDao;
import by.it_academy.MDK29522.dao.api.IVoteDao;

public interface IDaoProvider {
    IGenreDao getGenreDao();
    IArtistDao getArtisDao();
    IVoteDao getVoteDao();
}
