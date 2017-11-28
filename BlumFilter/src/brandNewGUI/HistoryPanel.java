package brandNewGUI;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class HistoryPanel extends JScrollPane {

	private static final long serialVersionUID = 657790551078091528L;
	private DefaultListModel<String> history_Model = new DefaultListModel<>();
	private JList<String> history = new JList<>(history_Model);

	public HistoryPanel(final MainFrame mainFrame) {
		this.add(history);
		history.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		history.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				try {
					mainFrame.SetElement(history.getSelectedValue().toString());
				} catch (NullPointerException NPE) {
					history.setSelectedIndex(0);
				}
			}
		});

		this.setViewportView(history);
	}

	public void AddToHistory(String element) {
		if (!history_Model.contains(element))
			this.history_Model.addElement(element);
	}

	public void Remove_From_History(String element) {
		this.history_Model.removeElement(element);
	}
}