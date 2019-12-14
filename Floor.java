import java.io.*;

public class Floor extends Area{
    private int floor_number; //number of floor
    private EntranceArea area;
    private Office[] offices = Office[10];
    private NewQueue queueInOffices = new NewQueue();

    //constructor of floor
    //to construct/create the floor needs the capacity(Nf)
    //and the floor_number
    //initializes: Nf, no_visitors, floor_number
    public Floor(int Nf, int floor_number) {
        C = Nf;
        this.floor_number = floor_number;
        System.out.println("A floor has been created with number: " +floor_number);
    }

    public int GetFloorNumber() {
        return floor_number;
    }

    public void CreateFArea(EntranceArea area) {
        this.area = area;
    }

    public EntranceArea GetFArea() {
        return area;
    }

    public void CreateOffices(int No) {
        for (int i = 0; i <= 9; i++) {
            Office office = new Office(No, i + 1);
            offices[i] = office;
        }
    }

    //check if the visitor can join the entrance area
    public int EnterEa(int have_escort) {
        if (area.Enter(have_escort) == 1) {
            return 1;
        } else {
            return 0;
        }
    }

    //check if the visitor can join the floor
    //Increase the number of visitors the current time
    public int Enter(int have_escort) {
        if (have_escort == 0) {
            if (no_visitors < C) { //Visitor can join the floor
                IncreaseVisitors();
                System.out.println("Entering floor number: " +floor_number);
                return 1;
            }
            System.out.println("Sorry, floor number " +floor_number "is full");
            return 0;
        }else {
            if (no_visitors < C - 1) { //Visitor can join the floor
                IncreaseVisitors();
                IncreaseVisitors();
                System.out.println("Entering floor number: " +floor_number);
                return 1;
            }
            System.out.println("Sorry, floor number " +floor_number "is full");
            return 0;
        }

    }

    //check if the visitor can join the office
    //Increase the number of visitors the current time
    public int EnterOffice(int off, int have_escort) {
        if (have_escort == 0) {
            if (offices[off - 1].Enter(have_escort) == 1) {
                return 1;
            }
        } else {
            if (offices[off - 1].Enter(have_escort) == 1) {
                return 1;
            }
        }
        return 0;

    }

    public void ReduceVisitorsOffice(int off) {
        offices[off - 1].Exit();
    }

    public void PutInOffices(Visitor vis) {
        queueInOffices.insert(vis);
    }

    public void PutInEA(Visitor vis) {
        area.PutInEA(vis);
    }

    public NewQueue ReturnQueue() {
        return queueInOffices;
    }

    public void IncreaseEA() {
        area.IncreaseVisitors();
    }

    public void ReduceEA() {
        area.ReduceVisitors();
    }

    //Destructor of floor
    public void ~Floor() {
        System.out.println("End of service!");
    }

}