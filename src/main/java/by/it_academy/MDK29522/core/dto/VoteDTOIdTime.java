package by.it_academy.MDK29522.core.dto;

import java.time.LocalDate;
import java.util.Objects;

public class VoteDTOIdTime {
    private VoteDTO voteDTO;
    private final long id;
    private final LocalDate timeCreate;

    public VoteDTOIdTime(long id, VoteDTO voteDTO, LocalDate timeCreate) {
        this.voteDTO = voteDTO;
        this.id = id;
        this.timeCreate = timeCreate;
    }

    public long getId() {
        return id;
    }

    public VoteDTO getVoteDTO() {
        return voteDTO;
    }

    public void setVoteDTO(VoteDTO voteDTO) {
        this.voteDTO = voteDTO;
    }

    public LocalDate getTimeCreate() {
        return timeCreate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VoteDTOIdTime that = (VoteDTOIdTime) o;
        return id == that.id && Objects.equals(voteDTO, that.voteDTO) && Objects.equals(timeCreate, that.timeCreate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(voteDTO, id, timeCreate);
    }
}
