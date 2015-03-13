package ivan.slavka;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;


public class Sample1 {

	public static void main(String[] args) throws IOException, AWTException{
		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_PAUSE);
	}
}
