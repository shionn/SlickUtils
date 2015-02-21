package shionn.slick.animation;

import org.newdawn.slick.geom.Vector2f;

/**
 * Code sous licence GPLv3 (http://www.gnu.org/licenses/gpl.html)
 * 
 * @author <b>Shionn</b>, shionn@gmail.com <i>http://shionn.org</i><br>
 *         GCS d- s+:+ a+ C++ UL/M P L+ E--- W++ N K- w-- M+ t+ 5 X R+ !tv b+ D+ G- e+++ h+ r- y+
 */
public class PathAnimation {

	public enum Mode {
		ONCE, LOOP
	}

	private Path path;
	private int duration;
	private int time;
	private boolean running = false;
	private Mode mode = Mode.ONCE;

	public PathAnimation(Path path, int duration) {
		this.path = path;
		this.duration = duration;
	}

	public Vector2f currentLocation() {
		return path.pointAt((float) time / duration);
	}

	public void update(int delta) {
		if (running) {
			time += delta;
			switch (mode) {
			case ONCE:
				if (time > duration) {
					time = duration;
					running = false;
				}
				break;
			case LOOP:
				if (time > duration) {
					time %= duration;
				}
				break;
			default:
				throw new RuntimeException("Not yet Implemented");
			}
		}
	}

	public void start() {
		time = 0;
		running = true;
	}

	public void start(Mode mode) {
		this.mode = mode;
		start();
	}

}
