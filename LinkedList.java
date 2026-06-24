class Node{
    int data;
    Node next;
    Node(int d){
        data=d;
        next=null;
    }
    void setNext(Node n){
        next=n;
    }

}

public class LinkedList{
    static Node head;
    public static void main(String[] args){
        head=new  Node(0);
        Node secondNode=new  Node(1);
        Node thirdNode=new  Node(2);
        Node fourthNode=new  Node(3);

        head.next=secondNode;
        secondNode.next=thirdNode;
        thirdNode.next=fourthNode;
        print();
    }

    static void print(){
        Node temp=head;
        while(temp!=null){
            System.out.print(temp.data+" ");
            temp=temp.next;
        }
    }



}