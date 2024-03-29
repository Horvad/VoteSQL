package by.it_academy.MDK29522.dao.memory.fabrics;

import by.it_academy.MDK29522.dao.api.IVoteDao;
import by.it_academy.MDK29522.dao.memory.VoteDaoMemory;

public class VoteDaoMemorySingleton {
    private static volatile IVoteDao instance;

    private VoteDaoMemorySingleton() {
    }

    public static IVoteDao getInstance() {
        if(instance==null){
            synchronized (VoteDaoMemorySingleton.class){
                if(instance==null){
                    instance = new VoteDaoMemory();
                }
            }
        }
        return instance;
    }

}
