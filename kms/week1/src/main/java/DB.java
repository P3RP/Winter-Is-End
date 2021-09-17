import javax.lang.model.type.NullType;
import javax.naming.ContextNotEmptyException;
import javax.xml.stream.events.Characters;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class Pair{
    int level;
    String job;
    Contents contents = new Contents();
    public Pair(int level,String job){
        this.level = level;
        this.job = job;
    }

    public int first(){
        return this.level;
    }

    public String second(){
        return this.job;
    }

}


public class DB {
    //짭 디비
    Map<String, List<Pair>> LCharacters = new HashMap<>();
    public boolean save(String id, int level, String job){
        List<Pair> list = new LinkedList<Pair>();
        Pair pair = new Pair(level,job);
        list.add(pair);
        LCharacters.put(id,list);

        return true;
    }

    public boolean remove(String id) {
        LCharacters.remove(id);
        return true;
    }
    public List<Pair> search(String id){
            List<Pair> pairs = LCharacters.get(id);
            return pairs;
    }

    public void printall() {
        for (String key :LCharacters.keySet()){
            System.out.println("ID : " + key + " LEVEL : " + LCharacters.get(key).get(0).first() + "JOB : " + LCharacters.get(key).get(0).second());
        }
    }
}
