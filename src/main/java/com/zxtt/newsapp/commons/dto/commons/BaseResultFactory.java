package com.zxtt.newsapp.commons.dto.commons;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 通用响应结构工厂
 */
@SuppressWarnings("all")
public class BaseResultFactory<T extends AbstractBaseDomain> {
    private static final String LOGGER_LEVEL_DEBUG="DEBUG";

    private static BaseResultFactory baseResultFactory;

    private BaseResultFactory(){

    }
    private static HttpServletResponse response;
    public static BaseResultFactory getInstance(HttpServletResponse response){
        if(baseResultFactory==null){
            synchronized (BaseResultFactory.class){
                if(baseResultFactory==null){
                    baseResultFactory=new BaseResultFactory();
                }
            }
        }
        BaseResultFactory.response=response;
        baseResultFactory.initResponse();
        return baseResultFactory;
    }

    // 构建单笔数据结果集
    public AbstractBaseResult build(String self, T attributes){

        return new SuccessResult(self,attributes);
    }
    // 构建多笔数据结果集
    public  AbstractBaseResult build(String self, int next, int last, List<T> attributes){

        return new SuccessResult(self,next,last,attributes);
    }
    //构建错误的响应结构
    public AbstractBaseResult build(int code,String title,String detail,String level){
        response.setStatus(code); // 请求失败响应码
        if (LOGGER_LEVEL_DEBUG.equals(level)){
            return new ErrorResult(code,title,detail);
        }else {
            return new ErrorResult(code,title,null);
    }

    }
    private void initResponse(){
        response.setHeader("Content-Type","application/vnd.api+json");
    }
}
