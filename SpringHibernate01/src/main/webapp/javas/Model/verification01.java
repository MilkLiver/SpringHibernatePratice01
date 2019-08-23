package Model;

import SessionFactories.UserEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.ui.ModelMap;

import java.util.List;

public class verification01 {
    public String userVerification(ModelMap model, String username, String password) {
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

        //查詢條件語句
        //String searchString="select username,age From UserEntity where age>=:inputage";
        String searchString = "From UserEntity where username=:inputUsername";

        List nameCheck = session.createQuery(searchString).setString("inputUsername", username).list();

        if (nameCheck.isEmpty()) {
            return "wname";
        }
        UserEntity userInfo = (UserEntity) nameCheck.get(0);
        if (!userInfo.getPassword().equals(password)) {
            return "wpass";
        }

        showmessage.append("id:" + userInfo.getId()
                + " user:" + userInfo.getUsername()
                + " age:" + userInfo.getAge()
                + " email:" + userInfo.getEmail()
                + " <br/>create time:" + userInfo.getCreateTime());

        return showmessage.toString();
    }

    public String getInfo(String username) {
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

        //查詢條件語句
        //String searchString="select username,age From UserEntity where age>=:inputage";
        String searchString = "From UserEntity where username=:inputUsername";

        List nameCheck = session.createQuery(searchString).setString("inputUsername", username).list();

        UserEntity userInfo = (UserEntity) nameCheck.get(0);

        showmessage.append("id:" + userInfo.getId()
                + " user:" + userInfo.getUsername()
                + " age:" + userInfo.getAge()
                + " email:" + userInfo.getEmail()
                + " <br/>create time:" + userInfo.getCreateTime());

        return showmessage.toString();
    }
}
