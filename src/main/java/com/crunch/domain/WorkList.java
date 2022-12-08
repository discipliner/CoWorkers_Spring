package com.crunch.domain;//package com.crunch.domain;
//

import lombok.Data;

import java.util.ArrayList;

//조우철
@Data
public class WorkList {
    private ArrayList<WorkDTO> list = new ArrayList<>();
    private int pageSize = 10;
    private int totalCount = 0;
    private int totalPage = 0;
    private int currentPage = 1;
    private int startNo = 0;
    private int endNo = 0;
    private int startPage = 0;
    private int endPage = 0;


    public void WorkList(int pageSize, int totalCount, int currentPage) {
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.currentPage = currentPage;
        calculator();
    }

    private void calculator() {
        totalPage = (totalCount - 1) / pageSize + 1;
        currentPage = Math.min(currentPage, totalPage);
        // oracle은 select sql 명령 실행결과 인덱스 값이 1부터 시작되므로 mysql에 사용했던 계산식에 1을
        // 더해서 사용해야 한다.
        startNo = (currentPage - 1) * pageSize + 1;
        endNo = startNo + pageSize - 1;
        endNo = Math.min(endNo, totalCount);
        startPage = (currentPage - 1) / 10 * 10 + 1;
        endPage = startPage + 9;
        endPage = Math.min(endPage, totalPage);
    }


}
