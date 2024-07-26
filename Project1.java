import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.Container;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.io.IOException;

public class Project1 {

  public static void main(String args[]) {

    String[] Solutions = new String[100];
    Solutions = inputFromFile("P1input.txt");
    String value = Solutions[0];
    // Solutions[0]="latipmo";

    PuzzleGUI pg = new PuzzleGUI();
    print(pg, Solutions);

  }

  public static void print(PuzzleGUI pg, String List[]) {
    String subjectLetters = List[0];
    char firstLetter=subjectLetters.charAt(0);
   int score=0;
    ArrayList<String> correctInputs = new ArrayList<>();
    Container myContentPane = pg.getContentPane();
    TextArea myTextArea = new TextArea();
    TextArea mySubscripts = new TextArea();
    myContentPane.add(myTextArea);
    myContentPane.add(mySubscripts);

    myTextArea.append(List[0]);
    String userInput = JOptionPane.showInputDialog("Type anything to start game");

    while (!userInput.equalsIgnoreCase("Stop")) { // loop to keep on running so user can constantly put inputs

      userInput = JOptionPane.showInputDialog("guess a word or Stop to quit");

      boolean z = true;
      while (z && !userInput.equalsIgnoreCase("Stop")) {

       // first letter must be in all cases
       if(!userInput.contains(String.valueOf(firstLetter))) {
        JOptionPane.showMessageDialog(null, "Invalid input! Word does not contain first letter.");
        break;
       }

       
       
        // letter case
        boolean validLetters = true;
        for (int i = 0; i < userInput.length(); i++) {
          if (!subjectLetters.contains(String.valueOf(userInput.charAt(i)))) {
            validLetters = false;
            break;
          }
        }

        if (!validLetters) {
          JOptionPane.showMessageDialog(null, "Invalid input! Word contains invalid letters.");
          break;
        }

        // length case
        if (userInput.length() < 5) {
          JOptionPane.showMessageDialog(null,
              "Users guess is less than five letters, guess a word again or Stop to quit");
          break;
        }
        

        // solutions case
        boolean d = false;
        for (int i = 0; i < List.length - 1; i++) {
          if (userInput.equalsIgnoreCase(List[i])) {
            correctInputs.add(userInput);
            // c++;
            // mySubscripts.append(userInput); // adds userInput to gui
            d = true;

          }
        }

        if (d == false) {
          JOptionPane.showMessageDialog(null,
              "Users guess is not in the solutions, guess a word again or Stop to quit");
          break;
        }

        // contains all letters
        // boolean b=true;
        // for(int i=0;i<subjectLetters.length();i++){
        //   char letter=subjectLetters.charAt(i);
        //   if(!userInput.contains(String.valueOf(letter))) {
        //     b=false;
        //     break;
        // }
        // if(b==true) score++; 
        // else 
        //   score+=3;
        //contains all letters
        // boolean b=true;
        // for(int i=0;i<userInput.length();i++){
        //   char c=userInput.charAt(i);
        //   if(!subjectLetters.contains(String.valueOf(c))) {
        //     b=false;
        //   }
        // }
        // if(b==false) score++;
        // else score+=3;;


        // double for loop
        boolean contains=true;
        for (int i=0;i<userInput.length();i++){
          for (int j=0;j<subjectLetters.length();j++){
            if(!String.valueOf(userInput.charAt(i)).equals(String.valueOf(subjectLetters.charAt(j)))) contains=false;
          }
        }
        if(contains==false) score++;
        else score=score+3;
        
        mySubscripts.setText("");
      for (String correctInput : correctInputs) {
        mySubscripts.append(correctInput + "\n");
      }
      mySubscripts.append("Your score is: " + score);
        userInput = JOptionPane.showInputDialog("guess a word or Stop to quit");
      }
      

    }
  }

    /*
     * for (String correctInput : correctInputs) {
     * mySubscripts.append(correctInput + "\n");
     * }
     * mySubscripts.append("Your score is: " + correctInputs.size());
     */
  

  public static String[] inputFromFile(String filename) { // inputTextFile method to take in data from text file and
    // store them in string array
    TextFileInput in = new TextFileInput(filename);
    String line = in.readLine();

    String[] List = new String[100]; // array where we will store the values
    int i = 0;

    while (line != null) {
      StringTokenizer myTokens = new StringTokenizer(line, ","); // implement a tokenizer to break apart any texts
      // with commas(,)

      while (myTokens.hasMoreTokens()) {

        List[i] = myTokens.nextToken();
        i++;
      }
      line = in.readLine();
    }
    in.close();
    return List;

  }

}