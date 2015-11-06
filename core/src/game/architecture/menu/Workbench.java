/*
 * Copyright (c) 2013 Roman Divotkey, Univ. of Applied Sciences Upper Austria.
 * All rights reserved.
 *
 * THIS CODE IS PROVIDED AS EDUCATIONAL MATERIAL AND NOT INTENDED TO ADDRESS
 * ALL REAL WORLD PROBLEMS AND ISSUES IN DETAIL.
 */
 
package game.architecture.menu;
 
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
 
public class Workbench extends Game {
       
        public static final float V_WIDTH = 1366;
        public static final float V_HEIGHT = 768;
        public static final String VERSION = "1.0.0";
        public static final String APP_TITLE = "Workbench";
 
        private Stage stage;
        private SpriteBatch batch;
       
        public Workbench(){}
        
        @Override
        public void create () {
                batch = new SpriteBatch();
                stage = new Stage();
                setScreen(new MenuScreen(this));
        }
 
        @Override
        public void render () {
                // TODO: add tasks that need to be updated for all screens (aka state)
               
                // call render of base class which will call render for current screen
                super.render();
               
        }
 
        public SpriteBatch getBatch() {
                return batch;
        }
       
        public Stage getStage() {
                return stage;
        }
}