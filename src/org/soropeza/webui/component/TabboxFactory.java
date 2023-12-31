package org.soropeza.webui.component;


import org.adempiere.webui.util.ZKUpdateUtil;
import org.zkoss.zul.Tabpanels;
import org.zkoss.zul.Tabs;

/**
 * 
 * @author Sergio Oropeza
 *
 */
public class TabboxFactory {

	/**
	 * New instance of grid for form layout
	 * @return Grid
	 */
	public static Tabbox newTabBox() {
		Tabbox tabBox = new Tabbox();
		ZKUpdateUtil.setHflex(tabBox, "1");
		ZKUpdateUtil.setVflex(tabBox, "1");
		
		Tabpanels tabpanels = new Tabpanels();
		Tabs tabs = new Tabs();
		ZKUpdateUtil.setHflex(tabpanels, "1");
		tabBox.appendChild(tabs);
		tabBox.appendChild(tabpanels);
		return tabBox;
	}
}
