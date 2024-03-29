package by.it_academy.MDK29522.dao.database;

import by.it_academy.MDK29522.dao.api.IVoteDao;
import by.it_academy.MDK29522.dao.database.dataBase.ds.api.IDataSourceWrapper;
import by.it_academy.MDK29522.core.dto.VoteDTO;
import by.it_academy.MDK29522.core.dto.VoteDTOIdTime;

import java.sql.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class VoteDaoDB implements IVoteDao {
    private final IDataSourceWrapper dataSource;

    private final String SQL_SAVE_VOTE = "INSERT INTO app.vote (about, date, email) VALUES (?,now(),?);";
    private final String SQL_SAVE_VOTE_ARTIST = "INSERT INTO app.vote_artist (vote_id, artist_id) VALUES(?,?);";
    private final String SQL_SAVE_VOTE_GENRE = "INSERT INTO app.vote_genre (vote_id, genre_id) VALUES(?,?);";
    private final String SQL_GET_VOTE = "SELECT id, about, date, email FROM app.vote;";
    private final String SQL_GET_VOTE_ARTIST = "SELECT vote_id, artist_id FROM app.vote_artist WHERE vote_id = ?;";
    private final String SQL_GET_VOTE_GENRES = "SELECT vote_id, genre_id FROM app.vote_genre WHERE vote_id = ?;";

    public VoteDaoDB(IDataSourceWrapper dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void save(VoteDTO voteDTO) {
        long id;
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE_VOTE,
                    Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,voteDTO.getAbout());
            preparedStatement.setString(2,voteDTO.getEmail());
            preparedStatement.executeUpdate();
            ResultSet resultSetForId = preparedStatement.getGeneratedKeys();
            resultSetForId.next();
            id = resultSetForId.getLong(1);
            saveVoteArtist(id,voteDTO);
            saveVoteGenre(id,voteDTO);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void saveVoteArtist(long id, VoteDTO voteDTO){
        try (Connection connection = dataSource.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE_VOTE_ARTIST);
            preparedStatement.setLong(1,id);
            preparedStatement.setLong(2,voteDTO.getArtisVote());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void saveVoteGenre(long id, VoteDTO voteDTO){
        try (Connection connection = dataSource.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE_VOTE_GENRE);
            Set<Long> genresVote = voteDTO.getGenreDTOSet();
            for (Long idGenre : genresVote){
                preparedStatement.setLong(1,id);
                preparedStatement.setLong(2,idGenre);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<VoteDTOIdTime> get() {
        List<VoteDTOIdTime> votes = new LinkedList<>();
        try (Connection connection = dataSource.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_VOTE);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                long id = resultSet.getLong("id");
                String about = resultSet.getString("about");
                LocalDate date = resultSet.getDate("date").toLocalDate();
                String email = resultSet.getString("email");
                long idArtist = getIdArtist(id);
                Set<Long> genres = getVoteGenre(id);
                votes.add(new VoteDTOIdTime(id,new VoteDTO(about,idArtist,genres,email),date));
            }
            return votes;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private long getIdArtist(long idVote){
        try (Connection connection = dataSource.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_VOTE_ARTIST);
            preparedStatement.setLong(1,idVote);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            long idVoteSQL = resultSet.getLong("vote_id");
            if(idVoteSQL==idVote){
                return resultSet.getLong("artist_id");
            } else {
                throw new IllegalArgumentException("Error select vote for artist");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Set<Long> getVoteGenre(Long voteId){
        try (Connection connection = dataSource.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_VOTE_GENRES);
            preparedStatement.setLong(1,voteId);
            ResultSet resultSet = preparedStatement.executeQuery();
            Set<Long> genres = new HashSet<>();
            while (resultSet.next()){
                long idGenres = resultSet.getLong("genre_id");
                genres.add(idGenres);
            }
            if(genres.size()==0){
                throw new IllegalArgumentException("Error vote for genres");
            }
            return genres;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
