package com.kc.model;

import java.awt.GraphicsDevice;



public class ScreenVO
{
	private String name;
	private GraphicsDevice screen;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	
	
	/**
	 * @return the screen
	 */
	public GraphicsDevice getScreen() {
		return screen;
	}

	/**
	 * @param screen the screen to set
	 */
	public void setScreen(GraphicsDevice screen) {
		this.screen = screen;
	}

	public String toString()
	{
		return this.name;
	}
}
