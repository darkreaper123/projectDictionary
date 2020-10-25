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
public class Word {
    private String word_target , word_expain ;  
    public String getTarget()
    {
        return this.word_target;
    }
    public String getExpain()
    {
        return this.word_expain;
    }
    public void setTarget(String word_target)
    {
        this.word_target = word_target;
    }
    public void setExpain(String word_expain )
    {
        this.word_expain = word_expain;
    }
    public void setWord(Word other)
    {
       this.word_target = other.getTarget();
       this.word_expain = other.getExpain();
    }
}
