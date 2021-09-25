/******************************************************************************
 * Product: Posterita Ajax UI 												  *
 * Copyright (C) 2007 Posterita Ltd.  All Rights Reserved.                    *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * Posterita Ltd., 3, Draper Avenue, Quatre Bornes, Mauritius                 *
 * or via info@posterita.org or http://www.posterita.org/                     *
 *****************************************************************************/

package org.soropeza.webui.component;

import java.util.Vector;

import org.adempiere.webui.component.ListModelTable;
import org.soropeza.listener.OnClickComponentTableListener;



/**
 * @author Sergio Oropeza
 */
public class WListbox extends org.adempiere.webui.component.WListbox implements  OnClickComponentTableListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5501893389366975849L;

	
	protected OnClickComponentTableListener onClickButtonlistener = null;
	private Vector<Vector<Object>> lines;


	private ListModelTable orderlineModelTable;

	/**
	 * Default constructor.
	 *
	 * Sets a row renderer and an empty model
	 */
	public WListbox()
	{
		super();
		WListItemRenderer rowRenderer = new WListItemRenderer();
	    rowRenderer.addTableValueChangeListener(this);
	    rowRenderer.addTableOnClickComponentListener(this);

		setItemRenderer(rowRenderer);
		setModel(new ListModelTable());
		lines = new Vector<Vector<Object>>();
		orderlineModelTable = new ListModelTable(lines);
	}


	public void addTableOnClickComponentListener(OnClickComponentTableListener listener){ //Update sergioropeza88@gmail.com
	    if (listener == null){
	    	return;
	    }
	    onClickButtonlistener = listener;
	}
	
	@Override
	public void onClickComponentTable(WListbox listBox, Object component, int index, int column) {
		if (onClickButtonlistener!=null)
			onClickButtonlistener.onClickComponentTable(this,component, index, column);
		
	}

	public void clear() {
		lines = new Vector<Vector<Object>>();	
	}
	public void addLine(Object ...rows) {
		Vector<Object> line = new Vector<Object>();
		for (Object row : rows) {
			line.add(row);
		}
		lines.add(line);
	}
	
	public void refresh() {
		orderlineModelTable = new ListModelTable(lines);
		setModel(orderlineModelTable);
		repaint();
	}

	public Vector<Vector<Object>> getLines() {
		return lines;
	}


	public void setLines(Vector<Vector<Object>> lines) {
		this.lines = lines;
	}

	public ListModelTable getOrderlineModelTable() {
		return orderlineModelTable;
	}


	public void setOrderlineModelTable(ListModelTable orderlineModelTable) {
		this.orderlineModelTable = orderlineModelTable;
	}

}
