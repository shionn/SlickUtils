package shionn.slick.exemple;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import shionn.slick.ui.PasswordTextField;

/**
 * Code sous licence GPLv3 (http://www.gnu.org/licenses/gpl.html)
 * 
 * @author <b>Shionn</b>, shionn@gmail.com <i>http://shionn.org</i><br>
 *         GCS d- s+:+ a+ C++ UL/M P L+ E--- W++ N K- w-- M+ t+ 5 X R+ !tv b+ D+ G- e+++ h+ r- y+
 */
public class PasswordTextFieldExemple extends BasicGame {

	public static void main(String[] args) throws SlickException {
		new AppGameContainer(new PasswordTextFieldExemple(), 800, 600, false).start();
	}

	private PasswordTextField password;

	public PasswordTextFieldExemple() {
		super(PasswordTextFieldExemple.class.getName());
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		Font font = container.getDefaultFont();
		password = new PasswordTextField(container, font, 50, 50, 700, font.getLineHeight());
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		password.render(container, g);
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		System.out.println(password.getText());
	}

}
