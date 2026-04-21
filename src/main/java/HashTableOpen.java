//333204
public class HashTableOpen {

    private static class Node {
        String value;
        Node next;

        public Node(String value) {
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

    public HashTableOpen(int size) {
        this.size = size;
        this.table = new Node[size];
    }

    private int hash(String str) {
        int h = 0;
        for (int i = 0; i < str.length(); i++) {
            h = 31 * h + str.charAt(i);
        }
        return (h & 0x7FFFFFFF) % size;
    }

    private Node findNodeBeforeNeeded(String value) {
        this.breadCrumbs += "Hash ";
        int hashed = hash(value);
        Node currentNode = this.table[hashed];

        this.breadCrumbs += "StartFindCycle ";
        while (currentNode != null &&
                currentNode.next != null &&
                !currentNode.next.value.equals(value)) {
            currentNode = currentNode.next;
        }
        this.breadCrumbs += "EndFindCycle ";
        return currentNode;
    }

    public void insert(String value) {
        this.breadCrumbs += "Hash ";
        int hashed = hash(value);

        this.breadCrumbs += "CreateNode ";
        Node newNode = new Node(value);

        this.breadCrumbs += "SetNextNode ";
        newNode.next = this.table[hashed];

        this.breadCrumbs += "SetNewHead ";
        this.table[hashed] = newNode;
    }

    public void delete(String value) {
        this.breadCrumbs += "Hash ";
        int hashed = hash(value);

        if (this.table[hashed] == null) {
            this.breadCrumbs += "BucketEmpty ";
            return;
        }

        if (this.table[hashed].value.equals(value)) {
            this.breadCrumbs += "First ";
            this.table[hashed] = this.table[hashed].next;
            this.breadCrumbs += "MoveHead ";
            return;
        }

        this.breadCrumbs += "NotFirst ";
        this.breadCrumbs += "CallFind ";

        Node nodeBefore = findNodeBeforeNeeded(value);

        if (nodeBefore.next != null) {
            this.breadCrumbs += "Found ";
            nodeBefore.next = nodeBefore.next.next;
            this.breadCrumbs += "Deleted ";
        } else {
            this.breadCrumbs += "NotFound ";
        }
    }

    public boolean find(String value) {
        int hashed = hash(value);
        this.breadCrumbs += "Hash ";

        if (this.table[hashed] == null) {
            this.breadCrumbs += "EmptyBucket ";
            return false;
        }

        if (this.table[hashed].value.equals(value)) {
            this.breadCrumbs += "First ";
            this.breadCrumbs += "Found ";
            return true;
        }

        this.breadCrumbs += "NotFirst ";
        this.breadCrumbs += "CallFind ";

        Node nodeBefore = findNodeBeforeNeeded(value);

        if (nodeBefore.next != null) {
            this.breadCrumbs += "Found ";
            return true;
        }

        this.breadCrumbs += "NotFound ";
        return false;
    }


}