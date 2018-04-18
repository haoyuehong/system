package system.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Author: mol
 * @Description:
 * @Date: create in 10:56 2018/4/10
 */
@Setter
@Getter
public class SiteVO {

    private String siteName;

    private Integer groupId;

    @JsonProperty("children")
    List<DeviceFunctionBase> deviceFunctionBases;
}
