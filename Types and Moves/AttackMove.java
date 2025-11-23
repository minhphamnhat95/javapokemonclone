//Implement AttackMove class here
public class AttackMove extends Move{
    
    private Type damageType;
    private int baseDamageAmount;
   
    public AttackMove(String name, int energyCost, Type damageType, int baseDamageAmount){
        super(name, energyCost);  //inherits from Move class
        this.damageType = damageType;
        this.baseDamageAmount = baseDamageAmount;
    }

    public Type getType(){
        return damageType;
    }

    public int getDamage(){
        return baseDamageAmount;
    }

    public String toString(){
        return super.toString() + " - does " + baseDamageAmount +" "+ damageType + " damage";
    }
}
