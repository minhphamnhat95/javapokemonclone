 //Implement Team class here
import java.util.List;
import java.util.ArrayList;
public class Team{
    
    private String name;
    private List<Monster> teamMembers;
    private List<Monster> activeMonsters;
    
    
    public Team(String name){
        this.name = name;
        this.teamMembers = new ArrayList<>(); 
        this.activeMonsters = new ArrayList<>();
    }

    public String getName(){
        return name;
    }

    public void add(Monster monsterAdded){
        teamMembers.add(monsterAdded);
    }

    public List<Monster> getActive(){
        activeMonsters.clear(); //clear the list initially to avoid the duplicate of the added monster
        for(Monster activeMonster: teamMembers){
            if(!activeMonster.isFainted()){  //if not fainted then active
                activeMonsters.add(activeMonster);
            }
        }
        return activeMonsters;
    }

    public boolean hasActive(){
        return !activeMonsters.isEmpty();  // if is NOT empty then hasActive = true
    }

    public String toString(){
        String result = "";
        for(Monster monster: teamMembers){
            result = result + "* " + monster.toString() + "\n";
        }
        return result;
    }
}
