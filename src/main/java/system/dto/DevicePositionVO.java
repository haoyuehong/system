package system.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Author: mol
 * @Description:
 * @Date: create in 9:12 2018/4/10
 */
@Getter
@Setter
public class DevicePositionVO {
    /**设备码**/
    private String deviceCode;
    /**设备经纬度**/
    private String lnglat;

    /**经纬度***/
    private List<String> lnglats;
}
