/**
 * Code sous licence KeJaiPasEncoreDefinit
 *
 * @author <b>Shionn</b>, shionn@gmail.com <i>http://shionn.org</i><br>
 * GCS d- s+:+ a- C++ UL/M P L+ E--- W++ N K- w-- M+ t+ 5 X R+ !tv b+ D+ G- e+++ h+ r- !y-
 */
package shionn.slick.ui.formater;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.newdawn.slick.Font;

import shionn.slick.ui.UITestConstants;

public class TextFormaterTest {
	private TextFormater formater = new TextFormater();

	@Test
	public void testSplitParagraph() {
		String[] paragraphs = formater.splitParagraph("paraph0\nparaph1\nparaph2");
		assertThat(paragraphs).contains("paraph0", "paraph1", "paraph2");
	}

	@Test
	public void testSplitLine() {
		Font font = mock(Font.class);
		when(font.getWidth(anyString())).then(new Answer<Integer>() {
			@Override
			public Integer answer(InvocationOnMock invocation) throws Throwable {
				return ((String) invocation.getArguments()[0]).length() * 8;
			}
		});
		List<String> lines = formater.splitLine(UITestConstants.LOREM_IPSUM_P4, font, 320);
		for (String line : lines) {
			assertThat(line.length()).isLessThanOrEqualTo(40);
			assertThat(UITestConstants.LOREM_IPSUM_P4).contains(line);
		}
		assertThat(lines).hasSize(11);
	}
}
