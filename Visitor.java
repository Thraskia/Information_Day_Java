public class Visitor {
    protected int no_office; //office that need to visit
    protected int no_floor; //floor that need to visit
    protected int priority_number; //priority number of visitor
    protected int have_escort;


    //constructor of visitor
    //to construct/create a visitor needs the office and the floor
    //initializes: no_office, no_floor, priority_number=0
    public Visitor(int no_office,int no_floor) {
        this.no_office = no_office;
        this.no_floor = no_floor;
        this.priority_number = 0;
        have_escort = 0; //he dont have an escort
    }

    public final void SetHaveEscort() {
        have_escort = 1;
    }

    public final int GetEscort() {
        return have_escort;
    }

    public final void SetPriorityNumber(int priority_number) {
        this.priority_number = priority_number;
    }

    public final int GetPriorityNumber() {
        return priority_number;
    }

    public final int GetFloor() {
        return this.no_floor;
    }

    public final int GetOffice() {
        return no_office;
    }

    //void Print(void){
    //        cout << "[( " << this->no_office << "," << this->no_floor << " )]";
    //}
}