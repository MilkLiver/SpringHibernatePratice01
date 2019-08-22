package Model;

import SessionFactories.UserEntity;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class SQLReadTest01 {
    public String ReadTest01(String condition) {

        //獲取載入配置管理類
        Configuration configuration = new Configuration();

        //不給引數就預設載入hibernate.cfg.xml檔案，
        configuration.configure("././SessionFactories/local_user.xml");

        //建立Session工廠物件
        SessionFactory factory = configuration.buildSessionFactory();

        //得到Session物件
        Session session = factory.openSession();

        //存放要顯示的內容
        StringBuffer showmessage = new StringBuffer();

        //查詢方法1(HQL查詢)
        /*Query query = session.createQuery(condition);
        List list1 = query.list();
        for(Object obj:list1){
            UserEntity res01=(UserEntity)obj;
            showmessage.append("name:"+res01.getUsername()
                    +" age:"+res01.getAge()
                    +" password:"+res01.getPassword()+"<br/>");
        }*/

        //查詢方法2(QBC查詢)
        /*//建立關於user物件的criteria物件
        Criteria criteria = session.createCriteria(UserEntity.class);
        //新增條件
        criteria.add(Restrictions.lt("age", 20));
        //查詢全部資料
        List list2 = criteria.list();
        for(Object obj:list2){
            UserEntity res02=(UserEntity)obj;
            showmessage.append("name:"+res02.getUsername()
                    +" age:"+res02.getAge()
                    +" password:"+res02.getPassword()+"<br/>");
        }*/

        //查詢方法3(本地SQL查詢)
        //將所有的記錄封裝成User物件存進List集合中
        SQLQuery sqlQuery = session.createSQLQuery(condition).addEntity(UserEntity.class);
        List list3 = sqlQuery.list();
        for(Object obj:list3){
            UserEntity res03=(UserEntity)obj;
            showmessage.append("name:"+res03.getUsername()
                    +" age:"+res03.getAge()
                    +" password:"+res03.getPassword()+"<br/>");
        }

        return showmessage.toString();
    }
}
