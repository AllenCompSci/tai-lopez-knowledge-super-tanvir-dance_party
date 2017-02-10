package hotpotato;

import com.sun.javaws.exceptions.InvalidArgumentException;
import knowledge.Background;
import knowledge.DrawingTools;
import knowledge.Graphics;
import knowledge.Motion;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

/**
 * @author onContentStop
 */
public class HotPotato extends Graphics {

	private int currCountdown = 3;
	private final int COUNTDOWN_FONT_SIZE = height / 10;
	private BufferedImage stringBuffer;
	private SpinningPolygon countdownPoly;
	private int stringBufferW = height / 5, stringBufferH = height / 5;
	private Graphics2D textGraphics;

	public HotPotato() {
		super("Hot Potato");
		countdownPoly = new SpinningPolygon(6, 1000, width / 2, height / 2, Color.GREEN, height / 5, Mayne.animTimer);
		stringBuffer = new BufferedImage(stringBufferW, stringBufferH, BufferedImage.TYPE_3BYTE_BGR);
		textGraphics = (Graphics2D) stringBuffer.getGraphics();
		textGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		textGraphics.setFont(new Font("Arial", Font.PLAIN, COUNTDOWN_FONT_SIZE));
		textGraphics.setColor(countdownPoly.getInnerColor());
		textGraphics.fillRect(0, 0, stringBufferW, stringBufferH);
		textGraphics.setColor(Color.black);
		f = textGraphics.getFont();
		DrawingTools.drawTextAround(f, "3", stringBufferW / 2, stringBufferH / 2 - 15, textGraphics);
		Mayne.colorSwitcher.begin();
		Mayne.countdownTimer.start();
		Mayne.animTimer.begin();
		CountDownColor.setInitialColor(Color.white);
		CountDownColor.setDestinationColor(Color.green);
	}

	@Override
	public void run() {
		while (running) {
			Background.updateColor();
			CountDownColor.updateColor();
			countdownPoly.setInnerColor(CountDownColor.getCurrentColor());
			updateSize();
			countdownPoly.setCenter(width / 2, height / 2);
			updateCountdown();
			if (visible) {
				draw();
			}
		}
	}

	private void updateCountdown() {
		int currTimer = Mayne.countdownTimer.getMS();
		int countdownStatus = currTimer / 1000 + 1;
		if (currTimer > 0) {
			textGraphics.dispose();
			stringBuffer = new BufferedImage(stringBufferW, stringBufferH, BufferedImage.TYPE_3BYTE_BGR);
			textGraphics = (Graphics2D) stringBuffer.getGraphics();
			textGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			textGraphics.setFont(new Font("Arial", Font.PLAIN, COUNTDOWN_FONT_SIZE));
			f = textGraphics.getFont();
			textGraphics.setColor(countdownPoly.getInnerColor());
			textGraphics.fillRect(0, 0, stringBufferW, stringBufferH);
			textGraphics.setColor(Color.black);
			DrawingTools.drawTextAround(f, Integer.toString(countdownStatus), stringBufferW / 2, stringBufferH / 2 - 15, textGraphics);
			currCountdown = countdownStatus;
		}
		if (currTimer == 0) {
			textGraphics.dispose();
			stringBuffer = new BufferedImage(stringBufferW, stringBufferH, BufferedImage.TYPE_3BYTE_BGR);
			textGraphics = (Graphics2D) stringBuffer.getGraphics();
			textGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			textGraphics.setFont(new Font("Arial", Font.PLAIN, COUNTDOWN_FONT_SIZE));
			f = textGraphics.getFont();
			textGraphics.setColor(countdownPoly.getInnerColor());
			textGraphics.fillRect(0, 0, stringBufferW, stringBufferH);
			textGraphics.setColor(Color.black);
			DrawingTools.drawTextAround(f, "GO!", stringBufferW / 2, stringBufferH / 2 - 15, textGraphics); // Modification of 10% reduction
			Mayne.animTimer2.start();
		}
		if(Mayne.animTimer2.isRunning()) {
			int exitTime = 1000;
			if(Mayne.animTimer2.getMS() <= exitTime) {
				int currY = height / 2;
				try {
					currY = Motion.getNumberBetween(Motion.MODE_EXPONENTIAL, height / 2, -200, (double) Mayne.animTimer2.getMS() / (double) exitTime);
				} catch (InvalidArgumentException e) {
					e.printStackTrace();
				}
				countdownPoly.setCenter(width / 2, currY);
			} else {
				Mayne.animTimer2.pause();
				countdownPoly.setCenter(width / 2, -200);
			}
		}
	}

	private void draw() {
		startDraw();
		art.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		art.setColor(Background.getCurrentColor());
		art.fillRect(0, 0, width, height);
		art.setColor(Color.black);
		DrawingTools.drawCenteredText(f, "HOT POTATO", width / 2, 100, art);
		countdownPoly.draw(art);
		AffineTransform trans;
		trans = textGraphics.getTransform();
		trans.translate(countdownPoly.getCenter().x - stringBufferW / 2, countdownPoly.getCenter().y - stringBufferH / 2);
		if(Mayne.countdownTimer.getMS() > 0)
		trans.rotate(countdownPoly.getAngle() / 3d, stringBufferW / 2, stringBufferH / 2);
		textGraphics.drawImage(stringBuffer, null, 0, 0);
		textGraphics = stringBuffer.createGraphics();
		art.drawImage(stringBuffer, trans, null);
		//TODO recover this code!
		endDraw();
	}
}
