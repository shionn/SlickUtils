package shionn.slick.animation;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.newdawn.slick.geom.Vector2f;

/**
 * Code sous licence GPLv3 (http://www.gnu.org/licenses/gpl.html)
 * 
 * @author <b>Shionn</b>, shionn@gmail.com <i>http://shionn.org</i><br>
 *         GCS d- s+:+ a+ C++ UL/M P L+ E--- W++ N K- w-- M+ t+ 5 X R+ !tv b+ D+ G- e+++ h+ r- y+
 */
public class BezierPathTest {

	private BezierPath two;
	private BezierPath tree;

	@Before
	public void setUp() throws Exception {
		two = new BezierPath(new Vector2f(10, 10), new Vector2f(50, 50));
		tree = new BezierPath(new Vector2f(0, 0), new Vector2f(50, 50), new Vector2f(0, 100));
	}

	@Test
	public void testPointAtFloat() throws Exception {
		assertThat(two.pointAt(0)).isEqualTo(new Vector2f(10, 10));
		assertThat(two.pointAt(.25f)).isEqualTo(new Vector2f(20, 20));
		assertThat(two.pointAt(.5f)).isEqualTo(new Vector2f(30, 30));
		assertThat(two.pointAt(1)).isEqualTo(new Vector2f(50, 50));
		assertThat(tree.pointAt(0)).isEqualTo(new Vector2f(0, 0));
		assertThat(tree.pointAt(.5f)).isEqualTo(new Vector2f(25, 50));
		assertThat(tree.pointAt(1)).isEqualTo(new Vector2f(0, 100));
	}

	@Test(expected = IllegalStateException.class)
	public void testBezierPathFloatArray() throws Exception {
		new BezierPath(.1f);
	}

}
