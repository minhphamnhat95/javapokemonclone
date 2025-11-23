//Implement RestoreMove class here
public class RestoreMove extends Move{

    private int healthRestored;
    private int energyRestored;
    
    public RestoreMove(String name, int energyCost, int healthRestored, int energyRestored){
        super(name, energyCost); //inherits from Move class
        this.healthRestored = healthRestored;
        this.energyRestored = energyRestored;
    }

    public int getHealthRestored(){
        return healthRestored;
    }

    public int getEnergyRestored(){
        return energyRestored;
    }

    public String toString(){
        
        if(energyRestored == 0){
            return super.toString() + " - restores " + healthRestored + " health";
        
        }else if (healthRestored ==0){
            return super.toString() + " - restores " + energyRestored + " energy";
        
        }else{
            return super.toString() + " - restores " + healthRestored + " health and " + energyRestored + " energy"; 
        }
    }
}
