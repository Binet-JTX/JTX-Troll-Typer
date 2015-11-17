import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class GUI extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JLabel label;
	
	public GUI() {
		super();
		buildMainFrame();
		this.setContentPane(buildMainContentPane());
		this.setVisible(true);
		
	}
	
	private void buildMainFrame(){
		setTitle("JTX Troll Typer");
		setSize(600,300);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
		//setOpacity(0);
		
		
	}
	
	private JPanel buildMainContentPane(){
		Color black = Color.BLACK;
		JPanel backPanel = new JPanel();
		backPanel.setBackground(black);
		backPanel.setSize(600,300);
		backPanel.setOpaque(true);
		backPanel.setLayout(new BorderLayout());
		label = new JLabel();
		setTextCenter("Type your troll text here");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setVerticalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Arial", Font.BOLD, 30));
		label.setForeground(Color.WHITE);
		label.setBackground(Color.black);
		label.setOpaque(true);
		backPanel.add(label, BorderLayout.CENTER);
		return backPanel;
	}
	
	public void setTextCenter(String text){
		text = text.replaceAll("\n", "<br>");
		label.setText("<html><div style='text-align: center;'>"+text+"</html>");
	}
	
	public String getCurrentText(){
		return label.getText(); 
	}
	
	public void setTextColor(Color color){
		label.setForeground(color);
	}
	
	public void setBackgroundColor(Color color){
		label.setBackground(color);
	}
	
	public void setTextSize(int textSize){
		label.setFont(new Font("Arial", Font.BOLD, textSize));
	}
}
