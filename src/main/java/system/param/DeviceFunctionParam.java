package system.param;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author: mol
 * @Description:
 * @Date: create in 16:15 2018/4/10
 */

@Setter
@Getter
public class DeviceFunctionParam {

    private Integer id;//设备码
    private Integer RealTimeparticNum;  //当前人数
    private Integer  hstoryparticNum;    //历史人数
    private Integer curCount;          //本次人数
    private String runTime;//开机时间（string）
    private String thisDevRunTimeDif;//本次时长（秒）
    private String devRunTimeDif;//总时长（秒）
}
