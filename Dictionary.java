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
public class Dictionary {
    Word words[] = new Word[10000];
    int sizeDictionary = 0;
    public void new_Word()
    {
        for(int i = 0;i < 1000;++i)
        {
            this.words[i] = new Word();
        }
    }
}
