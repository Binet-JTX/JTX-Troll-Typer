import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ControlGUI extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	private GUI destinationGUI;
	private boolean autoupdate = true;
	private boolean autoupdateBackgroundColor = true;
	private boolean autoupdateTextColor = true;
	private boolean autoupdateTextSize = true;
	private boolean fullscreen = true; 
	private boolean wasFullscreen = true;
	private boolean isOnSecondScreen = false;
	private JColorChooser colorChooser;
	private Color backgroundColor = Color.black;
	private Color textColor = Color.white;
	private int textSize = 30;
	
	public ControlGUI(GUI destinationGUI) {
		super();
		this.destinationGUI = destinationGUI;
		buildMainFrame();
		this.setContentPane(buildMainContentPane());
		this.setVisible(true);
		
		
	}
	
	private void buildMainFrame(){
		setTitle("JTX Troll Typer");
		setSize(600,350);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	private JPanel buildMainContentPane(){
		JPanel backPanel = new JPanel();
		backPanel.setSize(600,350);
		backPanel.setLayout(new BoxLayout(backPanel, BoxLayout.Y_AXIS));
		backPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.LINE_AXIS));
		mainPanel.setBorder(BorderFactory.createEmptyBorder(0,0,20,0));
		final JTextArea textArea = new JTextArea("Type your troll text here");
		textArea.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent arg0) {}
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				if (autoupdate) destinationGUI.setTextCenter(textArea.getText());
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {}
		});
		textArea.setPreferredSize(new Dimension(260,200));
		textArea.setMaximumSize(new Dimension(260,200));
		textArea.setMinimumSize(new Dimension(260,200));
		mainPanel.add(textArea);
		
		JPanel settingsPanel = new JPanel();
		settingsPanel.setSize(150, 220);
		settingsPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
		settingsPanel.setLayout(new BoxLayout(settingsPanel, BoxLayout.PAGE_AXIS));
		
		//CheckBox - Mise à jour auto du texte
		JCheckBox autoUpdateCB = new JCheckBox("Mise à jour auto du texte");
		autoUpdateCB.setSelected(autoupdate);  
		autoUpdateCB.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				autoupdate = !autoupdate;	
			}
		});
		autoUpdateCB.setAlignmentX(LEFT_ALIGNMENT);
		settingsPanel.add(autoUpdateCB);
		
		//CheckBox - Mise à jour auto de la couleur du texte
		JCheckBox autoUpdateTextColorCB = new JCheckBox("Mise à jour auto de la couleur du texte");
		autoUpdateTextColorCB.setSelected(autoupdateTextColor);  
		autoUpdateTextColorCB.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				autoupdateTextColor = !autoupdateTextColor;	
			}
		});
		autoUpdateTextColorCB.setAlignmentX(LEFT_ALIGNMENT);
		settingsPanel.add(autoUpdateTextColorCB);
		
		//CheckBox - Mise à jour auto de la couleur d'arri�re plan
		JCheckBox autoUpdateBackgroundColorCB = new JCheckBox("Mise à jour auto de la couleur d'arrière-plan");
		autoUpdateBackgroundColorCB.setSelected(autoupdateBackgroundColor);  
		autoUpdateBackgroundColorCB.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				autoupdateBackgroundColor = !autoupdateBackgroundColor;	
			}
		});
		autoUpdateBackgroundColorCB.setAlignmentX(LEFT_ALIGNMENT);
		settingsPanel.add(autoUpdateBackgroundColorCB);
		
		//Checkbox - MAJ auto de la taille du texte
		JCheckBox autoUpdateTextSizeCB = new JCheckBox("Mise à jour auto de la taille du texte");
		autoUpdateTextSizeCB.setSelected(autoupdateTextSize);  
		autoUpdateTextSizeCB.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				autoupdateTextSize = !autoupdateTextSize;	
			}
		});
		autoUpdateTextSizeCB.setAlignmentX(LEFT_ALIGNMENT);
		settingsPanel.add(autoUpdateTextSizeCB);
		
		//Chekbox - fullscreen on second Screen
		JCheckBox fullscreenCB = new JCheckBox("Activer le plein écran sur le deuxième �cran");
		fullscreenCB.setSelected(fullscreen);  
		fullscreenCB.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				fullscreen = !fullscreen;	
			}
		});
		fullscreenCB.setAlignmentX(LEFT_ALIGNMENT);
		settingsPanel.add(fullscreenCB);
		
		JPanel backgroundColorPanel = new JPanel();
		backgroundColorPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		final JPanel backgroundColorPreviewPanel = new JPanel();
		backgroundColorPreviewPanel.setMaximumSize(new Dimension(50,25));
		backgroundColorPreviewPanel.setPreferredSize(new Dimension(50,25));
		backgroundColorPreviewPanel.setBackground(backgroundColor);
		
		
		JButton backgroundColorButton = new JButton("Choisir la couleur d'arrière-plan");
		backgroundColorButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Color newColor = JColorChooser.showDialog(colorChooser, "Couleur d'arrière-plan", backgroundColor);
				if (newColor != null) {
					backgroundColor = newColor;
					backgroundColorPreviewPanel.setBackground(backgroundColor);
					if (autoupdateBackgroundColor) destinationGUI.setBackgroundColor(backgroundColor);
				}
			}
			
		});
		backgroundColorPanel.add(backgroundColorPreviewPanel);
		backgroundColorPanel.add(backgroundColorButton);
		backgroundColorPanel.setAlignmentX(LEFT_ALIGNMENT);
		settingsPanel.add(backgroundColorPanel);
		
		
		JPanel textColorPanel = new JPanel();
		textColorPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		final JPanel textColorPreviewPanel = new JPanel();
		textColorPreviewPanel.setMaximumSize(new Dimension(50,25));
		textColorPreviewPanel.setPreferredSize(new Dimension(50,25));
		textColorPreviewPanel.setBackground(textColor);
		
		 
		JButton textColorButton = new JButton("Choisir la couleur du texte");
		textColorButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Color newColor = JColorChooser.showDialog(colorChooser, "Couleur du texte", textColor);
				if (newColor != null) {
					textColor = newColor;
					textColorPreviewPanel.setBackground(textColor);
					if (autoupdateTextColor) destinationGUI.setTextColor(textColor);
				}
			}
			
		});
		
		textColorPanel.add(textColorPreviewPanel);
		textColorPanel.add(textColorButton);
		textColorPanel.setAlignmentX(LEFT_ALIGNMENT);
		settingsPanel.add(textColorPanel);
		
		colorChooser = new JColorChooser();
		
		SpinnerModel textSizeSpinnerModel = new SpinnerNumberModel(textSize, 1, 300, 1);
		final JSpinner textSizeSpinner = new JSpinner(textSizeSpinnerModel);
		textSizeSpinner.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				if ((int) textSizeSpinner.getValue() != textSize){
					textSize = (int) textSizeSpinner.getValue();
					if (autoupdateTextSize) destinationGUI.setTextSize(textSize);
				}	
			}
		});
		JPanel textSizePanel = new JPanel();
		textSizePanel.setMaximumSize(new Dimension(300, 60));
		textSizePanel.setPreferredSize(new Dimension(300, 30));
		textSizePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		textSizePanel.setAlignmentX(LEFT_ALIGNMENT);
		JLabel textSizeLabel = new JLabel("Taille du texte");
		textSizeSpinner.setMaximumSize(new Dimension(45,20));
		textSizePanel.add(textSizeLabel);		
		textSizePanel.add(textSizeSpinner);
		JLabel screenLabel = new JLabel("Second écran");
		Integer[] screens = {0,1};
		JComboBox<Integer> screenComboBox = new JComboBox<Integer>(screens);
		screenComboBox.setSelectedIndex(Main.secondScreen);
		screenComboBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox<Integer> cb = (JComboBox<Integer>) e.getSource();
				if (cb.getSelectedItem().equals(0)){
					Main.mainScreen=1;
					Main.secondScreen=0;
				}
				else {
					Main.mainScreen=0;
					Main.secondScreen=1;
				}
				
			}
		});
		screenComboBox.setMaximumSize(new Dimension(45,20));
		textSizePanel.add(screenLabel);
		textSizePanel.add(screenComboBox);
		
		settingsPanel.add(textSizePanel);
		mainPanel.add(settingsPanel);
		backPanel.add(mainPanel);
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER,30,10));
		JButton updateButton = new JButton("Mettre à jour les modifications");
		updateButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				destinationGUI.setTextCenter(textArea.getText().replaceAll("\n", "<br>"));
				destinationGUI.setTextColor(textColor);
				destinationGUI.setBackgroundColor(backgroundColor);
				destinationGUI.setTextSize(textSize);
				
			}
		});
		bottomPanel.add(updateButton);
		
		final JButton secondScreen = new JButton("Envoyer sur le second écran");
		secondScreen.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
				GraphicsDevice[] gd = ge.getScreenDevices();
				//Ramener sur le premier ecran	
				if (isOnSecondScreen){
					secondScreen.setText("Envoyer sur le second écran");
					showDestGUIOnScreen(Main.mainScreen);
					isOnSecondScreen = false;
					if (wasFullscreen) {
						quitFullscreen();
						destinationGUI = new GUI();
						destinationGUI.setTextCenter(textArea.getText().replaceAll("\n", "<br>"));
						destinationGUI.setTextColor(textColor);
						destinationGUI.setBackgroundColor(backgroundColor);
						destinationGUI.setTextSize(textSize);
					}
				}
				//Envoyer sur le deuxieme ecran
				else {
					if (Main.protection && gd.length<=1) {
						System.out.println("Erreur : Pas de second écran trouve");
						return;
					}
					showDestGUIOnScreen(Main.secondScreen);
					secondScreen.setText("Ramener sur le premier écran");
					isOnSecondScreen = true;
					wasFullscreen = fullscreen;
				}
				
			}
		});
		
		bottomPanel.add(secondScreen);
		bottomPanel.setAlignmentX(CENTER_ALIGNMENT);
		
		destinationGUI.setTextSize(textSize);
		destinationGUI.setTextColor(textColor);
		destinationGUI.setBackgroundColor(backgroundColor);
		destinationGUI.setTextCenter(textArea.getText());
		
		
		backPanel.add(bottomPanel);
		return backPanel;
	}
	
	private void showDestGUIOnScreen(int screen){
		showOnScreen(screen, destinationGUI, fullscreen);
	}
	
	private static void showOnScreen(int screen, JFrame frame, boolean fullscreen){
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice[] gd = ge.getScreenDevices();
		if (!Main.protection ||( screen > -1 && screen <gd.length)){
			if (!Main.protection || (fullscreen && screen == Main.secondScreen)) {
				//frame.setSize(gd[screen].getDefaultConfiguration().getBounds().x,gd[screen].getDefaultConfiguration().getBounds().y);
				gd[screen].setFullScreenWindow(frame);
			}
			else {
				frame.setSize(600,300);
				Rectangle bound = gd[screen].getDefaultConfiguration().getBounds();
				System.out.println("x: "+bound.x +" y: "+bound.y + " width: " + bound.width + " height : " + bound.height);
				frame.setLocation((int) (bound.x+bound.getWidth()/2-300), frame.getY()); 
			}
	
			return;
		} 
		System.out.println("Erreur : l'ecran "+screen+" n'a pas ete trouve");
	}
	
	private static void quitFullscreen(){
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice[] gd = ge.getScreenDevices();
		if (gd.length>1){
			gd[1].getFullScreenWindow().dispose();
		}
	}
} 
