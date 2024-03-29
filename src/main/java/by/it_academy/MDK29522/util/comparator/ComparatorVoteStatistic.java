package by.it_academy.MDK29522.util.comparator;

import by.it_academy.MDK29522.core.dto.VoteDTOIdTime;

import java.util.Comparator;

public class ComparatorVoteStatistic implements Comparator<VoteDTOIdTime> {
    @Override
    public int compare(VoteDTOIdTime o1, VoteDTOIdTime o2) {
        return o1.getTimeCreate().compareTo(o2.getTimeCreate());
    }
}
