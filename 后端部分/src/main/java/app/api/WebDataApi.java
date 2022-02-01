package app.api;


import app.dao.WebDataDao;
import app.model.Response;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class WebDataApi {
    @Autowired
    WebDataDao webDataDao;

    @GetMapping("/getWebData")
    public JSONObject getWebData(){
        return webDataDao.getWebData();
    }

    @PostMapping("/vadd")
    public Response vadd(){
        if(webDataDao.VisitorNumberAdd()>0){
            return new Response(200,"success!");
         }
        else{
            return new Response(500,"");
        }
    }
}
