package Model;

import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class ReceiveFileTest01 {
    public String ReceiveImage01(HttpServletRequest request, HttpServletResponse response, String path) throws IOException, ServletException {
        try {
            Part Photo02 = request.getPart("Photo02");

            // h是要上傳的文件的頭
            //例:form-data; name="Photo02"; filename="8525137i.jpg"
            String h = Photo02.getHeader("content-disposition");

            //把傳上来的檔案命名为"newPhoto02"保存在服務器中
            String fname = "newPhoto02";

            //substring是在獲取文件的後綴，改名但是不改后綴
            fname = fname + h.substring(h.lastIndexOf("."), h.length() - 1);
            System.out.println(fname);

            //按照路徑上傳文件(修改path可以改變文件在服務器中的存儲位置）
            Photo02.write(path + fname);

        }
        catch (Exception e) {
            return "Fail";
        }

        return "Success";
    }

    public String ReceiveFile01(HttpServletRequest request, HttpServletResponse response, String path) throws IOException, ServletException {

        //存放上傳檔案的名稱用的字串
        String fname = "";

        try {
            Part File01 = request.getPart("File");

            // h是要上傳的文件的頭
            //例:form-data; name="Photo02"; filename="8525137i.jpg"
            String h = File01.getHeader("content-disposition");

            //把傳上来的檔案命名为"newPhoto02"保存在服務器中 保持原名稱則不須更動
            //fname = "newPhoto02";


            if (fname.equals("") || fname.equals(null)) {
                fname = h.substring(h.indexOf("filename=\"") + 10, h.length() - 1);
            }
            else {
                //substring是在獲取文件的後綴，改名但是不改后綴
                fname = fname + h.substring(h.lastIndexOf("."), h.length() - 1);
            }

            System.out.println(fname + " upload success!");

            //按照路徑上傳文件(修改path可以改變文件在服務器中的存儲位置）
            File01.write(path + fname);

        }
        catch (Exception e) {
            return "Fail";
        }

        return fname + " Upload Success!";
    }

    public String ReceiveMultiFile01(HttpServletRequest request, HttpServletResponse response, String path) {

        StringBuffer showmssage = new StringBuffer();
        try {
            Collection<Part> partFiles = request.getParts();

            for (Part p : partFiles) {
                SaveFile01 partFile = new SaveFile01();
                showmssage.append(partFile.savefile(request, p, path) + "<br/>");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return "Fail";
        }
        showmssage.append("Upload Success!");

        return showmssage.toString();

    }
}
