public class NewQueue {
    private class Node {
        public Visitor data;
        public Node next;
    }

    private Node front;
    private Node rear;
    private int length;

    //constructor of queue
    //initializes: front, rear and length
    public NewQueue() {
        front = null;
        rear = null;
        length = 0;
    }

    //return the Visitor* in "position" of queue
    public  Visitor returnData(int position) {
        Node node = new Node();
        if (position == 1) {
            return front.data;
        } else if (position == Size()) {
            return rear.data;
        } else {
            node = front;
            for (int i = 1; i < position; i++) {
                node = node.next;
            }
            return node.data;
        }

    }

    public int GetLength() {
        return length;
    }

    // size of queue
    public int Size() {
        Node node = new Node();
        node = front;
        int size = 0;
        while (node != null) {
            size++;
            node = node.next;
        }
        return size--;

    }

    //insert item at the end of queue
    public void insert(Visitor item) {
        length++;
        Node node;
        node = new Node();
        if (front == null) {
            rear = front = node;
            rear.next = null;
            front.next = null;
            front.data = item;
        } else {
            Node temp = new Node();
            temp = front;
            if (temp == rear){//we have one node
                temp.next = node;
                rear = node;
                rear.data = item;
                rear.next = null;
            } else {
                while (temp != rear) {
                    temp = temp.next;
                }
                temp.next = node;
                node.data = item;
                node.next = null;
                rear = node;
            }
        }
    }
    //sort the queue from smaller to bigger
    private void Sort()
    {
        Node curr_i = new Node();
        curr_i = front;
        Node curr_j = new Node();
        Node temp = new Node();
        if (front == null) {
            return;
        } else {
            for (int i = 1; i < Size(); i++) {
                curr_j = curr_i.next;
                for (int j = i + 1; j <= Size(); j++) {
                    if ((curr_i.data).GetFloor() > (curr_j.data).GetFloor()) {
                        temp.data = curr_i.data;
                        curr_i.data = curr_j.data;
                        curr_j.data = temp.data;
                    }
                    curr_j = curr_j.next;
                }
                if (i == 1) {
                    front.data = curr_i.data;
                }
                curr_i = curr_i.next;
            }
            rear.data = curr_i.data;
            return;
        }
    }

    //delete the 1st node of queue
    private void delet() {
        Node temp = new Node();
        if (front != null) {
            //cout << "Deleted element is " << front->data << endl;
            temp = front;
            front = front.next;
            temp = null;
        }
    }

    //delete the node in "position" of queue
    private void specialDelete(int position) {
        Node curr = new Node();
        curr = front;
        if (front == null) {
            return;
        }
        if (position == 1) {
            delet();
            return;
        }
        if (length == 2) {
            if (position == 2) {
                curr = rear;
                front.next = null;
                rear = front;
            }
            length--;
            curr = null;
            //delete curr->temp
        }
        else {
            Node prev = new Node();
            if (position == length) {
                for (int i = 1; i < length - 1; i++) {
                    curr = curr.next;
                }
                curr.next = null;
                prev = curr;
                curr = rear;
                curr = null;
                rear = prev;
            } else {
                for (int i = 1; i < position; i++) {
                    prev = curr;
                    curr = curr.next;
                }
                prev.next = curr.next;
                curr = null;
            }
            length--;
        }
    }

    //print the queue
    private void display() {
        Node temp = front;
        while (temp != null) {
            System.out.print(temp.data);
            System.out.print(" ");
            temp = temp.next;
        }
        System.out.println("NULL");
    }
}