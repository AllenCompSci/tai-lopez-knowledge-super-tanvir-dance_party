package physics;

import java.time.Duration;

import static java.time.Duration.ZERO;

@SuppressWarnings ("InfiniteLoopStatement")
public class Timer implements Runnable {
	private Duration startTime;
	private boolean running;
	private boolean countingUp;
	private final int delay;

	public Timer() {
		running = false;
		startTime = ZERO;
		countingUp = true;
		delay = 10;
	}

	public Timer(int delay) {
		running = false;
		startTime = ZERO;
		countingUp = true;
		this.delay = delay;
	}

	public Timer(boolean countUp, int length) {
		running = false;
		if (!countUp) {
			startTime = Duration.ofMillis(length);
		} else {
			startTime = ZERO;
		}
		countingUp = countUp;
		delay = 10;

	}

	@Override
	public void run() {
		while(true) {
			if(running)
				if (countingUp) {
					startTime = startTime.plusMillis(delay);
				} else {
					startTime = startTime.minusMillis(delay);
					if (startTime.toMillis() <= 0) {
						running = false;
					}
				}

			try {
				Thread.sleep(delay);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public int getHours() {
		return (int) (startTime.getSeconds() / 3600.0);
	}

	public int getSeconds() {
		return (int) startTime.getSeconds();
	}

	public int getMS() {
		return (int) startTime.toMillis();
	}

	public void addSeconds(int seconds) {
		startTime = startTime.plusSeconds(seconds);
	}

	public void addMS(int millis) {
		startTime = startTime.plusMillis(millis);
	}
	public String toString(boolean zeroes) {
		int ms = (int) startTime.toMillis();
		int seconds = ms / 1000;
		int minutes = seconds / 60;
		int hours = minutes / 60;
		seconds %= 60;
		minutes %= 60;
		String out = "";
		int numSeparators;
		if(!zeroes) {
			if(hours > 0) {
				numSeparators = 4;
			} else if(minutes > 0) {
				numSeparators = 3;
			} else if(seconds > 0) {
				numSeparators = 2;
			} else {
				numSeparators = 1;
			}
			switch(numSeparators) {
				case 4:
					out = "" + hours + ":" + (minutes < 10 ? '0' : "") + minutes + ':' + (seconds < 10 ? '0' : "") + seconds + '.' + (ms / 10 % 100 < 10 ? '0' : "") + ms / 10 % 100;
					break;
				case 3:
					out = "" + minutes + ':' + (seconds < 10 ? '0' : "") + seconds + '.' + (ms / 10 % 100 < 10 ? '0' : "") + ms / 10 % 100;
					break;
				case 2:
					out = "" + seconds + '.' + (ms / 10 % 100 < 10 ? '0' : "") + ms / 10 % 100 + " s";
					break;
				case 1:
					out = "" + ms % 1000 + " ms";
			}
			return out;
		} else
			return ("" + hours + ':' + (minutes < 10 ? '0' : "") + minutes + ':' + (seconds < 10 ? '0' : "") + seconds + '.' + (ms / 10 % 100 < 10 ? '0' : "") + (ms / 10 % 100));
	}

	public String toString() {
		int ms = (int) startTime.toMillis();
		int seconds = ms / 1000;
		int minutes = seconds / 60;
		int hours = minutes / 60;
		seconds %= 60;
		minutes %= 60;
		ms /= 10;
		ms %= 100;
		return ("" + hours + ':' + (minutes < 10 ? '0' : "") + minutes + ':' + (seconds < 10 ? '0' : "") + seconds + '.' + (ms < 10 ? '0' : "") + (ms));
	}

	public void pause() {
		running = false;
	}

	public void resume() {
		running = true;
	}

	public void reset() {
		startTime = ZERO;
		running = false;
	}

	public void begin() {
		startTime = ZERO;
		running = true;
	}

	public void restart() {
		startTime = ZERO;
		running = true;
	}
	public void restart(int length) {
		if (!countingUp)
			startTime = Duration.ofMillis(length);
		else
			startTime = ZERO;
		running = true;
	}

	public boolean isRunning() {
		return running;
	}
}
