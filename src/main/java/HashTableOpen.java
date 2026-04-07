public class HashTableOpen {

    private static class Node{
        int value;
        Node next;

        public Node(int value){
            this.value = value;
            this.next = null;
        }

    }

    private int size;
    private Node[] table;

    public HashTableOpen(int size){
        this.size = size;
        this.table = new Node[size];
    }

    private int Hash(int num){
        return num % size;
    }

    private Node findNodeBeforeNeeded(int num){
        int hashedNum = Hash(num);
        Node currentNode = this.table[hashedNum];
        while (currentNode != null &&  currentNode.next != null && currentNode.next.value != num){
            currentNode = currentNode.next;
        }
        return currentNode;

    }

    public void Insert(int num){
        int hashedNum = Hash(num);
        Node newNode = new Node(num);
        newNode.next = this.table[hashedNum];
        this.table[hashedNum] = newNode;
    }

    public void Delete(int num){
        int hashedNum = Hash(num);
        if (this.table[hashedNum].value == num){
            this.table[hashedNum] = this.table[hashedNum].next;
            return;
        }

        Node nodeBeforeNeeded = findNodeBeforeNeeded(num);
        if (nodeBeforeNeeded != null){
            nodeBeforeNeeded.next = nodeBeforeNeeded.next.next;
        }
    }

    public boolean Find(int num){
        int hashedNum = Hash(num);

        if (this.table[hashedNum].value == num){
            return true;
        }
        Node nodeBeforeNeeded = findNodeBeforeNeeded(num);
        return nodeBeforeNeeded != null;
    }

}
