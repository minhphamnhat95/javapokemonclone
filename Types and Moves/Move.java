//Implement Move class here
public class Move{
  
    private String name;
    private int energyCost;
   
    public Move(String name, int energyCost){
        this.name = name;
        this.energyCost = energyCost;       
    }

    public String getName(){
        return name;
    }

    public int getEnergyCost(){
        return energyCost;
    }

    public String toString(){
        return name + " (" + energyCost + " energy)";
    }
}
