package shionn.slick.exemple;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import shionn.slick.animation.BezierPath;
import shionn.slick.animation.PathAnimation;

/**
 * Code sous licence GPLv3 (http://www.gnu.org/licenses/gpl.html)
 * 
 * @author <b>Shionn</b>, shionn@gmail.com <i>http://shionn.org</i><br>
 *         GCS d- s+:+ a+ C++ UL/M P L+ E--- W++ N K- w-- M+ t+ 5 X R+ !tv b+ D+ G- e+++ h+ r- y+
 */
public class PathAnimationExemple extends BasicGame {

	public static void main(String[] args) throws SlickException {
		new AppGameContainer(new PathAnimationExemple(), 800, 600, false).start();
	}

	private PathAnimation animation;

	public PathAnimationExemple() {
		super("PathAnimationExemple");
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		animation = new PathAnimation(new BezierPath(new Vector2f(100, 100),
				new Vector2f(700, 100), new Vector2f(700, 500), new Vector2f(100, 500)), 5000);
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		Vector2f p = animation.currentLocation();
		g.fillRect(p.x - 15, p.y - 15, 30, 30);
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		animation.update(delta);
	}

	@Override
	public void keyPressed(int key, char c) {
		switch (c) {
		case ' ':
			animation.start();
			break;
		case 'o':
			animation.start(PathAnimation.Mode.ONCE);
			break;
		case 'l':
			animation.start(PathAnimation.Mode.LOOP);
			break;
		}
	}

}
