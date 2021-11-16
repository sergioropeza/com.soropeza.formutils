package org.soropeza.webui.component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.adempiere.webui.component.Button;
import org.adempiere.webui.component.ComboEditorBox;
import org.adempiere.webui.component.Combobox;
import org.adempiere.webui.component.Grid;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.NumberBox;
import org.adempiere.webui.component.Row;
import org.adempiere.webui.component.Rows;
import org.adempiere.webui.component.ToolBarButton;
import org.adempiere.webui.editor.WEditor;
import org.adempiere.webui.editor.WSearchEditor;
import org.adempiere.webui.event.ValueChangeEvent;
import org.adempiere.webui.event.ValueChangeListener;
import org.adempiere.webui.util.ZKUpdateUtil;
import org.compiere.util.Env;
import org.soropeza.event.GridValueChangeEvent;
import org.soropeza.listener.GridValueChangeListener;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Components;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Cell;
import org.zkoss.zul.Decimalbox;
import org.zkoss.zul.Layout;

public class WGrid extends Grid implements ValueChangeListener, EventListener<Event> {

	private Rows rows;
	private Row header;
	private GridValueChangeListener listener;
	private String styleLabelsBold;
	private List<Integer> lstColSpan = new ArrayList<Integer>();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public WGrid() {
		super();
		styleLabelsBold = "font-size:13px;font-weight: bold";
		rows = this.newRows();
		header = rows.newRow();
		header.setStyle("background-color: #DEDDDD");

	}

	public void addHeader(String title, int colSpan) {
		Label lblTitle = new Label(title);
		lblTitle.setStyle(styleLabelsBold);
		lstColSpan.add(colSpan);
		header.appendCellChild(lblTitle, colSpan);

	}

	public void clear() {
		Components.removeAllChildren(this);
		rows = this.newRows();
		rows.appendChild(header);

	}

	public void addHeader(String title) {
		addHeader(title, 1);
	}

	public void addLine(Object... columns) {
		addLine(-1, columns);
	}

	public void addLine(Integer Record_ID, Object... columns) {
		addLine(null, Record_ID, columns);

	}

	public void addLine(ListCellStyle style, Integer Record_ID, Object... columns) {
		Row row = rows.newRow();
		if (style != null)
			row.setStyle(style.getStyle());
		row.setAttribute("Record_ID", Record_ID);

		for (int i = 0; i < columns.length; i++) {
			if (columns[i] == null) {
				row.appendCellChild(new Label());
			} else if (columns[i] instanceof SearchEditorColumn) {
				SearchEditorColumn editorColumn = (SearchEditorColumn) columns[i];
				editorColumn.addValueChangeListener(this);
				ZKUpdateUtil.setWidth(editorColumn.getComponent(), "100%");
				row.appendCellChild(editorColumn.getComponent(), lstColSpan.get(i));

			} else if (columns[i] instanceof TableDirEditorColumn) {
				TableDirEditorColumn editorColumn = (TableDirEditorColumn) columns[i];
				editorColumn.addValueChangeListener(this);
				ZKUpdateUtil.setWidth(editorColumn.getComponent(), "100%");
				row.appendCellChild(editorColumn.getComponent(), lstColSpan.get(i));

			} else if (columns[i] instanceof StringColumn) {
				StringColumn editorColumn = (StringColumn) columns[i];
				editorColumn.addValueChangeListener(this);
				ZKUpdateUtil.setWidth(editorColumn.getComponent(), "100%");
				row.appendCellChild(editorColumn.getComponent(), lstColSpan.get(i));

			} else if (columns[i] instanceof NumberColumn) {
				NumberColumn numberEditor = (NumberColumn) columns[i];
				numberEditor.addEventListener(Events.ON_CHANGE, this);
				row.appendCellChild(numberEditor, lstColSpan.get(i));

			} else if (columns[i] instanceof BooleanColumn) {
				BooleanColumn editorColumn = (BooleanColumn) columns[i];
				editorColumn.addEventListener(Events.ON_CHANGE, this);
				row.appendCellChild(editorColumn, lstColSpan.get(i));
			} else if (columns[i] instanceof KeyNamePairColumn) {
				KeyNamePairColumn editorColumn = (KeyNamePairColumn) columns[i];
				row.appendCellChild(editorColumn, lstColSpan.get(i));

			}else if (columns[i] instanceof ToolBarButton) {
				ToolBarButton btn = (ToolBarButton) columns[i];
				btn.setAttribute("Record_ID", Record_ID);
				row.appendCellChild(btn, lstColSpan.get(i));

			} else if (columns[i] instanceof Layout) {
				Layout layout = (Layout)columns[i];
				List<Component> childrens = layout.getChildren();
				for (Component component : childrens) {
					if (component instanceof Button) {
						Button btn = (Button)component;
						btn.setAttribute("Record_ID", Record_ID);
						btn.addEventListener(Events.ON_CLICK, this);
					}
				}
				row.appendCellChild(layout, lstColSpan.get(i));
			} else {
				row.appendCellChild(new Label(columns[i].toString()), lstColSpan.get(i));
			}
		}

	}

