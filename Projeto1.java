import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import Utils.ArvoreAVL;

public class Projeto1 {

    public static void main(String[] args) throws Exception{
        ArvoreAVL main;
        try{
            main = loadTree();
            
        } catch (IOException e){

            Scanner input = new Scanner(System.in);

            System.out.println("insira o caminho para a pasta selecionada:");

            String folder_path = input.nextLine();

            input.close();

            main = generateTree(folder_path);
            saveTree(main);
        }
        main.print();
        makeConsult(main);
    }
    

    public static void read_words(File arquivo,ArvoreAVL arvore){
        String content;
        try{
            content = Files.readString(Paths.get(arquivo.getAbsolutePath()));
        }
        catch(Exception e){
            e.printStackTrace();
            return;
        }
        content = content.replace("\n"," ").replace("\r","");
        String[] words = content.split(" ");

        for(String w : words){
            if(w.equals("")){
                continue;
            }
            if(!arvore.contains(w)){
                arvore.insert(w);
            }
            arvore.get_node_by_value(w).add_reference(arquivo.getName());
            
        }
        
        
    }

    public static ArvoreAVL generateTree(String folder_path){
        ArvoreAVL colecao = new ArvoreAVL();
        File folder = new File(folder_path);

        for(File file_entry:folder.listFiles()){
            System.out.println(file_entry.getName());
            read_words(file_entry,colecao);
        }   
        return colecao;
    }

    public static void saveTree(ArvoreAVL tree) throws IOException {
        FileOutputStream fileOutput = new FileOutputStream("Tree.save");
        ObjectOutputStream out = new ObjectOutputStream(fileOutput);
        out.writeObject(tree);
        out.close();
        fileOutput.close();
    }

    public static ArvoreAVL loadTree() throws IOException, ClassNotFoundException {
        FileInputStream fileIn = new FileInputStream("Tree.save");
        ObjectInputStream in = new ObjectInputStream(fileIn);
        ArvoreAVL saved = (ArvoreAVL) in.readObject();
        in.close();
        fileIn.close();
        return saved;
    }


    

    public static void makeConsult(ArvoreAVL tree){
        Scanner input = new Scanner(System.in);
        while(true){
            

            System.out.println("insira o termo para pesquisa:");

            String word = input.nextLine();

            if (word.equals("_exit_")){
                break;
            }

            if(!tree.contains(word)){
                System.out.printf("-------- %s -------- \n No Matches \n-------- \n",word);
            }
            else{
                tree.get_node_by_value(word).where.print();
            }
        }
        input.close();
    }
}