package com.bacode.hi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.bacode.hi.service.member.IMemberService;
import com.bacode.hi.vo.MemberVO;

@RestController
public class MemberController {
	
	@Autowired
	IMemberService service;

	 @RequestMapping("/member") // URL 주소
		public ModelAndView list(Model model) {

	    	System.out.println("list===========================>1");
			List<MemberVO> memberList = service.selectAllMember();
			MemberVO member = service.selectMember();
			
	    	System.out.println("list===========================>1"+memberList.toString());
	    	
			
	    	model.addAttribute("memberList",memberList);
			model.addAttribute("member",member);
	    	System.out.println("member===================???>"+member.getMem_name());
			
			
			ModelAndView view = new ModelAndView("memberList");// JSP 파일명
			
			return view; 
		}
	 
	 @RequestMapping("/ajax")
	 @ResponseBody
	 public MemberVO member(Model model, @RequestParam String mem_name) {
		 	System.out.println("ajax start");
		 	
		 	MemberVO vo = new MemberVO();
		 	
		 	 vo = service.selectOne(mem_name);
		 	
		 	
		 	if(vo==null) {
		 		
		 		vo = new MemberVO();
		 		vo.setMem_name("없는이름");
		 		vo.setMem_age("없음");
		 		vo.setMem_add("없다");
		 	}
		 	
		 	System.out.println("vo값 이름"+ vo.getMem_name()+"나이"+vo.getMem_age());
		 	System.out.println("ajax end");
		 	
		 
		 return vo;
	 }
	 
	 
	 
	 @RequestMapping("/insertMember")
	 public String insertMember(MemberVO memberInfo) {
		 System.out.println("====insert====");
		 service.insertMember(memberInfo);
		 
		 return "redirect:/member";
	 }
}
