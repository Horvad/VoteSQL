package by.it_academy.MDK29522.dao.api;

import by.it_academy.MDK29522.core.dto.VoteDTO;
import by.it_academy.MDK29522.core.dto.VoteDTOIdTime;

import java.util.List;

public interface IVoteDao {
    void save(VoteDTO voteDTO);
    List<VoteDTOIdTime> get();
}
