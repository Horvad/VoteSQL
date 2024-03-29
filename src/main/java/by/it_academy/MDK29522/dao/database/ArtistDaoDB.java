package by.it_academy.MDK29522.dao.database;

import by.it_academy.MDK29522.dao.api.IArtistDao;
import by.it_academy.MDK29522.dao.database.dataBase.ds.api.IDataSourceWrapper;
import by.it_academy.MDK29522.core.dto.ArtistDTO;
import by.it_academy.MDK29522.core.dto.ArtistID;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ArtistDaoDB implements IArtistDao {

    private final IDataSourceWrapper wrapper;

    private final String GET = "SELECT id, name FROM app.artist;";
    private final String ADD_SQL = "INSERT INTO app.artist (name) VALUES (?);";
    private final String GET_BY_ID = "SELECT id, name FROM app.artist WHERE id = ?;";
    private final String GET_BY_NAME = "SELECT id, name FROM app.artist WHERE name = ?;";


    public ArtistDaoDB(IDataSourceWrapper wrapper) {
        this.wrapper = wrapper;
    }

    @Override
    public void save(ArtistDTO artistDTO) {
        try(Connection conn = this.wrapper.getConnection()) {
            PreparedStatement preparedStatement = conn.prepareStatement(ADD_SQL);
            preparedStatement.setString(1,artistDTO.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ArtistID> getAll() {
        List<ArtistID> artists = new LinkedList<>();
        try (Connection connection = wrapper.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(GET);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                artists.add(new ArtistID(id,new ArtistDTO(name)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return artists;
    }

    @Override
    public ArtistID getById(long id) {
        String name = null;
        try(Connection connection = wrapper.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID);
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                name = resultSet.getString("name");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(name==null){
            throw new IllegalArgumentException("с данным id артиста не существует");
        }
        return new ArtistID(id, new ArtistDTO(name));
    }

    @Override
    public ArtistID getByName(String name) {
        long id = -1;
        try (Connection connection = wrapper.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_NAME);
            preparedStatement.setString(1,name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                id = resultSet.getLong("id");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(id==-1){
            throw new IllegalArgumentException("С таким именем артиста не существует");
        }
        return new ArtistID(id,new ArtistDTO(name));
    }

    @Override
    public boolean exist(String name) {
        long id  = -1;
        try (Connection connection = wrapper.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_NAME);
            preparedStatement.setString(1,name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet!=null){
            while (resultSet.next()){
                id = resultSet.getLong("id");
            }}
            if(id!=-1){
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private final String SQL_EDIT = "UPDATE app.artist SET name = ? WHERE id = ?;";
    @Override
    public void edit(long id, String name) {
        try (Connection connection = wrapper.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_EDIT);
            preparedStatement.setString(1,name);
            preparedStatement.setLong(2,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private final String SQL_DELETE = "DELETE FROM app.artist WHERE id = ?;";
    @Override
    public void delete(long id) {
        try (Connection connection = wrapper.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE);
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
