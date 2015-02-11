package shionn.slick.ui;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.newdawn.slick.Font;

import shionn.slick.ui.align.VerticalAlignement;

/**
 * Code sous licence GPLv3 (http://www.gnu.org/licenses/gpl.html)
 * 
 * @author <b>Shionn</b>, shionn@gmail.com <i>http://shionn.org</i><br>
 *         GCS d- s+:+ a+ C++ UL/M P L+ E--- W++ N K- w-- M+ t+ 5 X R+ !tv b+ D+ G- e+++ h+ r- y+
 */
@RunWith(MockitoJUnitRunner.class)
public class TextAreaTest {
	private static final int EXPECTED_LINE_COUNT = 10;
	private static final int X = 10;
	private static final int Y = 20;
	private static final int HEIGHT = 120;
	private static final int WIDTH = 100;
	private static final int CHAR_HEIGHT = 10;
	private static final int CHAR_WIDTH = 10;
	private static final int EXPECTED_LINE_COUNT_AFTER_ADD = 12;
	private TextArea subject = new TextArea(X, Y, WIDTH, HEIGHT);
	@Mock
	private Font font;

	@Before
	public void setUp() throws Exception {
		when(font.getLineHeight()).thenReturn(CHAR_HEIGHT);
		when(font.getWidth(anyString())).thenAnswer(new Answer<Integer>() {
			@Override
			public Integer answer(InvocationOnMock invocation) throws Throwable {
				return CHAR_WIDTH * ((String) (invocation.getArguments()[0])).length();
			}
		});

		subject.addText(UITestConstants.P1, font, VerticalAlignement.LEFT);
		subject.addText(UITestConstants.P2, font, VerticalAlignement.CENTER);
		subject.addText(UITestConstants.P3, font, VerticalAlignement.RIGHT);
	}

	@Test
	public void testRender() {
		subject.render();
		verify(font, times(EXPECTED_LINE_COUNT)).drawString(anyInt(), anyInt(), anyString());
	}

	@Test
	public void testRenderReverse() {
		subject.setBottomUp(true);
		subject.render();
		verify(font, times(EXPECTED_LINE_COUNT)).drawString(anyInt(), anyInt(), anyString());
	}

	@Test
	public void testClear() {
		subject.clear();
		subject.render();
		verify(font, times(0)).drawString(anyInt(), anyInt(), anyString());
	}

	@Test
	public void testAddText() {
		subject.addText(UITestConstants.LOREM_IPSUM_P4, font, VerticalAlignement.LEFT);
		subject.render();
		verify(font, times(EXPECTED_LINE_COUNT_AFTER_ADD)).drawString(anyInt(), anyInt(),
				anyString());
	}

}
