package by.it_academy.MDK29522.dao.database.dataBase.ds;

import by.it_academy.MDK29522.dao.database.dataBase.ds.api.IDataSourceWrapper;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

public class DataSourceC3P0 implements IDataSourceWrapper {
    private ComboPooledDataSource cpds;

    public DataSourceC3P0() throws PropertyVetoException {
        this.cpds = new ComboPooledDataSource();
        this.cpds.setDriverClass("org.postgresql.Driver");
        this.cpds.setJdbcUrl("jdbc:postgresql://localhost:5432/voteMy");
        this.cpds.setUser("postgres");
        this.cpds.setPassword("postgres");
    }

    @Override
    public Connection getConnection() throws SQLException {
        return this.cpds.getConnection();
    }

    @Override
    public void close() throws Exception {
        this.cpds.close();
    }
}
