package game.architecture.menu;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

import game.architecture.engine.Editor;
import game.architecture.engine.Engine;
import game.architecture.engine.ServiceLocator;
import game.architecture.systems.CameraSystem;

public class MenuScreen extends ScreenAdapter {

	private TextureAtlas atlas;
	private Skin skin;
	private Stage stage;

	public float pixelSize;

	public int selectedButton = 1;

	private List<TextButton> textButtons = new ArrayList<TextButton>();
	private Workbench workbench;

	public MenuScreen(Workbench workbench) {
		this.workbench = workbench;
	}

	@Override
	public void show() {

		super.show();

		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		skin = new Skin();
		atlas = new TextureAtlas(Gdx.files.internal("button.pack"));
		skin.addRegions(atlas);
		TextButtonStyle textButtonStyle = new TextButtonStyle();
		BitmapFont f = new BitmapFont(Gdx.files.internal("arial_black_32.fnt"));
		textButtonStyle.font = f;
		textButtonStyle.up = skin.getDrawable("button");
		textButtonStyle.down = skin.getDrawable("button");
		textButtonStyle.checked = skin.getDrawable("button");

		textButtons.add(new TextButton("START", textButtonStyle));
		textButtons.add(new TextButton("OPTIONS", textButtonStyle));
		textButtons.add(new TextButton("EDITOR", textButtonStyle));
		textButtons.add(new TextButton("EXIT", textButtonStyle));

		changeButtonSize();
		
		for (TextButton t : textButtons)
			stage.addActor(t);

		stage.draw();
	}

	@Override
	public void render(float delta) {

		if (Gdx.input.isKeyJustPressed(Keys.DPAD_UP)) {
			selectedButton--;
			if (selectedButton == 0) {
				selectedButton = textButtons.size();
			}
		}

		if (Gdx.input.isKeyJustPressed(Keys.DPAD_DOWN)) {
			selectedButton++;
			if (selectedButton > textButtons.size()) {
				selectedButton = 1;
			}
		}

		if (Gdx.input.isKeyJustPressed(Keys.ENTER)) {
			switch(selectedButton){
			case 1:
				((Game) Gdx.app.getApplicationListener()).setScreen(new Engine(workbench));
				break;
			case 2:
				break;
			case 3:
				((Game) Gdx.app.getApplicationListener()).setScreen(new Editor(workbench));
				break;
			case 4:
				System.exit(0);
				break;
			}
		}

		changeButtonSize();

		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		((CameraSystem)ServiceLocator.GetService(CameraSystem.class)).Update();
		
		stage.act(delta);
		stage.draw();
	}

	public void changeButtonSize() {
		
		float xLow  = ServiceLocator.V_WIDTH / 2 - 175;
		float xHigh = ServiceLocator.V_WIDTH / 2 - 150;
		
		float widthSmall = 300;
		float widthBig = 350;
		
		float heightSmall = 100;
		float heightBig = 120;
		
		switch(selectedButton){
		case 1:
			textButtons.get(0).setBounds(xLow, ServiceLocator.V_HEIGHT / 2 + 60, widthBig, heightBig);
			textButtons.get(1).setBounds(xHigh, ServiceLocator.V_HEIGHT / 2, widthSmall, heightSmall);
			textButtons.get(2).setBounds(xHigh, ServiceLocator.V_HEIGHT / 2 - 60, widthSmall, heightSmall);
			textButtons.get(3).setBounds(xHigh, ServiceLocator.V_HEIGHT / 2 - 120, widthSmall, heightSmall);
			break;
		case 2:
			textButtons.get(0).setBounds(xHigh, ServiceLocator.V_HEIGHT / 2 + 60, widthSmall, heightSmall);
			textButtons.get(1).setBounds(xLow, ServiceLocator.V_HEIGHT / 2, widthBig, heightBig);
			textButtons.get(2).setBounds(xHigh, ServiceLocator.V_HEIGHT / 2 - 60, widthSmall, heightSmall);
			textButtons.get(3).setBounds(xHigh, ServiceLocator.V_HEIGHT / 2 - 120, widthSmall, heightSmall);
			break;
		case 3: 
			textButtons.get(0).setBounds(xHigh, ServiceLocator.V_HEIGHT / 2 + 60, widthSmall, heightSmall);
			textButtons.get(1).setBounds(xHigh, ServiceLocator.V_HEIGHT / 2, widthSmall, heightSmall);
			textButtons.get(2).setBounds(xLow, ServiceLocator.V_HEIGHT / 2 - 60, widthBig, heightBig);
			textButtons.get(3).setBounds(xHigh, ServiceLocator.V_HEIGHT / 2 - 120, widthSmall, heightSmall);
			break;
		case 4:
			textButtons.get(0).setBounds(xHigh, ServiceLocator.V_HEIGHT / 2 + 60, widthSmall, heightSmall);
			textButtons.get(1).setBounds(xHigh, ServiceLocator.V_HEIGHT / 2, widthSmall, heightSmall);
			textButtons.get(2).setBounds(xHigh, ServiceLocator.V_HEIGHT / 2 - 60, widthSmall, heightSmall);
			textButtons.get(3).setBounds(xLow, ServiceLocator.V_HEIGHT / 2 - 120, widthBig, heightBig);
			break;
		}
	}

	@Override
	public void resize(int width, int height) {
		//super.resize(width, height);
	}

	@Override
	public void hide() {
		super.hide();
		dispose();
	}

	@Override
	public void dispose() {
		atlas.dispose();
		skin.dispose();
		stage.dispose();
		super.dispose();
	}

}