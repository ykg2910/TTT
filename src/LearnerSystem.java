
import java.util.ArrayList;
import java.util.Random;


public class LearnerSystem {
    
    GameManager eg;
    double[] hypothesis;
    int mode;
    double updateConstant = 0.1;

    
    public LearnerSystem( GameManager eg , double[] hypothesis , int mode){
        this.eg = eg;
        this.hypothesis = hypothesis;
        this.mode = mode;
    }
    
    public double evaluateBoard( int[][] board){
        int[] features = this.eg.getFeatures(board);
        double value = this.hypothesis[0];
        for( int i = 0 ; i < 6; i++){
            value += this.hypothesis[i+1]*features[i];                 
        }
        return value;
    }

    public void setHypothesis(double[] hypothesis) {
        this.hypothesis = hypothesis;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }
      
    public void chooseRandom(){
        ArrayList<int[][]> Successors;
        if( this.mode == 1)
            Successors = this.eg.getSuccessorsX();
        else
            Successors = this.eg.getSuccessorsO(); 
        Random randomGenerator = new Random();
        int random = randomGenerator.nextInt(Successors.size());
        this.eg.setBoard(Successors.get(random)); 
    }
    
    public void chooseMove(){
        ArrayList<int[][]> Successors;
        if( this.mode == 1)
            Successors = this.eg.getSuccessorsX();
        else
            Successors = this.eg.getSuccessorsO(); 
             
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
        this.eg.setBoard(bestSuccessor); 
    }
    
    public void updateWeights( ArrayList<int[][]> history, ArrayList<trainingExample> tes){
           for( int i = 0; i < history.size(); i++){
              double vEst = this.evaluateBoard(history.get(i)); 
              int[] features = tes.get(i).X;
              double vTrain = tes.get(i).y;
              this.hypothesis[0] += this.updateConstant*(vTrain - vEst);
              this.hypothesis[1] += this.updateConstant*(vTrain - vEst)*features[0];
              this.hypothesis[2] += this.updateConstant*(vTrain - vEst)*features[1];
              this.hypothesis[3] += this.updateConstant*(vTrain - vEst)*features[2];
              this.hypothesis[4] += this.updateConstant*(vTrain - vEst)*features[3];
              this.hypothesis[5] += this.updateConstant*(vTrain - vEst)*features[4];
              this.hypothesis[6] += this.updateConstant*(vTrain - vEst)*features[5];
           }
    }
}
