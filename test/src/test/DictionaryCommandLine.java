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
import java.util.*;

public class DictionaryCommandLine extends DictionaryCommanline1 {
    List<Word>searcher = new ArrayList<Word>();
    String for_search = new String();
    public void dictionaryBasic()
    {  
       super.insertFromCommandline();
       super.showAllWords();
    }
    public void dictionarySearcher()
    {
      //Scanner input = new Scanner(System.in);
      //for_search = input.nextLine();
      Word[] equal_Search = new Word[this.english_Vietnamese.sizeDictionary];
      int size_search = 0;
      for(int i = 0;i < this.english_Vietnamese.sizeDictionary;++i) {
        Word word_search = new Word();
        word_search.setWord(this.english_Vietnamese.words[i]);
        if(word_search.getTarget().indexOf(for_search)!= -1)
        {
           equal_Search[size_search] = new Word();
           equal_Search[size_search].setWord(word_search);
           size_search++;
        }
      }
      Sort quick = new Sort();
      quick.sortWord(equal_Search ,for_search ,0 ,size_search - 1);
      for(int i = 0;i < size_search;++i)
      {
         this.searcher.add(equal_Search[i]);
         System.out.println(equal_Search[i].getTarget() + "|" + equal_Search[i].getExpain());
      }
    }
    public void dictionaryAdvanced()
    {
      super.insertFromFile();
      super.showAllWords();
      super.dictionaryLookup();
    }
    public void dictionaryInsert()
    {
        super.insertFromFile();
    }
}
