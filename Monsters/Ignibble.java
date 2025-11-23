public class Ignibble extends Monster{
    
    public Ignibble(){
        super("Ignibble", Type.FIRE, 30, 60, new AttackMove("Coal Crunch", 15, Type.FIRE, 15)) ;  //inherits Monster class with the specific monster details
    }

    @Override
    public void evolve(){
        if(getStage() == 1){
            evolve("Lavox", 45,115, new RestoreMove("Magma Vein", 20, 25, 0)); //evolve to a new monster by overidding abstract method evolve() in Monster class
        }else if(getStage() == 2){
            evolve("Magmagnar", 100, 200, new AttackMove("Eruption Core", 30, Type.FIRE, 50));
        }
    }
}
