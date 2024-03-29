package by.it_academy.MDK29522.service;

import by.it_academy.MDK29522.dao.api.IVoteDao;
import by.it_academy.MDK29522.core.dto.VoteDTO;
import by.it_academy.MDK29522.core.dto.VoteDTOIdTime;
import by.it_academy.MDK29522.service.api.IArtistService;
import by.it_academy.MDK29522.service.api.IGenreService;
import by.it_academy.MDK29522.service.api.IVoteService;

import java.util.List;

public class VoteService implements IVoteService {
    private IGenreService genreService;
    private IArtistService artistService;
    private IVoteDao voteDao;

    public VoteService(IGenreService genreService, IArtistService artistService, IVoteDao voteDao) {
        this.genreService = genreService;
        this.artistService = artistService;
        this.voteDao = voteDao;
    }

    @Override
    public List<VoteDTOIdTime> getVotes() {
        return voteDao.get();
    }

    @Override
    public void saveVote(VoteDTO voteDTO) {
        if(validation(voteDTO)){
            voteDao.save(voteDTO);
        }
    }

    private boolean validation(VoteDTO voteDTO){
        if(voteDTO.getAbout().isBlank())
            return false;
        if(artistService.getNameForId(voteDTO.getArtisVote())==null)
            return false;
        for(long i : voteDTO.getGenreDTOSet()){
            if(genreService.getNameForId(i)==null){
                voteDTO.getGenreDTOSet().remove(i);
            }
        }
        if(voteDTO.getGenreDTOSet().size()>5||voteDTO.getGenreDTOSet().size()<3)
            return false;
        return true;
    }
}
