package by.it_academy.MDK29522.core.dto;

public class StatisticDTOGenres {
    private String name;
    private int count;
    private long idGenre;

    public StatisticDTOGenres(String name, int count, long idGenre) {
        this.name = name;
        this.count = count;
        this.idGenre = idGenre;
    }

    public StatisticDTOGenres(String name, long idGenre) {
        this.name = name;
        this.idGenre = idGenre;
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

    public long getIdGenre() {
        return idGenre;
    }

    public void setIdGenre(int idGenre) {
        this.idGenre = idGenre;
    }

    public void addCount(){
        this.count++;
    }

    public void removeCount(){
        this.count = 0;
    }
}
