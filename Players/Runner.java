import java.util.List ;
import java.util.Scanner ;

public class Runner {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in) ;

        int evolutionStage = 1 ; //change to 2 or 3 for more epic battles

        Team[] teams = TeamGenerator.generateTeams("Human", "Bot", evolutionStage) ;

        Player player = new HumanPlayer(teams[0], input) ;
        Player bot = new SafeBot(teams[1]) ;

        while (player.getTeam().hasActive() && bot.getTeam().hasActive()) {

            Monster playerMonster = player.chooseMonster(bot.getTeam()) ;
            Monster botMonster = bot.chooseMonster(player.getTeam()) ;

            System.out.println(playerMonster + " (you) vs. " + botMonster + " (me)") ;

            Move playerMove = player.chooseMove(playerMonster, bot.getTeam()) ;
            Move botMove = bot.chooseMove(botMonster, player.getTeam()) ;

            //Resolve attacks first
            if (playerMove instanceof AttackMove) {
                playerMonster.attempt(playerMove) ;
                botMonster.handle((AttackMove) playerMove) ;
            }
            if (botMove instanceof AttackMove) {
                botMonster.attempt(botMove) ;
                playerMonster.handle((AttackMove) botMove) ;
            }

            //Resolve restore moves if monster is active
            if (!playerMonster.isFainted() && playerMove instanceof RestoreMove) {
                playerMonster.attempt(playerMove) ;
                playerMonster.handle((RestoreMove) playerMove) ;
            }
            if (!botMonster.isFainted() && botMove instanceof RestoreMove) {
                botMonster.attempt(botMove) ;
                botMonster.handle((RestoreMove) botMove) ;
            }

            if (playerMonster.isFainted() && botMonster.isFainted()) {
                System.out.println("Your " + playerMonster.getName() + " and my " + botMonster.getName() + " knocked each other out!") ;
            } else {
                if (playerMonster.isFainted()) {
                    System.out.println("Your " + playerMonster.getName() + " fainted!") ;
                    botMonster.evolve();
                } 
                if (botMonster.isFainted()) {
                    System.out.println("My " + botMonster.getName() + " fainted!") ;
                    playerMonster.evolve();
                }
            }
        }

        if (player.getTeam().hasActive()) {
            System.out.println("You Win!");
        } else if (bot.getTeam().hasActive()) {
            System.out.println("I Win!");
        } else {
            System.out.println("It's a draw");
        }
    }
}
