package Hausaufgaben04;

public class MyDeque{
     private Node first = null;
     private Node last = null;

     public void addFirst(Node node){
         node.prev = null;
         node.next = this.first;
         this.first = node;
     }

     public void addLast(Node node){
         node.prev = last;
         node.next = null;
         last = node;
     }

     public String removeFirst(){
         if(first != last && first != null) { //the list has at least 2 nodes
             String ret = first.content;
             first = first.next;
             return ret;
         } else{    //list has only one or zero elements
             first = last = null;
             return null;
         }

     }

     public String removeLast(){
         if(last != first && last != null){ //the list has at least 2 nodes
             String ret = last.content;
             last = last.prev;
             return ret;
         } else{    //list has only 1 or zero elements
             last = first = null;
             return null;
         }
     }

     public boolean isEmpty(){
         if(first == null && last == null){
             return true;
         }else{
             return false;
         }
     }

     public void clear(){
         first = null;
         last = null;
     }
 }