package de.hdm.myjob.client;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;

public abstract class ShowDefinition extends VerticalPanel {
	
	/**
	 * Methode die beim Aufrufen eines GWT-Widgets ausgeführt wird
	 */
	public void onLoad(){
		
		super.onLoad();
		
		this.add(this.createHeadline(this.getHeadlineText()));
		
		run();
	}
	
	/**
	 * Erzeugung einer Überschrift für die Seite
	 * @param headlineText
	 * @return
	 */
	protected HTML createHeadline(String headlineText) {
	    HTML content = new HTML(headlineText);
	    content.setStylePrimaryName("myjob-headline");
	    return content;
	}
	
	/**
	 * Hinzufügen von Text auf der Seite
	 * @param text
	 */
	protected void append(String text) {
		HTML content = new HTML(text);
		content.setStylePrimaryName("myjob-simpletext"); 
		this.add(content);
	}

	/**
	 * Wird in den Supklassen initialisiert
	 * @return
	 */
	protected abstract String getHeadlineText();
	
	/**
	 * Wird in den Supklassen initialisiert
	 * @return
	 */
	protected abstract void run();
	
}