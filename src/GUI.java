import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class GUI extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Timer timer;
	private boolean speed;
	private boolean epileptic;

	
	private JLabel label;
	
	public GUI() {
		super();
		buildMainFrame();
		this.setContentPane(buildMainContentPane());
		this.setVisible(true);
		this.speed = false;
		this.epileptic = false;
		
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
		StringBuilder sb = new StringBuilder();
		//replace the *word* with italic words
		boolean firstItalic = true;
		boolean firstBold = true;
		for(int i=0; i<text.length(); i++){
			
			if (text.charAt(i)=='*') {
				if (i!=text.length()-1 && text.charAt(i+1)=='*'){
					sb.append(firstBold?"<b>":"</b>");
					i++;
					firstBold=!firstBold;
				}
				else{
					sb.append(firstItalic?"<i>":"</i>");
					firstItalic=!firstItalic;
				}
			}
			else if (text.charAt(i)=='_') {
				if (i!=text.length()-1 && text.charAt(i+1)=='_'){
					sb.append(firstBold?"<b>":"</b>");
					i++;
					firstBold=!firstBold;
				}
				else{
					sb.append(firstItalic?"<i>":"</i>");
					firstItalic=!firstItalic;
				}
			}
			else if (text.charAt(i)=='\\'){
				if (i!=text.length()-1) sb.append(text.charAt(++i));
				else sb.append('\\');
			}
			else {
				
				sb.append(text.charAt(i));
			}
		}
		label.setText("<html><div style='text-align: center;'>"+sb.toString()+"</html>");
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
		label.setFont(new Font("Arial", Font.PLAIN, textSize));
	}
	
	public void startTimer(){
		
		timer = new Timer(50, new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent ae) {
		    	
		        Color background = label.getBackground();
		    	int red =background.getRed();
		    	int green = background.getGreen();
		    	int blue = background.getBlue();
		    	float[] hsb = new float[3];
		    	Color.RGBtoHSB(red, green, blue, hsb);
		    	if (epileptic){
		    		float oldhsb = hsb[0];
		    		do {
		    			hsb[0] = (float) Math.random();
		    		} while(Math.abs(hsb[0]-oldhsb)<0.3);
		    	}
		    	else hsb[0]+= speed ? 0.05:0.01;
		    	label.setBackground(new Color(Color.HSBtoRGB(hsb[0],1, 1)));
		    	label.setForeground(new Color(Color.HSBtoRGB(hsb[0]+0.5f,1,1)));;
		        label.repaint();
		    }
		});
		
		timer.start();
	}
	
	private void stopTimer(){
		timer.stop();
	}
	
	/**
	 * Sets the animation to quick (true) or slow (false)
	 * @param b
	 */
	public void setSpeed(boolean b){
		speed = b;
	}
	
	/**
	 * Sets the animation of the background to be random
	 * @param b
	 */
	public void setEpileptic(boolean b){
		epileptic = b;
	}
}
