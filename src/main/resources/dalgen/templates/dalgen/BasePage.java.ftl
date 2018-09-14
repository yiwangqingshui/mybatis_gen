<@pp.dropOutputFile />
<#if gen.page??>
<#assign page =gen.page>
    <@pp.changeOutputFile name = "/src/main/java/${page.baseClassPath}/Page.java" />
package ${page.basePackageName};

import java.util.List;

/**
 * 分页工具类
 */
public class Page<T> {

 /**
     * 返回的数据
     */
    private List<T> datas;

    /**
     * 总条数
     */
    private int totalCount = 0;

    /**
     * 每页显示都是条
     */
    private int pageSize = 20;

    /**
     * 总页数
     */
    private int totalPageCount = 1;

    /**
     * 当前页
     */
    private int currPageNum = 1;

    /**
     *  分页查询的起始位置
     */
    private int startPosition;


    /**
     * 分页查询的结束位置
     */
    private int endPosition;

    /**
     * 查询的条件对象
     */
    private T condition;


    public List<T> getDatas() {
        return datas;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageSize() {
        if(this.pageSize <=0){
            this.pageSize=20;
        }
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPageCount() {
        int modulNo = this.totalCount % this.getPageSize();
        totalPageCount = modulNo==0?this.totalCount/this.getPageSize():this.totalCount/this.getPageSize()+1;
        return totalPageCount;
    }

    public void setTotalPageCount(int totalPageCount) {
        this.totalPageCount = totalPageCount;
    }

    public int getCurrPageNum() {
        if(this.currPageNum<=0){
            currPageNum=1;
        }
        return currPageNum;
    }

    public void setCurrPageNum(int currPageNum) {
        this.currPageNum = currPageNum;
    }

    public int getStartPosition() {
        startPosition = (this.getCurrPageNum()-1)*this.getPageSize();
        return startPosition;
    }

    public void setStartPosition(int startPosition) {
        this.startPosition = startPosition;
    }

    public int getEndPosition() {
        endPosition = this.getCurrPageNum()*this.getPageSize();
        return endPosition;
    }

    public void setEndPosition(int endPosition) {
        this.endPosition = endPosition;
    }

    public T getCondition() {
        return condition;
    }

    public void setCondition(T condition) {
        this.condition = condition;
    }
}
</#if>