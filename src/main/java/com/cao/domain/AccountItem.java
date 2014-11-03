package com.cao.domain;

/**
 * Created by caoyaojun on 11/1/14.
 */
public class AccountItem {
    private Long   id;
    private String name;
    private Long   startBorrow;
    private Long   startLend;
    private Long   thisBorrow;
    private Long   thisLend;
    private Long   totalBorrow;
    private Long   totalLend;
    private Long   endBorrow;
    private Long   endLend;


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

    public Long getStartBorrow() {
        return startBorrow;
    }

    public void setStartBorrow(Long startBorrow) {
        this.startBorrow = startBorrow;
    }

    public Long getStartLend() {
        return startLend;
    }

    public void setStartLend(Long startLend) {
        this.startLend = startLend;
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

    public Long getValueById(int index) {
        switch (index) {
            case 1:
                return this.getStartBorrow();
            case 2:
                return this.getStartLend();
            case 3:
                return this.getThisBorrow();
            case 4:
                return this.getThisLend();
            case 5:
                return this.getTotalBorrow();
            case 6:
                return this.getTotalLend();
            case 7:
                return this.getEndBorrow();
            case 8:
                return this.getEndLend();
            default:
                return 0L;
        }
    }
}
