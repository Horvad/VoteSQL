package by.it_academy.MDK29522.core.dto;

import java.util.Objects;
import java.util.Set;

public class VoteDTO {
    private String about;
    private long artisVote;
    private Set<Long> genreDTOSet;
    private String email;

    public VoteDTO() {
    }

    public VoteDTO(String about, long artisVote, Set<Long> genreDTOSet, String email) {
        this.about = about;
        this.artisVote = artisVote;
        this.genreDTOSet = genreDTOSet;
        this.email = email;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public long getArtisVote() {
        return artisVote;
    }

    public void setArtisVote(long artisVote) {
        this.artisVote = artisVote;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Long> getGenreDTOSet() {
        return genreDTOSet;
    }

    public void setGenreDTOSet(Set<Long> genreDTOSet) {
        this.genreDTOSet = genreDTOSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VoteDTO voteDTO = (VoteDTO) o;
        return artisVote == voteDTO.artisVote && Objects.equals(about, voteDTO.about) && Objects.equals(genreDTOSet, voteDTO.genreDTOSet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(about, artisVote, genreDTOSet);
    }
}
