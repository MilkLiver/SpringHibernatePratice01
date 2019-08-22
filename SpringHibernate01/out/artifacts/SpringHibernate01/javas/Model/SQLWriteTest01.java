package Model;

import SessionFactories.UserEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class SQLWriteTest01 {
    public String WriteTest01(String username,Integer age,String password) {
        //建立物件
        UserEntity user = new UserEntity();
        user.setUsername(username);
        user.setAge(age);
        user.setPassword(password);
        //獲取載入配置管理類
        Configuration configuration = new Configuration();
        //不給引數就預設載入hibernate.cfg.xml檔案，
        configuration.configure("././SessionFactories/local_user.xml");
        //建立Session工廠物件
        SessionFactory factory = configuration.buildSessionFactory();
        //得到Session物件
        Session session = factory.openSession();
        //使用Hibernate運算元據庫，都要開啟事務,得到事務物件
        Transaction transaction = session.getTransaction();
        //開啟事務
        transaction.begin();
        //把物件新增到資料庫中
        session.save(user);
        //提交事務
        transaction.commit();
        //關閉Session
        session.close();
        return "Create Success!";
    }
}
