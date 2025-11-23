import java.util.List ;

public class Runner {

    public static void main(String[] args) {

        //Feel free to change up the teams

        Team teamSafe = new Team("Safe") ;
        teamSafe.add(new Ignibble()) ;
        teamSafe.add(new Twigbit()) ;
        Player safeBot = new SafeBot(teamSafe) ;

        Team teamStrat = new Team("Strat") ;
        teamStrat.add(new Droplett()) ;
        teamStrat.add(new Twigbit()) ;
        Player stratBot = new StrategicBot(teamStrat) ;

        int currRound = 0 ;
        while (teamSafe.hasActive() && teamStrat.hasActive()) {
            currRound++ ;

            System.out.println("\nRound " + currRound) ;

            Monster safeMonster = safeBot.chooseMonster(stratBot.getTeam()) ;
            Monster stratMonster = stratBot.chooseMonster(safeBot.getTeam()) ;

            System.out.println("Safe Player chose " + safeMonster) ;
            System.out.println("Strat Player chose " + stratMonster) ;

            Move safeMove = safeBot.chooseMove(safeMonster, stratBot.getTeam()) ;
            Move stratMove = stratBot.chooseMove(stratMonster, safeBot.getTeam()) ;
 
            //Resolve attacks first
            if (safeMove instanceof AttackMove) {
                if (safeMonster.attempt(safeMove)) {
                    stratMonster.handle((AttackMove) safeMove) ;
                }
            }
            if (stratMove instanceof AttackMove) {
                if (stratMonster.attempt(stratMove)) {
                    safeMonster.handle((AttackMove) stratMove) ;
                }
            }

            //Resolve restore moves if monster is active
            if (!safeMonster.isFainted() && safeMove instanceof RestoreMove) {
                if (safeMonster.attempt(safeMove)) {
                    safeMonster.handle((RestoreMove) safeMove) ;
                }
            }
            if (!stratMonster.isFainted() && stratMove instanceof RestoreMove) {
                if (stratMonster.attempt(stratMove)) {
                    stratMonster.handle((RestoreMove) stratMove) ;
                }
            }

            //Evolve monsters if they knock out their opponent
            if (safeMonster.isFainted() && !stratMonster.isFainted()) {
                stratMonster.evolve() ;
            }
            if (stratMonster.isFainted() && !safeMonster.isFainted()) {
                safeMonster.evolve() ;
            }
        }

        if (teamSafe.hasActive() && !teamStrat.hasActive()) {
            System.out.println("Team Safe Wins!");
        } else if(teamStrat.hasActive() && !teamSafe.hasActive()){
            System.out.println("Team Strat Wins!");
        } else {
            System.out.println("It's a draw!");
        }
    }
}
