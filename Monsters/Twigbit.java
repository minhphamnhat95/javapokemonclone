public class Twigbit extends Monster{
    
    public Twigbit(){
        super("Twigbit", Type.FOREST, 45, 45, new AttackMove("Leaf Flick", 10, Type.FOREST, 10)) ;    //inherits Monster class with the specific monster details
    }

    @Override
    public void evolve(){
        if(getStage() == 1){
            evolve("Branchor", 80, 80, new RestoreMove("Root Mend", 25, 20, 0)); //evolve to a new monster by overidding abstract method evolve() in Monster class
        }else if(getStage() == 2){
            evolve("Sylvanox", 150, 150, new AttackMove("Thorn Storm", 30, Type.FOREST, 40));
        }
    }
}
