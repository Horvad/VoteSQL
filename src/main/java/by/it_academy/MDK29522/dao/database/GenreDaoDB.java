package by.it_academy.MDK29522.dao.database;

import by.it_academy.MDK29522.dao.api.IGenreDao;
import by.it_academy.MDK29522.dao.database.dataBase.ds.api.IDataSourceWrapper;
import by.it_academy.MDK29522.core.dto.GenreDTO;
import by.it_academy.MDK29522.core.dto.GenreID;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class GenreDaoDB implements IGenreDao {
    private final IDataSourceWrapper dataSource;
    private final String SQL_GET_BY_NAME = "SELECT id, name FROM app.genre WHERE name = ?;";
    private final String SQL_SAVE = "INSERT INTO app.genre (name) VALUES (?);";
    private final String SQL_GET_ALL = "SELECT id, name from app.genre;";
    private final String SQL_GET_BY_ID = "SELECT id, name FROM app.genre WHERE id = ?;";
    private final String SQL_EXIST_BY_NAME = "SELECT name FROM app.genre WHERE name = ?;";
    private final String SQL_EDIT = "UPDATE app.genre SET name = ? WHERE id = ?;";
    private final String SQL_DELETE = "DELETE FROM app.genre WHERE id = ?;";

    public GenreDaoDB(IDataSourceWrapper dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void save(GenreDTO genreDTO) {
        try (Connection connection = dataSource.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE);
            preparedStatement.setString(1,genreDTO.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<GenreID> getAll() {
        List<GenreID> genres = new LinkedList<>();
        try (Connection connection = dataSource.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                genres.add(new GenreID(id, new GenreDTO(name)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return genres;
    }

    @Override
    public GenreID getById(long id) {
        String name = null;
        try (Connection connection = dataSource.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_BY_ID);
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                name = resultSet.getString("name");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(name==null){
            throw new IllegalArgumentException("С таки id жанра не существует");
        }
        return new GenreID(id,new GenreDTO(name));
    }

    @Override
    public GenreID getByName(String name) {
        long id = -1;
        try (Connection connection = dataSource.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_BY_NAME);
            preparedStatement.setString(1,name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                id = resultSet.getLong("id");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(id == -1){
            throw new IllegalArgumentException("С таким инмем жанра не существует");
        }
        return new GenreID(id, new GenreDTO(name));
    }
    @Override
    public boolean exist(String name) {
        String nameSelect = null;
        try (Connection connection = dataSource.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_EXIST_BY_NAME);
            preparedStatement.setString(1,name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                nameSelect = resultSet.getString("name");
            }
            if(name.equals(nameSelect)){
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void edit(long id, String name) {
        try (Connection connection = dataSource.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_EDIT);
            preparedStatement.setString(1,name);
            preparedStatement.setLong(2,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(long id) {
        try (Connection connection = dataSource.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE);
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
