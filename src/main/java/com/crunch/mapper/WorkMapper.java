package com.crunch.mapper;

import com.crunch.domain.WorkDTO;

import java.util.ArrayList;
import java.util.HashMap;

public interface WorkMapper {
    void workInsert(WorkDTO workDTO);

    ArrayList<WorkDTO> workSelectList(HashMap<String, Integer> hmap);


    int workSelectCount();

    WorkDTO workSelectByWorkID(int idx);


    void workDelete(int idx);

    void workUpdate(WorkDTO workDTO);

    ArrayList<WorkDTO> workSelectPriority();
}
