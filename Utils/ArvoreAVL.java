package Utils;

public class ArvoreAVL extends ArvoreBinaria  {
    
    public ArvoreAVL(){
    }

    /*
    Calcular o balanceamento em 1 nó:
        Altura Direita - Altura Esquerda
    */

    public int[] fator_balanceamento(){
        Node[] Nodes = this.percorrer_preorder();
        int[] balancing = new int[Nodes.length];

        for(int idx = 0; idx < Nodes.length;idx++){
            
        }

        return balancing;
    }
}
