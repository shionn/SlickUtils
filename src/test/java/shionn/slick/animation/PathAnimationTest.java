package shionn.slick.animation;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.newdawn.slick.geom.Vector2f;

import shionn.slick.animation.PathAnimation.Mode;

/**
 * Code sous licence GPLv3 (http://www.gnu.org/licenses/gpl.html)
 *
 * @author <b>Shionn</b>, shionn@gmail.com <i>http://shionn.org</i><br>
 *         GCS d- s+:+ a+ C++ UL/M P L+ E--- W++ N K- w-- M+ t+ 5 X R+ !tv b+ D+ G- e+++ h+ r- y+
 */
public class PathAnimationTest {

	private PathAnimation animation;

	@Before
	public void setUp() throws Exception {
		animation = new PathAnimation(new BezierPath(0, 0, 100, 100, 0, 100), 100);
	}

	@Test
	public void testStartOnce() throws Exception {
		assertThat(animation.currentLocation()).isEqualTo(new Vector2f(0, 0));
		animation.start(Mode.ONCE);
		assertThat(animation.currentLocation()).isEqualTo(new Vector2f(0, 0));
		animation.update(50);
		assertThat(animation.currentLocation()).isEqualTo(new Vector2f(50, 75));
		animation.update(1000);
		assertThat(animation.currentLocation()).isEqualTo(new Vector2f(0, 100));
		animation.update(42);
		assertThat(animation.currentLocation()).isEqualTo(new Vector2f(0, 100));
	}

	@Test
	public void testStartLOOP() throws Exception {
		assertThat(animation.currentLocation()).isEqualTo(new Vector2f(0, 0));
		animation.start(Mode.LOOP);
		assertThat(animation.currentLocation()).isEqualTo(new Vector2f(0, 0));
		animation.update(50);
		assertThat(animation.currentLocation()).isEqualTo(new Vector2f(50, 75));
		animation.update(50);
		assertThat(animation.currentLocation()).isEqualTo(new Vector2f(0, 100));
		animation.update(25);
		assertThat(animation.currentLocation()).isEqualTo(new Vector2f(37.5f, 43.75f));
		animation.update(75);
		assertThat(animation.currentLocation()).isEqualTo(new Vector2f(0, 100));
	}

}
