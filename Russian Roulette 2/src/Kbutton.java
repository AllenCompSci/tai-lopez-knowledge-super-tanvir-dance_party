import java.awt.*;

public class Kbutton {
    private int x, y, sizeX, sizeY;
    private int maxFontSize;
    private boolean clicking;
    private boolean isVisible;
    private boolean canClick;
    private Color baseColor, coverColor, borderColor = Color.BLACK;
    private String text;
    private Font font;
    private FontMetrics fontInfo;

    public Kbutton() {
        clicking = false;
        baseColor = Color.WHITE;
        coverColor = new Color(0, 0, 0, 0);
        isVisible = false;
        canClick = true;
        maxFontSize = 18;
    }

    public Kbutton(int x1, int y1, int size_x, int size_y, String text) {
        x = x1;
        y = y1;
        sizeX = size_x;
        sizeY = size_y;
        this.text = text;
        clicking = false;
        baseColor = Color.WHITE;
        coverColor = new Color(0, 0, 0, 0);
        isVisible = false;
        canClick = true;
    }

    public Kbutton(int x1, int y1, int size_x, int size_y, String text, int maxFontSize) {
        x = x1;
        y = y1;
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

    public Kbutton(int x1, int y1, int size_x, int size_y, String text, Color bColor) {
        x = x1;
        y = y1;
        sizeX = size_x;
        sizeY = size_y;
        this.text = text;
        clicking = false;
        baseColor = bColor;
        coverColor = new Color(0, 0, 0, 0);
        isVisible = false;
        canClick = true;
    }

    public Kbutton(int x1, int y1, int size_x, int size_y, String text, Color bColor, int maxFontSize) {
        x = x1;
        y = y1;
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
        if (isVisible) {
            coverColor = new Color(0, 0, 0, 32);
        }
    }

    private void click() {
        if (isVisible && canClick) {
            clicking = true;
            coverColor = new Color(0, 0, 0, 128);
        }
    }

    private void unClick() {
        clicking = false;
        canClick = false;
        if (isVisible) {
            coverColor = new Color(0, 0, 0, 32);
        }

    }

    private void UnHover() {
        if (isVisible) {
            coverColor = new Color(0, 0, 0, 0);
        }
    }

    public void setVisible(boolean b) {
        isVisible = b;
    }

    public void setCanClick(boolean b) {
        canClick = b;
    }

    private boolean isInBounds(int x1, int y1) {
        return x1 > x && y1 > y && x1 < (x + sizeX) && y1 < (y + sizeY);
    }

    public void draw(int x1, int y1, Graphics2D art) {
        if (clicking && !Main.giraffe.getFrame().clicking()) {
            if (isInBounds(x1, y1)) {
                Main.giraffe.doClickAction(this);
                unClick();
            } else {
                unClick();
            }
        }
        if (isInBounds(x1, y1))
            hover();
        else
            UnHover();
        if (Main.giraffe.getFrame().clicking()) {
            if (Main.giraffe.getFrame().getMouseButton() == 1) {
                if (isInBounds(x1, y1)) {
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
            art.fillRect(x, y, sizeX, sizeY);
            art.setColor(coverColor);
            art.fillRect(x, y, sizeX, sizeY);
            art.setColor(borderColor);
            art.drawRect(x, y, sizeX, sizeY);
            art.setColor(Color.black);
            art.drawString(text, x + (sizeX / 2 - fontInfo.stringWidth(text) / 2), y + (sizeY / 2 + textSize / 3));
            art.setFont(art.getFont().deriveFont(12f));
        }

    }
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

    public void setPos(int x1, int y1) {
        x = x1;
        y = y1;
    }

    public void setX(int x1) {
        x = x1;
    }

    public void setY(int y1) {
        y = y1;
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
        return x;
    }

    public int getY() {
        return y;
    }

    public Dimension getSize() {
        return new Dimension(sizeX, sizeY);
    }



    public Color getBaseColor() {
        return baseColor;
    }

    public void setBaseColor(Color baseColor) {
        this.baseColor = baseColor;
    }

}
