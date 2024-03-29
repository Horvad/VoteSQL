package by.it_academy.MDK29522.core.dto;

public class StatisticDTOArtists {
    private String name;
    private long idArtists;
    private int count;

    public StatisticDTOArtists(String name, long idArtists) {
        this.name = name;
        this.idArtists = idArtists;
        this.count = 0;
    }

    public StatisticDTOArtists(String name, int idArtists, int count) {
        this.name = name;
        this.idArtists = idArtists;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public long getIdArtists() {
        return idArtists;
    }

    public void setIdArtists(long idArtists) {
        this.idArtists = idArtists;
    }

    public void addCount(){
        this.count++;
    }

    public void removeCount(){
        this.count = 0;
    }
}
