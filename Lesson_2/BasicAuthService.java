package root;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class BasicAuthService implements AuthService {
//    private final Set<Record> records;
    private DataBase records = new DataBase();

    public BasicAuthService() {
//        records = new HashSet<>();
//        records.add(new Record(1, "Barboss", "l1", "p1","avatars/pic1.png"));
//        records.add(new Record(2, "Kelvin", "l2", "p2","avatars/pic2.png"));
//        records.add(new Record(3, "Nicky", "l3", "p3","avatars/pic3.png"));
//        records.add(new Record(4, "Klaus", "l4", "p4","avatars/pic4.png"));
    }

    @Override
    public Record findRecord(String login, String password) {
//        for (Record record : records) {
//            if (record.getLogin().equals(login) && record.getPassword().equals(password)) {
        Record currentRecord = records.getUser(login,password);
               if (currentRecord != null) return currentRecord;
//            }
//        }
        return null;
    }

    @Override
    public void setRecord(String name,String login,String password){
        records.setUser(name,login,password,"avatars/pic5.png");
    }

    @Override
    public Set<Record> getRecords() {
        return records.getRecords();
    }

    public void closeDataBaseConnection(){
        records.closeConnection();
    }
}
