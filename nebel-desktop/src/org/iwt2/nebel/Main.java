package org.iwt2.nebel;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Nelbe";
		cfg.useGL20 = false;
		//-- Collisiond works !!!
		//cfg.width = 480;
		//cfg.height = 320;
		//---- WVGA800 800 x 480
		// Android - Fine but slow.
		// Collisions works fine
		//cfg.width = 800;
		//cfg.height = 480;
		//----
		// PC - It works nice
		//cfg.width = 640;
		//cfg.height = 480;
		//---- WQVGA400 400 x 240
		// PC - It works nice
		cfg.width = 400;
		cfg.height = 240;
		//---- QVGA 320 x 240
		// PC - It works but the main screen which is too big
		//cfg.width = 320;
		//cfg.height = 240;
		//---- FWQVGA 432 x 240
		// PC - It avoid the statisic screen after the first time
		//cfg.width = 432;
		//cfg.height = 240;
		// Sony Xperia S
		//cfg.width = 1280;
		//cfg.height = 720;
		
		new LwjglApplication(new NebelGame(), cfg);
	}
}
