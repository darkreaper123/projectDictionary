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
public class Sort {
    int action(Word[] words , String word_substr , int left , int right)
   {
      Word high = new Word();
      high.setWord(words[right]);
      int i = left - 1;
      for(int j = left;j < right;++j)
      {
         if(words[j].getTarget().indexOf(word_substr) <= high.getTarget().indexOf(word_substr))
         {
           i++;
           Word c = new Word();
           c.setWord(words[j]);
           words[j].setWord(words[i]);
           words[i].setWord(c);
         }
      }
      Word c = new Word();
      c.setWord(words[i + 1]);
      words[i + 1].setWord(words[right]);
      words[right].setWord(c);
      return i + 1;
   }
   void sortWord(Word[] words , String word_substr , int left , int right)
   {
      if(left <= right)
      {
        int mid = action(words,word_substr,left,right);
        sortWord(words ,word_substr ,left ,mid - 1);
        sortWord(words ,word_substr ,mid + 1 ,right);
      }
   }
}
