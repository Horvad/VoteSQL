package by.it_academy.MDK29522.dao.memory.fabrics;

import by.it_academy.MDK29522.dao.api.IGenreDao;
import by.it_academy.MDK29522.dao.memory.GenreDaoMemory;

public class GenreDaoMemorySingleton {
    private static volatile IGenreDao instance;

    private GenreDaoMemorySingleton() {
    }

    public static IGenreDao getInstance() {
        if(instance==null){
            synchronized (GenreDaoMemorySingleton.class){
                if(instance==null){
                    instance = new GenreDaoMemory();
                }
            }
        }
        return instance;
    }
}
