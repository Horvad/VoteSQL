package by.it_academy.MDK29522.service;

import by.it_academy.MDK29522.core.dto.*;
import by.it_academy.MDK29522.dto.*;
import by.it_academy.MDK29522.service.api.IArtistService;
import by.it_academy.MDK29522.service.api.IGenreService;
import by.it_academy.MDK29522.service.api.IStatisticService;
import by.it_academy.MDK29522.service.api.IVoteService;
import by.it_academy.MDK29522.util.comparator.ComparatorArtistStatistic;
import by.it_academy.MDK29522.util.comparator.ComparatorGenreStatistic;
import by.it_academy.MDK29522.util.comparator.ComparatorVoteStatistic;

import java.util.*;

public class StatisticService implements IStatisticService {
    IVoteService voteService;
    IGenreService genreService;
    IArtistService artistService;

    public StatisticService(IVoteService voteService, IGenreService genreService, IArtistService artistService) {
        this.voteService = voteService;
        this.genreService = genreService;
        this.artistService = artistService;
    }

    @Override
    public List<StatisticDTOArtists> getArtists() {
        List<VoteDTOIdTime> votes = voteService.getVotes();
        List<StatisticDTOArtists> statisticArtists = new LinkedList<>();
        List<ArtistID> artists = artistService.getArtists();
        for(ArtistID artistID : artists){
            statisticArtists.add(new StatisticDTOArtists(artistID.getArtist().getName(), artistID.getId()));
        }
        for(VoteDTOIdTime vote : votes){
            for(StatisticDTOArtists statisticArtist : statisticArtists){
                if(vote.getVoteDTO().getArtisVote()==statisticArtist.getIdArtists()){
                    statisticArtist.addCount();
                    break;
                }
            }
        }
        Collections.sort(statisticArtists, new ComparatorArtistStatistic());
        return statisticArtists;
    }

    @Override
    public List<StatisticDTOGenres> getGenres() {
        List<VoteDTOIdTime> votes = voteService.getVotes();
        List<StatisticDTOGenres> genres = new LinkedList<>();
        List<GenreID> genreIDS = genreService.getGenres();
        for(GenreID genre : genreIDS){
            genres.add(new StatisticDTOGenres(genre.getGenre().getName(),genre.getId()));
        }
        for(VoteDTOIdTime vote : votes){
            for(long idGenre : vote.getVoteDTO().getGenreDTOSet()){
                for(StatisticDTOGenres genreStat : genres){
                    if(idGenre==genreStat.getIdGenre()){
                        genreStat.addCount();
                        break;
                    }
                }
            }
        }
        Collections.sort(genres, new ComparatorGenreStatistic());
        return genres;
    }

    @Override
    public List<VoteDTOIdTime> getVotes() {
        List<VoteDTOIdTime> votes = new LinkedList<>();
        List<VoteDTOIdTime> oldVotes = voteService.getVotes();
        for(VoteDTOIdTime vote : oldVotes){
            votes.add(vote);
        }
        Collections.sort(votes, new ComparatorVoteStatistic());
        return votes;
    }
}
