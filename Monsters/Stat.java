//Implement your Stat class here
public class Stat{

    private int currValue;
    private final int maxValue; //final because it cant be changed

    public Stat(int currValue){
        this.currValue = currValue;
        this.maxValue = currValue;
        
    }
    public int getCurrValue(){
        return currValue;
    }

    public int getMaxValue(){
        return maxValue;
    }

    public void add(int addAmount){
        currValue = currValue  + addAmount;
        if(currValue > maxValue){
            currValue = maxValue; // value never exceed set max value 
        }
    }

    public void subtract(int subtractAmount){
        currValue = currValue - subtractAmount;
        if(currValue < 0){
            currValue = 0;  //value never < 0
        }
    }

    public String toString(){
        return currValue + "/" + maxValue;
    }
}
