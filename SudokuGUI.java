
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SudokuGUI extends JFrame {
    
    private JTextField[][] cells = new JTextField[11][11];
    private JButton solveButton;
    private JButton clearButton;
    SudokuGrid grid = new SudokuGrid();
    public SudokuGUI() {
        setTitle("Sudoku");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        JPanel gridPanel = new JPanel(new GridLayout(9, 9));
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                JTextField field = new JTextField();
                field.setHorizontalAlignment(JTextField.CENTER);
                field.setFont(new Font("Arial", Font.BOLD, 20));
                
                int top = (i % 3 == 0) ? 3 : 1;
                int left = (j % 3 == 0) ? 3 : 1;
                int bottom = (i == 8) ? 3 : 1;
                int right = (j == 8) ? 3 : 1;
                field.setBorder(BorderFactory.createMatteBorder(top, left, bottom, right, Color.BLACK));
                cells[i][j] = field;
                gridPanel.add(field);
            }
        }
        
        JPanel buttonPanel = new JPanel();
        solveButton = new JButton("Solve");
        clearButton = new JButton("Clear");
        buttonPanel.add(solveButton);
        buttonPanel.add(clearButton);
        add(gridPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        
        clearButton.addActionListener(e -> clearBoard());
        solveButton.addActionListener(e -> solveBoard());
        
    }
    
    private void solveBoard() {
        SudokuGrid grid = new SudokuGrid();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                String number = cells[i][j].getText().trim();
                if (!number.isEmpty()) {
                    grid.fillCell(i + 1, j + 1, Integer.parseInt(number));
                }
            }
        }
        SudokuSolver solve = new SudokuSolver(grid);
        if (solve.solve()) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (cells[i][j].getText().trim().isEmpty()) {
                        int value = grid.getDigit(i + 1, j + 1);
                        cells[i][j].setText(String.valueOf(value));
                        cells[i][j].setForeground(Color.BLUE);
                    }
                }
                
            }
        }else JOptionPane.showMessageDialog(this,"No solution");
    }
    public boolean isCorrect(int r, int c, int d){
        if(grid.givesCon(r, c, d)){
            return true;
        }
        return false;
    }
    public void clearBoard() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                cells[i][j].setText("");
                cells[i][j].setForeground(Color.BLACK);
                
            }
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new SudokuGUI().setVisible(true);
        });
    }
    
}
