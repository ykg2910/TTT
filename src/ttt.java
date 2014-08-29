
import java.util.Scanner;


public class ttt {

    /*
    public static void getHypothesis1( LearnerSystem p1, LearnerSystem p2 , Marker c1) {
        int xwins = 0, owins = 0, draw = 0;

        for (int i = 0; i < 100000; i++) {
            GameManager teg = new GameManager();
            p1.eg = teg;
            p2.eg = teg;
            while (!teg.isDone()) {
                p1.chooseMove();
                if (teg.isDone()) {
                    break;
                }
                p2.chooseRandom();
            }

            int winner = teg.getWinner();
            if (winner == 1) {
                xwins += 1;
            } else if (winner == 2) {
                owins += 1;
            } else {
                draw += 1;
            }

            c1.setHypothesis(p1.hypothesis);
            p1.updateWeights(teg.history, c1.getTrainingExamples(teg.history));

        }

        System.out.println("player1 wins " + xwins);
        System.out.println("player2 wins " + owins);
        System.out.println("draws " + draw);

        for (int i = 0; i < 7; i++) {
            System.out.print(p1.hypothesis[i] + " ");
        }
        System.out.println();
    }
    
    
    public static void getHypothesis2( LearnerSystem p1, LearnerSystem p2 , Marker c2) {
        
        int xwins = 0, owins = 0, draw = 0;

        for (int i = 0; i < 100000; i++) {
            GameManager teg = new GameManager();
            p1.eg = teg;
            p2.eg = teg;
            while (!teg.isDone()) {
                p1.chooseRandom();
                if (teg.isDone()) {
                    break;
                }
                p2.chooseMove();
            }

            int winner = teg.getWinner();
            if (winner == 1) {
                xwins += 1;
            } else if (winner == 2) {
                owins += 1;
            } else {
                draw += 1;
            }

            c2.setHypothesis(p2.hypothesis);
            p2.updateWeights(teg.history, c2.getTrainingExamples(teg.history));

        }

        System.out.println("player1 wins " + xwins);
        System.out.println("player2 wins " + owins);
        System.out.println("draws " + draw);

        for (int i = 0; i < 7; i++) {
            System.out.print(p2.hypothesis[i] + " ");
        }
        System.out.println();
    }
     * 
     */

    
    /*
     * public static double[][] hypos(){
        GameManager eg = new GameManager();
        double[] h1 = {0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5};
        double[] h2 = {0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5};
        LearnerSystem p1 = new LearnerSystem(eg, h1, 1);
        LearnerSystem p2 = new LearnerSystem(eg, h2, 2);
        Marker c1 = new Marker(h1, 1);
        Marker c2 = new Marker(h2, 2);
        h1 = p1.hypothesis;
        h2 = p2.hypothesis;
        double h[][] = new double[2][7];
        h[0] = h1;
        h[1] = h2;
        return h;
    }
    */
    
    public static double[][] gethypothesis(){
        GameManager eg = new GameManager();
        double[] h1 = {0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5};
        double[] h2 = {0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5};
        LearnerSystem p1 = new LearnerSystem(eg, h1, 1);
        LearnerSystem p2 = new LearnerSystem(eg, h2, 2);
        Marker c1 = new Marker(h1, 1);
        Marker c2 = new Marker(h2, 2);
         int xwins = 0, owins = 0, draw = 0;

        for (int i = 0; i < 10000; i++) {
            GameManager teg = new GameManager();
            p1.eg = teg;
            p2.eg = teg;
            
          if( i < 10000){
            while (!teg.isDone()) {
                p1.chooseMove();
                if (teg.isDone()) {
                    break;
                }
                p2.chooseRandom();
            }
          }
          else{
              while (!teg.isDone()) {
                p1.chooseMove();
                if (teg.isDone()) {
                    break;
                }
                p2.chooseMove();
            }
          
          }

            int winner = teg.getWinner();
            if (winner == 1) {
                xwins += 1;
            } else if (winner == 2) {
                owins += 1;
            } else {
                draw += 1;
            }

            c1.setHypothesis(p1.hypothesis);
            c2.setHypothesis(p2.hypothesis);
            p1.updateWeights(teg.history, c1.getTrainingExamples(teg.history)); 
            p2.updateWeights(teg.history, c2.getTrainingExamples(teg.history));

        }

        System.out.println("player1 wins " + xwins);
        System.out.println("player2 wins " + owins);
        System.out.println("draws " + draw);

        for (int i = 0; i < 7; i++) {
            System.out.print(p1.hypothesis[i] + " ");
        }
        System.out.println();
        
        for (int i = 0; i < 7; i++) {
            System.out.print(p2.hypothesis[i] + " ");
        }
        
        
        h1 = p1.hypothesis;
        h2 = p2.hypothesis;
        double h[][] = new double[2][7];
        h[0] = h1;
        h[1] = h2;
        return h;
         
         
        /*
       Scanner sc = new Scanner(System.in);
        
        while( true){
          GameManager eg2 = new GameManager();
          p1.eg = eg2;
          p2.eg = eg2;
          while(!eg2.isDone()){
              int x = sc.nextInt();
              int y = sc.nextInt();
              eg2.board[y][x] = 2;
              eg2.printBoard();
              if (eg2.isDone()) break;
              p1.chooseMove();
              eg2.printBoard();
              
          }
          
            int winner = eg2.getWinner();
            if (winner == 1) {
                xwins += 1;
            } else if (winner == 2) {
                owins += 1;
            } else {
                draw += 1;
            }
         * 
         */
/*
            c1.setHypothesis(p1.hypothesis);
            c2.setHypothesis(p2.hypothesis);
            p1.updateWeights(eg2.history, c1.getTrainingExamples(eg2.history)); 
            p2.updateWeights(eg2.history, c2.getTrainingExamples(eg2.history));
 * 
 */
          
         
        }
        
        
    /*
    
    public static void main(String[] args) {

        GameManager eg = new GameManager();
        double[] h1 = {0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5};
        double[] h2 = {0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5};
        LearnerSystem p1 = new LearnerSystem(eg, h1, 1);
        LearnerSystem p2 = new LearnerSystem(eg, h2, 2);
        Marker c1 = new Marker(h1, 1);
        Marker c2 = new Marker(h2, 2);
        gethypothesis();
       
    }
    
 * 
 */
}
