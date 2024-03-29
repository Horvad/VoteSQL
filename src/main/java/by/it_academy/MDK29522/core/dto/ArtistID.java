package by.it_academy.MDK29522.core.dto;

import java.util.Objects;

public class ArtistID {
    private final long id;
    private ArtistDTO artist;

    public ArtistID(long id, ArtistDTO artist) {
        this.id = id;
        this.artist = artist;
    }

    public long getId() {
        return id;
    }

    public ArtistDTO getArtist() {
        return artist;
    }

    public void setArtist(ArtistDTO artist) {
        this.artist = artist;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArtistID artistID = (ArtistID) o;
        return id == artistID.id && Objects.equals(artist, artistID.artist);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, artist);
    }
}
