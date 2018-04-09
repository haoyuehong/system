package system.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author: mol
 * @Description:
 * @Date: create in 8:25 2018/3/28
 */
@Getter
@Setter
public class DeviceFunctionBase {
    /**设备名**/
    private String deviceName;
    /**历史人数**/
    private Integer hstoryNum;
    /**总时常**/
    private Integer totalDuration;
}
