package com.bacode.hi.controller;

import java.rmi.RemoteException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.bacode.hi.service.ItestService;
import com.bacode.hi.vo.TestVO;

@RestController
public class SampleController {

	@Autowired
	ItestService service;
	
	 @RequestMapping("/2")
	    public String root_test() throws Exception{
	        return "Hello Root(/)";
	    }
	 
	    @RequestMapping("/demo2")
	    public @ResponseBody String demo_test() {
	        return "Hello demo(/demo)";
	    }
	    
	    @RequestMapping("/list") // URL 주소
		public ModelAndView list(Model model) {

	    	System.out.println("list===========================>1");
			List<TestVO> vo = service.getTestVoList();
	    	System.out.println("list===========================>1"+vo.toString());
			
			model.addAttribute("id", vo.get(0).getId());
			model.addAttribute("name", vo.get(0).getName());
			ModelAndView view = new ModelAndView("list");
			
			return view; // JSP 파일명
		}


}
