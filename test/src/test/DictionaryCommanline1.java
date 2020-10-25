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
public class DictionaryCommanline1 extends DictionaryManagement {
    public void showAllWords()
    {
         String show_All = "No|English|Vietnamese\n";
         for(int i = 0;i < english_Vietnamese.sizeDictionary;++i)
         {
              show_All += Integer.toString(i+1)+"|" + this.english_Vietnamese.words[i].getTarget() + "|" + this.english_Vietnamese.words[i].getExpain() + "\n";
         }
         System.out.print(show_All);
    }
}
