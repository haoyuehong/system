package system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import system.Service.DeviceFunctionService;
import system.Service.DeviceImageService;
import system.common.JsonData;
import system.dto.DeviceFunctionVO;
import system.model.DeviceFunction;
import system.model.DeviceImage;
import system.param.DeviceFunctionParam;
import system.param.DeviceImageParam;
import system.utils.BeanValidator;
import system.utils.UploadUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: mol
 * @Description:
 * @Date: create in 11:35 2018/4/10
 */
@Controller
@RequestMapping(value = "/dev")
public class DeviceInterface {

    @Autowired
    private DeviceImageService deviceImageService;
    @Autowired
    private DeviceFunctionService functionService;

    /**
     * 现场情况图片上传接口
     * @param image 上传的图片文件
     * @return
     */
    @RequestMapping(value = "/deviceInfo/devSceneImage.do")
    @ResponseBody
    public JsonData spotImageUpload(MultipartFile image, Integer devCode) throws Exception{
        JsonData<String> json = UploadUtils.uploadPic(image, "627" + "/upload/spot/image/" + devCode + "/");
        //将路径保存到数据库
        DeviceImage devImage = new DeviceImage();
        devImage.setDeviceCode(devCode.toString());
        devImage.setDeviceImagePath(json.getMsg());
        devImage.setImageUploadTime(new Date());
        devImage.setImageType(4);
        deviceImageService.save(devImage);
        return JsonData.createSuccess("上传成功",2);
    }


    /**
     * 人数接口:当前人数、历史人数、本次人数
     */
    @RequestMapping(value = "/questions/participantQuiz.do")
    @ResponseBody
    public JsonData working(DeviceFunctionParam functionParam){
        DeviceFunction deviceRun = new DeviceFunction();
        deviceRun.setRealNum(functionParam.getRealTimeparticNum());
        deviceRun.setHstoryNum(functionParam.getHstoryparticNum());
        deviceRun.setCurNum(functionParam.getCurCount());
        deviceRun.setDeviceCode(functionParam.getId().toString());
        DeviceFunctionVO functionVO = functionService.findByDeviceCode(functionParam.getId().toString());
        if(functionVO != null){
            deviceRun.setId(functionVO.getId());
            functionService.updateDevRun(deviceRun);
        }else{
            functionService.save(deviceRun);
        }
        return JsonData.createSuccess("数据保存成功");
    }

    /**
     *时间接口：开机时间（string）  本次时长（秒） 总时长（秒）
     */
    @RequestMapping(value = "/questions/devRunTimeDif.do")
    @ResponseBody
    public JsonData devRunTimeDif(DeviceFunctionParam functionParam){
        DeviceFunction deviceRun = new DeviceFunction();
        //设备码
        deviceRun.setDeviceCode(functionParam.getId().toString());
        //最后上传时间
        deviceRun.setLastUploadTime(new Date());
        //本次开机时长
        deviceRun.setThisDuration(Integer.parseInt(functionParam.getThisDevRunTimeDif()));
        //字符串转成日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = sdf.parse(functionParam.getRunTime());
        } catch (ParseException e) {
            return JsonData.createError("日期格式错误");
        }
        //开机时间
        deviceRun.setOnTime(date);
        //开机总时长
        deviceRun.setTotalDuration((int) Double.parseDouble(functionParam.getDevRunTimeDif()));
        DeviceFunctionVO functionVO = functionService.findByDeviceCode(functionParam.getId().toString());
        if(functionVO != null){
            deviceRun.setId(functionVO.getId());
            functionService.updateDevRun(deviceRun);
        }else{
            functionService.save(deviceRun);
        }
        return JsonData.createSuccess("数据保存成功");
    }

    /**
     * 其他图片上传
     */
    @RequestMapping(value = "/deviceInfo/devImage.do")
    @ResponseBody
    public JsonData devImageUpload(DeviceImageParam deviceImageParam)throws Exception {
        BeanValidator.check(deviceImageParam);
        JsonData<String> json = UploadUtils.uploadPic(deviceImageParam.getImage(), "627" + "/upload/devImage/image/" + deviceImageParam.getId());
        //将路径保存导数据库
        DeviceImage devImage = new DeviceImage();
        devImage.setDeviceCode(deviceImageParam.getId());
        devImage.setDeviceImagePath(json.getMsg());
        devImage.setImageUploadTime(new Date());
        devImage.setImageType(deviceImageParam.getImageType());
        deviceImageService.save(devImage);
        return JsonData.createSuccess("上传成功");
    }
}
