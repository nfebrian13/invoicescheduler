package com.nana.bankapp.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nana.bankapp.model.Term;
import com.nana.bankapp.services.TermService;
import com.nana.bankapp.util.AuthenticationName;

@Controller

@RequestMapping("/term")
public class TermController {

	@Autowired
	TermService ps;
	
	@Autowired
	AuthenticationName authName;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		binder.registerCustomEditor(Date.class, "createdDate", new CustomDateEditor(dateFormat, false));
		binder.registerCustomEditor(Date.class, "updatedDate", new CustomDateEditor(dateFormat, false));
	}
	
	@RequestMapping("/add")
	public String newTerm(Model model) {
		String name = authName.getLoginName();
		
		model.addAttribute("username", name);
		model.addAttribute("term", new Term());
		return "term_add_form";
	}

	/*
	@GetMapping("/list")
	public String listTerm(Model model) {
		List<Term> term = ps.getTerm();
		String name = authName.getLoginName();
		
		model.addAttribute("username", name);
		model.addAttribute("term", term);
		return "term_list";
	} */

	@GetMapping("/edit")
	public String updateTerm(@RequestParam("termId") String termId, Model model) {
		Term term = ps.getTerm(termId);
		String name = authName.getLoginName();
		
		model.addAttribute("username", name);
		model.addAttribute("term", term);
		return "term_edit_form";
	}

	@GetMapping("/delete")
	public String deleteTerm(@RequestParam("termId") String termId, Model model) {
		String name = authName.getLoginName();
		
		model.addAttribute("username", name);
		ps.deleteTerm(termId);
		return "redirect:/term/list";
	}

	@RequestMapping(value = "/saveterm", method = RequestMethod.POST)
	public String saveTerm(@Valid @ModelAttribute("term") Term term, BindingResult result, Model model) {
		String name = authName.getLoginName();
		
		if (result.hasErrors()) {
			model.addAttribute("username", name);
			return "term_add_form";
		} else {
			model.addAttribute("username", name);
			ps.saveTerm(term);
			return "redirect:/term/list";
		}
	}

	@RequestMapping(value = "/editterm", method = RequestMethod.POST)
	public String editTerm(@Valid @ModelAttribute("term") Term term, BindingResult result, Model model) {
		String name = authName.getLoginName();
		
		if (result.hasErrors()) {
			model.addAttribute("username", name);
			return "term_edit_form";
		} else {
			model.addAttribute("username", name);
			ps.editTerm(term);
			return "redirect:/term/list?edit=true";
		}
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model, Integer offset, Integer maxResults, @RequestParam(required=false) boolean edit) {
		String name = authName.getLoginName();
		
		if (edit) {
			model.addAttribute("term", ps.pageTermList(offset, maxResults, true));
		} else {
			model.addAttribute("term", ps.pageTermList(offset, maxResults, false));
		}
		
		model.addAttribute("username", name);		
		model.addAttribute("count", ps.count());
		model.addAttribute("offset", offset);
		return "term_list";
	}

}
