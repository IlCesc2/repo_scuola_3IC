import java.util.UUID;

public class List {
    private Node head =null;
    private int[] reasonCounters = new int[3]; // PRIORITY COUNTERS


    public Node getHead() {
        return this.head;
    }

    public void setHead(Node head) {
        this.head = head;
    }

    public int queueSize() {
        int o = 0;
        for (int i : reasonCounters) {
            o+=i;
        }
        return o;
    }

    
    public void addNewUser(Utente newUser) {
        Node newNode = new Node(newUser);
        if (head== null) {
            head=newNode;
            return;

        }
        Node c = head;
        while (c.getNext()!= null) {
            c=c.getNext();
        }
        c.setNext(newNode);
        newNode.setPrev(c);
        reasonCounters[newUser.getReason()-1]++;
    }

    
    public Utente removeUser(int reason) {
        if (head== null || reasonCounters[reason-1] ==0) {
            return null;
        }

        Node sel = null;
        int rSelCounter = reasonCounters[reason-1];
        Node c = head;

        while (c.getNext()!= null && rSelCounter > 0) {
            
            if (c.getUser().getReason() == reason) {
                // for setting it the first time
                if (sel == null ) { // || !sel.getUser().hasCard()
                    sel=c;
                    
                }
                else if (c.getUser().hasCard()) {  
                    sel=c;
                    
                }

                if (sel.getUser().hasCard()) break;
                
                rSelCounter--;
                //break;
            }
            c=c.getNext();
        }

        if (sel.equals(head)) {
            head=head.getNext();
        } else if(sel.getNext() == null){
            sel.getPrev().setNext(null);
        } else{

            sel.getPrev().setNext(sel.getNext());
            sel.getNext().setPrev(sel.getPrev());
        }
        reasonCounters[reason-1]--;
        return sel.getUser();
    }

    // prints the queue (FOR TESTING)
    public void printALL() {
        Node c = head;
        while (c != null) {
            System.out.println(c.getUser().toString());
            c=c.getNext();
        }
    }

    // checks if the ID is already registered in the queue
    public boolean isUserAlreadyRegistered(UUID id){
        if (head== null) return false;

        
        Node c = head;
        while (c != null) {
            System.out.println(c.getUser().getId().equals(id));
            if (c.getUser().hasCard() && c.getUser().getId().equals(id)) {
                return true;
            }
            c=c.getNext();
        }
        return false;
    }


}
