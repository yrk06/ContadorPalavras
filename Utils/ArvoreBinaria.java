package Utils;

import java.util.Arrays;

/* Adaptado pra string */
public class ArvoreBinaria {
    Node root;

    public ArvoreBinaria(){
        this.root = null;
    }

    public void insert(String info){
        if(root == null){
            Node nodeToAdd = new Node(info);
            root = nodeToAdd;
            return;
        }
        root.insert_element(info);
    }

    public void print(){
        if(root != null){
            root.print(0);
        }
    }

    public Node[] percorrer_preorder(){
        if(this.root == null){
            return null;
        }
        Node[] result = root.percorrer_preorder();
        return result;
    }

    public Node[] percorrer_inorder(){
        if(this.root == null){
            return null;
        }
        Node[] result = root.percorrer_inorder();
        return result;
    }

    public Node[] percorrer_posorder(){
        if(this.root == null){
            return null;
        }
        Node[] result = root.percorrer_posorder();
        return result;
    }

    public void remove_biggest(){
        if (root == null){
            return;
        }
        Node previous = root;
        Node current = root;
        while(true){
            if(current.right == null){
                if(current == root){
                    root = null;
                    return;
                }
                previous.right = null;
                return;
            }
            previous = current;
            current = current.right;
        }
    }

    public void remove_smallest(){
        if (root == null){
            return;
        }
        Node previous = root;
        Node current = root;
        while(true){
            if(current.left == null){
                if(current == root){
                    root = null;
                    return;
                }
                previous.left = null;
                return;
            }
            previous = current;
            current = current.left;
        }
    }

    public void remove_value(String value){
        Node previous = root;
        Node current = root;
        boolean was_left = false;
        while(current != null){
            if(value == current.info){
                if(current == root){
                    root = null;
                    return;
                }
                if (was_left) previous.left = null;
                else previous.right = null;
                return;
            }

            int max = (current.info.length() < value.length()) ? current.info.length():value.length();

            for(int char_pos = 0; char_pos<max;char_pos++){

                if (value.charAt(char_pos) > current.info.charAt(char_pos)){
                    previous = current;
                    current = current.right;
                    was_left = false;
                    break;
                }
                else if (value.charAt(char_pos) < current.info.charAt(char_pos)){
                    previous = current;
                    current = current.left;
                    was_left = true;
                    break;
                }


            }
        }
    }
    
    public String get_biggest(){
        if (root == null){
            return "";
        }
        Node current = root;
        while(true){
            if(current.right == null){
                return current.info;
            }
            current = current.right;
        }
    }

    public String get_smallest(){
        if (root == null){
            return "";
        }
        Node current = root;
        while(true){
            if(current.left == null){
                return current.info;
            }
            current = current.left;
        }
    }

    public boolean contains(String value){
        Node current = root;
        while(current != null){
            if(value.equals(current.info)){
                return true;
            }
            int max = (current.info.length() < value.length()) ? current.info.length():value.length();

            for(int char_pos = 0; char_pos<max;char_pos++){

                if (value.charAt(char_pos) > current.info.charAt(char_pos)){
                    current = current.right;
                    break;
                }
                else if (value.charAt(char_pos) < current.info.charAt(char_pos)){
                    current = current.left;
                    break;
                }


            }

        }
        return false;
    }

    public Node get_node_by_value(String value){
        Node current = root;
        while(current != null){
            if(value == current.info){
                return current;
            }
            int max = (current.info.length() < value.length()) ? current.info.length():value.length();

            for(int char_pos = 0; char_pos<max;char_pos++){

                if (value.charAt(char_pos) > current.info.charAt(char_pos)){
                    current = current.right;
                    break;
                }
                else if (value.charAt(char_pos) < current.info.charAt(char_pos)){
                    current = current.left;
                    break;
                }


            }
        }
        return null;
    }

    public Node get_node_by_path(int path,int size){
        Node current = root;
        int depth = 0;
        while(current != null){
            if (depth == size){
                return current;
            }
            boolean direction = ( ((path >> depth) & 1) == 1) ? true : false;

            if (direction){
                current = current.right;
                current = current.left;
            }
            else {
                current = current.left;
            }
            depth++;
        }
        return current;
    }

