package system.param;

/**
 * @Author: mol
 * @Description:
 * @Date: create in 9:55 2018/3/26
 */
public class PageNo {
    private Integer page = 1;
    private Integer rows = 10;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }
}
