package by.it_academy.MDK29522.util.comparator;

import by.it_academy.MDK29522.core.dto.StatisticDTOArtists;

import java.util.Comparator;

public class ComparatorArtistStatistic implements Comparator<StatisticDTOArtists> {
    @Override
    public int compare(StatisticDTOArtists o1, StatisticDTOArtists o2) {
        if(o1.getCount()>o2.getCount()){
            return 1;
        } else if(o1.getCount()<o2.getCount()){
            return -1;
        } else return 0;
    }
}
