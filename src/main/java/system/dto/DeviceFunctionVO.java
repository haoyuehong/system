package system.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;

/**
 * @Author: mol
 * @Description:
 * @Date: create in 15:03 2018/3/27
 */
@Getter
@Setter
public class DeviceFunctionVO extends DeviceFunctionBase{
    private Integer id;
    /**设备码**/
    private String deviceCode;
    /**设备安装地址**/
    private String deviceAddress;
    /**设备安装时间**/
    private Date buildTime;
    /**实时人数**/
    private Integer realNum;
    /**本次参与人数**/
    private Integer curNum;
    /**开机时间**/
    private Date onTime;
    /**本次开机时常**/
    private Integer thisDuration;
    /**最后上传时间**/
    private Date lastUploadTime;
    /**开关机状态**/
    private Boolean deviceStatus;
    /**是否有图片上传**/
    private Boolean deviceImage;


}
