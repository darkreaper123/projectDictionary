/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

/**
 *
 * @author DELL
 */
import java.util.Scanner;
import java.io.File;  
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
public class DictionaryManagement {
    Dictionary english_Vietnamese = new Dictionary();
    public void insertFromCommandline()
    {
        english_Vietnamese.new_Word();
        Scanner input = new Scanner(System.in);
        int word_number = input.nextInt();
        for(int i = 0; i < word_number ; ++i)
        {
             Scanner line_One = new Scanner(System.in);
             Scanner line_Two = new Scanner(System.in);
             String target = line_One.nextLine();
             String expain = line_Two.nextLine();
             this.english_Vietnamese.words[i].setTarget(target);
             this.english_Vietnamese.words[i].setExpain(expain);
             this.english_Vietnamese.sizeDictionary++;
        }
    }
    public void insertFromFile()
    {
        english_Vietnamese.new_Word();
        try {
             File dictionaries = new File("dictionaries.txt");
             Scanner readFile = new Scanner(dictionaries);
             while(readFile.hasNextLine())
             {
              String readOne = readFile.nextLine();
              System.out.println(readOne);
              String[] readTwo = readOne.trim().split("\\|");
              String target = readTwo[0];
              String expain = readTwo[1];
              this.english_Vietnamese.words[this.english_Vietnamese.sizeDictionary].setTarget(target);
              this.english_Vietnamese.words[this.english_Vietnamese.sizeDictionary].setExpain(expain);
              this.english_Vietnamese.sizeDictionary ++;
             }
        }
        catch (FileNotFoundException e) {
              System.out.println("An error occurred.");
              e.printStackTrace();
              System.out.println(1);
        }
    }
    public void dictionaryLookup()
    {
      Scanner input = new Scanner(System.in);
      String search = input.nextLine();
      for(int i = 0;i < this.english_Vietnamese.sizeDictionary;++i)
      {
        if(search.equals(this.english_Vietnamese.words[i].getTarget()))
        {
          System.out.println(this.english_Vietnamese.words[i].getTarget() + "|" + this.english_Vietnamese.words[i].getExpain());
          break;
        }
      }
    }
    public void dictionaryExportToFile()
    {
      try
      {
        FileWriter save = new FileWriter("dictionary_save.txt");
        String show_All = "No|English|Vietnamese\n";
         for(int i = 0;i < english_Vietnamese.sizeDictionary;++i)
         {
              show_All += Integer.toString(i+1)+"|" + this.english_Vietnamese.words[i].getTarget() + "|" + this.english_Vietnamese.words[i].getExpain() + "\n";
         }
         save.write(show_All);
         save.close();
      }
      catch (IOException e) 
      {
      System.out.println("An error occurred.");
      e.printStackTrace();
      }
      
    }
}