	@Override
	public void valueChange(ValueChangeEvent evt) {
		Object source = evt.getSource();
		WEditor editor = (WEditor) source;
		Cell cell = (Cell) editor.getComponent().getParent();
		Row row = (Row) cell.getParent();
		int iRow = row.getIndex();
		int iColumn = getColumnIndex(cell, row);
		if (listener != null && evt.getOldValue() != evt.getNewValue()) {
			GridValueChangeEvent event = new GridValueChangeEvent(source, iRow, iColumn, evt.getOldValue(),
					evt.getNewValue());
			listener.gridValueChange(event);
		}
	}

	private int getColumnIndex(Cell cell, Row row) {
		List<Component> cells = row.getChildren();
		for (int j = 0; j < cells.size(); j++) {
			if ((Cell) cells.get(j) == cell)
				return j;
		}
		return -1;
	}

	public int getRowRecord_ID(int iRow) {
		Row row = (Row) getCell(iRow, 0).getParent();
		int Record_ID = (int) row.getAttribute("Record_ID");
		return Record_ID;
	}
	public void setValue(Object Value, int row, int column) {
		setValue(Value, row, column, true);
	}

	public void setValue(Object Value, int row, int column, Boolean isActiveEvent) {
		Component component = getCell(row, column).getFirstChild();
		if (component instanceof Combobox) {
			Combobox editor = (Combobox) component;
			editor.setValue(Value);
			if (isActiveEvent) {
				fireValueChangeEvent(editor, Value);
			}
		} else if (component instanceof NumberBox) {
			NumberBox editor = (NumberBox) component;
			editor.setValue(Value);
			if (isActiveEvent) {
				fireValueChangeEvent(editor, Value);
			}
		} else if (component instanceof Label) {
			Label editor = (Label) component;
			String value = Value == null ? "" : Value.toString();
			editor.setValue(value);

		}else if (component instanceof ComboEditorBox) {
			ComboEditorBox editor = (ComboEditorBox) component;
			WSearchEditor searchEditor = (WSearchEditor) editor.getAttribute("searchEditor");
			searchEditor.setValue(Value);
			
		}

	}

	public void setCellVisible(Boolean isVisible, int row, int column) {
		Component component = getCell(row, column).getFirstChild();
		component.setVisible(isVisible);

	}

	public Object getValue(int row, int column) {
		Component component = getCell(row, column).getFirstChild();
		Object value = null;
		if (component instanceof Combobox) {
			Combobox editor = (Combobox) component;
			if (editor.getSelectedItem() != null)
				value = editor.getSelectedItem().getValue();
		} else if (component instanceof NumberBox) {
			NumberBox editor = (NumberBox) component;
			value = editor.getValue();
		} else if (component instanceof KeyNamePairColumn) {
			KeyNamePairColumn editor = (KeyNamePairColumn) component;
			value = editor.getRecord_ID();
		}else if (component instanceof ComboEditorBox) {
			ComboEditorBox editor = (ComboEditorBox) component;
			WSearchEditor searchEditor = (WSearchEditor) editor.getAttribute("searchEditor");
			value= searchEditor.getValue();	
		}else if (component instanceof BooleanColumn) {
			BooleanColumn editor = (BooleanColumn) component;
			value= editor.getValue();
		}
		return value;
	}

	public int getSize() {
		int size = 0;
		if (getRows() != null) {
			size = getRows().getChildren().size();
		}
		return size;
	}

	@Override
	public void onEvent(Event evt) throws Exception {
		
		if (evt.getTarget() instanceof Decimalbox) {
			Decimalbox source = (Decimalbox) evt.getTarget();
			NumberColumn numberColumn =(NumberColumn) source.getParent();
			Cell cell = (Cell) numberColumn.getParent();
			Row row = (Row) cell.getParent();
			int iRow = row.getIndex();
			int iColumn = getColumnIndex(cell, row);
			if (listener != null) {
				GridValueChangeEvent event = new GridValueChangeEvent(source, iRow, iColumn, numberColumn.getOldValue(), source.getValue());
				listener.gridValueChange(event);
			}
			
		}

	}

	private void fireValueChangeEvent(Component source, Object value) {
		Cell cell = (Cell) source.getParent();
		Row row = (Row) cell.getParent();
		int iRow = row.getIndex();
		int iColumn = getColumnIndex(cell, row);
		if (listener != null) {
			GridValueChangeEvent event = new GridValueChangeEvent(source, iRow, iColumn, value, value);
			listener.gridValueChange(event);
		}
	}

	public void addGridValueChangeListener(GridValueChangeListener listener) {
		if (this.listener == null)
			this.listener = listener;
	}

}
