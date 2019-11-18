package com.zxtt.newsapp.bug;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zxtt.newsapp.commons.constance.FilesStatus;
import com.zxtt.newsapp.commons.constance.TimeDifference;
import com.zxtt.newsapp.commons.entity.Comment;
import com.zxtt.newsapp.commons.entity.Files;
import com.zxtt.newsapp.commons.entity.News;
import com.zxtt.newsapp.commons.entity.User;
import com.zxtt.newsapp.commons.mapper.FilesMapper;
import com.zxtt.newsapp.commons.mapper.UserMapper;
import com.zxtt.newsapp.commons.utils.DownLoadImage;
import com.zxtt.newsapp.commons.utils.EmojiFilter;
import com.zxtt.newsapp.service.comment.CommentService;
import com.zxtt.newsapp.service.files.FilesService;
import com.zxtt.newsapp.service.news.NewsService;
import com.zxtt.newsapp.service.user.UserService;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.sql.SQLException;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.stream.IntStream;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class NewsAppBug {
    @Autowired
    private UserService userService;
    @Autowired
    private FilesService filesService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private NewsService newsService;

    private static Integer [] nums=new Integer[]{4388,1988,4288,4188,5088,1388,5188,3288,4888,7978};
    private final String agent="User-Agent";
    private final String header= "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36";
    @Test
    public void javaBug(){
        // 创建 HttpClient 客户端
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse httpResponse = null;
        HttpGet httpGet=null;
        for (Integer num : nums) {
            String httpNewsUrl="https://m.weibo.cn/api/container/getIndex?containerid=102803_ctg1_"+num+"_-_ctg1_"+num+"&openApp=0";
            httpGet= new HttpGet(httpNewsUrl);
            // 设置代理（模拟浏览器版本）
            httpGet.setHeader(agent,header);
            try {
                // 请求并获得响应结果
                httpResponse = httpClient.execute(httpGet);
                String r = EntityUtils.toString(httpResponse.getEntity());
                JSONObject jsonObject = JSONObject.parseObject(r);
                JSONArray jsonArray = jsonObject.getJSONObject("data").getJSONArray("cards");
                for (Object o : jsonArray){
                    newsInfo((JSONObject) o);//一个新闻
                }
            } catch (IOException e) {
                System.out.println("\n\nHTTP----------出现异常\n\n");
            }
        }
    }


    /**
     * 一个新闻的信息
     * @param e
     */
    public void newsInfo(JSONObject e) throws IOException {
        JSONObject result = e.getJSONObject("mblog");//mblog中的数据

        //清洗title中数据
        String title = result.getString("text");
        int index = title.indexOf("<");
        if(index!=-1){
            title=title.replace(title.substring(index,title.lastIndexOf(">")+1),"");
        }
        //保存新闻用户数据到数据库
        Long userid = userInfo(result.getJSONObject("user"));

        //添加新闻信息到数据库
        Long newsid = addNewsInfo(title, userid);

        newsCommentInfo(result.getString("id"),newsid);
    }

    public Long addNewsInfo(String tilte,Long userid){
        News news = new News();
        news.setTypeid(Long.parseLong(String.valueOf(new Random().nextInt(7))));
        news.setTitle(EmojiFilter.filterEmoji(tilte));
        news.setUserid(userid);
        news.setCreatedate(new Date());
        return newsService.insert(news).getId();
    }


    /**
     * 评论信息
     */
    public void newsCommentInfo(String id,Long newsid) throws IOException {
        // 创建 HttpClient 客户端
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse httpResponse = null;
        String httpCommentUrl="https://m.weibo.cn/comments/hotflow?id="+id+"&mid="+id+"&max_id_type=1";
        HttpGet httpGet = new HttpGet(httpCommentUrl);
        // 设置代理（模拟浏览器版本）
        httpGet.setHeader(agent,header);
        httpResponse = httpClient.execute(httpGet);
        JSONObject jsonObject = JSONObject.parseObject(EntityUtils.toString(httpResponse.getEntity()));
        if(jsonObject==null){
            return;
        }
        JSONObject data = jsonObject.getJSONObject("data");
        if(data==null){
            return;
        }
        JSONArray data1 = data.getJSONArray("data");
        for (Object o : data1) {
            //添加一个评论
            addNewsCommentInfo((JSONObject)o,newsid);
        }
    }

    public void addNewsCommentInfo(JSONObject jsonObject,Long newsid) throws IOException {
        //评论内容
        String text = jsonObject.getString("text");
        int index = text.indexOf("<");
        if(index!=-1){
            text=text.replace(text.substring(index,text.lastIndexOf(">")+1),"");
        }
        //评论和用户信息
        saveCommentInfo(text, newsid, userInfo(jsonObject.getJSONObject("user")));
    }

    @Transactional
    public void saveCommentInfo(String content,Long newsid,Long userid){
        Comment comment = new Comment();
        comment.setContent(EmojiFilter.filterEmoji(content));
        comment.setCreatedate(new Date());
        comment.setNewsid(newsid);
        comment.setUserid(userid);
        try{
            commentService.insert(comment).getId();
        }catch (Exception e){
            System.out.println("出现异常----------------->\n\n\n");
        }
    }

    /**
     * 保存文件到数据库
     * @param filesurl
     * @param i
     */
    public void saveFilesInfo(String filesurl,int i,long id){
        Files files = new Files();
        files.setIsflag(i);
        files.setFileurl(filesurl);
        files.setNewsid(id);
        filesService.insert(files);
    }
    /**
     * 用户信息
     */
    public Long userInfo(JSONObject jsonObject) throws IOException {

        //用户头像
        User user = new User();
        //用户名
        user.setName(jsonObject.getString("screen_name"));//用户名
        user.setPassword(UUID.randomUUID().toString().substring(0,8));//密码
        user.setCreatedate(new Date());//创建时间
        user.setAge(new Random().nextInt(40));
        //保存用户头像图片
        Long userid = userService.insert(user).getId();
        saveFilesInfo(DownLoadImage.downNetWorkImage(
                jsonObject.getString("profile_image_url")),FilesStatus.userFiles.getI(),userid);
        return userid;
    }




}
