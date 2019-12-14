public class Elevator extends Area {
    private static int who_visitor=1;
    private NewQueue queueDeletedVisitors = new NewQueue();

    //constructor of elevator
    //to construct/create the elevator needs the capacity(Nl)
    //initializes: Nl, no_visitors=0(is empty of visitors)
    public Elevator(int Nl) {
        C = Nl;
        System.out.println("A lift has been created");
    }
    //check if the visitor can join the elevator
    //Increase the number of visitors the current time
    public int Enter(int have_escort) {
        if (have_escort == 0) {
            if (no_visitors < C) { //Visitor can join the elevator
                IncreaseVisitors();
                System.out.println("Visitor in the lift. ");
                return 1;
            } else {
                System.out.println("You are not allowed to enter!");
                return 0;
            }
        } else {
            if (no_visitors < C - 1) { //Visitor can join the elevator
                IncreaseVisitors();
                IncreaseVisitors();
                System.out.print("Visitor and escort in the lift. ");
                System.out.print("\n");
                return 1;
            } else {
                System.out.println("You are not allowed to enter!");
                return 0;
            }
        }

    }
    //if there is space in floor and there visitors in elevator
    //check if they are going to specific floor
    //(if yes) get office..
    //        if can join the office put them in office and delete the from elevator
    //        else put them in entrance area(if there is space) else go to next visitor
    //(If no) go to next floor
    private void Stop_Up(Floor f, NewQueue queueElevator) {
        int off = 0;
        int have_escort;
        if (queueElevator.Size() != 0) {
            have_escort = (queueElevator.returnData(who_visitor)).GetEscort();
        }
        int x = f.Enter(have_escort);
        while (x == 1 && who_visitor <= queueElevator.Size()) { //visitor can join the floor
            System.out.print("Entering floor number: " +f.GetFloorNumber());
            System.out.println("Visitor in the entrance area ");
            have_escort = (queueElevator.returnData(who_visitor)).GetEscort();
            if ((queueElevator.returnData(who_visitor)).GetFloor() == f.GetFloorNumber()) {
                off = (queueElevator.returnData(who_visitor)).GetOffice();
                if (f.EnterOffice(off, have_escort) == 1) { //put in office
                    if (have_escort == 0) {
                        f.PutInOffices(queueElevator.returnData(who_visitor));
                        System.out.println("Priority_number is: " +(queueElevator.returnData(who_visitor)).GetPriorityNumber());
                        Exit();
                        queueElevator.specialDelete(who_visitor);
                    } else {
                        f.PutInOffices(queueElevator.returnData(who_visitor));
                        f.PutInOffices(queueElevator.returnData(who_visitor + 1));
                        System.out.println("Priority_number is: " +(queueElevator.returnData(who_visitor)).GetPriorityNumber());
                        Exit();
                        Exit();
                        queueElevator.specialDelete(who_visitor);
                        queueElevator.specialDelete(who_visitor);
                    }
                } else {
                    f.ReduceVisitorsOffice(off); //reduce visitors;
                    if (f.EnterEa(have_escort) == 1) {
                        if (have_escort == 0) {
                            System.out.println("Please, wait outside for entrance in office number " +off);
                            System.out.println("Priority_number is: " +(queueElevator.returnData(who_visitor)).GetPriorityNumber());
                            f.PutInEA(queueElevator.returnData(who_visitor));
                            Exit();
                            queueElevator.specialDelete(who_visitor);
                        } else {
                            System.out.println("Please, wait outside for entrance in office number " +off);
                            System.out.println("Priority_number is: " (queueElevator.returnData(who_visitor)).GetPriorityNumber());
                            f.PutInEA(queueElevator.returnData(who_visitor));
                            f.PutInEA(queueElevator.returnData(who_visitor + 1));
                            Exit();
                            Exit();
                            queueElevator.specialDelete(who_visitor);
                            queueElevator.specialDelete(who_visitor);
                        }
                    } else {
                        if (have_escort == 0) {
                            f.ReduceEA();
                            f.ReduceVisitors();
                            who_visitor++;
                        } else {
                            f.ReduceEA();
                            f.ReduceEA();
                            f.ReduceVisitors();
                            f.ReduceVisitors();
                            who_visitor += 2;
                        }

                    }
                }
                x = f.Enter(have_escort);
            } else {
                //because it"s a sorted queue
                if (have_escort == 0) {
                    System.out.println("There isn't visitor that goes to floor ");
                    f.ReduceVisitors();
                    return;
                } else {
                    System.out.println("There isn't visitor that goes to floor ");
                    f.ReduceVisitors();
                    f.ReduceVisitors();
                    return;
                }

            }

        }

        f.ReduceVisitors();
    }
    //Take random visitors from offices and put them in elevator
    //(if there is space)
    //Take visitors from entrance area and put them in offices
    private void Stop_Down(Floor f, NewQueue queueInOffices, EntranceArea area) {
        int off;
        //cout << "queueInOffices is: ";
        //queueInOffices.display();
        if (queueInOffices.Size() != 0) { //maybe list is empty
            int size = queueInOffices.Size();
            int have_escort = (queueInOffices.returnData(1)).GetEscort();
            while (size > 0 && Enter(have_escort) == 1) { //until elevator = full && while thereare visitors in offices
                 if (have_escort == 0) {
                    System.out.println("Visitor can join the elavator");
                    queueDeletedVisitors.insert(queueInOffices.returnData(1));
                    off = (queueInOffices.returnData(1)).GetOffice();
                    queueInOffices.delet();
                    f.ReduceVisitorsOffice(off);
                    size = queueInOffices.Size();
                    if (size <= 0) {
                        break;
                    }
                } else {
                    System.out.println("Visitor and escort can join the elavator");
                    queueDeletedVisitors.insert(queueInOffices.returnData(1));
                    queueDeletedVisitors.insert(queueInOffices.returnData(2));
                    off = (queueInOffices.returnData(1)).GetOffice();
                    queueInOffices.delet();
                    queueInOffices.delet();
                    f.ReduceVisitorsOffice(off);
                    f.ReduceVisitorsOffice(off);
                    size = queueInOffices.Size();
                    if (size <= 0) {
                        Exit();
                        break;
                    }
                }
                 have_escort = (queueInOffices.returnData(1)).GetEscort();

                }
            if (size == 0) {
                Exit();
            }
        } else {
            return;
        }
        //cout << "queueDeletedVisitors is: ";
        //queueDeletedVisitors.display();
        NewQueue qArea = (area.GetQueue());
        //cout << "in ea: "<< endl;
        //qArea.display();
        if (qArea.GetLength() != 0) {
            int i = 1;
            while ((qArea.Size()) > 0) {
                int off = (qArea.returnData(i)).GetOffice();
                int have_escort = (qArea.returnData(i)).GetEscort();
                if (f.EnterOffice(off, have_escort) == 1) {
                    if (have_escort == 0) {
                        queueInOffices.insert(qArea.returnData(i));
                        if (i == 1) {
                            qArea.delet();
                        } else {
                            qArea.specialDelete(i);
                        }
                        area.Exit(); //no_visitors--;
                    } else {
                        queueInOffices.insert(qArea.returnData(i));
                        queueInOffices.insert(qArea.returnData(i + 1));
                        if (i == 1) {
                            qArea.delet();
                            qArea.specialDelete(2);
                        } else {
                            qArea.specialDelete(i);
                            qArea.specialDelete(i + 1);
                        }
                        area.Exit(); //no_visitors--;
                        area.Exit();
                    }
                } else {
                    if (have_escort == 0) {
                        i++;
                        f.ReduceVisitorsOffice(off);
                    } else {
                        i += 2;
                        f.ReduceVisitorsOffice(off);
                        f.ReduceVisitorsOffice(off);
                    }

                }
            }
        } else {
            return;
        }

    }
    //At the end empty all visitors that have served from the building
    private int Empty_All() {
        int size = queueDeletedVisitors.Size();
        while ((queueDeletedVisitors.Size()) > 0) {
            int have_escort = (queueDeletedVisitors.returnData(1)).GetEscort();
            if (have_escort == 0) {
                System.out.println("I cannot believe I finished!");
                System.out.println("My priority number is: " +(queueDeletedVisitors.returnData(1)).GetPriorityNumber());
                Exit();
                queueDeletedVisitors.delet();
            } else {
                System.out.println("I cannot believe that me and my escort have finished !");
                System.out.println("My priority number is: " +(queueDeletedVisitors.returnData(1)).GetPriorityNumber());
                Exit();
                Exit();
                queueDeletedVisitors.delet();
                queueDeletedVisitors.delet();
            }
        }
        return size;

    }

    //Destructor of elevator
    public void ~Elevator() {
        System.out.println("No more ups and downs");
    }

}

//private int Elevator.who_visitor = 1;