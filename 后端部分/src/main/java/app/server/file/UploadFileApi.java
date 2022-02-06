package app.server.file;

import app.model.Response;
import com.alibaba.fastjson.JSONObject;
import org.apache.tomcat.jni.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@CrossOrigin
@RestController
public class UploadFileApi {
    @Autowired
    FileDao fileDao;
    @PostMapping("uploadimg")
    public Response uploadimg(@RequestParam("upload") MultipartFile file, HttpServletRequest request,
                              HttpServletResponse response){
        try {
            //上传文件
            FastDFSClient dfs = new FastDFSClient();
            //文件名
            String filename = file.getOriginalFilename();
            //后缀名
            String extName = filename.substring(filename.lastIndexOf(".")+1);
            //上传文件
            String url = dfs.uploadFile(file.getBytes(), extName);
            //构建完整的图片地址。
            String imgpath = FileServerAddr.getFileserver()+"/"+url;
            DateFormat df3 = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.CHINA);

           System.out.println("上传地址:"+imgpath);
            File file1 = new File(imgpath,extName,file.getSize());
            file1.setDate(df3.format(new Date()));
            fileDao.addFile(file1);
            return new Response(200,imgpath);

        } catch (Exception e) {
            e.printStackTrace();
            return new Response(400,"上传错误");
        }

    }
    @GetMapping("delFile")
    public Response deleteFile(String filename){
       try {
           FastDFSClient dfs = new FastDFSClient();

           if( dfs.testDelete(filename)){
               return new Response(200,"delete success!");
           }
           else{
               return new Response(200,"no delete file!");
           }

       }
       catch (Exception e){
           e.printStackTrace();
           return new Response(500,"delete fail!");
       }
    }
    @GetMapping("getAllFiles")
    public List<File> getAllFiles(){
        return fileDao.getAllFiles();
    }
    @PostMapping("delFile")
    public Response delFile(@RequestBody File file){
        if(fileDao.delFile(file.getId())>0){
            return deleteFile(file.getFileaddr());
        }
        else{
            return new Response(500,"no delFile id");
        }
    }
    @PostMapping("addFile")
    public Response addFile(@RequestBody File file){
        if(fileDao.addFile(file)>0){
            return new Response(200,"delFile success!");
        }
        else{
            return new Response(500,"delFile fail!");
        }
    }
    @PostMapping("uploadimgeditor")
    public JSONObject uploadimg(@RequestParam("upload") MultipartFile file){
        JSONObject object = new JSONObject();
        List<Img> imgs = new ArrayList<>();
        try {
            Img img = new Img();
            //上传文件
            FastDFSClient dfs = new FastDFSClient();
            //文件名
            String filename = file.getOriginalFilename();
            //后缀名
            String extName = filename.substring(filename.lastIndexOf(".")+1);
            //上传文件
            String url = dfs.uploadFile(file.getBytes(), extName);
            //构建完整的图片地址。
            String imgpath = FileServerAddr.getFileserver()+"/"+url;
            object.put("errno",0);
            img.setHref(imgpath);
            img.setUrl(imgpath);
            img.setAlt("这是图片");
            imgs.add(img);

        } catch (Exception e) {
            object.put("error",1);
        }
        object.put("data",imgs);
        return object;
    }

}
