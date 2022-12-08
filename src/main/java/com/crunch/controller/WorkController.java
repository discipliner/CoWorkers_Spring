package com.crunch.controller;

import com.crunch.domain.WorkDTO;
import com.crunch.domain.WorkList;
import com.crunch.mapper.WorkMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;

@Controller
@Slf4j
public class WorkController {

    @Autowired
    private SqlSession sqlSession;
    @RequestMapping(value = "/work")
    public String work(HttpServletRequest request, Model model, WorkList workList, WorkDTO workDTO) {
        log.info("work()");
        WorkMapper mapper = sqlSession.getMapper(WorkMapper.class);

        int pageSize = 10;
        int currentPage = 1;
        try {
            currentPage = Integer.parseInt(request.getParameter("currentPage"));
        } catch (Exception e) {
        }
        int totalCount = mapper.workSelectCount();
        workList.setTotalCount(totalCount);
        workList.WorkList(pageSize, totalCount ,currentPage);

        HashMap<String, Integer> hmap = new HashMap<>();
        hmap.put("startNo", workList.getStartNo());
        hmap.put("endNo", workList.getEndNo());
        workList.setList(mapper.workSelectList(hmap));
        ArrayList<WorkDTO> priority = mapper.workSelectPriority();
        model.addAttribute("priority", priority);
        model.addAttribute("workList", workList);


        return "work/work";
    }

    @RequestMapping(value = "/workInsert")
    public String workInsert() {
        log.info("workInsert()");


        return "work/workInsert";
    }
    @RequestMapping(value = "/workInsertOK")
    public String workInsertOK(WorkDTO workDTO) {
        log.info("workInsertOK()");
        WorkMapper mapper = sqlSession.getMapper(WorkMapper.class);
        mapper.workInsert(workDTO);
        return "redirect:work";
    }

    @RequestMapping(value = "/workUpdate")
    public String workUpdate(HttpServletRequest request, Model model, WorkDTO workDTO) {
        log.info("workUpdate()");
        WorkMapper mapper = sqlSession.getMapper(WorkMapper.class);
        log.info("workUpdate() : "+ mapper);
        workDTO = mapper.workSelectByWorkID(Integer.parseInt(request.getParameter("workID")));
        model.addAttribute("dto", workDTO);
        model.addAttribute("currentPage", request.getParameter("currentPage"));
        model.addAttribute("enter", "\r\n");

        return "work/workUpdate";
    }

    @RequestMapping(value = "/workUpdateOK")
    public String workUpdateOK(HttpServletRequest request, WorkDTO workDTO, Model model) {
        log.info("workUpdateOK()");
        WorkMapper mapper = sqlSession.getMapper(WorkMapper.class);
        log.info("workDTO: " + workDTO);
        mapper.workUpdate(workDTO);
        int idx = Integer.parseInt((request.getParameter("workID")));

        return "redirect:work?currentPage=" + request.getParameter("currentPage");
    }

    @RequestMapping(value = "/workDelete")
    public String workDelete(HttpServletRequest request, Model model) {
        log.info("workDelete()");
        WorkMapper mapper = sqlSession.getMapper(WorkMapper.class);
        int idx = Integer.parseInt(request.getParameter("workID"));
        mapper.workDelete(idx);
        model.addAttribute("currentPage", request.getParameter("currentPage"));
        return "redirect:work";
    }

    @RequestMapping(value = "/workView")
    public String workView(HttpServletRequest request, Model model) {
        log.info("workView()");
        WorkMapper mapper = sqlSession.getMapper(WorkMapper.class);
        log.info("idx: "+request.getParameter("workID"));
        int idx = Integer.parseInt(request.getParameter("workID"));
        WorkDTO workDTO = mapper.workSelectByWorkID(idx);
        model.addAttribute("dto", workDTO);
        model.addAttribute("currentPage", request.getParameter("currentPage"));
        model.addAttribute("enter", "\r\n");
        log.info("workDTO : " + workDTO);

        return "work/workView";
    }


}
