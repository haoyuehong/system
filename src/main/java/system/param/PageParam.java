package system.param;

/**
 * @Author: mol
 * @Description:
 * @Date: create in 10:59 2018/3/14
 */
public class PageParam {

    /**
     * 当前页，默认为第一页
     */
    private Integer page = 1;

    /**
     * 页大小，默认为10
     */
    private Integer rows;

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
