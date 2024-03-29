package by.it_academy.MDK29522.dao.memory;

import by.it_academy.MDK29522.dao.api.IVoteDao;
import by.it_academy.MDK29522.core.dto.VoteDTO;
import by.it_academy.MDK29522.core.dto.VoteDTOIdTime;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class VoteDaoMemory implements IVoteDao {
    private List<VoteDTOIdTime> votes;
    private int id;

    public VoteDaoMemory(List<VoteDTOIdTime> votes) {
        this.votes = votes;
        this.id = votes.size()+1;
    }

    public VoteDaoMemory() {
        votes = new LinkedList<>();
        id = 1;
    }

    @Override
    public void save(VoteDTO voteDTO) {
        votes.add(new VoteDTOIdTime(id,voteDTO, LocalDate.now()));
        id++;
    }

    @Override
    public List<VoteDTOIdTime> get() {
        return votes;
    }

}
