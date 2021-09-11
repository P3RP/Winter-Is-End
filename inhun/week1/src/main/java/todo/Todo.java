package todo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Todo {
    List<Map> TODOList;
    String str;
    String command;

    Todo() {
        this.TODOList = new ArrayList<>();
    }

    public static void main(String[] args) throws InterruptedException {
        Todo todo = new Todo();

        todo.run();
    }


    public void getNewTODO(BufferedReader br) throws IOException {
        System.out.print("항목: ");
        String todo = br.readLine();
        System.out.print("목표 날짜: ");
        String target_date = br.readLine();
        addTODO(todo, target_date);
    }
    public void addTODO(String todo, String target_date)
    {
        Map<String, String> TODO = new HashMap<>();
        TODO.put("TODO", todo);
        TODO.put("target_date", target_date);
        TODO.put("complete", "no");
        TODOList.add(TODO);
    }

    public void completeTODO(BufferedReader br) throws IOException {
        System.out.println("index: ");
        String index_str = br.readLine();
        int index = Integer.parseInt(index_str);
        Stream stream_notComplete = TODOList.stream()
                .filter(TODO_HashMap -> TODO_HashMap.get("complete").toString().equals("no"));
        // 모르겠어요
        List<Map> notComplete = (List<Map>) stream_notComplete.collect(Collectors.toList());
        notComplete.get(index).replace("complete", "yes");
    }

    public void deleteTODO(BufferedReader br) throws IOException {
        System.out.println("index: ");
        String index_str = br.readLine();
        int index = Integer.parseInt(index_str);
        this.TODOList.remove(index);
    }


    public void PrintCommand() {
        System.out.println("1. add TODO");
        System.out.println("2. complete TODO");
        System.out.println("3. delete TODO");

    }

    public void printTODO() {
        System.out.println("====미완료====");
        Stream notComplete = TODOList.stream()
                .filter(TODO_HashMap -> TODO_HashMap.get("complete").toString().equals("no"));
        // .filter(oo -> oo.get("complete").toString().equals("no"));
        notComplete.forEach(i -> System.out.println(i));
        System.out.println("====완료====");
        Stream Complete = TODOList.stream()
                .filter(TODO_HashMap -> TODO_HashMap.get("complete").toString().equals("yes"));
        Complete.forEach(i -> System.out.println(i));
        PrintCommand();
    }

    public void run()
    {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        while (true)
        {
            printTODO();
            try {
                String command = br.readLine();
                switch(command) {
                    case "1":
                        getNewTODO(br);
                        break;

                    case "2":
                        completeTODO(br);
                        break;

                    case "3":
                        deleteTODO(br);
                        break;
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
