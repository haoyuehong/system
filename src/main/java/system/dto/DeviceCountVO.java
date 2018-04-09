package system.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Author: mol
 * @Description:
 * @Date: create in 8:24 2018/3/28
 */
@Getter
@Setter
public class DeviceCountVO {
    /**设备名**/
    private String deviceName;
    List<DeviceFunctionBase> deviceFunctionBases;


}
