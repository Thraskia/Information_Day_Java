import java.io.*;

public class Office extends Area {
    private int office_number; //number of office

    //constructor of office
    //to construct/create the office needs the capacity(No)
    //and office_number
    //initializes: office_number
    public Office(int No, int office_number) {
        C = No;
        System.out.println("Office number: " +office_number " has been created.");
        this.office_number = office_number;
    }

    public int Enter(int have_escort) {
        if (have_escort == 0) {
            if (no_visitors < C) {
                IncreaseVisitors();
                System.out.println("Entering office number: " +office_number);
                return 1;
            }else {
                return 0;
            }
        } else {
            if (no_visitors < C - 1) {
                IncreaseVisitors();
                IncreaseVisitors();
                System.out.println("Entering office number: " +office_number);
                return 1;
            }else {
                return 0;
            }
        }

    }

    //Destructor of office
    public void ~Office() {
        System.out.println("End of the work!");
    }
}