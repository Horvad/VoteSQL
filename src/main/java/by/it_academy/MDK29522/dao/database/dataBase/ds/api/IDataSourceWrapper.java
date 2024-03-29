package by.it_academy.MDK29522.dao.database.dataBase.ds.api;

import java.sql.Connection;
import java.sql.SQLException;

public interface IDataSourceWrapper extends AutoCloseable{
    Connection getConnection() throws SQLException;
}
