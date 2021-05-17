package Utils;

import java.io.*;

public class ListaEncadeada implements Serializable {
    NodeLE first;
    NodeLE last;

    public ListaEncadeada(){
        first = last = null;
    }
    boolean isEmpty(){
        return first == null;
    }
    public void insert(int n, String arq){
        NodeLE el = new NodeLE(n,arq);
        insert(el);
    }
    public void insert(NodeLE n){
        if(isEmpty()){
            first = n;
            last = n;
            return;
        }
        /*The current NodeLE*/
        NodeLE lp = first;
        /*The last tested NodeLE*/
        NodeLE pb = first;
        do{
            if(lp == null){
                insertLast(n);
                break;
            }
            if(n.isLessT(lp.value)){
                
                if(lp == first){
                    insertFirst(n);
                    break;
                }
                else{
                    insertAfter(pb, n);
                    break;
                }
            }
            pb = lp;
            lp = lp.next;

        }while(pb != null);
        return;
    }
   
    public void insertUnordered(int n,String arquive){
        NodeLE el = new NodeLE(n, arquive);
        insertUnordered(el);
    }
    public void insertUnordered(NodeLE n){
        insertLast(n);
    }

    void insertAfter(NodeLE base, NodeLE n){
        if(base == last){
            insertLast(n);
            return;
        }
        n.next = base.next;
        base.next = n;
    }
    void insertFirst(NodeLE n){
        n.next = this.first;
        this.first = n;
    }
    void insertLast(NodeLE n){
        if(last == null){
            first = last = n;
            return;
        }
        this.last.next = n;
        this.last = n;
    }

    public NodeLE pop_first(){
        NodeLE newFirst = first.next;
        NodeLE oldFirst = first;

        first = newFirst;
        return oldFirst;
    }

    public NodeLE pop_last(){
        //Find before last NodeLE
        int len = this.length();
        NodeLE bl = this.NodeAt(len - 2);
        if(bl == null){
            NodeLE NodeLEToReturn = last;
            last = null;
            first = null;
            return NodeLEToReturn;
        }
        NodeLE NodeLEToReturn = pop_after(bl);
        last = bl;


        return NodeLEToReturn;
    }

    public NodeLE pop_after(NodeLE NodeLE){
        if(NodeLE.next == last){
            return pop_last();
        }
        NodeLE elToPop = NodeLE.next;
        NodeLE.next = elToPop.next;

        return elToPop;
    }

    //4 funções para encontrar se um valor está contido

    //Essas duas são as funções recursivas, entretanto uma procura um nó especifico
    // A outra procura um Nó com um valor especifico
    boolean contains(NodeLE p, NodeLE currentNodeLE){
        if(currentNodeLE == null){
            return false;
        }
        if(p == currentNodeLE){
            return true;
        }
        return contains(p, currentNodeLE.next);
    }

    boolean contains(int value, NodeLE currentNodeLE){
        if(currentNodeLE == null){
            return false;
        }
        //Problemas de comparação com Doubles
        if(value == currentNodeLE.value){
            return true;
        }
        return contains(value, currentNodeLE.next);
    }

    boolean contains(String arq, NodeLE currentNodeLE){
        if(currentNodeLE == null){
            return false;
        }

        if(arq.equals(currentNodeLE.arquive)){
            return true;
        }
        return contains(arq, currentNodeLE.next);
    }

    //2 funções "starter" que iniciam a busca recursiva no primeiro nó
    public boolean contains(NodeLE p){
        return contains(p,first);
    }

    public boolean contains(int value){
        return contains(value,first);
    }

    public boolean contains(String arq){
        return contains(arq,first);
    }

    public int length(){
        return rec_length(first, 0);
    }

    int rec_length(NodeLE cn, int depth){
        if(cn == null){
            return depth;
        }

        return rec_length(cn.next, ++depth);
    }

    //Duas funções para procurar o elemento no Index, uma retorna o nó, a outra retorna o Valor
    public NodeLE NodeAt(int pos){
        return rec_find(first, pos);
    }

    public int valueAt(int pos){
        return rec_find(first, pos).value;
    }

    public NodeLE findArquive(String arq){
        return rec_find(arq, first);
    }

    //A base recursiva que procura um nó de um indice especifico
    /*NodeLE<T> rec_find(NodeLE<T> cn, int depth, int target){
        if(target < depth){
            return null;
        }
        if(cn == null){
            return null;
        }
        if(depth == target){
            return cn;
        }
        return rec_find(cn.next, ++depth, target);
    }*/
    NodeLE rec_find(NodeLE cn, int target){
        //System.out.print("> ");
        //System.out.println(target);
        if(cn == null){
            return null;
        }
        if(target <= 0){
            return cn;
        }
        
        return rec_find(cn.next, --target);
    }
    
    NodeLE rec_find(String arq, NodeLE currentNodeLE){
        if(currentNodeLE == null){
            return null;
        }
        //Problemas de comparação com Doubles
        if(arq.equals(currentNodeLE.arquive)){
            return currentNodeLE;
        }
        return rec_find(arq, currentNodeLE.next);
    }

    public void print(){
        NodeLE pb = first;
        System.out.println("--------");
        while(pb != null){
            System.out.printf("File: %s Frequency: %d \n",pb.arquive,pb.value);
            pb = pb.next;
        }
        System.out.println("--------");
    }
    public static class NodeLE implements Serializable {
        public int value;
        public String arquive;
        NodeLE next = null;

        public NodeLE(int v, String arq){
            value = v;
            this.arquive = arq;
        }
        //Hope for the best
        public boolean isLessT(int value){
            return this.value < value;
        }

        public boolean valueEquals(int value){
            return this.value == value;
        }
    }
}

