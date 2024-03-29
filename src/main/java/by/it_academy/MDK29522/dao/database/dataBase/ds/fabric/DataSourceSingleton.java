package by.it_academy.MDK29522.dao.database.dataBase.ds.fabric;

import by.it_academy.MDK29522.dao.database.dataBase.ds.DataSourceC3P0;
import by.it_academy.MDK29522.dao.database.dataBase.ds.api.IDataSourceWrapper;

import java.beans.PropertyVetoException;

public class DataSourceSingleton {
    private static IDataSourceWrapper instance;

    private DataSourceSingleton() {
    }

    public static IDataSourceWrapper getInstance() throws PropertyVetoException {
        if(instance==null){
            synchronized (DataSourceSingleton.class){
                if(instance==null){
                    instance = new DataSourceC3P0();
                }
            }
        }
        return instance;
    }
}
