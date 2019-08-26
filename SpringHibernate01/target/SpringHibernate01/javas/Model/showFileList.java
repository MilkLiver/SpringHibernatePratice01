package Model;

import java.io.File;

public class showFileList {
    public String showFiles() {
        StringBuffer fileList = new StringBuffer();
        String path = "D:\\GW\\JavaProjects\\SpringHibernate01\\src\\main\\webapp\\WEB-INF\\uploadSource";
        File file = new File(path);
        if (file != null || "".equals(path)) {
            File[] tempList = file.listFiles();
            if (tempList != null) {
                for (File f : tempList) {
                    if (f.isDirectory()) {
                        //System.out.println("Directory: " + f.getName());
                    }
                    if (f.isFile()) {
                        fileList.append("<li><a href=\"/SpringHibernate01/download/" + f.getName() + "\">" + f.getName() + "</a></li>");
                        //System.out.println("File: " + f.getName());
                    }
                }
            }
        }
        return fileList.toString();
    }
}
