//Implement your Monster class/interface here
import java.util.List;
import java.util.ArrayList;

public abstract class Monster{
    private String name;
    private Type type;
    private List<Move> allMoves;
    private int stage;
    private Stat health;
    private Stat energy;

    public Monster(String name, Type type, int health, int energy, Move move){
        this.name = name;
        this.type = type;
        this.health = new Stat(health);
        this.energy = new Stat(energy);
        this.allMoves = new ArrayList<>();
        this.allMoves.add(move); //add the inital move passed into the constructor
        this.stage = 1;
    }

    public String getName(){
        return name;
    }


    public Type getType(){
        return type;
    }


    public int getStage(){
        return stage;
    } 


    public int getHealth(){
        return health.getCurrValue();
    }


    public int getMaxHealth(){
        return health.getMaxValue();
    }


    public boolean isFainted(){
        
        if (health.getCurrValue() == 0){
            return true;
        }
            return false;
    }   
    

    public int getEnergy(){
        return energy.getCurrValue();
    }


    public int getMaxEnergy(){
        return energy.getMaxValue(); 
    }


    public List<Move> getAllMoves(){
        return allMoves;
    }


    public List<Move> getAvailableMoves(){
         
         List<Move> availableMoves = new ArrayList<Move>();
         
         for(int i = 0; i< allMoves.size(); i++){
            Move move = allMoves.get(i);
            if(move.getEnergyCost() <= this.energy.getCurrValue()){  //add move to the available list if sufficient energy to cast
                availableMoves.add(move);
            }
         }

         if(availableMoves.isEmpty()){
            availableMoves.add(new RestoreMove("Rest", 0, 0, 20)); // if cant make any move then Rest.
         }
         return availableMoves;
    }

    public boolean attempt(Move moveAttempted){
        
        if(moveAttempted.getEnergyCost() <= energy.getCurrValue()){
            System.out.println(name + " did " + moveAttempted.getName());
            energy.subtract(moveAttempted.getEnergyCost());
            return true;
        
        }else{
            System.out.println(name + " is too tired to do " + moveAttempted.getName());
            return false;
        }
    }


    public void handle(AttackMove attackHandled){
        
        if(type.isWeakAgainst(attackHandled.getType())){ //counter type
            System.out.println("It's super effective!");
            health.subtract(attackHandled.getDamage()*2);
        
        }else if(type.isStrongAgainst(attackHandled.getType())){  //"is countered" type
            health.subtract(attackHandled.getDamage()/2);
            System.out.println("It's not very effective...");
        
        }else if(this.type == attackHandled.getType()){   //same type
            health.subtract(attackHandled.getDamage());   
        }
    }

    public void handle(RestoreMove restoreMoveHandled){
        health.add(restoreMoveHandled.getHealthRestored());
        energy.add(restoreMoveHandled.getEnergyRestored());
    }

    public void evolve(String newName, int newHealth, int newEnergy, Move newMove){
        String oldName = this.name; //store old name to print the evolve status in line 130
        this.name = newName;
        this.health = new Stat(newHealth);
        this.energy = new Stat(newEnergy);
        this.allMoves.add(newMove);
        this.stage = stage + 1;
        System.out.println(oldName + " evolved into " + newName + "!");
    }

    public abstract void evolve();

    public String toString(){
        return name + " (" + health.getCurrValue() + "/" + health.getMaxValue() + " health, " + energy.getCurrValue() + "/" + energy.getMaxValue() + " energy)";
    }
}
