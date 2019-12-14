import java.io.*;

public class EntranceArea extends Area {
    private NewQueue queueInEA = new NewQueue();

    //constructor of entrance area
    //to construct/create the area needs the capacity(Ne)
    //initializes: Ne, no_visitors=0(is empty of visitors)
    public EntranceArea(int Ne) {
        System.out.println("The entrance has been created");
        C = Ne;
    }

    public int Enter(int have_escort) {
        if (have_escort == 0) {
            if (no_visitors < C) {
                IncreaseVisitors();
                return 1;
            }else{
                return 0;
            }
        } else {
            if (no_visitors < C - 1) {
                IncreaseVisitors();
                IncreaseVisitors();
                return 1;
            } else {
                return 0;
            }
        }

    }

    public void PutInEA(Visitor vis) {
        queueInEA.insert(vis);
    }

    public NewQueue GetQueue() {
        return queueInEA;
    }

    //Destructor of entrance area
    public void ~EntranceArea() {
        System.out.println("End of waiting people");s
    }


}