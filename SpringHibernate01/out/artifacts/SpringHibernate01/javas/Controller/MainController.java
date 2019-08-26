package Controller;

import Model.*;
import SessionFactories.UserEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Controller
public class MainController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String indexGet(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        model.addAttribute("msg", "Method:"
                + request.getMethod() + "<br/>" + request.getRequestURI());

        return "index";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String indexPost(ModelMap model, HttpServletRequest request) {
        model.addAttribute("msg", "Method:"
                + request.getMethod() + "<br/>" + request.getRequestURI());

        model.addAttribute("msg2", "Method:"
                + request.getMethod() + "<br/>" + request.getAuthType());

        return "Post";
    }

    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public String Post_Auth(ModelMap model, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Post_Auth postRequest = new Post_Auth();
        String result = null;
        try {
            result = postRequest.Basic_Auth(request, response);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        model.addAttribute("msg", result);
        //response.getWriter().write(result);
        return "Post";
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        return "hello";
    }

    @RequestMapping(value = "/createTest", method = RequestMethod.GET)
    public String createTest(ModelMap model) {
        String username = "test03";
        Integer age = 19;
        String password = "123456";

        SQLWriteTest01 user01 = new SQLWriteTest01();
        user01.WriteTest01(username, age, password);

        model.addAttribute("msg", "Create Success");
        model.addAttribute("name", username);
        model.addAttribute("age", age.toString());
        model.addAttribute("password", password);
        return "create";
    }

    @RequestMapping(value = "/readTest", method = RequestMethod.GET)
    public String readTest(ModelMap model) {
        //String condition01="FROM UserEntity";
        String condition02 = "Select * From user where age>=18";

        SQLReadTest01 read = new SQLReadTest01();
        String result = read.ReadTest01(condition02);
        System.out.println(result);

        model.addAttribute("msg", "Read Success");
        model.addAttribute("msg2", condition02);
        model.addAttribute("msg3", result);
        return "read";
    }

    @RequestMapping(value = "/readTest2", method = RequestMethod.GET)
    public String readTest2(ModelMap model) {
        String condition = "18";

        SQLReadTest02 read = new SQLReadTest02();
        String result = read.SafeReadTest01(condition);
        System.out.println(result);

        model.addAttribute("msg", "Read Success");
        model.addAttribute("msg2", condition);
        model.addAttribute("msg3", result);
        return "read";
    }

    @RequestMapping(value = "/receiveImage", method = RequestMethod.POST)
    public String receiveImage(ModelMap model, HttpServletRequest request, HttpServletResponse response, @RequestParam("Photo01") MultipartFile Photo01) throws IOException, ServletException {
        //System.out.println(request.getPart("Photo01").getName());
        //System.out.println(request.getPart("Photo02").getSize());

        //System.out.println(Photo01.getSize());
        //System.out.println(Photo02.getSize());

        String savePath = "D:\\GW\\JavaProjects\\SpringHibernate01\\src\\main\\webapp\\";

        ReceiveFileTest01 req = new ReceiveFileTest01();
        String status = req.ReceiveImage01(request, response, savePath);

        model.addAttribute("msg", status);

        /*String path="D:\\GW\\JavaProjects\\SpringHibernate01\\src\\main\\webapp";
        Part Photo02 = request.getPart("Photo02");
        String h = Photo02.getHeader("content-disposition");
        String fname = "newPhoto02";
        fname = fname + h.substring(h.lastIndexOf("."), h.length() - 1);
        Photo02.write(path+fname);*/

        //Photo01.getInputStream().toString();
        return "msgTest";
    }

    @RequestMapping(value = "/receiveFile", method = RequestMethod.POST)
    public String receiveFile(ModelMap model, HttpServletRequest request, HttpServletResponse response, @RequestParam("Photo01") MultipartFile Photo01) throws IOException, ServletException {

        String savePath = "D:\\GW\\JavaProjects\\SpringHibernate01\\src\\main\\webapp\\WEB-INF\\uploadSource\\";

        ReceiveFileTest01 req = new ReceiveFileTest01();
        String status = req.ReceiveFile01(request, response, savePath);

        model.addAttribute("msg", status);

        return "msgTest";
    }

    @RequestMapping(value = "/receiveMultiFile", method = RequestMethod.POST)
    public String receiveMultiFile(ModelMap model, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String savePath = "D:\\GW\\JavaProjects\\SpringHibernate01\\src\\main\\webapp\\WEB-INF\\uploadSource\\";

        ReceiveFileTest01 req = new ReceiveFileTest01();
        String status = req.ReceiveMultiFile01(request, response, savePath);

        model.addAttribute("msg", status);

        return "msgTest";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, params = "login")
    public String login_POST(ModelMap model, @ModelAttribute("UserEntity") UserEntity userEntity, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        verification01 verif = new verification01();
        String res = verif.userVerification(model, userEntity.getUsername(), userEntity.getPassword());

        if (res.equals("wname")) {
            model.addAttribute("warning", "user not found!");
            return "login";
        }
        if (res.equals("wpass")) {
            model.addAttribute("warning", "input wrong password!");
            return "login";
        }

        Cookie cookie = new Cookie("loginSuccess", userEntity.getUsername());
        cookie.setMaxAge(10 * 60);
        response.addCookie(cookie);

        model.addAttribute("username", userEntity.getUsername());
        model.addAttribute("info", res);

        return "loginResult";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login_GET(ModelMap model, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if ("loginSuccess".equals(cookie.getName())) {

                verification01 verif = new verification01();
                String info = verif.getInfo(cookie.getValue());

                model.addAttribute("username", cookie.getValue());
                model.addAttribute("info", info);

                return "loginResult";
            }
        }

        return "login";
    }

    @RequestMapping(value = "/imageTest", method = RequestMethod.GET)
    public String imageTest(ModelMap model, HttpServletRequest request, HttpServletResponse response) {

        return "imgTest01";
    }

    @RequestMapping(value = "/fileUpload", method = RequestMethod.GET)
    public String fileUpload_GET(ModelMap model, HttpServletRequest request, HttpServletResponse response) throws IOException {

        boolean cookieStatus = false;
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if ("loginSuccess".equals(cookie.getName())) {
                cookieStatus = true;
            }
        }
        if (!cookieStatus) response.sendRedirect("/SpringHibernate01/login");

        return "fileUpload";
    }

    @RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
    public String fileUpload_POST(ModelMap model, HttpServletRequest request, HttpServletResponse response) {

        String savePath = "D:\\GW\\JavaProjects\\SpringHibernate01\\src\\main\\webapp\\WEB-INF\\uploadSource\\";
        //String savePath = "/uploadSource/";

        ReceiveFileTest01 req = new ReceiveFileTest01();
        String status = req.ReceiveMultiFile01(request, response, savePath);

        model.addAttribute("msg", status);
        return "msgTest";
    }

    @RequestMapping(value = "/fileDownload", method = RequestMethod.GET)
    public String fileDownload(ModelMap model, HttpServletRequest request, HttpServletResponse response) throws IOException {

        boolean cookieStatus = false;
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if ("loginSuccess".equals(cookie.getName())) {
                cookieStatus = true;
            }
        }
        if (!cookieStatus) response.sendRedirect("/SpringHibernate01/login");

        showFileList fileList = new showFileList();
        model.addAttribute("filelist", fileList.showFiles());
        return "fileDownload";
    }

    @RequestMapping("/download/*")
    public void downloadFile3(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String sourceFolderPath = "D:\\GW\\JavaProjects\\SpringHibernate01\\src\\main\\webapp\\WEB-INF\\uploadSource\\";
        //String filename = "8525137i.jpg";
        String filename = request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/") + 1);

        File file = new File(sourceFolderPath + filename);

        // force browser download
        response.setContentType("application/force-download");
        response.setContentLength((int) file.length());
        response.setHeader("content-disposition", "attachment; filename=" + filename);

        // export binary file
        BufferedInputStream br = new BufferedInputStream(new FileInputStream(file));
        OutputStream out = response.getOutputStream();

        int ch;
        while ((ch = br.read()) != -1) {
            out.write(ch);
        }
        out.flush();
        out.close();
    }

    @RequestMapping("/CookieTest01")
    public void CookieTest01(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Cookie[] cookies = request.getCookies();
        Cookie cookie = new Cookie("CookieOwO", "OAO");
        cookie.setMaxAge(40);
        cookie.setPath("/");
        response.addCookie(cookie);
        for (Cookie cok : cookies) {
            System.out.println(cok.getName());
            System.out.println(cok.getValue());
            System.out.println(cok.getPath());
            if ("CookieOwO".equals(cok.getName())) {
                System.out.println("aaaaaaaaaaaaaaaaaa");
            }
        }
    }
}