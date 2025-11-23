import java.util.List ;
import java.util.Random ;

public class Runner {

    public static Monster getRandomMonster(Team team, Random r) {

        List<Monster> monsters = team.getActive();
        int index = r.nextInt(monsters.size()) ;
        return monsters.get(index);
    }

    public static Move getRandomMove(Monster monster, Random r) {

        List<Move> moves = monster.getAvailableMoves() ;
        return moves.get(r.nextInt(moves.size())) ; 
    }

    public static void main(String[] args) {

        Random r = new Random() ;

        Team teamRed = new Team("Red") ;
        teamRed.add(new Ignibble()) ;
        teamRed.add(new Twigbit()) ;

        System.out.println("Team Red: \n" + teamRed) ;
        
        Team teamBlue = new Team("Blue") ;
        teamBlue.add(new Droplett()) ;
        teamBlue.add(new Twigbit()) ;

        System.out.println("Team Blue: \n" + teamBlue) ;  

        while (teamRed.hasActive() && teamBlue.hasActive()) {

            Monster redMonster = getRandomMonster(teamRed, r) ;
            Monster blueMonster = getRandomMonster(teamBlue, r) ;

            System.out.println(redMonster + " (red) vs. " + blueMonster + " (blue)") ;

            Move redMove = getRandomMove(redMonster, r) ;
            Move blueMove = getRandomMove(blueMonster, r) ;

            //Resolve attacks first
            if (redMove instanceof AttackMove) {
                redMonster.attempt(redMove) ;
                blueMonster.handle((AttackMove) redMove) ;
            }
            if (blueMove instanceof AttackMove) {
                blueMonster.attempt(blueMove) ;
                redMonster.handle((AttackMove) blueMove) ;
            }

            //Resolve restore moves if monster is active
            if (!redMonster.isFainted() && redMove instanceof RestoreMove) {
                redMonster.attempt(redMove) ;
                redMonster.handle((RestoreMove) redMove) ;
            }
            if (!blueMonster.isFainted() && blueMove instanceof RestoreMove) {
                blueMonster.attempt(blueMove) ;
                blueMonster.handle((RestoreMove) blueMove) ;
            }

            if (redMonster.isFainted() && blueMonster.isFainted()) {
                System.out.println("Red's " + redMonster.getName() + " and Blue's " + blueMonster.getName() + " knocked each other out!") ;
            } else {
                if (redMonster.isFainted()) {
                    System.out.println("Red's " + redMonster.getName() + " fainted!") ;
                    blueMonster.evolve();
                } 
                if (blueMonster.isFainted()) {
                    System.out.println("Blue's " + blueMonster.getName() + " fainted!") ;
                    redMonster.evolve();
                }
            }
            System.out.println();
        }

        if (teamRed.hasActive()) {
            System.out.println("Red Team Wins!");
        } else if (teamBlue.hasActive()) {
            System.out.println("Blue Team Wins!");
        } else {
            System.out.println("It's a draw");
        }
    }
}
