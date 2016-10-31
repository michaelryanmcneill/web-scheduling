package comp110.FXCrew;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.WindowConstants;

import comp110.KarenBot; 

public class GUI extends Frame implements ActionListener, WindowListener {
	private Button btnRun;   
	public GUI(){
	  setLayout(new FlowLayout());
	  btnRun = new Button("Run");   // construct the Button component
      add(btnRun);                    // "super" Frame adds Button
      btnRun.addActionListener(this);
         // btnCount is the source object that fires ActionEvent when clicked.
         // The source add "this" instance as an ActionEvent listener, which provides
         //  an ActionEvent handler called actionPerformed().
         // Clicking btnCount invokes actionPerformed().
      addWindowListener(this);
      setTitle("COMP 110 Tool");  // "super" Frame sets its title
      setSize(500, 500);        // "super" Frame sets its initial window size
      setVisible(true);         // "super" Frame shows
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GUI app= new GUI();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		KarenBot karenBot = new KarenBot(new FXAlgo());
	    karenBot.run("week1", 100);
	}
	  @Override
	   public void windowClosing(WindowEvent evt) {
	      System.exit(0);  // Terminate the program
	   }
	 
	   // Not Used, but need to provide an empty body to compile.
	   @Override public void windowOpened(WindowEvent evt) { }
	   @Override public void windowClosed(WindowEvent evt) { }
	   @Override public void windowIconified(WindowEvent evt) { }
	   @Override public void windowDeiconified(WindowEvent evt) { }
	   @Override public void windowActivated(WindowEvent evt) { }
	   @Override public void windowDeactivated(WindowEvent evt) { }
}
