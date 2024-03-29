package by.it_academy.MDK29522.dao.database.dataBase.fabric;

import by.it_academy.MDK29522.dao.api.IVoteDao;
import by.it_academy.MDK29522.dao.database.VoteDaoDB;
import by.it_academy.MDK29522.dao.database.dataBase.ds.fabric.DataSourceSingleton;

import java.beans.PropertyVetoException;

public class VoteDaoBDSingleton {
    private static volatile IVoteDao instance;
    private VoteDaoBDSingleton(){}

    public static IVoteDao getInstance() throws PropertyVetoException {
        if(instance==null){
            synchronized (VoteDaoBDSingleton.class){
                if(instance==null){
                    instance = new VoteDaoDB(DataSourceSingleton.getInstance());
                }
            }
        }
        return instance;
    }
}
