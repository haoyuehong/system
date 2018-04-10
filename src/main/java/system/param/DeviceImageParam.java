package system.param;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: mol
 * @Description:
 * @Date: create in 15:50 2018/4/10
 */
@Setter
@Getter
public class DeviceImageParam {

    private Integer imageType = 4;
    @NotBlank(message = "设备id不能为空")
    private String id;

    @NotBlank(message = "图片文件不能为空")
    private MultipartFile image;

    private String name;

}
