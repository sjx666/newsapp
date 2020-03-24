package com.zxtt.newsapp.web.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.zxtt.newsapp.commons.constance.TimeDifference;
import com.zxtt.newsapp.commons.entity.Comment;
import com.zxtt.newsapp.commons.entity.Files;
import com.zxtt.newsapp.commons.entity.News;
import com.zxtt.newsapp.commons.entity.User;
import com.zxtt.newsapp.commons.enums.FileEnum;
import com.zxtt.newsapp.commons.res.CommentRes;
import com.zxtt.newsapp.service.comment.CommentService;
import com.zxtt.newsapp.service.files.FilesService;
import com.zxtt.newsapp.service.news.NewsService;
import com.zxtt.newsapp.service.praise.PraiseService;
import com.zxtt.newsapp.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.filters.ExpiresFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 新闻控制业务  ?
 */
@Controller
@RequestMapping("/news")
@Slf4j
public class NewsController {


    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @Autowired
    private PraiseService praiseService;
    @Autowired
    private FilesService filesService;
    @Autowired
    private NewsService newsService;
    @RequestMapping("/index")
    public String index(Model model){
        List<News> news = newsService.findnewsList();
        for (News news1: news) {
            String s=   news1.getFiles().getFileurl();
            news1.getFiles().setFileurl("../"+s);
        }
        model.addAttribute("news",news);

        return "index";
    }
    @RequestMapping("/shang")
    public String shang(Model model){
        List<News> news = newsService.findnewsList();
        model.addAttribute("news",news);

        return "shangchuan";
    }

    @RequestMapping("/sa")
    @ResponseBody
    public List<News> sa(Model model){
        List<News> news = newsService.findnewsList();
        model.addAttribute("news",news);

        return news;
    }

    @RequestMapping("/fileUpload")
    @ResponseBody
    public String add(MultipartFile myFile,HttpServletRequest req) {
        //判断文件上传是否为空
        if (!myFile.isEmpty()) {
            String fileName = myFile.getOriginalFilename();
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            String paths = "D:\\aa\\newsapp\\src\\main\\resources\\static\\";
            String pathss = paths + fileName;
            System.out.println("qqqqq");
            java.io.File file = new java.io.File(pathss);
            try {
                myFile.transferTo(file);
                return "index";
            } catch (IllegalStateException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }


        }
        return "shangchuan";
    }


    /**
     * genghuanhuan
     * @param id
     * @param pageNum
     * @param model
     * @return
     */
    @GetMapping(value="getNewsDetail")
    public String getNewsDetail(@RequestParam(value = "id")Long id,
                                @RequestParam(value="pageNum",required = false)Integer pageNum,
                                Model model){

        /**
         * 当前页数
         */
        Integer num=1;
        if(pageNum!=null && pageNum>1){
            num=pageNum;
        }
        Map<String ,Object> map=new HashMap<String, Object>();
        News news1 = new News();
        news1.setId(id);
        News news = newsService.selectOne(news1);
        //新闻
        map.put("news",news);
        Files files = new Files();
        files.setNewsid(id);
        files.setIsflag(FileEnum.news.getResult());
        List<Files> list2 = filesService.getList(files);
        list2.forEach(files1 -> {
            files1.setFileurl("../"+files1.getFileurl());
        });
        //新闻图片
        map.put("newsstatic",list2);
        //新闻评论
        Comment comment = new Comment();
        comment.setNewsid(id);
        PageInfo<Comment> page = commentService.page(comment, num, 5);
        List<CommentRes> commentRes = new ArrayList<>();
        if(page!=null){
            List<Comment> list = page.getList();
            for (Comment comment1 : list) {
                CommentRes commentRes1 = new CommentRes();
                commentRes1.setNewsid(comment1.getNewsid());
                commentRes1.setId(comment1.getId());
                commentRes1.setContent(comment.getContent());
                commentRes1.setJlDate(TimeDifference.messageTime(comment1.getCreatedate()));
                commentRes1.setPraisecount(comment1.getPraisecount());
                commentRes1.setUserid(comment1.getUserid());
                User user = new User();
                user.setId(comment1.getUserid());
                User userinfo = userService.selectOne(user);
                commentRes1.setName(userinfo.getName());
                Files files1 = new Files();
                files1.setIsflag(FileEnum.userImageUrl.getResult());
                files1.setNewsid(userinfo.getId());
                List<Files> list1 = filesService.getList(files1);
                if(list1!=null&&list1.size()>0){
                    commentRes1.setUserImage("../"+list1.get(0).getFileurl());
                }
                commentRes.add(commentRes1);
            }
        }
        map.put("comments",commentRes);

        model.addAttribute("map",map);
        log.info("news res 出参 {} ", JSON.toJSONString(map));
        return "content";
    }

    //新鲜事列表
    @RequestMapping("/list")
    public String list(Model model){
       List<News> list=newsService.selectNewsList();

            model.addAttribute("list",list);

    return "freshnews";
    }
    //删除
    @RequestMapping("/delete")
    public String deleteFreshNews(Long id){
        newsService.deleteFreshNews(id);

        return "redirect:list";
    }
    @RequestMapping("/add1")
    public String addNews(Model model){
        List<News> list=newsService.selectNewsList();

            model.addAttribute("news",new News());

        return "addfreshnews";
    }
    @RequestMapping("/addFreshNews")
    public String addFreshNews(News news){
        System.out.println("1111111111111111");
        newsService.addFreshNews(news);
        System.out.println(news.getUserid());
        return "redirect:list";
    }

   @RequestMapping("/updatefreshnews")
    public String updateAir( Long id,Model model){
       System.out.println("121234567890");
        News news=newsService.selectOneById(id);
       System.out.println("id"+id);
        model.addAttribute("news",news);
        return "addfreshnews";
    }

}