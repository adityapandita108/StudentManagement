import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.util.ArrayList;

public class StudentManagementGUI extends JFrame {

    private JTextField txtId, txtName, txtCourse, txtMarks;
    private JButton btnAdd, btnClear;
    private JTable table;
    private DefaultTableModel tableModel;
    private ArrayList<String[]> students = new ArrayList<>();

    private final Color NAVY  = new Color(26, 35, 126);
    private final Color STEEL = new Color(21, 101, 192);
    private final Color LIGHT = new Color(232, 234, 246);
    private final Color WHITE = Color.WHITE;
    private final Color GREEN = new Color(27, 94, 32);
    private final Color GRAY  = new Color(69, 90, 100);

    public StudentManagementGUI() {
        setTitle("Student Management System");
        setSize(860, 520);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(LIGHT);

        add(buildTitlePanel(), BorderLayout.NORTH);
        add(buildFormPanel(), BorderLayout.CENTER);
        add(buildTablePanel(), BorderLayout.SOUTH);

        btnAdd.addActionListener(e -> addStudent());
        btnClear.addActionListener(e -> clearFields());
        setVisible(true);
    }

    private JPanel buildTitlePanel() {
        JPanel p = new JPanel(new BorderLayout());
        p.setBackground(NAVY);
        p.setBorder(BorderFactory.createEmptyBorder(14, 20, 14, 20));
        JLabel title = new JLabel("Student Management System", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setForeground(WHITE);
        JLabel sub = new JLabel("GUI-Based Java CRUD Application  |  Assignment 2", SwingConstants.CENTER);
        sub.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        sub.setForeground(new Color(200, 210, 255));
        JPanel inner = new JPanel(new GridLayout(2, 1, 0, 4));
        inner.setBackground(NAVY);
        inner.add(title); inner.add(sub);
        p.add(inner, BorderLayout.CENTER);
        return p;
    }

    private JPanel buildFormPanel() {
        JPanel wrapper = new JPanel(new BorderLayout(0, 12));
        wrapper.setBackground(LIGHT);
        wrapper.setBorder(BorderFactory.createEmptyBorder(18, 24, 10, 24));
        JPanel fields = new JPanel(new GridLayout(2, 4, 14, 10));
        fields.setBackground(LIGHT);
        txtId = styledField(); txtName = styledField();
        txtCourse = styledField(); txtMarks = styledField();
        fields.add(labeledBox("Student ID", txtId));
        fields.add(labeledBox("Full Name", txtName));
        fields.add(labeledBox("Course", txtCourse));
        fields.add(labeledBox("Marks", txtMarks));
        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER, 16, 0));
        buttons.setBackground(LIGHT);
        btnAdd   = styledButton("➕  Add Student",  GREEN);
        btnClear = styledButton("✖  Clear Fields", GRAY);
        buttons.add(btnAdd); buttons.add(btnClear);
        wrapper.add(fields, BorderLayout.CENTER);
        wrapper.add(buttons, BorderLayout.SOUTH);
        return wrapper;
    }

    private JPanel buildTablePanel() {
        JPanel p = new JPanel(new BorderLayout());
        p.setBackground(LIGHT);
        p.setBorder(BorderFactory.createEmptyBorder(0, 24, 20, 24));
        JLabel lbl = new JLabel("  Student Records");
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lbl.setForeground(WHITE); lbl.setOpaque(true);
        lbl.setBackground(STEEL);
        lbl.setBorder(BorderFactory.createEmptyBorder(6, 8, 6, 8));
        String[] cols = {"ID", "Name", "Course", "Marks"};
        tableModel = new DefaultTableModel(cols, 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };
        table = new JTable(tableModel);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        table.setRowHeight(26);
        JTableHeader header = table.getTableHeader();
        header.setBackground(NAVY); header.setForeground(WHITE);
        header.setFont(new Font("Segoe UI", Font.BOLD, 12));
        JScrollPane scroll = new JScrollPane(table);
        scroll.setPreferredSize(new Dimension(0, 180));
        scroll.setBorder(BorderFactory.createLineBorder(STEEL, 1));
        p.add(lbl, BorderLayout.NORTH);
        p.add(scroll, BorderLayout.CENTER);
        return p;
    }

    private void addStudent() {
        String id = txtId.getText().trim(), name = txtName.getText().trim();
        String course = txtCourse.getText().trim(), marks = txtMarks.getText().trim();
        if (id.isEmpty() || name.isEmpty() || course.isEmpty() || marks.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields!", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        for (String[] s : students) {
            if (s[0].equals(id)) {
                JOptionPane.showMessageDialog(this, "Student ID already exists!", "Duplicate ID", JOptionPane.WARNING_MESSAGE);
                return;
            }
        }
        String[] row = {id, name, course, marks};
        students.add(row);
        tableModel.addRow(row);
        clearFields();
        JOptionPane.showMessageDialog(this, "Student added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private void clearFields() {
        txtId.setText(""); txtName.setText("");
        txtCourse.setText(""); txtMarks.setText("");
        txtId.requestFocus();
    }

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
        p.add(lbl, BorderLayout.NORTH);
        p.add(field, BorderLayout.CENTER);
        return p;
    }

    private JButton styledButton(String text, Color bg) {
        JButton b = new JButton(text);
        b.setFont(new Font("Segoe UI", Font.BOLD, 12));
        b.setBackground(bg); b.setForeground(WHITE);
        b.setFocusPainted(false); b.setBorderPainted(false);
        b.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b.setPreferredSize(new Dimension(168, 38));
        return b;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(StudentManagementGUI::new);
    }
  }
