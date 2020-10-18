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
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import javax.swing.JScrollBar;
import java.awt.event.AdjustmentListener;
import java.awt.event.AdjustmentEvent;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.util.Locale; 
import javax.speech.Central; 
import javax.speech.synthesis.Synthesizer; 
import javax.speech.synthesis.SynthesizerModeDesc; 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
public class DictionaryApplication {
    DictionaryCommandLine app = new DictionaryCommandLine();
    JButton search;
    JButton for_add = new JButton("Add");
    JButton for_delete = new JButton("Delete");
    JFrame screen = new JFrame("English - Vietnamese dictionary");
    JTextField word_search = new JTextField();
    JTextField word_delete = new JTextField();
    JTextField word_english = new JTextField();
    JTextField word_vietnam = new JTextField();
    JTextField word_addEnglish = new JTextField();
    JTextField word_addVietnam = new JTextField();
    JTextField word_API = new JTextField();
    JTextField word_translate = new JTextField();
    JButton accept_add = new JButton("Add to list");
    JButton accept_remove = new JButton("Remove from list");
    JButton[] show = new JButton[100];
    String[] explain_show = new String[100];
    JButton for_next = new JButton("Next");
    JButton speak_engSAR = new JButton("English");
    JButton speak_vietSAR = new JButton("Vietnam");
    JButton for_API = new JButton("Test API translate");
    int sizeShow = 0;
    int width_screen = 1280;
    int height_screen = 768;
    int max_buttonScreen = 18;
    int scroll_extent = 1000;
    //int size_save;
    int id_next = 0;
    Speech ttp = new Speech();
    Api trans = new Api();
    DictionaryApplication()
    {
        app.dictionaryInsert();
        screen.setLayout(null);
        search = new JButton("Search");
        search.setVisible(true);
        search.setBounds(0,0,250,50);
        screen.setVisible(true);
        for_add.setBounds(250,0,125,50);
        for_delete.setBounds(375,0,125,50);
        screen.add(search);
        screen.add(for_add);
        screen.add(for_delete);
        word_search.setBounds(0,50,500,50);
        word_english.setBounds(500,50,300,50);
        word_vietnam.setBounds(800,50,300,50);
        word_english.setVisible(false);
        word_vietnam.setVisible(false);
        word_addEnglish.setVisible(false);
        word_addEnglish.setBounds(800, 0 ,850 ,100);
        word_addVietnam.setVisible(false);
        word_addVietnam.setBounds(800, 100 ,850 ,100);
        accept_add.setBounds(800 ,200 ,200 ,100);
        accept_add.setVisible(false);
        word_delete.setBounds(800, 0 ,850 ,100);
        word_delete.setVisible(false);
        accept_remove.setBounds(800 ,100 ,200 ,100);
        accept_remove.setVisible(false);
        for_next.setBounds(500,0,80,30);
        for_next.setVisible(false);
        speak_engSAR.setBounds(500,100,100,30);
        speak_engSAR.setVisible(false);
        speak_vietSAR.setBounds(800,100,100,30);
        speak_vietSAR.setVisible(false);
        for_API.setBounds(1500 ,0,400,50);
        word_API.setBounds(800,400,700,60);
        word_API.setVisible(false);
        word_translate.setBounds(800,460,700,60);
        word_translate.setVisible(false);
        word_translate.setEditable(false);
        for(int i = 2;i <= 21;++i)
        {
             JButton a = new JButton();
             a.setBounds(0 ,50 * i ,500 ,50);
             a.setVisible(false);
             show[sizeShow] = a;
             screen.add(show[sizeShow]);
             sizeShow++;
        }
        screen.add(word_search);
        screen.add(word_addEnglish);
        screen.add(word_addVietnam);
        screen.add(accept_add);
        screen.add(word_delete);
        screen.add(accept_remove);
        screen.setSize(width_screen ,height_screen);
        screen.add(for_next);
        screen.add(word_english);
        screen.add(word_vietnam);
        screen.add(speak_engSAR);
        screen.add(speak_vietSAR);
        screen.add(for_API);
        screen.add(word_API);
        screen.add(word_translate);
    }
    public void removeSearcher()
    {
        for(int i = 0;i < sizeShow;++i)
        {
            show[i].setVisible(false);
            show[i].setText("");
        }
    }
    public void runApplication()
    {
        word_search.addKeyListener(new KeyAdapter()
        {
            
            public void keyReleased(KeyEvent e)
            {
                word_translate.setVisible(false);
                word_API.setVisible(false);
                speak_engSAR.setVisible(false);
                speak_vietSAR.setVisible(false);
                word_vietnam.setVisible(false);
                word_english.setVisible(false);
                id_next = 0;
                for_next.setVisible(false);
                app.searcher = new ArrayList<Word>();
                app.for_search = word_search.getText();
                app.dictionarySearcher();
                String[] save_wordEnglish = new String[100];
                String[] save_wordVietnam = new String[100];
                int i = 0;
                int j = 0;
                for(Word s : app.searcher)
                {
                    if(i > max_buttonScreen - 1) break;
                    if(i == max_buttonScreen - 1) show[i].setText("See all result");
                    else
                    {
                        show[i].setText(s.getTarget());
                        show[i].setBackground(Color.WHITE);
                    }
                    explain_show[i] = s.getExpain();
                    show[i].setVisible(true);
                    String target = show[i].getText();
                    String expain = explain_show[i];
                    final int save_i = i;
                    show[i].addActionListener(new ActionListener()
                    {
                        public void actionPerformed(ActionEvent e)
                        {
                            if(save_i == max_buttonScreen - 1) for_next.setVisible(true);
                            else for_next.setVisible(false);
                            word_vietnam.setVisible(true);
                            word_english.setVisible(true);
                            word_vietnam.setEditable(false);
                            word_english.setEditable(false);
                            speak_engSAR.setVisible(true);
                            speak_vietSAR.setVisible(true);
                            Word[] save = new Word[app.searcher.size()];
                            int id = 0;
                            for(Word s : app.searcher)
                            { 
                                save[id] = new Word();
                                save[id].setWord(s);
                                id++;
                            }
                            if(save_i + 1 < max_buttonScreen) id_next = save_i;
                            else id_next = 0;
                            word_english.setText(save[id_next].getTarget());
                            word_vietnam.setText(save[id_next].getExpain());
                            speak_engSAR.addMouseListener(new MouseAdapter()
                            {
                               public void mouseReleased(MouseEvent e)
                               {
                                   ttp.speak(word_english.getText());
                               }
                            });
                            speak_vietSAR.addMouseListener(new MouseAdapter()
                            {
                               public void mouseReleased(MouseEvent e)
                               {
                                   ttp.speak(word_vietnam.getText());
                               }
                            });
                            id_next+=1;
                            for_next.addActionListener(new ActionListener()
                            {
                                public void actionPerformed(ActionEvent e)
                                {
                                    word_english.setText(save[id_next].getTarget());
                                    word_vietnam.setText(save[id_next].getExpain());
                                    id_next = (id_next + 1)%app.searcher.size();
                                }
                            });
                        }
                    });
                    final int size_save = i;
                    show[i].addMouseListener(new MouseAdapter()
                    {
                       public void mouseEntered(MouseEvent e)
                       {
                           show[size_save].setBackground(Color.GREEN);
                       }
                        public void mouseExited(MouseEvent e)
                       {
                           show[size_save].setBackground(Color.WHITE);
                       }
                    });
                    i++;
                }   
                if(app.for_search.replace(" ","").equals("")) i = 0;
                while(i < sizeShow)
                {
                    show[i].setText("");
                    show[i].setVisible(false);
                    explain_show[i] = "";
                    i++;
                }
                
            }
        });
        for_add.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                word_translate.setVisible(false);
                word_API.setVisible(false);
                speak_engSAR.setVisible(false);
                speak_vietSAR.setVisible(false);
                word_vietnam.setVisible(false);
                word_english.setVisible(false);
                for_next.setVisible(false);
                word_addEnglish.setVisible(true);
                word_addVietnam.setVisible(true);
                accept_add.setVisible(true);
                word_delete.setVisible(false);
                word_search.setVisible(false);
                word_search.setText("");
                removeSearcher();
                accept_remove.setVisible(false);
                word_addEnglish.addKeyListener(new KeyAdapter()
                {
                   public void keyReleased(KeyEvent e)
                   {
                       word_addVietnam.addKeyListener(new KeyAdapter()
                       {
                           public void keyReleased(KeyEvent e)
                           {
                               accept_add.addActionListener(new ActionListener()
                               {
                                   public void actionPerformed(ActionEvent e)
                                   {
                                       String word_english = word_addEnglish.getText();
                                       String word_vietnam = word_addVietnam.getText();
                                       int size = app.english_Vietnamese.sizeDictionary;
                                       Word newWord = new Word();
                                       newWord.setTarget(word_english);
                                       newWord.setExpain(word_vietnam);
                                       app.english_Vietnamese.words[size].setWord(newWord);
                                       app.english_Vietnamese.sizeDictionary++;
                                       app.showAllWords();
                                       word_addEnglish.setText("");
                                       word_addVietnam.setText("");
                                   }
                               });
                           }
                       });
                   }
                 });
            }
        });
        search.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                word_translate.setVisible(false);
                word_API.setVisible(false);
                speak_engSAR.setVisible(false);
                speak_vietSAR.setVisible(false);
                word_vietnam.setVisible(false);
                word_english.setVisible(false);
                for_next.setVisible(false);
                word_addEnglish.setVisible(false);
                word_addVietnam.setVisible(false);
                accept_add.setVisible(false);
                word_delete.setVisible(false);
                word_search.setVisible(true);
                word_search.setText("");
                accept_remove.setVisible(false);
            }
        });
        for_delete.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                word_translate.setVisible(false);
                word_API.setVisible(false);
                speak_engSAR.setVisible(false);
                speak_vietSAR.setVisible(false);
                word_vietnam.setVisible(false);
                word_english.setVisible(false);
                for_next.setVisible(false);
                word_addEnglish.setVisible(false);
                word_addVietnam.setVisible(false);
                accept_add.setVisible(false);
                word_delete.setVisible(true);
                word_search.setVisible(false);
                word_search.setText("");
                removeSearcher();
                accept_remove.setVisible(true);
                word_delete.addKeyListener(new KeyAdapter()
                {
                    public void keyReleased(KeyEvent e)
                    {
                        accept_remove.addActionListener(new ActionListener()
                        {
                            public void actionPerformed(ActionEvent e)
                            {
                                for(int i = 0;i < app.english_Vietnamese.sizeDictionary;++i)
                                {
                                    if(app.english_Vietnamese.words[i].getTarget().equals(word_delete.getText()))
                                    {
                                        int size = app.english_Vietnamese.sizeDictionary;
                                        int j = i;
                                        while(j < size - 1)
                                        {
                                            app.english_Vietnamese.words[j].setWord(app.english_Vietnamese.words[j + 1]);
                                            j++;
                                        }
                                        app.english_Vietnamese.words[j] = new Word();
                                        app.english_Vietnamese.sizeDictionary-=1;
                                    }
                                }
                                word_delete.setText("");
                            }
                        });
                    }
                });
            }
        });
        for_API.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                speak_engSAR.setVisible(false);
                speak_vietSAR.setVisible(false);
                word_vietnam.setVisible(false);
                word_english.setVisible(false);
                for_next.setVisible(false);
                word_addEnglish.setVisible(false);
                word_addVietnam.setVisible(false);
                accept_add.setVisible(false);
                word_delete.setVisible(false);
                word_search.setVisible(false);
                word_search.setText("");
                word_API.setVisible(true);
                word_translate.setVisible(true);
                accept_remove.setVisible(false);
                word_API.addKeyListener(new KeyAdapter()
                {
                   public void keyReleased(KeyEvent e)
                   {
                       try
                       {
                           String word_get = word_API.getText();
                           word_translate.setText(trans.translate(word_get));
                           System.out.println(trans.translate(word_get));
                       }
                       catch(Exception f)
                       {
                           word_translate.setText("");
                           System.out.println(1);
                       }
                   }
                });
            }
        });
    }
}
