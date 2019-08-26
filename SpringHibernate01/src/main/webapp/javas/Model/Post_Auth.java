package Model;

import net.sf.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Base64;
import java.util.Enumeration;

public class Post_Auth {
    public String Basic_Auth(HttpServletRequest request, HttpServletResponse response) throws IOException {
        StringBuffer readstr = new StringBuffer();
        String line;
        String json_timestamp = null;
        String sessionAuth = null;
        JSONObject sendJson = new JSONObject();
        Base64.Decoder decoder = Base64.getDecoder();
        Base64.Encoder encoder = Base64.getEncoder();


        Boolean exist = false;
        Enumeration headers = request.getHeaderNames();
        while (headers.hasMoreElements()) {
            if (headers.nextElement().equals("authorization")) {
                exist = true;
            }
        }
        if (!exist) {
            response.setStatus(401);
            sendJson.put("status", "Error");
            response.getWriter().write(sendJson.toString());
            return "Fail";
        }

        try {
            sessionAuth = (String) request.getHeader("authorization");
        } catch (Exception e) {

            return "Fail";
        }
        sessionAuth = sessionAuth.split(" ")[1];
        if (sessionAuth == null || sessionAuth.equals("")) {
            response.setStatus(401);
            sendJson.put("status", "Error");
            response.getWriter().write(sendJson.toString());
            System.out.println("Error");
            return "Fail";

        }
        //System.out.println(sessionAuth);

        byte[] auth_decode = decoder.decode(sessionAuth);
        sessionAuth = new String(auth_decode, "UTF-8");
        String userandpass[] = sessionAuth.split(":");

        if (!userandpass[0].equals("admin") || !userandpass[1].equals("Admin123456")) {
            response.setStatus(401);
            sendJson.put("status", "Error");
            response.getWriter().write(sendJson.toString());
            System.out.println("Error");
            System.out.println("user:" + userandpass[0] + "\npassword:" + userandpass[1]);
            return "Fail";
        }


        try {
            System.out.println("user:" + userandpass[0] + "\npassword:" + userandpass[1]);
        } catch (Exception e) {
            System.out.println(e);
        }


        request.setCharacterEncoding("UTF-8");

        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null) {
                readstr.append(line);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        JSONObject json = JSONObject.fromObject(readstr.toString());

        try {
            json_timestamp = json.getString("timestamp");
            System.out.println(json_timestamp);
        } catch (Exception e) {
            System.out.println(e);
        }

        sendJson.put("status", "SUCCESS");
        sendJson.put("timestamp", json_timestamp);

        //response.getWriter().write(sendJson.toString());
        return sendJson.toString();
    }
}
