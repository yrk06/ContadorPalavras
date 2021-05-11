import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import Utils.ArvoreAVL;
import Utils.ArvoreBinaria;

public class Projeto1 {

    public static void main(String[] args){

        ArvoreAVL colecao = new ArvoreAVL();

        Scanner input = new Scanner(System.in);

        System.out.println("insira o caminho para a pasta selecionada:");

        String folder_path = input.nextLine();
        File folder = new File(folder_path);

        for(File file_entry:folder.listFiles()){
            System.out.println(file_entry.getName());
            read_words(file_entry,colecao);
        }
        colecao.print();
        input.close();
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
            if(arvore.contains(w)){
                continue;
            }
            arvore.insert(w);
        }
        
        
    }
}