import java.util.List ;
import java.util.ArrayList ;

public class Runner {

    public static void main(String[] args) {

        List<Move> moves = new ArrayList<Move>() ;
        
        moves.add(new AttackMove("Flame", 5, Type.FIRE, 10)) ;
        moves.add(new AttackMove("Ice Blast", 15, Type.WATER, 50)) ;
        moves.add(new RestoreMove("Second Wind", 10, 50, 50)) ;

        for (Move move: moves) {
            System.out.println(move) ;
        }
    }


}
