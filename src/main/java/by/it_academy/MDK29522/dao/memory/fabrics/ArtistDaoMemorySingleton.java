package by.it_academy.MDK29522.dao.memory.fabrics;

import by.it_academy.MDK29522.dao.api.IArtistDao;
import by.it_academy.MDK29522.dao.memory.ArtistDaoMemory;

public class ArtistDaoMemorySingleton {
    private volatile static IArtistDao instance;

    private ArtistDaoMemorySingleton() {
    }

    public static IArtistDao getInstance() {
        if(instance==null){
            synchronized (ArtistDaoMemorySingleton.class){
                if(instance==null){
                    instance = new ArtistDaoMemory();
                }
            }
        }
        return instance;
    }
}
