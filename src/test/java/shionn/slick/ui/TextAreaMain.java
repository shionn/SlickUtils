package shionn.slick.ui;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import shionn.slick.ui.align.VerticalAlignement;

/**
 * Code sous licence GPLv3 (http://www.gnu.org/licenses/gpl.html)
 * 
 * @author <b>Shionn</b>, shionn@gmail.com <i>http://shionn.org</i><br>
 *         GCS d- s+:+ a+ C++ UL/M P L+ E--- W++ N K- w-- M+ t+ 5 X R+ !tv b+ D+ G- e+++ h+ r- y+
 */
public class TextAreaMain extends BasicGame {

	public static void main(String[] args) throws SlickException {
		new AppGameContainer(new TextAreaMain(), 800, 600, false).start();
	}

	private TextArea textArea;

	public TextAreaMain() {
		super(TextArea.class.getName());
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		textArea = new TextArea(50, 50, 700, 500);
		textArea.addText(UITestConstants.P1, container.getDefaultFont(), VerticalAlignement.LEFT);
		textArea.addText(UITestConstants.P2, container.getDefaultFont(), VerticalAlignement.CENTER);
		textArea.addText(UITestConstants.P3, container.getDefaultFont(), VerticalAlignement.RIGHT);
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		g.drawRect(50, 50, 700, 500);
		textArea.render();
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
	}

	public void keyPressed(int key, char c) {
		switch (c) {
		case ' ':
			textArea.setBottomUp(!textArea.getBottomUp());

			break;

		default:
			break;
		}
	};
}
