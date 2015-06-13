package com.rak.funnyfarmv4.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.rak.funnyfarmv4.Maingame;
import com.rak.funnyfarmv4.client.ipl.HTMLLeaderboard;
import com.rak.funnyfarmv4.client.ipl.HTMLgps;

public class HtmlLauncher extends GwtApplication {

        @Override
        public GwtApplicationConfiguration getConfig () {
                return new GwtApplicationConfiguration(480, 320);
        }

        @Override
        public ApplicationListener getApplicationListener () {
                return new Maingame(new HTMLgps(),1,1,new HTMLLeaderboard());
        }
}