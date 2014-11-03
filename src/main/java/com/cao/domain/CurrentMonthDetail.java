package com.cao.domain;

/**
 * Created by caoyaojun on 11/1/14.
 */
public class CurrentMonthDetail {
    private Long   id;
    private String name;
    private Long   thisBorrow;
    private Long   thisLend;
    private Long   totalBorrow;
    private Long   totalLend;
    private Long   endBorrow;
    private Long   endLend;

    public Long getValueById(int index) {
        switch (index) {
            case 1:
                return this.getThisBorrow();
            case 2:
                return this.getThisLend();
            case 3:
                return this.getTotalBorrow();
            case 4:
                return this.getTotalLend();
            case 5:
                return this.getEndBorrow();
            case 6:
                return this.getEndLend();
            default:
                return 0L;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getThisBorrow() {
        return thisBorrow;
    }

    public void setThisBorrow(Long thisBorrow) {
        this.thisBorrow = thisBorrow;
    }

    public Long getThisLend() {
        return thisLend;
    }

    public void setThisLend(Long thisLend) {
        this.thisLend = thisLend;
    }

    public Long getTotalBorrow() {
        return totalBorrow;
    }

    public void setTotalBorrow(Long totalBorrow) {
        this.totalBorrow = totalBorrow;
    }

    public Long getTotalLend() {
        return totalLend;
    }

    public void setTotalLend(Long totalLend) {
        this.totalLend = totalLend;
    }

    public Long getEndBorrow() {
        return endBorrow;
    }

    public void setEndBorrow(Long endBorrow) {
        this.endBorrow = endBorrow;
    }

    public Long getEndLend() {
        return endLend;
    }

    public void setEndLend(Long endLend) {
        this.endLend = endLend;
    }
}
