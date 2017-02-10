package hotpotato;

import java.awt.event.KeyEvent;

/**
 * @author onContentStop
 */
public class KeyHandler {
	public static void pressKey(KeyEvent key) {
		int keyCode = key.getKeyCode();
		if (keyCode == KeyEvent.VK_SPACE) {
			//toss the potato
		}
	}
}
