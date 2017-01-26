package mainmenu;

import java.awt.*;

import static mainmenu.MainMenu.window;

@SuppressWarnings ("CanBeFinal")
class Button {
	private int x1, y1, sizeX, sizeY;
	private int maxFontSize;
	private boolean clicking;
	private boolean isVisible;
	/**
	 * Prevents infinite clicking by forcing one click per mouse click or at least while the mouse stays in bounds.
	 */
	private boolean canClick;
	private boolean isToggled;
	private Color baseColor, coverColor, borderColor = Color.BLACK;
	private String text;
	private Font font;
	private FontMetrics fontInfo;

	public Button() {
		clicking = false;
		baseColor = Color.WHITE;
		coverColor = new Color(0, 0, 0, 0);
		isVisible = false;
		canClick = true;
		maxFontSize = 50;
	}
	public Button(int x, int y, int size_x, int size_y, String text) {
		x1 = x;
		y1 = y;
		sizeX = size_x;
		sizeY = size_y;
		this.text = text;
		clicking = false;
		baseColor = Color.WHITE;
		coverColor = new Color(0, 0, 0, 0);
		isVisible = false;
		canClick = true;
		maxFontSize = 30;
	}

	@SuppressWarnings ("SameParameterValue")
	public Button(int x, int y, int size_x, int size_y, String text, int maxFontSize) {
		x1 = x;
		y1 = y;
		sizeX = size_x;
		sizeY = size_y;
		this.text = text;
		clicking = false;
		baseColor = Color.WHITE;
		coverColor = new Color(0, 0, 0, 0);
		isVisible = false;
		canClick = true;
		this.maxFontSize = maxFontSize;
	}

	public Button(int x, int y, int size_x, int size_y, String text, Color bColor) {
		x1 = x;
		y1 = y;
		sizeX = size_x;
		sizeY = size_y;
		this.text = text;
		clicking = false;
		baseColor = bColor;
		coverColor = new Color(0, 0, 0, 0);
		isVisible = false;
		canClick = true;
		maxFontSize = 30;
	}

	/**
	 * Creates a new Button.
	 *
	 * @param x           x-coordinate of top-left corner, in pixels.
	 * @param y           y-coordinate of top-left corner, in pixels.
	 * @param size_x      Size of the button in the x direction.
	 * @param size_y      Size of the button in the y direction.
	 * @param text        Text to display on the button.
	 * @param bColor      Color of the button (optional, default is white)
	 * @param maxFontSize Maximum size of text (optional but can improve readability of the button)
	 */
	public Button(int x, int y, int size_x, int size_y, String text, Color bColor, int maxFontSize) {
		x1 = x;
		y1 = y;
		sizeX = size_x;
		sizeY = size_y;
		this.text = text;
		clicking = false;
		baseColor = bColor;
		coverColor = new Color(0, 0, 0, 0);
		isVisible = false;
		canClick = true;
		this.maxFontSize = maxFontSize;
	}

	private void hover() {
		if(isVisible) {
			coverColor = new Color(0, 0, 0, 32);
		}
	}

	private void click() {
		if(isVisible && canClick) {
			clicking = true;
			coverColor = new Color(0, 0, 0, 128);
		}
	}

	private void unClick() {
		clicking = false;
		canClick = false;
		if(isVisible) {
			coverColor = new Color(0, 0, 0, 32);
		}
	}

	private void unHover() {
		if(isVisible) {
			coverColor = new Color(0, 0, 0, 0);
		}
	}

	public void setVisible(boolean b) {
		isVisible = b;
	}

	/**
	 * Renders the button onto graphics.
	 *
	 * @param art Graphics to draw on
	 */
	public void draw(Graphics2D art) {
		int x = window.getFrame().mouseX;
		int y = window.getFrame().mouseY;
		if(clicking && !window.getFrame().isClicking()) {
			if(isInBounds(x, y)) {
				try {
					ClickHandler.clickButton(this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				unClick();
			} else {
				unClick();
			}
		}
		if(isInBounds(x, y))
			hover();
		else
			unHover();
		if(window.getFrame().isClicking()) {
			if(window.getFrame().getMouseButton() == 1) {
				if(isInBounds(x, y)) {
					click();
				}
			}
		} else {
			canClick = true;
		}
		font = art.getFont().deriveFont(sizeY);
		fontInfo = art.getFontMetrics(font);
		float textSize = getTextSize(art);
		font = art.getFont().deriveFont(textSize);
		fontInfo = art.getFontMetrics(font);
		if(isVisible) {
			art.setFont(font);
			art.setColor(baseColor);
			art.fillRect(x1, y1, sizeX, sizeY);
			art.setColor(coverColor);
			art.fillRect(x1, y1, sizeX, sizeY);
			art.setColor(borderColor);
			art.drawRect(x1, y1, sizeX, sizeY);
			art.setColor(Color.black);
			art.drawString(text, x1 + (sizeX / 2 - fontInfo.stringWidth(text) / 2), y1 + (sizeY / 2 + textSize / 3));
			art.setFont(art.getFont().deriveFont(12f));
		}
	}

	/**
	 * @param art Graphics from which to derive a font
	 * @return Returns the optimal size for button text, from 0 to maxFontSize.
	 */
	private float getTextSize(Graphics2D art) {
		float width = fontInfo.stringWidth(text) * text.length(), height = sizeY;
		while(width > sizeX) {
			height -= 0.1;
			font = art.getFont().deriveFont(height);
			fontInfo = art.getFontMetrics(font);
			width = fontInfo.stringWidth(text);
		}
		if(height > maxFontSize)
			height = maxFontSize;
		return height;
	}

	/**
	 * @param x x-coordinate to test
	 * @param y y-coordinate to test
	 * @return Returns whether the given coordinates are within the Button's bounds.
	 */
	private boolean isInBounds(int x, int y) {
		return x > x1 && y > y1 && x < (x1 + sizeX) && y < (y1 + sizeY);
	}

	public boolean isClicking() {
		return clicking;
	}

	public boolean isVisible() {
		return isVisible;
	}

	public void setText(String s) {
		text = s;
	}

	public String getText() {
		return text;
	}

	public void setBorderColor(Color c) {
		borderColor = c;
	}

	public void setPos(int x, int y) {
		x1 = x;
		y1 = y;
	}

	public void setX(int x) {
		x1 = x;
	}

	public void setY(int y) {
		y1 = y;
	}

	public void setSize(int x, int y) {
		sizeX = x;
		sizeY = y;
	}

	public void setSizeX(int x) {
		sizeX = x;
	}

	public void setSizeY(int y) {
		sizeY = y;
	}

	public int getX() {
		return x1;
	}

	public int getY() {
		return y1;
	}

	public Dimension getSize() {
		return new Dimension(sizeX, sizeY);
	}

	public void setToggled(boolean toggled) {
		isToggled = toggled;
	}

	public boolean isToggled() {
		return isToggled;
	}

	public void toggle() {
		isToggled = !isToggled;
	}

	public Color getBaseColor() {
		return baseColor;
	}

	public void setBaseColor(Color baseColor) {
		this.baseColor = baseColor;
	}
}
