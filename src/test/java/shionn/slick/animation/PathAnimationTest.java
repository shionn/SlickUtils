package shionn.slick.animation;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.newdawn.slick.geom.Vector2f;

import shionn.slick.animation.PathAnimation.Mode;

/**
 * Code sous licence GPLv3 (http://www.gnu.org/licenses/gpl.html)
 *
 * @author <b>Shionn</b>, shionn@gmail.com <i>http://shionn.org</i><br>
 *         GCS d- s+:+ a+ C++ UL/M P L+ E--- W++ N K- w-- M+ t+ 5 X R+ !tv b+ D+ G- e+++ h+ r- y+
 */
@RunWith(MockitoJUnitRunner.class)
public class PathAnimationTest {

	@Mock
	private AnimationListener listener;
	private PathAnimation animation;

	@Before
	public void setUp() throws Exception {
		animation = new PathAnimation(new BezierPath(0, 0, 100, 100, 0, 100), 100);
		animation.addListener(0, listener);
		animation.addListener(33, listener);
		animation.addListener(100, listener);
	}

	@Test
	public void testStartOnce() throws Exception {
		assertThat(animation.currentLocation()).isEqualTo(new Vector2f(0, 0));
		animation.start(Mode.ONCE);
		assertThat(animation.currentLocation()).isEqualTo(new Vector2f(0, 0));
		animation.update(50);
		verify(listener, times(2)).on();
		assertThat(animation.currentLocation()).isEqualTo(new Vector2f(50, 75));
		animation.update(1000);
		verify(listener, times(3)).on();
		assertThat(animation.currentLocation()).isEqualTo(new Vector2f(0, 100));
		animation.update(42);
		verify(listener, times(3)).on();
		assertThat(animation.currentLocation()).isEqualTo(new Vector2f(0, 100));
	}

	@Test
	public void testStartLOOP() throws Exception {
		assertThat(animation.currentLocation()).isEqualTo(new Vector2f(0, 0));
		animation.start(Mode.LOOP);
		assertThat(animation.currentLocation()).isEqualTo(new Vector2f(0, 0));
		animation.update(50);
		verify(listener, times(2)).on();
		assertThat(animation.currentLocation()).isEqualTo(new Vector2f(50, 75));
		animation.update(50);
		verify(listener, times(2)).on();
		assertThat(animation.currentLocation()).isEqualTo(new Vector2f(0, 100));
		animation.update(25);
		verify(listener, times(3)).on();
		assertThat(animation.currentLocation()).isEqualTo(new Vector2f(37.5f, 43.75f));
		animation.update(75);
		verify(listener, times(4)).on();
		assertThat(animation.currentLocation()).isEqualTo(new Vector2f(0, 100));
	}

}
