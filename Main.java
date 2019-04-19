package breakout;
import java.util.*;
import javax.swing.*;
public class Main {

	public static void main(String[] args) {
		//System.out.println("XXX");
		// TODO Auto-generated method stub
		Gameplay gameplay  = new Gameplay();
		JFrame obj = new JFrame();
		obj.setBounds(10,10,700,600);
		obj.setResizable(false);
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		obj.setTitle("Break Game");
		obj.add(gameplay);
		obj.setVisible(true);
	}

}
