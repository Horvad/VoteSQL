package by.it_academy.MDK29522.dao.database.provider;

import by.it_academy.MDK29522.dao.api.IArtistDao;
import by.it_academy.MDK29522.dao.api.IGenreDao;
import by.it_academy.MDK29522.dao.api.IVoteDao;
import by.it_academy.MDK29522.dao.database.provider.api.IDaoProvider;

public class ChoiceDaoProvider implements IDaoProvider{
    private boolean useBD = true;

    private static volatile ChoiceDaoProvider instance;
    private IDaoProvider daoProvider;

    private ChoiceDaoProvider() {
        if(useBD){
            this.daoProvider = new DaoDBProvider();
        } else {
            this.daoProvider = new DaoMemoryProvider();
        }
    }


    @Override
    public IGenreDao getGenreDao() {
        return daoProvider.getGenreDao();
    }

    @Override
    public IArtistDao getArtisDao() {
        return daoProvider.getArtisDao();
    }

    @Override
    public IVoteDao getVoteDao() {
        return daoProvider.getVoteDao();
    }

    public static IDaoProvider getInstance(){
        if(instance==null){
            synchronized (ChoiceDaoProvider.class){
                if(instance==null){
                    instance = new ChoiceDaoProvider();
                }
            }
        }
        return instance;
    }
}
