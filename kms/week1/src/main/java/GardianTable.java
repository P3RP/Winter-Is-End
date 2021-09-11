import java.util.LinkedList;
import java.util.List;

class Guardian {
    String Name;
    int level;
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
    public Guardian(int level, String Name){
        this.level = level;
        this.Name = Name;
    }
}
public class GardianTable {
    List<Guardian> guardianList = new LinkedList<>();
    Guardian guardian = new Guardian(302,"우르닐");

    guardianList.add(guardian);

}
