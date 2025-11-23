//Implement StrategicBot class here
import java.util.List;

public class StrategicBot extends Player{   
    
    public StrategicBot (Team team){
        super(team);
    }

    @Override
    public Monster chooseMonster(Team oppTeam){ //override abstract method in Player class
        
        Monster bestMonster = getTeam().getActive().get(0); //avoid return null
        int maxOverall = 0;

        for(int i = 0; i < getTeam().getActive().size(); i++){
            
            Monster monster = getTeam().getActive().get(i);
            int damage = 0;
            Move bestAttack = Utils.getBestAttack(monster); //get the best attack expected from the team
            Monster targetMonster = oppTeam.getActive().get(0);
            
            for(int j = 0; j < oppTeam.getActive().size(); j++){
                Monster oppMonster = oppTeam.getActive().get(j);
                
                if(oppMonster.getHealth() > targetMonster.getHealth()){  //always target the opponent with highest health
                    targetMonster = oppMonster;
                }
            }
            
            damage = Utils.calculateDamageInflicted(targetMonster, bestAttack); //calc the damage when using bestAttack on target Monster
            int overall = (monster.getHealth() + monster.getEnergy() + damage)/3; //Overall is calc as average of (energy + health + damage expected to deal)

            if(overall > maxOverall){
                maxOverall = overall;
                bestMonster = monster;
            }
        }
        return bestMonster; //highest overall
        }


    public Move chooseMove(Monster monster, Team oppTeam){
        List<Move> availableMoves = monster.getAvailableMoves(); //store availablemoves of monster in a List to avoid repetitive lines

        if(availableMoves.size() == 1){
            return availableMoves.get(0); //if can't make any move then REST
        }

        Monster targetMonster = oppTeam.getActive().get(0);
            
        for(int i = 0; i < oppTeam.getActive().size(); i++){
            Monster oppMonster = oppTeam.getActive().get(i);
            if(oppMonster.getHealth() > targetMonster.getHealth()){ //target monster with highest health
                targetMonster = oppMonster;
            }
        }
            
        Move koAttack = Utils.getBestAttack(monster);
        if(!Utils.wouldSurvive(targetMonster, koAttack)){ //if the monster can knockout the target monster with 1 move then do it
            return koAttack;
        }
        
            
        Move bestHeal = Utils.getBestHeal(monster);

        if(monster.getHealth() < monster.getMaxHealth()/2){
            if(Utils.calculateHealthRestored(monster, bestHeal) > 10 && Utils.calculateHealthRestored(monster, bestHeal) <= (monster.getMaxHealth() - monster.getHealth())){  //the health restore should be more than 10 and less or equal to missing health to avoid wasting turn
                return bestHeal;
            }
        }
            
        Move bestMove = monster.getAvailableMoves().get(0); //avoid null
        int mostDamage = 0;

        for(int i = 0; i<monster.getAvailableMoves().size(); i++){
            Move move = monster.getAvailableMoves().get(i);
            int damage = Utils.calculateDamageInflicted(targetMonster, move); //in any other case, go for the move that deals most damage
            if(damage > mostDamage){
                mostDamage = damage;
                bestMove = move;
            }
        }
        return bestMove;
        }    
    }
