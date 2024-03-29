package by.it_academy.MDK29522.dao.database.dataBase.fabric;

import by.it_academy.MDK29522.dao.database.GenreDaoDB;
import by.it_academy.MDK29522.dao.api.IGenreDao;
import by.it_academy.MDK29522.dao.database.dataBase.ds.fabric.DataSourceSingleton;

import java.beans.PropertyVetoException;

public class GenreDaoDBSingleton {
    private static volatile IGenreDao instance;
    private  GenreDaoDBSingleton(){}

    public static IGenreDao getInstance() throws PropertyVetoException {
        if(instance==null){
            synchronized (GenreDaoDBSingleton.class){
                if(instance==null){
                    instance = new GenreDaoDB(DataSourceSingleton.getInstance());
                }
            }
        }
        return instance;
    }
}
