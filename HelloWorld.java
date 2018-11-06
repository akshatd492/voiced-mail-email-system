/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import static com.sun.java.accessibility.util.AWTEventMonitor.addActionListener;
import edu.cmu.sphinx.frontend.util.Microphone;
import edu.cmu.sphinx.recognizer.Recognizer;
import edu.cmu.sphinx.result.Result;
import edu.cmu.sphinx.util.props.ConfigurationManager;
import edu.cmu.sphinx.util.props.PropertyException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;
import java.io.IOException;
import java.net.URL;
/**
 *
 * @author hp
 */
public class HelloWorld {

    public static void main(String[] args) {
        try {
            URL url;
            if (args.length > 0) {
            //    url = new File(args[0]).toURI().toURL();
                
                url = HelloWorld.class.getResource("helloworld.config.xml");
                
            } else {
                url = HelloWorld.class.getResource("helloworld.config.xml");
            }

            System.out.println("Loading...");

            ConfigurationManager cm = new ConfigurationManager(url);

	    Recognizer recognizer = (Recognizer) cm.lookup("recognizer");
	    Microphone microphone = (Microphone) cm.lookup("microphone");


            /* allocate the resource necessary for the recognizer */
            recognizer.allocate();

            /* the microphone will keep recording until the program exits */
	    if (microphone.startRecording()) {

		System.out.println
		    ("Say: (Read Mail | Wrire Mail) " );
                            
              //              +
              //       "( Bhiksha | Evandro | Paul | Philip | Rita | Will )");

		while (true) {
		    System.out.println
			("Start speaking. Press Ctrl-C to quit.\n");

                    /*
                     * This method will return when the end of speech
                     * is reached. Note that the endpointer will determine
                     * the end of speech.
                     */ 
		    Result result = recognizer.recognize();
		    
		    if (result != null) {
			String resultText = result.getBestFinalResultNoFiller();
                        
                         if(resultText.equalsIgnoreCase("Read Mail") || resultText == "Read Mail")
                        {
                            
                            
                            System.out.println("11111111");
                            
                        class ReadMailListener implements ActionListener {

        public void actionPerformed(ActionEvent arg0) {
            
            System.out.println("inside action read");
            
            GUIMailsget test = new GUIMailsget();
            test.guiMailsget();
        }
    }
                              
                        }
                         
                         else if (resultText.equalsIgnoreCase("Write Mail") || resultText == "Write Mail") {
                             System.out.println("222222222");
                              class WriteMailListener implements ActionListener {

        public void actionPerformed(ActionEvent arg0) {
            
             System.out.println("inside action read");
            
            GUIMailsend send = new GUIMailsend();
            send.guiMailsend();
        }
    }
                    
                }
                         
                         
			System.out.println("You said: " + resultText + "\n");
		    } else {
			System.out.println("I can't hear what you said.\n");
		    }
		}
	    } else {
		System.out.println("Cannot start microphone.");
		recognizer.deallocate();
		System.exit(1);
	    }
        } catch (IOException e) {
            System.err.println("Problem when loading HelloWorld: " + e);
            e.printStackTrace();
        } catch (PropertyException e) {
            System.err.println("Problem configuring HelloWorld: " + e);
            e.printStackTrace();
        } catch (InstantiationException e) {
            System.err.println("Problem creating HelloWorld: " + e);
            e.printStackTrace();
        }
    }
}
