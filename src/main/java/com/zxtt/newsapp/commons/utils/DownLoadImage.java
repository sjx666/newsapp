package com.zxtt.newsapp.commons.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.UUID;

public class DownLoadImage {
    static final String relationUrl="src"+File.separator+"main"+File.separator+"resources"+File.separator+"static"+File.separator;

    /**
     * 下载网络图片 到本地
     * @param imgPath
     * @return
     * @throws IOException
     */
    public static String downNetWorkImage(String imgPath) throws IOException {
        String imageUrl=UUID.randomUUID()+".gif";
        //1.定位网络图片路径
        URL url = new URL(imgPath);
        //2.建立与网络图片的连接,获取该图片的输入流
        URLConnection connection = url.openConnection();
        InputStream inputStream = connection.getInputStream();
        //3.在本地建一个图片路径,接收与存储网络图片
        File file = new File(relationUrl+imageUrl);
        FileOutputStream outputStream = new FileOutputStream(file);
        //4.通过字节数组循环读取网络图片到本地
        byte[] bs=new byte[1024];
        int len=0;
        while((len=inputStream.read(bs))!=-1){
            outputStream.write(bs,0,len);
        }
        //5.关闭流
        inputStream.close();
        outputStream.close();
        return imageUrl;
    }
}