    public int size(){
        if (root == null){
            return -1;
        }
        return root.size();
    }
    
    public class Node {
        Node left;
        Node right;
        public String info;

    
        public Node(String info){
            this.info = info;
            this.right = null;
            this.left = null;
        }

        public void insert_element(String info){
            int max = (this.info.length() < info.length()) ? this.info.length():info.length();

            for(int char_pos = 0; char_pos<max;char_pos++){

                if (info.charAt(char_pos) > this.info.charAt(char_pos)){
                    if (this.right != null){
                        this.right.insert_element(info);
                        return;
                    }
                    this.right = new Node(info);
                    return;
                }
                else if (info.charAt(char_pos) < this.info.charAt(char_pos)){
                    if (this.left != null){
                        this.left.insert_element(info);
                        return;
                    }
                    this.left = new Node(info);
                    return;
                }


            }
            
        }

        public void print(int depth){
            String prefix = depthPrefixes(depth,false);
            System.out.printf("%s%s\n",prefix,this.info);

            if(this.left != null){
                this.left.print(depth+1);
            }
            else{
                String prefix_l = depthPrefixes(depth+1,false);
                System.out.printf("%s%c\n",prefix_l,'\u2205');
            }

            if(this.right != null){
                this.right.print(depth+1);
            }
            else{
                String prefix_r = depthPrefixes(depth+1,true);
                System.out.printf("%s%c\n",prefix_r,'\u2205');
            }
        }

        String depthPrefixes(int depth,boolean last){
            if (depth == 0) return "";
            String result = "";
            for(int i = 1;i<depth;i++){
                result += "|    ";
            }
            result += (last) ? "└── " : "├── ";
            return result;
        }
    
        public Node[] percorrer_preorder(){
            Node[] list = {this};

            if (this.left != null){
                Node[] temp_result = this.left.percorrer_preorder();

                int list_len = list.length;
                list = Arrays.copyOf(list, list_len + temp_result.length);
                System.arraycopy(temp_result, 0, list, list_len, temp_result.length);
            }
            if (this.right != null){
                Node[] temp_result = this.right.percorrer_preorder();

                int list_len = list.length;
                list = Arrays.copyOf(list, list_len + temp_result.length);
                System.arraycopy(temp_result, 0, list, list_len, temp_result.length);
            }
            return list;
        }

        public Node[] percorrer_inorder(){
            Node[] list = {};

            if (this.left != null){
                Node[] temp_result = this.left.percorrer_preorder();

                int list_len = list.length;
                list = Arrays.copyOf(list, list_len + temp_result.length);
                System.arraycopy(temp_result, 0, list, list_len, temp_result.length);
            }

            {
                Node[] root = {this};
                int list_len = list.length;
                list = Arrays.copyOf(list, list_len + root.length);
                System.arraycopy(root, 0, list, list_len, root.length);
            }

            if (this.right != null){
                Node[] temp_result = this.right.percorrer_preorder();

                int list_len = list.length;
                list = Arrays.copyOf(list, list_len + temp_result.length);
                System.arraycopy(temp_result, 0, list, list_len, temp_result.length);
            }
            return list;
        }

        public Node[] percorrer_posorder(){
            Node[] list = {};

            if (this.left != null){
                Node[] temp_result = this.left.percorrer_preorder();

                int list_len = list.length;
                list = Arrays.copyOf(list, list_len + temp_result.length);
                System.arraycopy(temp_result, 0, list, list_len, temp_result.length);
            }

            if (this.right != null){
                Node[] temp_result = this.right.percorrer_preorder();

                int list_len = list.length;
                list = Arrays.copyOf(list, list_len + temp_result.length);
                System.arraycopy(temp_result, 0, list, list_len, temp_result.length);
            }

            {
                Node[] root = {this};
                int list_len = list.length;
                list = Arrays.copyOf(list, list_len + root.length);
                System.arraycopy(root, 0, list, list_len, root.length);
            }

            return list;
        }

        public int size(){
            int my_size = 1;

            if (this.right != null){
                my_size += this.right.size();
            }
            if (this.left != null){
                my_size += this.left.size();
            }
            return my_size;
        }
    }
}
