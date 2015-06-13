package com.rak.funnyfarmv4.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.rak.funnyfarmv4.Maingame;
import com.rak.funnyfarmv4.desktop.ipl.DekstopGps;
import com.rak.funnyfarmv4.desktop.ipl.ConfirmUIDesktop;
import com.rak.funnyfarmv4.desktop.ipl.LunchAppDesktop;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new Maingame(new DekstopGps(),1,1,new ConfirmUIDesktop(),new LunchAppDesktop()), config);
	}
}
