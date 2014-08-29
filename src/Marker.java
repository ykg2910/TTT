
import java.util.ArrayList;


public class Marker {
    
    double[] hypothesis;
    int mode;
    GameManager eg;
    
    public Marker( double[] hypothesis , int mode){
        this.hypothesis = hypothesis;
        this.mode = mode;
        eg = new GameManager();
    }
    
    public void setHypothesis(double[] hypothesis) {
        this.hypothesis = hypothesis;
    }
    
    public double evaluateBoard( int[][] board){
        int[] features = this.eg.getFeatures(board);
        double value = this.hypothesis[0];
        for( int i = 0 ; i < 6; i++){
            value += this.hypothesis[i+1]*features[i];                 
        }
        return value;
    }
    
    
    public ArrayList<trainingExample> getTrainingExamples( ArrayList<int[][]> history){
            ArrayList<trainingExample> tes = new ArrayList<trainingExample>();
            for( int i = 0; i < history.size(); i++){
               if( eg.isDone(history.get(i))){
                    if( eg.getWinner(history.get(i)) == this.mode){
                      tes.add(new trainingExample(this.eg.getFeatures(history.get(i)) , 100));
                    }
                    else if( eg.getWinner(history.get(i)) == 0){
                      tes.add(new trainingExample(this.eg.getFeatures(history.get(i)) , 0));
                    }
                    else{
                       tes.add(new trainingExample(this.eg.getFeatures(history.get(i)) , -100));
                    }
                   
               }
               else{
                   if( i + 2 >= history.size()){
                      if( eg.getWinner(history.get(history.size()-1)) == 0){
                            tes.add(new trainingExample(this.eg.getFeatures(history.get(i)) , 0));
                      }
                      else{
                            tes.add(new trainingExample(this.eg.getFeatures(history.get(history.size()-1)) , -100)); 
                      }
                   }
                   else{
                       int[] X = this.eg.getFeatures(history.get(i));
                       double y = this.evaluateBoard(history.get(i+2));
                       tes.add(new trainingExample( X , y)); 
                   }  

               }
               
            }
            return tes;
    }
}

