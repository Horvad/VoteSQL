package by.it_academy.MDK29522.util.comparator;

import by.it_academy.MDK29522.core.dto.StatisticDTOGenres;

import java.util.Comparator;

public class ComparatorGenreStatistic implements Comparator<StatisticDTOGenres> {

    @Override
    public int compare(StatisticDTOGenres o1, StatisticDTOGenres o2) {
        if(o1.getCount()>o2.getCount()){
            return 1;
        } else if(o1.getCount()<o2.getCount()){
            return -1;
        } else return 0;
    }
}
