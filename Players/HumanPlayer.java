//Implement HumanPlayer class here
import java.util.List ;
import java.util.Scanner ;

public class HumanPlayer extends Player{
    private Scanner input;
    
    public HumanPlayer(Team team, Scanner input){
        super(team);
        this.input = input;
    }

    @Override
    public Monster chooseMonster(Team oppTeam){  //override abstract method in Player class
        
        Monster chosenMonster;
        System.out.println("Your opponent:"); 
        
        for(int i = 0; i < oppTeam.getActive().size(); i++){
            System.out.println("* " + oppTeam.getActive().get(i)); //print a list of opponents
        }
        
        System.out.println("\n" + "Please choose a monster:");
        
        for(int i = 0; i < getTeam().getActive().size(); i++){
            System.out.println((i+1) + ": " + getTeam().getActive().get(i)); //print a numbered list of your monsters to choose
            }
        
        while(true){
            int choiceMonster = input.nextInt();
            
            if(choiceMonster < 1 || choiceMonster > getTeam().getActive().size()){
                System.out.println("Invalid choice, try again");
            
            }else{
                chosenMonster = getTeam().getActive().get(choiceMonster - 1);  //choiceMonster - 1 because the list number = the position in the array-1 (choice 1 is in Array.get(0))
                break;
            }
        }
        return chosenMonster;
    }

    @Override
    public Move chooseMove(Monster monster, Team oppTeam){ //override abstract method in Player class
        
        Move chosenMove;
        System.out.println("Please choose a move:");
    
        for(int i = 0; i < monster.getAvailableMoves().size(); i++){
            System.out.println((i+1) + ": " + monster.getAvailableMoves().get(i)); //print a numbered list of your move to choose
            }

        while(true){ 
            int choiceMove = input.nextInt();
            
            if(choiceMove < 1 || choiceMove > monster.getAvailableMoves().size()){
                System.out.println("Invalid choice, try again");
            
            }else{
                chosenMove = monster.getAvailableMoves().get(choiceMove - 1); //choiceMove - 1 because the list number = (the position in the array - 1) E.x:(choice 1 is in Array.get(0))
                break;
            }
        }
        return chosenMove;
    }    
}


