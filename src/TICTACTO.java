
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class TICTACTO extends JApplet {
    
    //static double[] h1 = {  18.376425450404096, 14.14011232159159, -25.976691186465942, 8.44642107750988, -1.0305633968516053, 10.632096918203267, -136.61811974965798 };
    //static double[] h2 = { -50.06608845472791, -24.125942415554128, 28.26720419714514, -15.298489056282266, 10.330708372770307, -32.189393363122456, 98.59057461386918 };

    static double[] h1 = {0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5};
    static double[] h2 = {0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5};

    int[][] board = new int[3][3];

    char turn = 'X';
    JLabel jlblStatus = new JLabel("X's turn to play");
    
    cell cells[][] = new cell[3][3];
 

    public TICTACTO() {
        JButton b = new JButton();
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                restart();
            }
        });
        
        
        JPanel p = new JPanel(new GridLayout(3, 3));
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                p.add(cells[i][j] = new cell());

        p.setBorder(new LineBorder(Color.red, 1));
        jlblStatus.setBorder(new LineBorder(Color.yellow, 1));
        
        add(b , BorderLayout.NORTH);
        add(p, BorderLayout.CENTER);
        add(jlblStatus, BorderLayout.SOUTH);
    }

    public boolean isFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (cells[i][j].getToken() == ' ') {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean isWon(char token) {
        for (int i = 0; i < 3; i++) {
            if ((cells[i][0].getToken() == token)
                    && (cells[i][1].getToken() == token)
                    && (cells[i][2].getToken() == token)) {
                return true;
            }
        }

        for (int j = 0; j < 3; j++) {
            if ((cells[0][j].getToken() == token)
                    && (cells[1][j].getToken() == token)
                    && (cells[2][j].getToken() == token)) {
                return true;
            }
        }

        if ((cells[0][0].getToken() == token)
                && (cells[1][1].getToken() == token)
                && (cells[2][2].getToken() == token)) {
            return true;
        }

        if ((cells[0][2].getToken() == token)
                && (cells[1][1].getToken() == token)
                && (cells[2][0].getToken() == token)) {
            return true;
        }

        return false;
    }
    
    public double evaluateBoard( int[][] board){
        int[] features = getFeatures(board);
        double[] h;
        if( turn == 'X') h = h1;
        else h = h2;
        double value = h[0];
        for( int i = 0 ; i < 6; i++){
            value += h[i+1]*features[i];                 
        }
        return value;
    }
    
    public ArrayList<int[][]> getSuccessorsX(int[][] board){
        ArrayList<int[][]> Successors = new ArrayList();
        for( int i = 0 ; i < 3; i++)
            for( int j = 0 ; j < 3; j++){
                if( board[i][j] == 0){
                    board[i][j] = 1;              
                    Successors.add(this.cloneBoard(board)); 
                    board[i][j] = 0;
                }
            }
        return  Successors;
    } 
    
    public ArrayList<int[][]> getSuccessorsO(int[][] board){
        ArrayList<int[][]> Successors = new ArrayList();
        for( int i = 0 ; i < 3; i++)
            for( int j = 0 ; j < 3; j++){
               if( board[i][j] == 0){
                    board[i][j] = 2;              
                    Successors.add(this.cloneBoard(board)); 
                    board[i][j] = 0;
               }
            }
        return  Successors;
    }
    
    public int[][] cloneBoard( int[][] board){
        int[][] clone = new int[3][3];
        for( int i = 0; i < 3; i++)
            for( int j = 0; j < 3; j++)
                clone[i][j] = board[i][j];
        return clone;
    }
    
    public int[] getFeatures( int[][] board){
        ArrayList<int[]> possibilities = new ArrayList<int[]>();
        int[][] Rows = this.getRows(board);
        for( int i = 0; i < 3; i++) possibilities.add(Rows[i]); 
        int[][] Columns = this.getColumns(board);
        for( int i = 0; i < 3; i++) possibilities.add(Columns[i]);
        int[][] Diagonals = this.getDiagonals(board);
        for( int i = 0; i < 2; i++) possibilities.add(Diagonals[i]); 
        
        int[] features = new int[6];
        for( int i = 0 ; i < possibilities.size(); i++){
             int[] possibility = possibilities.get(i);
             int b = 0 , x = 0 , o = 0;
             for( int j = 0; j < 3; j++){
                 if( possibility[j] == 0) b += 1;
                 else if( possibility[j] == 1) x += 1;
                 else o += 1;
             }
             if( x == 2 && b == 1) features[0] += 1;  
             else if( o == 2 && b == 1) features[1] += 1;  
             else if( x == 1 && b == 2) features[2] += 1;  
             else if( o == 1 && b == 2) features[3] += 1;
             else if( x == 3) features[4] += 1;
             else if( o == 3) features[5] += 1;
        }
        
        return features;
    }
    
    public int[][] getRows( int[][] board){
        return board;
    }
    
    public int[][] getColumns( int[][] board){
        int[][] columns = new int[3][3];
        for( int i = 0; i < 3; i++)
            for( int j = 0; j < 3; j++)
                columns[j][i] = board[i][j];
        return columns;
    }
    
    public int[][] getDiagonals( int[][] board){
        int[][] diagonals = new int[2][3];
        diagonals[0][0] = board[0][0]; diagonals[0][1] = board[1][1]; diagonals[0][2] = board[2][2];
        diagonals[1][0] = board[0][2]; diagonals[1][1] = board[1][1]; diagonals[1][2] = board[2][0];
        return diagonals;
    }
    
    public int[][] getBoard(){
        int[][] board = new int[3][3];
        for( int i = 0; i < 3; i++)
            for( int j = 0; j < 3; j++){
                char c = cells[i][j].getToken();
                if( c == 'X') board[i][j] = 1;
                else if( c == 'O') board[i][j] = 2;
            }
        return board;
    }
    
    public int[][] getBoardForHistory(){
        int[][] board = new int[3][3];
        for( int i = 0; i < 3; i++)
            for( int j = 0; j < 3; j++){
                char c = cells[i][j].getToken();
                if( c == turn) board[i][j] = 1;
                else if( c == ' ') board[i][j] = 0;
                else board[i][j] = 2;
            }
        return board;
    }
    
    public void chooseMove( char turn){
        ArrayList<int[][]> Successors;
        int[][] board = getBoard();
        if( turn == 'X')
            Successors = getSuccessorsX(board);
        else
            Successors = getSuccessorsO(board); 
             
        int[][] bestSuccessor = Successors.get(0);
        double bestScore = this.evaluateBoard( bestSuccessor);
        
        for( int i  = 1; i < Successors.size(); i++){
            int[][] currentSuccesor = Successors.get(i);
            double currentScore = this.evaluateBoard(currentSuccesor);
            if( currentScore > bestScore){
              bestScore = currentScore;
              bestSuccessor = currentSuccesor;
            }
        }
        setBoard(bestSuccessor , board); 
        
    }
    
    public void setBoard(int[][] next , int[][] current){
         
        for( int i = 0; i < 3; i++)
            for( int j = 0; j < 3; j++)
                if( current[i][j] != next[i][j]){
                    cells[i][j].token = turn;
                    repaint();
                    //tt.eg.setBoard(getBoardForHistory());
                    if(isWon(turn)){
                      turn = ' ';
                      jlblStatus.setText( turn + " won! The game is over");
                      learn();
                    }
                    else if(isFull()){
                        turn = ' ';
                        jlblStatus.setText("The game is draw");
                        learn();
                    }
                    else{
                        turn = ( turn == 'X') ? 'O': 'X'; 
                        jlblStatus.setText( turn + "'s turn");  
                    }
                }
    }

    class cell extends JPanel {

        char token = ' ';

        public cell() {
            setBorder(new LineBorder(Color.BLACK, 1));
            addMouseListener(new MouseListener());
        }

        public char getToken() {
            return token;
        }

        public void setToken(char c) {
            token = c;
           // tt.eg.setBoard(getBoardForHistory()); 
            repaint();
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            if (token == 'X') {
                g.drawLine(10, 10, getWidth() - 10, getHeight() - 10);
                g.drawLine(getWidth() - 10, 10, 10, getHeight() - 10);
            } else if (token == 'O') {
                g.drawOval(10, 10, getWidth() - 20, getHeight() - 20);
            }
        }

        private class MouseListener extends MouseAdapter {

            public void mouseClicked(MouseEvent e) {
                if (token == ' ' && turn != ' ') {
                    setToken(turn);
                    if (isWon(turn)) {
                        turn = ' '; 
                        jlblStatus.setText( turn + " won! The game is over");
                        learn();
                    } else if (isFull()) {
                        turn = ' '; 
                        jlblStatus.setText( "The game is draw");
                        learn();                     
                    } else {
                            turn = ( turn == 'X') ? 'O': 'X'; 
                            jlblStatus.setText( turn + "'s turn"); 
                            chooseMove(turn); 
                    }
                }
            }
        }
    }
    
    public void learn(){
     /*   tt.c1.hypothesis = h1;
        tt.c2.hypothesis = h2;    
        tt.p1.updateWeights(tt.eg.history, tt.c1.getTrainingExamples(tt.eg.history)); 
        tt.p2.updateWeights(tt.eg.history, tt.c2.getTrainingExamples(tt.eg.history));
        h1 = tt.p1.hypothesis;
        h2 = tt.p2.hypothesis;
        System.out.println();
        System.out.println("updated hypothesis");
        for (int i = 0; i < 7; i++) {
            System.out.print(h1[i] + " ");
        }
        System.out.println();
        
        for (int i = 0; i < 7; i++) {
            System.out.print(h2[i] + " ");
        }
      * 
      */
    }
    
    public void restart(){
        Random r = new Random();
        int e = r.nextInt(2);
        if( e == 0) turn = 'X'; else turn = 'O';
        for( int i = 0; i < 3; i++){
          for( int j = 0; j < 3; j++){
             cells[i][j].token = ' ';
          }
        }
        repaint();
        jlblStatus.setText( turn + "'s turn"); 
        if( turn == 'O')
           chooseMove(turn);
    } 

    public static void main(String[] args) {
        double[][] h = ttt.gethypothesis();
        //h1 = h[0];  h2 = h[1];
        
        double[] h10 = { 8.65348305211155, 13.234958498689545, -20.26054784448286, 5.826348654164921, -1.8814977042889143, 29.304467998187675, -106.20801159783278}; 
        double[] h11 = { -37.83883977910609, -20.526237471641743, 24.411166368413213, -16.098306253653693, 8.892980978865793, -50.71328584283987, 81.35811354445119 };
        
        h1 = h10;  h2 = h11;
        
        
        JFrame frame = new JFrame("tic tac toe");
        TICTACTO applet = new TICTACTO();
        frame.add(applet,BorderLayout.CENTER);
        frame.setSize(300, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
