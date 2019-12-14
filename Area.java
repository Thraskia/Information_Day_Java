public class Area {
    protected int C; //area's capacity
    protected int no_visitors;

    public Area() {
        no_visitors = 0;
    }

    //Increase the number of visitors the current time
    public void IncreaseVisitors() {
        no_visitors++;
    }

    //Reduce the number of visitors the current time
    public void ReduceVisitors() {
        no_visitors--;
    }

    //return the current no of visitors in area
    public int GetNoVisitors() {
        return no_visitors;
    }

    public int Exit() {
        ReduceVisitors();
    }

}