package org.ardestan.mac;

import java.awt.Desktop;
import java.awt.Image;
import java.awt.Taskbar;
import java.awt.desktop.AboutEvent;
import java.awt.desktop.AboutHandler;
import java.awt.desktop.PreferencesEvent;
import java.awt.desktop.PreferencesHandler;
import java.awt.desktop.QuitEvent;
import java.awt.desktop.QuitHandler;
import java.awt.desktop.QuitResponse;

/**
 * @author hnishino
 *
 */
public class MacAppHelperJDK9orLater implements AboutHandler, PreferencesHandler, QuitHandler
{
	
	/**
	 * @param listener
	 */
	public static void addMacAppHelperListener(MacAppHelperListener listener)
	{
		Desktop desktop = Desktop.getDesktop();
		
		MacAppHelperJDK9orLater helper = new MacAppHelperJDK9orLater(listener);
		desktop.setAboutHandler(helper);
		desktop.setPreferencesHandler(helper);
		desktop.setQuitHandler(helper);
	}
	
	/**
	 * @param dockIconImage
	 */
	public static void setMacDockIconImage(Image dockIconImage) 
	{
		Taskbar.getTaskbar().setIconImage(dockIconImage);
	}
	
	MacAppHelperListener listener = null;

	/**
	 * @param listener
	 */
	public MacAppHelperJDK9orLater(MacAppHelperListener listener)
	{
		this.listener = listener;
	}
	/**
	 *
	 */
	@Override
	public void handleQuitRequestWith(QuitEvent e, QuitResponse response) {
		listener.handleQuit();
		response.cancelQuit();
	}

	/**
	 *
	 */
	@Override
	public void handlePreferences(PreferencesEvent e) {
		listener.handlePreferences();
	}

	/**
	 *
	 */
	@Override
	public void handleAbout(AboutEvent e) {
		listener.handleAbout();
	}


}
