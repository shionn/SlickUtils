package shionn.slick.ui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.newdawn.slick.Font;

import shionn.slick.ui.align.VerticalAlignement;
import shionn.slick.ui.formater.TextFormater;

/**
 * Code sous licence GPLv3 (http://www.gnu.org/licenses/gpl.html)
 * 
 * @author <b>Shionn</b>, shionn@gmail.com <i>http://shionn.org</i><br>
 *         GCS d- s+:+ a+ C++ UL/M P L+ E--- W++ N K- w-- M+ t+ 5 X R+ !tv b+ D+ G- e+++ h+ r- y+
 */
public class TextArea {
	private int x;
	private int y;
	private int width;
	private int height;
	private List<TextAreaLine> lines = new ArrayList<>();
	private TextFormater formater = new TextFormater();
	private boolean bottomUp;

	public TextArea(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public void render() {
		int liney = getStartLine();
		for (int i = 0; i < lines.size(); i++) {
			liney = renderText(lines.get(i), liney);
		}
	}

	private int getStartLine() {
		int firstFontSize = lines.size() > 0 ? lines.get(0).getFont().getLineHeight() : 0;
		return bottomUp ? y + height - firstFontSize : y;
	}

	private int renderText(TextAreaLine line, int liney) {
		int y = liney;
		for (String p : formater.splitParagraph(line.getText())) {
			for (String l : getLines(line.getFont(), p)) {
				// ici des itération sont faite en trop !
				if (!isLastLine(y)) {
					renderLine(l, y, line.getFont(), line.getAlign());
				}
				y = getNextLineY(line.getFont(), y);
			}
		}
		return y;
	}

	private boolean isLastLine(int y) {
		return (bottomUp && y < this.y) || (!bottomUp && y >= this.getStartLine() + height);
	}

	private int getNextLineY(Font font, int y) {
		return y + (bottomUp ? -font.getLineHeight() : font.getLineHeight());
	}

	private List<String> getLines(Font font, String p) {
		List<String> lines = formater.splitLine(p, font, width);
		if (bottomUp) {
			Collections.reverse(lines);
		}
		return lines;
	}

	private void renderLine(String line, int liney, Font font, VerticalAlignement alignement) {
		switch (alignement) {
		case CENTER:
			font.drawString(x + width / 2 - font.getWidth(line) / 2, liney, line);
			break;
		case RIGHT:
			font.drawString(x + width - font.getWidth(line), liney, line);
			break;
		case LEFT:
		default:
			font.drawString(x, liney, line);
			break;
		}
	}

	public void clear() {
		this.lines.clear();
	}

	public void addText(String text, Font font, VerticalAlignement align) {
		this.lines.add(new TextAreaLine(text, font, align));
	}

	public void addFirstText(String text, Font font, VerticalAlignement align) {
		this.lines.add(0, new TextAreaLine(text, font, align));
	}

	public void setBottomUp(boolean bottomUp) {
		this.bottomUp = bottomUp;
	}

	public int count() {
		return lines.size();
	}

	public boolean getBottomUp() {
		return bottomUp;
	}

	// public void limit(int size) {
	// this.lines = lines.subList(0, Math.min(lines.size(), size));
	// }

}