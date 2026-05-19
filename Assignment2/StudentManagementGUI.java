import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class StudentManagementGUI extends JFrame {

    // ── Input Fields ─────────────────────────────────────────
    private JTextField txtId, txtName, txtCourse, txtMarks;

    // ── Buttons ──────────────────────────────────────────────
    private JButton btnAdd, btnUpdate, btnDelete, btnClear;

    // ── Table ────────────────────────────────────────────────
    private JTable table;
    private DefaultTableModel tableModel;

    // ── Data Store ───────────────────────────────────────────
    private ArrayList<String[]> students = new ArrayList<>();

    // ── Colors ───────────────────────────────────────────────
    private final Color NAVY   = new Color(26,  35,  126);
    private final Color STEEL  = new Color(21,  101, 192);
    private final Color LIGHT  = new Color(232, 234, 246);
    private final Color WHITE  = Color.WHITE;
    private final Color DARK   = new Color(33,  33,  33);
    private final Color GREEN  = new Color(27,  94,  32);
    private final Color RED    = new Color(183, 28,  28);
    private final Color ORANGE = new Color(230, 81,  0);
    private final Color GRAY   = new Color(69,  90,  100);

    // ── Constructor ──────────────────────────────────────────
    public StudentManagementGUI() {
        setTitle("Student Management System");
        setSize(860, 620);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(0, 0));
        getContentPane().setBackground(LIGHT);

        add(buildTitlePanel(),  BorderLayout.NORTH);
        add(buildFormPanel(),   BorderLayout.CENTER);
        add(buildTablePanel(),  BorderLayout.SOUTH);

        table_selectListener(); // attach AFTER table is built

        // ── Button Listeners ─────────────────────────────────
        btnAdd.addActionListener(e    -> addStudent());
        btnUpdate.addActionListener(e -> updateStudent());
        btnDelete.addActionListener(e -> deleteStudent());
        btnClear.addActionListener(e  -> clearFields());

        setVisible(true);
    }

    // ── Title Panel ──────────────────────────────────────────
    private JPanel buildTitlePanel() {
        JPanel p = new JPanel(new BorderLayout());
        p.setBackground(NAVY);
        p.setBorder(BorderFactory.createEmptyBorder(14, 20, 14, 20));

        JLabel title = new JLabel("Student Management System", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setForeground(WHITE);

        JLabel sub = new JLabel("GUI-Based Java CRUD Application  |  Assignment 2",
                SwingConstants.CENTER);
        sub.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        sub.setForeground(new Color(200, 210, 255));

        JPanel inner = new JPanel(new GridLayout(2, 1, 0, 4));
        inner.setBackground(NAVY);
        inner.add(title);
        inner.add(sub);
        p.add(inner, BorderLayout.CENTER);
        return p;
    }

    // ── Form + Buttons Panel ──────────────────────────────────
    private JPanel buildFormPanel() {
        JPanel wrapper = new JPanel(new BorderLayout(0, 12));
        wrapper.setBackground(LIGHT);
        wrapper.setBorder(BorderFactory.createEmptyBorder(18, 24, 10, 24));

        // ── Fields ──────────────────────────────────────────
        JPanel fields = new JPanel(new GridLayout(2, 4, 14, 10));
        fields.setBackground(LIGHT);

        txtId     = styledField();
        txtName   = styledField();
        txtCourse = styledField();
        txtMarks  = styledField();

        fields.add(labeledBox("Student ID",  txtId));
        fields.add(labeledBox("Full Name",   txtName));
        fields.add(labeledBox("Course",      txtCourse));
        fields.add(labeledBox("Marks",       txtMarks));

        // ── Buttons ──────────────────────────────────────────
        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER, 16, 0));
        buttons.setBackground(LIGHT);

        btnAdd    = styledButton("➕  Add Student",    GREEN);
        btnUpdate = styledButton("✏  Update Student", ORANGE);
        btnDelete = styledButton("🗑  Delete Student", RED);
        btnClear  = styledButton("✖  Clear Fields",   GRAY);

        buttons.add(btnAdd);
        buttons.add(btnUpdate);
        buttons.add(btnDelete);
        buttons.add(btnClear);

        wrapper.add(fields,  BorderLayout.CENTER);
        wrapper.add(buttons, BorderLayout.SOUTH);

        return wrapper;
    }

    // ── Table Panel ───────────────────────────────────────────
    private JPanel buildTablePanel() {
        JPanel p = new JPanel(new BorderLayout());
        p.setBackground(LIGHT);
        p.setBorder(BorderFactory.createEmptyBorder(0, 24, 20, 24));

        // Section label
        JLabel lbl = new JLabel("  Student Records");
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lbl.setForeground(WHITE);
        lbl.setOpaque(true);
        lbl.setBackground(STEEL);
        lbl.setBorder(BorderFactory.createEmptyBorder(6, 8, 6, 8));

        // Table
        String[] cols = {"ID", "Name", "Course", "Marks"};
        tableModel = new DefaultTableModel(cols, 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };
        table = new JTable(tableModel);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        table.setRowHeight(26);
        table.setSelectionBackground(new Color(197, 202, 233));
        table.setGridColor(new Color(200, 210, 230));
        table.setShowGrid(true);

        // Header styling
        JTableHeader header = table.getTableHeader();
        header.setBackground(NAVY);
        header.setForeground(WHITE);
        header.setFont(new Font("Segoe UI", Font.BOLD, 12));
        header.setPreferredSize(new Dimension(0, 30));

        // Alternating row colors
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable t, Object v,
                    boolean sel, boolean foc, int r, int c) {
                super.getTableCellRendererComponent(t, v, sel, foc, r, c);
                if (sel) {
                    setBackground(new Color(197, 202, 233));
                } else {
                    setBackground(r % 2 == 0 ? WHITE : LIGHT);
                }
                setHorizontalAlignment(c == 0 ? CENTER : LEFT);
                setBorder(BorderFactory.createEmptyBorder(0, 6, 0, 6));
                return this;
            }
        });

        JScrollPane scroll = new JScrollPane(table);
        scroll.setPreferredSize(new Dimension(0, 200));
        scroll.setBorder(BorderFactory.createLineBorder(STEEL, 1));

        p.add(lbl,    BorderLayout.NORTH);
        p.add(scroll, BorderLayout.CENTER);
        return p;
    }

    // ── CRUD Methods ──────────────────────────────────────────
    private void addStudent() {
        String id     = txtId.getText().trim();
        String name   = txtName.getText().trim();
        String course = txtCourse.getText().trim();
        String marks  = txtMarks.getText().trim();

        if (id.isEmpty() || name.isEmpty() || course.isEmpty() || marks.isEmpty()) {
            showMsg("Please fill in all fields!", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        // Check duplicate ID
        for (String[] s : students) {
            if (s[0].equals(id)) {
                showMsg("Student ID already exists!", "Duplicate ID", JOptionPane.WARNING_MESSAGE);
                return;
            }
        }
        String[] row = {id, name, course, marks};
        students.add(row);
        tableModel.addRow(row);
        clearFields();
        showMsg("Student added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private void updateStudent() {
        int selected = table.getSelectedRow();
        if (selected == -1) {
            showMsg("Please select a student from the table to update.",
                    "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String id     = txtId.getText().trim();
        String name   = txtName.getText().trim();
        String course = txtCourse.getText().trim();
        String marks  = txtMarks.getText().trim();

        if (id.isEmpty() || name.isEmpty() || course.isEmpty() || marks.isEmpty()) {
            showMsg("Please fill in all fields!", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        students.set(selected, new String[]{id, name, course, marks});
        tableModel.setValueAt(id,     selected, 0);
        tableModel.setValueAt(name,   selected, 1);
        tableModel.setValueAt(course, selected, 2);
        tableModel.setValueAt(marks,  selected, 3);
        clearFields();
        showMsg("Student updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private void deleteStudent() {
        int selected = table.getSelectedRow();
        if (selected == -1) {
            showMsg("Please select a student from the table to delete.",
                    "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to delete this student?",
                "Confirm Delete", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            students.remove(selected);
            tableModel.removeRow(selected);
            clearFields();
            showMsg("Student deleted successfully!", "Deleted", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void clearFields() {
        txtId.setText("");
        txtName.setText("");
        txtCourse.setText("");
        txtMarks.setText("");
        table.clearSelection();
        txtId.requestFocus();
    }

    // Auto-fill fields when row is clicked
    private void table_selectListener() {
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int r = table.getSelectedRow();
                if (r >= 0) {
                    txtId.setText(tableModel.getValueAt(r, 0).toString());
                    txtName.setText(tableModel.getValueAt(r, 1).toString());
                    txtCourse.setText(tableModel.getValueAt(r, 2).toString());
                    txtMarks.setText(tableModel.getValueAt(r, 3).toString());
                }
            }
        });
    }

    // ── UI Helpers ────────────────────────────────────────────
    private JTextField styledField() {
        JTextField f = new JTextField();
        f.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        f.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(STEEL, 1),
                BorderFactory.createEmptyBorder(5, 8, 5, 8)));
        return f;
    }

    private JPanel labeledBox(String label, JTextField field) {
        JPanel p = new JPanel(new BorderLayout(0, 4));
        p.setBackground(LIGHT);
        JLabel lbl = new JLabel(label);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lbl.setForeground(NAVY);
        p.add(lbl,   BorderLayout.NORTH);
        p.add(field, BorderLayout.CENTER);
        return p;
    }

    private JButton styledButton(String text, Color bg) {
        JButton b = new JButton(text);
        b.setFont(new Font("Segoe UI", Font.BOLD, 12));
        b.setBackground(bg);
        b.setForeground(WHITE);
        b.setFocusPainted(false);
        b.setBorderPainted(false);
        b.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b.setPreferredSize(new Dimension(168, 38));
        b.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                b.setBackground(bg.brighter());
            }
            public void mouseExited(MouseEvent e) {
                b.setBackground(bg);
            }
        });
        return b;
    }

    private void showMsg(String msg, String title, int type) {
        JOptionPane.showMessageDialog(this, msg, title, type);
    }

    // ── Main ──────────────────────────────────────────────────
    public static void main(String[] args) {
        SwingUtilities.invokeLater(StudentManagementGUI::new);
    }
}
