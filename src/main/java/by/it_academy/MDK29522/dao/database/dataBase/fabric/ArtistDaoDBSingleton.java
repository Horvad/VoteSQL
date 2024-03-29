package by.it_academy.MDK29522.dao.database.dataBase.fabric;

import by.it_academy.MDK29522.dao.api.IArtistDao;
import by.it_academy.MDK29522.dao.database.ArtistDaoDB;
import by.it_academy.MDK29522.dao.database.dataBase.ds.fabric.DataSourceSingleton;

import java.beans.PropertyVetoException;

public class ArtistDaoDBSingleton {
    private static volatile IArtistDao instance;

    private ArtistDaoDBSingleton() {
    }

    public static IArtistDao getInstance() throws PropertyVetoException {
        if(instance==null){
            synchronized (ArtistDaoDBSingleton.class){
                if(instance==null){
                    instance = new ArtistDaoDB(DataSourceSingleton.getInstance());
                }
            }
        }
        return instance;
    }
}
