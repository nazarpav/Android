package com.nazar_pavliuk.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.nazar_pavliuk.game.GDX_main;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
//		config.width=800;
//		config.height=360;
		config.width=800;
		config.height=400;
//		config.width=1920;
//		config.height=1080;
		new LwjglApplication(new GDX_main(), config);
	}
}
