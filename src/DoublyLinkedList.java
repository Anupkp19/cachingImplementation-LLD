public class DoublyLinkedList<K,V> {

     private final Node<K,V> head;
     private final Node<K,V> tail;

     public DoublyLinkedList(){
         this.head=new Node<>(null,null);
         this.tail = new Node<>(null,null);
         head.next=tail;
         tail.prev=head;
     }

     public void addNodeDLL(Node<K,V> node){
         node.next=head.next;
         node.prev=head;
         head.next.prev=node;
         head.next=node;
     }

     public void removeNodeDLL(Node<K,V> node){
         node.next.prev=node.prev;
         node.prev.next=node.next;
     }

     public void addToFront(Node<K,V> node){
         removeNodeDLL(node);
         addNodeDLL(node);
     }

     public Node<K,V> removeLast(){
         if(tail.prev==head) return null;
         Node<K,V> lastNode = tail.prev;
         removeNodeDLL(lastNode);
         return lastNode;

     }

}
