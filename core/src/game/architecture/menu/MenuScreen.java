package game.architecture.menu;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import game.architecture.engine.Engine;

public class MenuScreen extends ScreenAdapter {

	OrthographicCamera camera;

	float screenWidth = Gdx.graphics.getWidth();
	float screenHeight = Gdx.graphics.getHeight();

	private TextureAtlas atlas;
	private Skin skin;
	private Stage stage;

	public float pixelSize;

	public int selectedButton = 1;

	private TextButton startButton, optionsButton, exitButton;

	private Workbench workbench;

	public MenuScreen(Workbench workbench) {
		this.workbench = workbench;
		pixelSize = 120.0f / Gdx.graphics.getHeight();

		camera = new OrthographicCamera();
		camera.setToOrtho(false, screenWidth * pixelSize, screenHeight * pixelSize);

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

		startButton = new TextButton("START", textButtonStyle);
		optionsButton = new TextButton("OPTIONS", textButtonStyle);
		exitButton = new TextButton("EXIT", textButtonStyle);
		
		changeButtonSize();

		startButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				((Game) Gdx.app.getApplicationListener()).setScreen(new Engine(workbench));
			}
		});

		optionsButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				// ((Game) Gdx.app.getApplicationListener()).setScreen(new
				// OptionScreen(workbench));
			}
		});

		exitButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				System.exit(0);
			}
		});
		
		stage.addActor(startButton);
		stage.addActor(optionsButton);
		stage.addActor(exitButton);
		stage.draw();
	}

	@Override
	public void render(float delta) {

		if (Gdx.input.isKeyJustPressed(Keys.DPAD_UP)) {
			selectedButton--;
			if (selectedButton == 0) {
				selectedButton = 3;
			}
		}

		if (Gdx.input.isKeyJustPressed(Keys.DPAD_DOWN)) {
			selectedButton++;
			if (selectedButton > 3) {
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
				System.exit(0);
				break;
			}
			
		}

		changeButtonSize();

		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camera.update();

		stage.act(delta);
		stage.draw();

		// if (Gdx.input.isTouched()) {
		// // ((Game) Gdx.app.getApplicationListener()).setScreen(game);
		// dispose();
		// }

	}

	public void changeButtonSize() {
		
		float xLow  = screenWidth / 2 - 175;
		float xHigh = screenWidth / 2 - 150;
		
		float widthSmall = 300;
		float widthBig = 350;
		
		float heightSmall = 100;
		float heightBig = 120;
		
		switch(selectedButton){
		case 1:
			startButton.setBounds(xLow, screenHeight / 2 + 60, widthBig, heightBig);
			optionsButton.setBounds(xHigh, screenHeight / 2, widthSmall, heightSmall);
			exitButton.setBounds(xHigh, screenHeight / 2 - 60, widthSmall, heightSmall);
			break;
		case 2:
			startButton.setBounds(xHigh, screenHeight / 2 + 60, widthSmall, heightSmall);
			optionsButton.setBounds(xLow, screenHeight / 2, widthBig, heightBig);
			exitButton.setBounds(xHigh, screenHeight / 2 - 60, widthSmall, heightSmall);
			break;
		case 3: 
			startButton.setBounds(xHigh, screenHeight / 2 + 60, widthSmall, heightSmall);
			optionsButton.setBounds(xHigh, screenHeight / 2, widthSmall, heightSmall);
			exitButton.setBounds(xLow, screenHeight / 2 - 60, widthBig, heightBig);
			break;
		}
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
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