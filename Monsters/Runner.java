import java.util.Random ;
import java.util.List ;

public class Runner {

    public static Monster getRandomMonster(Random r) {

        int value = r.nextInt(3) ;
        if (value == 1) {
            return new Ignibble() ;
        } else if (value == 2) {
            return new Droplett() ;
        } else {
            return new Twigbit() ;
        }
    }

    public static Move getRandomMove(Monster monster, Random r) {

        List<Move> moves = monster.getAvailableMoves() ;
        return moves.get(r.nextInt(moves.size())) ; 
    }

    public static void main(String[] args) {

        Random r = new Random() ;

        Monster monster1 = getRandomMonster(r) ;
        Monster monster2 = getRandomMonster(r) ;

        //evolve monsters for a more epic battle
        //monster1.evolve() ;
        //monster2.evolve() ;

        while (!monster1.isFainted() && !monster2.isFainted()) {

            System.out.println(monster1 + " vs. " + monster2) ;

            Move move1 = getRandomMove(monster1, r) ;
            Move move2 = getRandomMove(monster2, r) ;    

            //Resolve attacks first
            if (move1 instanceof AttackMove) {
                monster1.attempt(move1) ;
                monster2.handle((AttackMove) move1) ;
            }
            if (move2 instanceof AttackMove) {
                monster2.attempt(move2) ;
                monster1.handle((AttackMove) move2) ;
            }

            //Resolve restore moves if monster is active
            if (!monster1.isFainted() && move1 instanceof RestoreMove) {
                monster1.attempt(move1) ;
                monster1.handle((RestoreMove) move1) ;
            }
            if (!monster2.isFainted() && move2 instanceof RestoreMove) {
                monster2.attempt(move2) ;
                monster2.handle((RestoreMove) move2) ;
            }

        }

        if (monster1.isFainted() && monster2.isFainted()) {
            System.out.println(monster1.getName() + " and " + monster2.getName() + " knocked each other out!") ;
            System.out.println("It's a draw!") ;
        } else {
            if (monster1.isFainted()) {
                System.out.println(monster1.getName() + " fainted!") ;
                monster2.evolve();
                System.out.println(monster2.getName() + " wins!");
            } 
            if (monster2.isFainted()) {
                System.out.println(monster2.getName() + " fainted!") ;
                monster1.evolve();
                System.out.println(monster1.getName() + " wins!");
            }
        }
    }       
}
