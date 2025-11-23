//Implement Type enum here
public enum Type{
    FIRE, WATER, FOREST;

    public boolean isStrongAgainst(Type other){
        
        if (this == FIRE && other == WATER || this == FOREST && other == FIRE || this == WATER && other == FOREST || this == other){ 
            return false;
        
        } else{
            return true;
        }
    }

    public boolean isWeakAgainst(Type other){
        
        if(isStrongAgainst(other) == true || this == other){ //if type is not stronger then it is weaker. same type this == other neither stronger nor weaker
            return false;
        
        }else{
            return true;
        }
    }

    public String toString(){
        return name().toLowerCase();
    }
}

