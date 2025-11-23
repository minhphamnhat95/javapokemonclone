//Implement Player interface/class here
public abstract class Player{
    
    private Team team;

    public Player(Team team){
        this.team = team;
    }

    public Team getTeam(){
        return team;
    }

    public abstract Monster chooseMonster(Team oppTeam);

    public abstract Move chooseMove(Monster monster, Team oppTeam);

}
