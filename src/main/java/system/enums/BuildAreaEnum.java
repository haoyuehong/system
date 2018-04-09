package system.enums;

/**
 * @Author: mol
 * @Description:
 * @Date: create in 15:06 2018/3/26
 */
public enum BuildAreaEnum {

    SCHOOL("school","学校"),
    AREA("area","地区"),
    ;

    private String type;
    private String desc;

    BuildAreaEnum(String type,String desc){
        this.type = type;
        this.desc = desc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
