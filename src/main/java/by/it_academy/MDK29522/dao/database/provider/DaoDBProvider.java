package by.it_academy.MDK29522.dao.database.provider;

import by.it_academy.MDK29522.dao.api.IArtistDao;
import by.it_academy.MDK29522.dao.api.IGenreDao;
import by.it_academy.MDK29522.dao.api.IVoteDao;
import by.it_academy.MDK29522.dao.database.dataBase.fabric.ArtistDaoDBSingleton;
import by.it_academy.MDK29522.dao.database.dataBase.fabric.GenreDaoDBSingleton;
import by.it_academy.MDK29522.dao.database.dataBase.fabric.VoteDaoBDSingleton;
import by.it_academy.MDK29522.dao.database.provider.api.IDaoProvider;

import java.beans.PropertyVetoException;

public class DaoDBProvider implements IDaoProvider {
    @Override
    public IGenreDao getGenreDao() {
        try {
            return GenreDaoDBSingleton.getInstance();
        } catch (PropertyVetoException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public IArtistDao getArtisDao() {
        try {
            return ArtistDaoDBSingleton.getInstance();
        } catch (PropertyVetoException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public IVoteDao getVoteDao() {
        try {
            return VoteDaoBDSingleton.getInstance();
        } catch (PropertyVetoException e) {
            throw new RuntimeException(e);
        }
    }
}
