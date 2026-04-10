//333204
public class HashTableOpen {

    private static class Node{
        int value;
        Node next;

        public Node(int value){
            this.value = value;
            this.next = null;
        }

    }
    public String breadCrumbs = "";

    public void clearBreadcrumbs() {
        this.breadCrumbs = "";
    }

    private int size;
    private Node[] table;

    public HashTableOpen(int size){
        this.size = size;
        this.table = new Node[size];
    }

    private int hash(int num){
        return num % size;
    }

    private Node findNodeBeforeNeeded(int num){
        this.breadCrumbs += "Hash ";
        int hashedNum = hash(num);
        Node currentNode = this.table[hashedNum];
        this.breadCrumbs += "StartFindCycle ";
        while (currentNode != null &&  currentNode.next != null && currentNode.next.value != num){
            currentNode = currentNode.next;
        }
        this.breadCrumbs += "EndFindCycle ";
        return currentNode;

    }

    public void insert(int num){
        this.breadCrumbs += "Hash ";
        int hashedNum = hash(num);
        this.breadCrumbs += "CreateNode ";
        Node newNode = new Node(num);
        this.breadCrumbs += "SetNextNode ";
        newNode.next = this.table[hashedNum];
        this.breadCrumbs += "SetNewHead ";
        this.table[hashedNum] = newNode;
    }

    public void delete(int num){
        this.breadCrumbs += "Hash ";
        int hashedNum = hash(num);
        if (this.table[hashedNum] == null){
            this.breadCrumbs += "BucketEmpty ";
            return ;
        }
        if (this.table[hashedNum].value == num){
            this.breadCrumbs += "First ";
            this.table[hashedNum] = this.table[hashedNum].next;
            this.breadCrumbs += "MoveHead ";
            return;
        }

        this.breadCrumbs += "NotFirst ";
        this.breadCrumbs += "CallFind ";
        Node nodeBeforeNeeded = findNodeBeforeNeeded(num);
        if (nodeBeforeNeeded.next != null){
            this.breadCrumbs += "Found ";
            nodeBeforeNeeded.next = nodeBeforeNeeded.next.next;
            this.breadCrumbs += "Deleted ";
        } else{
            this.breadCrumbs += "NotFound ";
        }
    }

    public boolean find(int num){
        int hashedNum = hash(num);
        this.breadCrumbs += "Hash ";

        if (this.table[hashedNum] == null){
            this.breadCrumbs += "EmptyBucket ";
            return false;
        }

        if (this.table[hashedNum].value == num){
            this.breadCrumbs += "First ";
            this.breadCrumbs += "Found ";
            return true;
        }
        this.breadCrumbs += "NotFirst ";
        this.breadCrumbs += "CallFind ";
        Node nodeBeforeNeeded = findNodeBeforeNeeded(num);
        if (nodeBeforeNeeded.next != null){
            this.breadCrumbs += "Found ";
            return true;
        }
        this.breadCrumbs += "NotFound ";
        return false;
    }

}
