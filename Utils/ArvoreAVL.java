package Utils;

public class ArvoreAVL extends ArvoreBinaria  {
    
    public ArvoreAVL(){
    }

    @Override
    public void insert(String info){
        if(root == null){
            Node nodeToAdd = new Node(info);
            root = nodeToAdd;
            return;
        }
        root.insert_element(info);
        balanceamento(root);
    }
    
    public int checar_balanceamento(Node x) {
		// Node l = x.left;
		// Node r = x.right;
		if (x == null) {
			return 0;
		}
        int left_size = 0;
        int right_size = 0;
		if (x.left != null) {
            left_size = x.left.size();
		}
		if (x.right != null) {
			right_size = x.right.size();
		}
		return (left_size - right_size);
	}

	public void balanceamento(Node x) {
        int fator_balanceamento = checar_balanceamento(x);

        if (Math.abs(fator_balanceamento) > 1){
            if (fator_balanceamento > 0){
                //Desbalanceado pra esquerda
                if (Math.abs(checar_balanceamento(x.left)) > 1){
                    balancear(x.left);
                }
                else{
                    balancear(x);
                }
            } else {
                //Desbalanceado para direita
                if (Math.abs(checar_balanceamento(x.right)) > 1){
                    balancear(x.right);
                }
                else{
                    balancear(x);
                }
            }
        }
	}

	public void balancear(Node x) {
		if (x == null) {
			return;
		}
        //Guardar a referencia da árvore para consertar inconsistencias
        Node[] references = this.percorrer_inorder();
        Node parent = null;
        for(Node ref : references){
            if (ref.right == x || ref.left == x){
                parent = ref;
            }
        }
		if (checar_balanceamento(x) > 1) {
			
            //Peca a direita vira o Nroot
            Node newRoot = x.left;
            //O que ta a direita do new Root vai ficar a esquerda do root atual
            Node nRootOldRight = newRoot.right;
            x.left = nRootOldRight;

            //O root atual fica a direita do new root
            newRoot.right = x;

            //caso especial
            if (x == root){
                root = newRoot;
                return;
            }
            //Precisa arrumar as referencias
            if (parent != null){
                if (parent.right == x){
                    parent.right = newRoot;
                }
                if (parent.left == x){
                    parent.left = newRoot;
                }
            }
            
            // rotação direita
			/*Node q = x.left;
			Node aux = q.right;
			q.right = x;
			x.left = aux;*/

		} else {
            //Peca a direita vira o Nroot
            Node newRoot = x.right;
            //O que ta a esquerda do new Root vai ficar a direita do root atual
            Node nRootOldLeft = newRoot.left;
            x.right = nRootOldLeft;

            //O root atual fica a esquerda do new root
            newRoot.left = x;

            //caso especial
            if (x == root){
                root = newRoot;
            }

            //Precisa arrumar as referencias
            if (parent != null){
                if (parent.right == x){
                    parent.right = newRoot;
                }
                if (parent.left == x){
                    parent.left = newRoot;
                }
            }

			// rotação esquerda
			/*Node q = x.right;
			Node aux = q.left;
			q.left = x;
			x.right = aux;*/
		}
	}
}
