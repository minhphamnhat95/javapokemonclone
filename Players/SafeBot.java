//Implement SafeBot here
import java.util.List;

public class SafeBot extends Player{

    public SafeBot(Team team){
        super(team);
    }

    @Override
    public Monster chooseMonster(Team oppTeam){ //override abstract method in Player class
    
        Monster monster = getTeam().getActive().get(0);
        
        for(int i = 0; i < getTeam().getActive().size(); i = i + 1){
            Monster currentMonster = getTeam().getActive().get(i); 
            if(currentMonster.getHealth() > monster.getHealth()){ //get the monster with most health
                monster = currentMonster;
            }
        }
        return monster;
    }

    @Override
    public Move chooseMove(Monster monster, Team oppTeam ){ //override abstract method in Player class
        
        List<Move> availableMoves = monster.getAvailableMoves();
        Move bestMove = null;

        if(monster.getHealth() < monster.getMaxHealth()/2){
            
            for(int i = 0; i<availableMoves.size(); i++){
                Move currentMove = availableMoves.get(i);
                
                if(currentMove instanceof RestoreMove){
                    RestoreMove restoreMove = (RestoreMove) currentMove;   //cast type RestoreMove for currentMove
                    
                    if(bestMove == null){
                        bestMove = restoreMove;
                    
                    }else if(bestMove instanceof RestoreMove){
                        
                        if(restoreMove.getHealthRestored() > ((RestoreMove) bestMove).getHealthRestored()){ //cast type RestoreMove for bestMove
                            bestMove = restoreMove;
                        }
                    }
                }
            }

        }else{
            for(int i = 0; i < availableMoves.size(); i++){
                Move currentMove = availableMoves.get(i);
                
                if(currentMove instanceof AttackMove){
                    AttackMove attackMove = (AttackMove) currentMove; //cast type AttackMove for currentMove
                    
                    if(bestMove == null){
                        bestMove = attackMove;
                    
                    }else if(bestMove instanceof AttackMove){
                        
                        if(attackMove.getDamage() > ((AttackMove)bestMove).getDamage()){ //cast type AttackMove for bestMove
                            bestMove = attackMove;
                        }
                    }
                }    
            }
        }

        if (availableMoves.size() == 1 || bestMove == null){ // if cant make anymove or no move is set as bestMove then bestmove = Rest (only choice in availableMoves)
            bestMove = availableMoves.get(0);      
        }    
        return bestMove;
    }
}
