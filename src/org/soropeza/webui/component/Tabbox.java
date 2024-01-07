package org.soropeza.webui.component;

import org.adempiere.webui.component.Tab;
import org.zkoss.zul.Tabpanel;
import org.zkoss.zul.Tabpanels;
import org.zkoss.zul.Tabs;

public class Tabbox extends org.adempiere.webui.component.Tabbox{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Tabpanel newTabpanel(String label) {
		Tabpanel tabPanel = new Tabpanel();
		Tab tab= new Tab();
		tab.setLabel(label);

		Tabpanels tabpanels = this.getTabpanels();
		Tabs tabs = this.getTabs();
		tabpanels.appendChild(tabPanel);
		tabs.appendChild(tab);
		
		return tabPanel;
		
	}
	

}
