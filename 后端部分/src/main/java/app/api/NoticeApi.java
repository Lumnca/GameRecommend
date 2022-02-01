package app.api;

import app.dao.NoticeDao;
import app.model.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class NoticeApi {
    @Autowired
    NoticeDao noticeDao;
    @GetMapping("/getNotices")
    public List<Notice> getAllNotice(){
        return  noticeDao.getAllNotice();
    }

}
