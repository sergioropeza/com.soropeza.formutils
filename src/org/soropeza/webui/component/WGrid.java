package org.soropeza.webui.component;

import java.util.List;

import org.adempiere.webui.component.Checkbox;
import org.adempiere.webui.component.Combobox;
import org.adempiere.webui.component.Grid;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.NumberBox;
import org.adempiere.webui.component.Row;
import org.adempiere.webui.component.Rows;
import org.adempiere.webui.editor.WEditor;
import org.adempiere.webui.editor.WNumberEditor;
import org.adempiere.webui.editor.WSearchEditor;
import org.adempiere.webui.editor.WStringEditor;
import org.adempiere.webui.editor.WTableDirEditor;
import org.adempiere.webui.event.ValueChangeEvent;
import org.adempiere.webui.event.ValueChangeListener;
import org.adempiere.webui.util.ZKUpdateUtil;
import org.soropeza.event.GridValueChangeEvent;
import org.soropeza.listener.GridValueChangeListener;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Cell;

public class WGrid extends Grid implements ValueChangeListener, EventListener<Event> {

	private Rows rows;
	private Row header;
	private GridValueChangeListener listener;
	private String styleLabelsBold;
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
		header.appendCellChild(lblTitle, colSpan);

	}

	public void addHeader(String title) {
		addHeader(title, 1);
	}

	public void addLine(Object... columns) {
		addLine(-1, columns);
	}

	public void addLine(Integer Record_ID, Object... columns) {
		Row row = rows.newRow();
		row.setAttribute("Record_ID", Record_ID);

		for (int i = 0; i < columns.length; i++) {

			if (columns[i] instanceof SearchEditorColumn) {
				SearchEditorColumn editorColumn = (SearchEditorColumn) columns[i];
				editorColumn.addValueChangeListener(this);
				ZKUpdateUtil.setWidth(editorColumn.getComponent(), "100%");
				row.appendCellChild(editorColumn.getComponent());

			} else if (columns[i] instanceof TableDirEditorColumn) {
				TableDirEditorColumn editorColumn = (TableDirEditorColumn) columns[i];
				editorColumn.addValueChangeListener(this);
				ZKUpdateUtil.setWidth(editorColumn.getComponent(), "100%");
				row.appendCellChild(editorColumn.getComponent());

			} else if (columns[i] instanceof StringColumn) {
				StringColumn editorColumn = (StringColumn) columns[i];
				editorColumn.addValueChangeListener(this);
				ZKUpdateUtil.setWidth(editorColumn.getComponent(), "100%");
				row.appendCellChild(editorColumn.getComponent());

			} else if (columns[i] instanceof NumberColumn) {
				NumberColumn numberEditor = (NumberColumn) columns[i];
				numberEditor.addValueChangeListener(this);
				row.appendCellChild(numberEditor.getComponent());

			} else if (columns[i] instanceof BooleanColumn) {
				BooleanColumn editorColumn = (BooleanColumn) columns[i];
				editorColumn.addEventListener(Events.ON_CHANGE, this);
				row.appendCellChild(editorColumn);
			} else if (columns[i] instanceof KeyNamePairColumn) {
				KeyNamePairColumn editorColumn = (KeyNamePairColumn) columns[i];
				row.appendCellChild(editorColumn);
			}
		}

	}

	@Override
	public void valueChange(ValueChangeEvent evt) {
		Object source = evt.getSource();
		WEditor editor = (WEditor) source;
		Cell cell = (Cell) editor.getComponent().getParent();
		Row row = (Row) cell.getParent();
		int iRow = row.getIndex() - 1;
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

	public void setValue(int row, int column, Object Value) {
		Component component = getCell(row, column).getFirstChild();
		if (component instanceof Combobox) {
			Combobox editor = (Combobox) component;
			editor.setValue(Value);
		} else if (component instanceof NumberBox) {
			NumberBox editor = (NumberBox) component;
			editor.setValue(Value);
		}

	}

	public Object getValue(int row, int column) {
		Component component = getCell(row, column).getFirstChild();
		Object value = null;
		if (component instanceof Combobox) {
			Combobox editor = (Combobox) component;
			value = editor.getSelectedItem().getValue();
		} else if (component instanceof NumberBox) {
			NumberBox editor = (NumberBox) component;
			value = editor.getValue();
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
	public void onEvent(Event event) throws Exception {
		// TODO Auto-generated method stub

	}

	public void addGridValueChangeListener(GridValueChangeListener listener) {
		if (this.listener == null)
			this.listener = listener;
	}

}
