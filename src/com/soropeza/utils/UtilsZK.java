package com.soropeza.utils;



import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Div;

public final class UtilsZK {
	
	public static Component makeRightAlign(Component component) {
		Div div = new Div();
		div.setWidth("100%");
		div.setStyle("text-align: right");
		div.appendChild(component);
		
		return div;
	}

}
