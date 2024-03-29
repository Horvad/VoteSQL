package by.it_academy.MDK29522.dao.database.provider;

import by.it_academy.MDK29522.dao.api.IArtistDao;
import by.it_academy.MDK29522.dao.api.IGenreDao;
import by.it_academy.MDK29522.dao.api.IVoteDao;
import by.it_academy.MDK29522.dao.memory.fabrics.VoteDaoMemorySingleton;
import by.it_academy.MDK29522.dao.memory.fabrics.ArtistDaoMemorySingleton;
import by.it_academy.MDK29522.dao.memory.fabrics.GenreDaoMemorySingleton;
import by.it_academy.MDK29522.dao.database.provider.api.IDaoProvider;

public class DaoMemoryProvider implements IDaoProvider {
    @Override
    public IGenreDao getGenreDao() {
        return GenreDaoMemorySingleton.getInstance();
    }

    @Override
    public IArtistDao getArtisDao() {
        return ArtistDaoMemorySingleton.getInstance();
    }

    @Override
    public IVoteDao getVoteDao() {
        return VoteDaoMemorySingleton.getInstance();
    }
}
