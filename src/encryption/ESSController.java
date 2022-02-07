/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package encryption;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.TextFlow;

/**
 *
 * @author nofoo
 */
public class ESSController implements Initializable {
    
    private final Random random = new Random();
    private char character = ' ';
    private char[] letters;
    private final ArrayList<Character> list = new ArrayList();
    private ArrayList<Character> randomEncryptedList = new ArrayList();
    
    @FXML
    private ComboBox S1;
    
    @FXML
    private Button gb;

    @FXML
    private TextField inb;

    @FXML
    private ComboBox msb;

    @FXML
    public TextArea outb;
    
   
    public void message() throws IOException{
       String em = S1.getSelectionModel().getSelectedItem().toString();
       String os = msb.getSelectionModel().getSelectedItem().toString();
       
       if(em.equals("ASCII")){
          if(os.equals("New Key")){
              newKey(0);
        }else if(os.equals("Get Key")){
           getKey();
       }else if(os.equals("Encrypt A Message")){
           encryptMessage();
       }else if(os.equals("Decrypt A Message")){
           decryptMessage();
       } 
      }
        
          
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       ObservableList<String> msb1 = FXCollections.observableArrayList("New Key", "Get Key" , "Encrypt A Message" , "Decrypt A Message");
       msb.setItems(msb1);
       ObservableList<String> S1L = FXCollections.observableArrayList("ASCII" , "Caesar Cipher");
       S1.setItems(S1L);
    }

    //append text so it prints out nicely in the GUI (String Version)
    void appendTextC(Character x) {
        outb.setText(outb.getText() + x);
    }
    //Character Version
    public void appendText(String t){
        outb.setText(outb.getText() + t);
    }
    
    
    //functions to call that actually make the key and stuff
    public void newKey(int t) {
        character = ' ';
        list.clear();
        randomEncryptedList.clear();
        
        for(int i = 32; i < 127; i++) {
            list.add(character);
            character++;
        }
        
        randomEncryptedList = new ArrayList(list);
        Collections.shuffle(randomEncryptedList);
        if (t == 0) {
            outb.setText("*****  NEW KEY GENERATED! *****\n");
            appendText("Select the Get Key option to view it");
        } else {
        }
    }
    
    public void getKey() {
       outb.setText("*********** SECRET ENCRYPTION KEY BELOW ***********\n\n");
       for (int i = 0; i < list.size(); i++) {
        appendTextC(list.get(i));
        }
       for (int i = 0; i < randomEncryptedList.size(); i++) {
        appendTextC(randomEncryptedList.get(i));
        }
        appendText("\n\n(Use this key to Encrypt or Decrypt a Message)"); 
    }
    
    private void encryptMessage() {
        String message = inb.getText();
        
        letters = message.toCharArray();
        
        for(int i = 0; i < letters.length; i++) {
            
            for(int e = 0; e < list.size(); e++) {
                if(letters[i] == list.get(e)) {
                    letters[i] = randomEncryptedList.get(e);
                    break;
                }
            }
        }
        outb.setText("Encrypted Message: ");
        for(char x : letters) {
            appendTextC(x);
        }
    }
    private void decryptMessage() {
        String message = inb.getText();
        
        letters = message.toCharArray();
        
        for(int i = 0; i < letters.length; i++) {
            
            for(int d = 0; d < randomEncryptedList.size(); d++) {
                if(letters[i] == randomEncryptedList.get(d)) {
                    letters[i] = list.get(d);
                    break;
                }
            }
        }
        outb.setText("Decrypted Message: "); 
        for(char x : letters) {
            appendTextC(x);
        }
    }
   
}
   
    
    


