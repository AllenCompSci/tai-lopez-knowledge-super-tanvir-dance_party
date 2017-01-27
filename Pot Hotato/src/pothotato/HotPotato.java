package pothotato;

import knowledge.Background;
import knowledge.Constants;
import knowledge.DrawingTools;
import knowledge.Graphics;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

/**
 * Created by 254397 on 1/27/2017.
 */
public class HotPotato extends Graphics {
	private int currCountdown = 3;
	private BufferedImage stringBuffer;
	private SpinningPolygon countdownPoly;
	private int stringBufferW = 100, stringBufferH = 100;
	private Graphics2D textGraphics;
	public HotPotato() {
		super("Hot Potato");
		countdownPoly = new SpinningPolygon(5, 1000, width / 2, height / 2, Color.GREEN, 100, Mayne.animTimer);
		stringBuffer = new BufferedImage(stringBufferW, stringBufferH, BufferedImage.TYPE_3BYTE_BGR);
		textGraphics = (Graphics2D) stringBuffer.getGraphics();
		textGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		textGraphics.setFont(new Font("Arial", Font.PLAIN, 50));
		textGraphics.setColor(countdownPoly.getInnerColor());
		textGraphics.fillRect(0, 0, stringBufferW, stringBufferH);
		textGraphics.setColor(Color.black);
		f = textGraphics.getFont();
		DrawingTools.drawTextAround(f, "3", stringBufferW / 2, stringBufferH / 2 - stringBufferH / 7, textGraphics);
		Mayne.countdownTimer.start();
	}
	@Override
	public void run(){
		while(running) {
			Background.updateColor();
			updateSize();
			updateCountdown();
			countdownPoly.setCenter(width / 2, height  /2);
			if(visible) {
				draw();
			}
		}
	}

	private void updateCountdown() {
		int countdownStatus = Mayne.countdownTimer.getMS() / 1000 + 1;
		if(countdownStatus < currCountdown) {
			textGraphics.dispose();
			stringBuffer = new BufferedImage(stringBufferW,stringBufferH,BufferedImage.TYPE_3BYTE_BGR);
			textGraphics = (Graphics2D) stringBuffer.getGraphics();
			textGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			textGraphics.setFont(new Font("Arial", Font.PLAIN, 50));
			f = textGraphics.getFont();
			textGraphics.setColor(countdownPoly.getInnerColor());
			textGraphics.fillRect(0, 0, stringBufferW, stringBufferH);
			textGraphics.setColor(Color.black);
			DrawingTools.drawTextAround(f, Integer.toString(countdownStatus), stringBufferW/2, stringBufferH/2 - (stringBufferH / 7), textGraphics);
			currCountdown = countdownStatus;
		}
		if(Mayne.countdownTimer.getMS() == 0) {
			textGraphics.dispose();
			stringBuffer = new BufferedImage(stringBufferW,stringBufferH,BufferedImage.TYPE_3BYTE_BGR);
			textGraphics = (Graphics2D) stringBuffer.getGraphics();
			textGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			textGraphics.setFont(new Font("Arial", Font.PLAIN, 50));
			f = textGraphics.getFont();
			textGraphics.setColor(countdownPoly.getInnerColor());
			textGraphics.fillRect(0, 0, stringBufferW, stringBufferH);
			textGraphics.setColor(Color.black);
			DrawingTools.drawTextAround(f, "GO!", stringBufferW/2, stringBufferH/2 - (stringBufferH / 10), textGraphics); // Modification of 10% reduction
			Mayne.animTimer2.start();
		}
	}

	private void draw() {
		startDraw();
		art.setColor(Background.getCurrentColor());
		art.fillRect(0, 0, width, height);
		art.setColor(Color.black);
		DrawingTools.drawCenteredText(f, "HOT POTATO", width / 2, 100, art);
		int countdownX = width / 2, countdownY = height / 2;
		countdownPoly.draw(art);
		AffineTransform trans = new AffineTransform();

		trans.setTransform(new AffineTransform());
		art.translate(countdownX - stringBufferW/2, countdownY - stringBufferH/2);
		if(Mayne.countdownTimer.getMS() > 0)
			trans.rotate(Constants.pi / 7 * countdownPoly.getAngle(), stringBufferW/2, stringBufferH/2);
		art.drawImage(stringBuffer, trans, null);
		art.translate(0, 0);
		endDraw();
	}
}
