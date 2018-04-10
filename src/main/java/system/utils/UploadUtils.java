package system.utils;

import com.github.iamdrq.staticlient.FileUploadClient;
import com.github.iamdrq.staticlient.FileUploadResult;
import org.springframework.web.multipart.MultipartFile;
import system.common.JsonData;
/**
 * @author: GPJ
 * @Description: 文件上传工具类
 * @Date Created in 17:04 2017/10/24
 * @Modified By:
 */
public class UploadUtils {
    public static JsonData<String> uploadPic(MultipartFile file, String path) throws Exception{
        FileUploadClient fileUploadClient = new FileUploadClient("https://static.jdksh.cn/","lgfiletest");
        FileUploadResult uploadResult = fileUploadClient.upload(path, file.getOriginalFilename(), file.getInputStream());
        if(uploadResult.getCode() == 0){
            return JsonData.createError(uploadResult.getMsg());
        }
       return JsonData.createSuccess(uploadResult.getPath().get(0).replace("627/",""));
    }
}
