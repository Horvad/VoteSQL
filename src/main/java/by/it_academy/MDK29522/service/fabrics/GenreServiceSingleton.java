package by.it_academy.MDK29522.service.fabrics;

import by.it_academy.MDK29522.dao.database.provider.ChoiceDaoProvider;
import by.it_academy.MDK29522.service.GenreService;
import by.it_academy.MDK29522.service.api.IGenreService;

public class GenreServiceSingleton {
    private static volatile IGenreService instance;

    private GenreServiceSingleton() {
    }

    public static IGenreService getInstance() {
        if(instance==null){
            synchronized (GenreServiceSingleton.class){
                if(instance==null){
                    instance = new GenreService(ChoiceDaoProvider.getInstance().getGenreDao());
                }
            }
        }
        return instance;
    }
}
