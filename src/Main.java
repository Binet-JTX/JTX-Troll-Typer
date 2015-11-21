
public class Main {
	
	static int mainScreen = 1;
	static int secondScreen = 0;
	static boolean protection = true;
	
	public static void main(String[] args) {
		
		GUI gui = new GUI();
		ControlGUI control = new ControlGUI(gui);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		gui.startTimer();
		try {
			Thread.sleep(2500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		gui.setSpeed(true);
		try {
			Thread.sleep(3500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		gui.setEpileptic(true);
	}
}
