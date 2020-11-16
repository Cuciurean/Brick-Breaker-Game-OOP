import javax.swing.JFrame;

public class Main {
	
	public static void main(String[] args)
	{
		JFrame obiect = new JFrame();
		
		Joc joc = new Joc();
		
		obiect.setBounds(10, 10, 700, 600);
		obiect.setTitle("Brick Breaker");
		obiect.setResizable(false);
		obiect.setVisible(true);
		obiect.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		obiect.add(joc);
	}
} 
