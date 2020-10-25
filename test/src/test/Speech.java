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
import java.util.Locale; 
import javax.speech.Central; 
import javax.speech.synthesis.Synthesizer; 
import javax.speech.synthesis.SynthesizerModeDesc; 
public class Speech {
    Synthesizer speak_english ;
    Speech()
    {
        try
        {
            System.setProperty( 
                "freetts.voices", 
                "com.sun.speech.freetts.en.us"
                    + ".cmu_us_kal.KevinVoiceDirectory"); 
  
            // Register Engine 
            Central.registerEngineCentral( 
                "com.sun.speech.freetts"
                + ".jsapi.FreeTTSEngineCentral"); 
            speak_english= Central.createSynthesizer( 
                    new SynthesizerModeDesc(Locale.US)); 
            speak_english.allocate();
        }
        catch (Exception e) { 
            e.printStackTrace(); 
        } 
    }
    public void speak(String word_new)
    {
        try
        {
            speak_english.speakPlainText( 
               word_new, null);
            speak_english.waitEngineState( 
                Synthesizer.QUEUE_EMPTY); 
        }
        catch (Exception e) { 
            e.printStackTrace(); 
        } 
        
    }
    public void reset()
    {
        try
        {
            speak_english.deallocate();
        }
        catch (Exception e) { 
            e.printStackTrace(); 
        } 
    }
}
