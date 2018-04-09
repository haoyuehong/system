package system.common;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import system.exception.SystemException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * @Author: mol
 * @Description:
 * @Date: create in 8:36 2018/3/14
 */

@Slf4j
public class SpringExceptionResolver implements HandlerExceptionResolver {
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) {
        String url = request.getRequestURL().toString();
        ModelAndView mv;
        String defaultMsg = "System error";
        //定义文本请求和页面请求
        //json请求
        if(url.endsWith(".do")){
            //判断是否是我们自定义的异常
            if(e instanceof SystemException){
                //创建异常返回信息
                JsonData jsonData = JsonData.createError(e.getMessage());
                //封装异常信息
                mv = new ModelAndView("jsonView",jsonData.toMap());
            }else{
                log.error("unkwon json exception={},url={},",e,url);
                JsonData jsonData = JsonData.createError(defaultMsg);
                mv = new ModelAndView("jsonView",jsonData.toMap());
            }
        }else if(url.endsWith(".html")){
            //页面请求
            log.error("unkwon page exception={},url={},",e,url);
            JsonData jsonData = JsonData.createError(defaultMsg);
            mv = new ModelAndView("exception",jsonData.toMap());
        }else{
            log.error("unkwon exception={},url={},",e,url);
            JsonData jsonData = JsonData.createError(defaultMsg);
            mv = new ModelAndView("jsonView",jsonData.toMap());
        }
        return mv;
    }
}
