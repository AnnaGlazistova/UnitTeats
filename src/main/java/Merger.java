import java.util.ArrayList;
import java.util.Collections;

public class Merger {

    public static ArrayList<Integer> merge(ArrayList<Integer> team1, ArrayList<Integer> team2) {
        ArrayList<Integer> team = new ArrayList<>();
        team.addAll(team1);
        team.addAll(team2);
        ArrayList<Integer> newTeam = new ArrayList<>();
        while (newTeam.size() < 10) {
            newTeam.add(Collections.max(team));
            team.remove(Collections.max(team));
        }
        return newTeam;
    }
}
