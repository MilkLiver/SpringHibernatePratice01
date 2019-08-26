package Model;

import SessionFactories.UserEntity;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class SQLReadTest02 {
    public String SafeReadTest01(String condition) {

        //獲取載入配置管理類
        Configuration configuration = new Configuration();

        //不給引數就預設載入hibernate.cfg.xml檔案，
        configuration.configure("SessionFactories/local_user.xml");

        //建立Session工廠物件
        SessionFactory factory = configuration.buildSessionFactory();

        //得到Session物件
        Session session = factory.openSession();

        //存放要顯示的內容
        StringBuffer showmessage = new StringBuffer();
        //查詢條件語句
        //String searchString="select username,age From UserEntity where age>=:inputage";
        String searchString="From UserEntity where age>=:inputage";

        //查詢方法3(本地SQL查詢)
        //將所有的記錄封裝成User物件存進List集合中
        List list3 = session.createQuery(searchString).setString("inputage",condition).list();
        for(Object obj:list3){
            UserEntity res03=(UserEntity)obj;
            showmessage.append("name:"+res03.getUsername()
                    +" age:"+res03.getAge()
                    +" password:"+res03.getPassword()+"<br/>");
        }

        return showmessage.toString();
    }
}
