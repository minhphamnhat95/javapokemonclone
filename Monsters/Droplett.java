public class Droplett extends Monster{
    
    public Droplett(){
        super("Droplett", Type.WATER, 50, 40, new AttackMove("Drip Jab", 5, Type.WATER, 5)) ;    //inherits Monster class with the specific monster details
    }

    @Override
    public void evolve(){
        if(getStage() == 1){
            evolve("Rainelle", 100, 60, new RestoreMove("Healing Mist", 25, 30, 0)); //evolve to a new monster by overidding abstract method evolve() in Monster class
        }else if(getStage() == 2){
            evolve("Vaporeign", 175, 125, new AttackMove("Storm Surge", 35, Type.WATER, 35));
        }
    }
}
