package system.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

public class Device implements Serializable {
    private Integer id;

    @NotNull
    private Integer groupId;
    @NotBlank
    private String deviceName;

    private String deviceCode;
    @NotBlank
    private String deviceAddress;

    private String deviceLatitude;

    private Date buildTime;
    private Byte deviceMap;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column device.id
     *
     * @param id the value for device.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column device.group_id
     *
     * @return the value of device.group_id
     *
     * @mbg.generated
     */
    public Integer getGroupId() {
        return groupId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column device.group_id
     *
     * @param groupId the value for device.group_id
     *
     * @mbg.generated
     */
    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column device.device_name
     *
     * @return the value of device.device_name
     *
     * @mbg.generated
     */
    public String getDeviceName() {
        return deviceName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column device.device_name
     *
     * @param deviceName the value for device.device_name
     *
     * @mbg.generated
     */
    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName == null ? null : deviceName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column device.device_code
     *
     * @return the value of device.device_code
     *
     * @mbg.generated
     */
    public String getDeviceCode() {
        return deviceCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column device.device_code
     *
     * @param deviceCode the value for device.device_code
     *
     * @mbg.generated
     */
    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode == null ? null : deviceCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column device.device_address
     *
     * @return the value of device.device_address
     *
     * @mbg.generated
     */
    public String getDeviceAddress() {
        return deviceAddress;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column device.device_address
     *
     * @param deviceAddress the value for device.device_address
     *
     * @mbg.generated
     */
    public void setDeviceAddress(String deviceAddress) {
        this.deviceAddress = deviceAddress == null ? null : deviceAddress.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column device.device_latitude
     *
     * @return the value of device.device_latitude
     *
     * @mbg.generated
     */
    public String getDeviceLatitude() {
        return deviceLatitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column device.device_latitude
     *
     * @param deviceLatitude the value for device.device_latitude
     *
     * @mbg.generated
     */
    public void setDeviceLatitude(String deviceLatitude) {
        this.deviceLatitude = deviceLatitude == null ? null : deviceLatitude.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column device.build_time
     *
     * @return the value of device.build_time
     *
     * @mbg.generated
     */
    public Date getBuildTime() {
        return buildTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column device.build_time
     *
     * @param buildTime the value for device.build_time
     *
     * @mbg.generated
     */
    public void setBuildTime(Date buildTime) {
        this.buildTime = buildTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column device.device_map
     *
     * @return the value of device.device_map
     *
     * @mbg.generated
     */
    public Byte getDeviceMap() {
        return deviceMap;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column device.device_map
     *
     * @param deviceMap the value for device.device_map
     *
     * @mbg.generated
     */
    public void setDeviceMap(Byte deviceMap) {
        this.deviceMap = deviceMap;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", groupId=").append(groupId);
        sb.append(", deviceName=").append(deviceName);
        sb.append(", deviceCode=").append(deviceCode);
        sb.append(", deviceAddress=").append(deviceAddress);
        sb.append(", deviceLatitude=").append(deviceLatitude);
        sb.append(", buildTime=").append(buildTime);
        sb.append(", deviceMap=").append(deviceMap);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}