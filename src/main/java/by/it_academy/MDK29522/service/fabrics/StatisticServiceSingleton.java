package by.it_academy.MDK29522.service.fabrics;

import by.it_academy.MDK29522.service.StatisticService;
import by.it_academy.MDK29522.service.api.IStatisticService;

public class StatisticServiceSingleton {
    private static volatile IStatisticService instance;
    private StatisticServiceSingleton() {
    }

    public static IStatisticService getInstance() {
        if(instance==null){
            synchronized (StatisticServiceSingleton.class){
                if(instance==null){
                    instance = new StatisticService(
                            VoteServiceSingleton.getInstance(),
                            GenreServiceSingleton.getInstance(),
                            ArtistServiceSingleton.getInstance()
                    );
                }
            }
        }
        return instance;
    }
}
