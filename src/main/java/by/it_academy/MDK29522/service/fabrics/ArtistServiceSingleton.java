package by.it_academy.MDK29522.service.fabrics;

import by.it_academy.MDK29522.dao.database.provider.ChoiceDaoProvider;
import by.it_academy.MDK29522.service.ArtistService;
import by.it_academy.MDK29522.service.api.IArtistService;

public class ArtistServiceSingleton {
    private static volatile IArtistService instance;

    private ArtistServiceSingleton() {
    }

    public static IArtistService getInstance() {
        if(instance==null){
            synchronized (ArtistServiceSingleton.class){
                if(instance==null){
                    instance = new ArtistService(ChoiceDaoProvider.getInstance().getArtisDao());
                }
            }
        }
        return instance;
    }
}
