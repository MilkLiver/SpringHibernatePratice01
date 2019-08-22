package Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;

public class SaveFile01 {
    public String savefile(HttpServletRequest request, Part partFile, String savePath) {

        //存放上傳檔案的名稱用的字串
        String fname = "";

        try {

            // h是要上傳的文件的頭
            //例:form-data; name="Photo02"; filename="8525137i.jpg"
            String h = partFile.getHeader("content-disposition");

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
            System.out.println(savePath+fname);
            partFile.write(savePath + fname);

        } catch (IOException e) {
            e.printStackTrace();
            return "Fail";
        }
        return fname;
    }
}
