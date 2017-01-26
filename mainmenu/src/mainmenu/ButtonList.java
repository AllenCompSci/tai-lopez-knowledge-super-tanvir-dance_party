package mainmenu;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created on 4/7/2016 at 11:17 PM.
 */
class ButtonList {
	@SuppressWarnings ("CanBeFinal")
	private List<Button> buttons;
	private String name;

	public ButtonList(String name) {
		this.name = name;
		buttons = new ArrayList<>();
	}

	public ButtonList(String name, Button b) {
		this.name = name;
		buttons = new ArrayList<>(Collections.singletonList(b));
	}

	public ButtonList(String name, List<Button> b) {
		this.name = name;
		buttons = b;
	}

	public List<Button> getButtons() {
		return buttons;
	}

	public void add(Button b) {
		buttons.add(b);
	}

	public void drawAll(Graphics2D art) {
		for(Button b : buttons) {
			b.draw(art);
		}
	}

	public void add(Button[] bs) {
		Collections.addAll(buttons, bs);
	}

	public void add(List<Button> bs) {
		buttons.addAll(bs);
	}

	public Button get(int index) {
		return buttons.get(index);
	}

	public int size() {
		return buttons.size();
	}

	public void setVisible(boolean visible) {
		for(Button button : buttons) {
			button.setVisible(visible);
		}
	}

	public String getName() {
		return name;
	}

	public List<Button> toList() {
		return buttons;
	}
	public void sort() {
		List<String> buttonNames = new ArrayList<>();
		for(Button b : buttons) {
			buttonNames.add(b.getText());
		}
		Collections.sort(buttonNames);
		List<Button> newButtons = new ArrayList<>();
		for(int i = 0; i < buttons.size(); i++) {
			for(Button b : buttons) {
				if(b.getText().equals(buttonNames.get(i))) {
					newButtons.add(b);
				}
			}
		}
		buttons = newButtons;
	}
}
