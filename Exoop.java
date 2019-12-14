import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

class exoop{
    public static int main(String[] args){
        //int N = atoi(args[1]);
        //int Nf = atoi(args[2]);
        //int Ng = atoi(args[3]);
        //int No = atoi(args[4]);
        //int Nl = atoi(args[5]);
        //int K = atoi(args[6]);
        //int L = atoi(args[7]);

        //Integer N = Integer.valueOf(args[1]);
        //Integer Nf = Integer.valueOf(args[2]);
        //Integer Ng = Integer.valueOf(args[3]);
        //Integer No = Integer.valueOf(args[4]);
        //Integer Nl = Integer.valueOf(args[5]);
        //Integer K = Integer.valueOf(args[6]);
        //Integer L = Integer.valueOf(args[7]);


        int K = 50;
        int N = 300;
        int Nf = 50;
        int Ng = 90;
        int No = 4;
        int Nl = 10;
        int L = 1; //Circles that the elevator must work

        int Ne = Nf - 10 * No;
        int end = 0;

        //if entries are right create:
        //1 building, 1 ground floor, 4 floors, 4 areas and
        //10 offices for each floor
        Building building = new Building(N);
        GroundFloor gf;
        if (Ng < N / 2){
            gf = new GroundFloor(Ng);
            building.CreateGroundFloor(gf);
        }else{
            System.out.print("wrong input");
            System.out.print("\n");
            return -1;
        }
        Floor f1;
        Floor f2;
        Floor f3;
        Floor f4;
        EntranceArea ea1;
        EntranceArea ea2;
        EntranceArea ea3;
        EntranceArea ea4;
        Elevator elevator;
        if (Nf < N / 3 && No < Nf){
            f1 = new Floor(Nf, 1);
            f2 = new Floor(Nf, 2);
            f3 = new Floor(Nf, 3);
            f4 = new Floor(Nf, 4);
            ea1 = new EntranceArea(Ne);
            ea2 = new EntranceArea(Ne);
            ea3 = new EntranceArea(Ne);
            ea4 = new EntranceArea(Ne);
            building.CreateFloors(f1, f2,f3,f4, ea1, ea2, ea3, ea4, No);
        }else{
            return -1;
        }
        if (Nl > No){
            elevator = new Elevator(Nl);
            building.CreateElevator(elevator);
        }else{
            return -1;
        }
        //create K visitors with random floor and office destination
        //1<=offices<=10 and 1<=floors<=4
        //Visitor** visitors = new Visitor* [K]
        int i = 1;
        Queue visitors = new Queue();
        Random rand = new Random();
        while (i <= K){ //K== number of visitors that have created
            int random_floor = rand.nextInt(4) + 1;
            int random_office = rand.nextInt(10) + 1;
            visitors.add(new Visitor(random_office, random_floor));
            System.out.println("I am visitor: " +i);
            int random_escort = rand.nextInt();
            if (random_escort % 2 == 0){ //he's visitor's escort
                System.out.println("I'm an escort visitor");
                i++;
                visitors.insert(new Visitor(random_office, random_floor));
            }
            i++;
        }
        int l = 1;
        Queue queueGF = new Queue();
        Queue queueElevator = new Queue();
        int flag = 1; //vis_build!=0 && end!=K
        while (l <= L && flag == 1) {
            int have_escort = (visitors.returnData(1)).GetEscort();
            int pn = building.Enter(have_escort);
            int size = 1;
            while (size <= K && pn != 0) {
                if (have_escort == 0) {
                    System.out.println("Visitor can join the building");
                    System.out.println("Visitor can join the ground floor");
                    queueGF.insert(visitors.returnData(size));
                    visitors.specialDelete(size);
                    gf.Wait(queueGF.returnData(end));
                    pn = building.Enter(have_escort);
                    size = 1;
                    end++;
                }else{
                    if (size != K) {
                        System.out.println("Visitor and escort can join the building");
                        System.out.println("Visitor and escort can join the ground floor");
                        queueGF.insert(visitors.returnData(1));
                        queueGF.insert(visitors.returnData(2));
                        gf.Wait(queueGF.returnData(end));
                        visitors.delet();
                        visitors.delet();
                        pn = building.Enter(have_escort);
                        end += 2;
                    }else{
                        size += 2;
                    }

                }
                if (visitors.Size() != 0) {
                    have_escort = (visitors.returnData(1)).GetEscort();
                }else{
                    System.out.println(" list is empty ");
                    break;
                }
            }
            //queueGF.display();
            have_escort = (queueGF.returnData(1)).GetEscort();
            int x = building.EnterElevator(have_escort);
            int count = 1;
            end = queueGF.Size();
            while (count <= end && x == 1) {
                if (have_escort == 0) {
                    System.out.println("Visitor in the elevator ");
                    queueElevator.insert(queueGF.returnData(count));
                    queueGF.specialDelete(count);
                    count = 1;
                    have_escort = (queueGF.returnData(count)).GetEscort();
                    x = building.EnterElevator(have_escort);
                } else {
                    if (count != end) {
                        System.out.println("Visitor and escort in the elevator ");
                        queueElevator.insert(queueGF.returnData(1));
                        queueGF.delet();
                        queueElevator.insert(queueGF.returnData(1));
                        queueGF.delet();
                        have_escort = (queueGF.returnData(1)).GetEscort();
                        x = building.EnterElevator(have_escort);
                    } else {
                        count += 2;
                    }

                }

            }
            queueElevator.Sort();
            building.ElevatorOperate(queueElevator);
            l++;
            //there aren't visitors in building && there aren't visitors outside the building
            if (building.GetNoVisitors() == 0 && visitors.Size() == 0) {
                flag = 0;
            }
        }


        return 0;

}