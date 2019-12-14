import java.io.*;

public class Building extends Area {
    private Floor f1;
    private Floor f2;
    private Floor f3;
    private Floor f4;
    private GroundFloor gf;
    private Elevator elevator;

    //constructor of building
    //to construct/create the building needs the capacity(N)
    //initializes: N, no_visitors=0(is empty of visitors)
    public Building(int N) {
        C = N;
        System.out.println("A new building is ready for serving citizens.");
    }

    //the current no of ground floor's visitors
    public int GetSumVisGF() {
        return gf.GetNoVisitors();
    }

    //the current no of visitors in elevator
    public int GetSumVisEl() {
        return elevator.GetNoVisitors();
    }

    //Initializes the data member gf
    public void CreateGroundFloor(GroundFloor gf) {
        this.gf = gf;
    }

    //Initializes the data member f1,f2,f3,f4
    //Initializes area which is floor's data member
    public final void CreateFloors(Floor f1, Floor f2, Floor f3, Floor f4, EntranceArea ea1, EntranceArea ea2, EntranceArea ea3, EntranceArea ea4, int No) {
        this.f1 = f1;
        (this.f1).CreateFArea(ea1);
        (this.f1).CreateOffices(No);
        this.f2 = f2;
        (this.f2).CreateFArea(ea2);
        (this.f2).CreateOffices(No);
        this.f3 = f3;
        (this.f3).CreateFArea(ea3);
        (this.f3).CreateOffices(No);
        this.f4 = f4;
        (this.f4).CreateFArea(ea4);
        (this.f4).CreateOffices(No);
    }

    //Initializes the data member elevator
    public void CreateElevator(Elevator elevator) {
        this.elevator = elevator;
    }

    //check if the visitor can join the building and the ground floor
    //Increase the number of visitors the current time
    public int Enter(int have_escort) {
        if (have_escort == 0) {
            if (no_visitors < C) { //visitor can join the building
                if ((this.gf).Enter(have_escort) == 1) {
                    System.out.println("Visitor can join the building");
                    System.out.println("Visitor can join the ground floor");;
                    (this.gf).IncreaseVisitors();
                    IncreaseVisitors();
                    return 1;
                }
            }
            System.out.println("Please, come tomorrow");
            return 0;
        } else {
            if (no_visitors < C - 1) { //visitor can join the building
                if ((this.gf).Enter(have_escort) == 1) {
                    System.out.println("Visitor and escort can join the building");
                    System.out.println("Visitor and escort can join the ground floor");
                    (this.gf).IncreaseVisitors();
                    (this.gf).IncreaseVisitors();
                    IncreaseVisitors();
                    IncreaseVisitors();
                    return 1;
                }
            }
            System.out.println("Please, come tomorrow");
            return 0;
        }
    }

    //check if the visitor can join the elevator
    public int EnterElevator(int have_escort) {
        return (this.elevator).Enter(have_escort);
    }

    //Elevator's operation
    public void ElevatorOperate(NewQueue queueElevator) {
        elevator.Stop_Up(f1, queueElevator);
        elevator.Stop_Up(f2, queueElevator);
        elevator.Stop_Up(f3, queueElevator);
        elevator.Stop_Up(f4, queueElevator);
        elevator.Stop_Down(f4, f4.ReturnQueue(), f4.GetFArea());
        elevator.Stop_Down(f3, f3.ReturnQueue(), f3.GetFArea());
        elevator.Stop_Down(f2, f2.ReturnQueue(), f2.GetFArea());
        elevator.Stop_Down(f1, f1.ReturnQueue(), f1.GetFArea());
        no_visitors -= (elevator.Empty_All());
    }

    //Destructor of building
    public void ~Building() {
        System.out.println("Service not available any longer. Go elsewhere!");
    }

}