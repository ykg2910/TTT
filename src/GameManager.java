
import java.util.ArrayList;


public class GameManager {
    
    int[][] board;
    ArrayList<int[][]> history;
    
    public GameManager(){
        this.board = this.generateBoard(); 
        this.history = new ArrayList<int[][]>();
        this.history.add(this.cloneBoard(this.board)); 
    }
    
    public int[][] generateBoard(){
        int[][] board = new int[3][3];
        return board;
    }
    
    public int[][] cloneBoard( int[][] board){
        int[][] clone = new int[3][3];
        for( int i = 0; i < 3; i++)
            for( int j = 0; j < 3; j++)
                clone[i][j] = board[i][j];
        return clone;
    }
    
    public void setBoard( int[][] board){
         this.board = board;
         this.history.add(this.cloneBoard(this.board));
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
    
    public boolean isDone(){
        boolean done = true;
        for( int i = 0; i < 3; i++)
            for( int j = 0; j < 3; j++)
                if( board[i][j] == 0)
                    done = false;
        
        ArrayList<int[]> possibilities = new ArrayList<int[]>();
        int[][] Rows = this.getRows(board);
        for( int i = 0; i < 3; i++) possibilities.add(Rows[i]); 
        int[][] Columns = this.getColumns(board);
        for( int i = 0; i < 3; i++) possibilities.add(Columns[i]);
        int[][] Diagonals = this.getDiagonals(board);
        for( int i = 0; i < 2; i++) possibilities.add(Diagonals[i]); 
        
        for( int i = 0 ; i < possibilities.size(); i++){
            int[] possibility = possibilities.get(i);
            int b = 0 , x = 0 , o = 0;
            for( int j = 0; j < 3; j++){
                if( possibility[j] == 0) b += 1;
                else if( possibility[j] == 1) x += 1;
                else o += 1;
            }
            if( x == 3 || o == 3) return true;
        }
        
        return done;
    }
    
    public boolean isDone( int[][] board){
        boolean done = true;
        for( int i = 0; i < 3; i++)
            for( int j = 0; j < 3; j++)
                if( board[i][j] == 0)
                    done = false;
        
        ArrayList<int[]> possibilities = new ArrayList<int[]>();
        int[][] Rows = this.getRows(board);
        for( int i = 0; i < 3; i++) possibilities.add(Rows[i]); 
        int[][] Columns = this.getColumns(board);
        for( int i = 0; i < 3; i++) possibilities.add(Columns[i]);
        int[][] Diagonals = this.getDiagonals(board);
        for( int i = 0; i < 2; i++) possibilities.add(Diagonals[i]); 
        
        for( int i = 0 ; i < possibilities.size(); i++){
            int[] possibility = possibilities.get(i);
            int b = 0 , x = 0 , o = 0;
            for( int j = 0; j < 3; j++){
                if( possibility[j] == 0) b += 1;
                else if( possibility[j] == 1) x += 1;
                else o += 1;
            }
            if( x == 3 || o == 3) return true;
        }
        
        return done;
    }
    
    public int getWinner(){        
        if( this.isDone()){         
            ArrayList<int[]> possibilities = new ArrayList<int[]>();
            int[][] Rows = this.getRows(board);
            for( int i = 0; i < 3; i++) possibilities.add(Rows[i]); 
            int[][] Columns = this.getColumns(board);
            for( int i = 0; i < 3; i++) possibilities.add(Columns[i]);
            int[][] Diagonals = this.getDiagonals(board);
            for( int i = 0; i < 2; i++) possibilities.add(Diagonals[i]); 
        
            for( int i = 0 ; i < possibilities.size(); i++){
                int[] possibility = possibilities.get(i);
                int b = 0 , x = 0 , o = 0;
                for( int j = 0; j < 3; j++){
                    if( possibility[j] == 0) b += 1;
                    else if( possibility[j] == 1) x += 1;
                    else o += 1;
                }
                if( x == 3 ) return 1;
                if( o == 3 ) return 2;
             }
            
            return 0;
        }
        else{
            System.out.println(" game not done can not determine winner");
            return -1;
        }
    }
    
    public int getWinner( int[][] board){        
        if( this.isDone(board)){         
            ArrayList<int[]> possibilities = new ArrayList<int[]>();
            int[][] Rows = this.getRows(board);
            for( int i = 0; i < 3; i++) possibilities.add(Rows[i]); 
            int[][] Columns = this.getColumns(board);
            for( int i = 0; i < 3; i++) possibilities.add(Columns[i]);
            int[][] Diagonals = this.getDiagonals(board);
            for( int i = 0; i < 2; i++) possibilities.add(Diagonals[i]); 
        
            for( int i = 0 ; i < possibilities.size(); i++){
                int[] possibility = possibilities.get(i);
                int b = 0 , x = 0 , o = 0;
                for( int j = 0; j < 3; j++){
                    if( possibility[j] == 0) b += 1;
                    else if( possibility[j] == 1) x += 1;
                    else o += 1;
                }
                if( x == 3 ) return 1;
                if( o == 3 ) return 2;
             }
            
            return 0;
        }
        else{
            System.out.println(" game not done can not determine winner");
            return -1;
        }
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
    
    public ArrayList<int[][]> getSuccessorsX(){
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
    
    public ArrayList<int[][]> getSuccessorsO(){
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
    
    public ArrayList<int[][]> getHistory(){
        return this.history;
    }
    
    public void printBoard(){
        System.out.println();
        for( int i = 0; i < 3; i++){
          for( int j = 0; j < 3; j++){
              if( board[i][j] == 0)
                  System.out.print("  | ");
              else if( board[i][j] == 1)
                  System.out.print("X | ");
              else
                  System.out.print("O | ");
          }
          System.out.println();
        }
    }
      
    public void printBoard(int[][] board){
        System.out.println();
        for( int i = 0; i < 3; i++){
          for( int j = 0; j < 3; j++){
              if( board[i][j] == 0)
                  System.out.print("  | ");
              else if( board[i][j] == 1)
                  System.out.print("X | ");
              else
                  System.out.print("O | ");
          }
          System.out.println();
        }
    }
}


