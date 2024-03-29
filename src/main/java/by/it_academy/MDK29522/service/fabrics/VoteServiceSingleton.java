package by.it_academy.MDK29522.service.fabrics;

import by.it_academy.MDK29522.dao.database.provider.ChoiceDaoProvider;
import by.it_academy.MDK29522.service.VoteService;
import by.it_academy.MDK29522.service.api.IVoteService;

public class VoteServiceSingleton {
    private volatile static IVoteService instance;

    private VoteServiceSingleton() {
    }

    public static IVoteService getInstance() {
        if(instance==null){
            synchronized (VoteServiceSingleton.class){
                if(instance==null){
                    instance = new VoteService(
                            GenreServiceSingleton.getInstance(),
                            ArtistServiceSingleton.getInstance(),
                            ChoiceDaoProvider.getInstance().getVoteDao());
                }
            }
        }
        return instance;
    }
}
