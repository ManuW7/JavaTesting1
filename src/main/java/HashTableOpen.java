public class HashTableOpen {

    private static class Node{
        private int value;
        private Node next;

        public Node(int value){
            this.value = value;
        }

        public Node(){
            this.next = null;
        }

        public int getValue(){
            return value;
        }

        public Node getNext(){
            return this.next;
        }

        public void setValue(int value){
            this.value = value;
        }

        public void setNext(Node next){
            this.next = next;
        }
    };

    private int SIZE = 10;
    private Node[] table;

    public HashTableOpen(){

    }



}
