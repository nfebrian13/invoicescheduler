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

import com.nana.bankapp.model.Email;
import com.nana.bankapp.services.EmailService;
import com.nana.bankapp.util.AuthenticationName;

@Controller
@RequestMapping("/email")
public class EmailController {

	@Autowired
	EmailService es;

	@Autowired
	AuthenticationName authName;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		binder.registerCustomEditor(Date.class, "createdDate", new CustomDateEditor(dateFormat, false));
		binder.registerCustomEditor(Date.class, "updatedDate", new CustomDateEditor(dateFormat, false));
	}

	@RequestMapping("/add")
	public String newRcustomer(Model model) {
		String name = authName.getLoginName();
		
		model.addAttribute("username", name);
		model.addAttribute("email", new Email());
		return "email_add_form";
	}
	
	/*
	@GetMapping("/list")
	public String listEmail(Model model) {
		String name = authName.getLoginName();
		List<Email> email = es.getEmail();
		
		model.addAttribute("username", name);
		model.addAttribute("email", email);
		return "email_list";
	}*/

	@GetMapping("/edit")
	public String updateEmail(@RequestParam("emailId") String emailId, Model model) {
		Email email = es.getEmail(emailId);
		String name = authName.getLoginName();
		
		model.addAttribute("username", name);
		model.addAttribute("email", email);
		return "email_edit_form";
	}

	@GetMapping("/delete")
	public String deleteEmail(@RequestParam("emailId") String emailId, Model model) {
		String name = authName.getLoginName();
		
		model.addAttribute("username", name);
		es.deleteEmail(emailId);
		return "redirect:/email/list";
	}


	@RequestMapping(value = "/saveemail", method = RequestMethod.POST)
	public String saveEmail(@Valid @ModelAttribute("email") Email email, BindingResult result, Model model) {
		String name = authName.getLoginName();
		
		if (result.hasErrors()) {
			model.addAttribute("username", name);
			return "email_add_form";
		} else {
			model.addAttribute("username", name);
			es.saveEmail(email);
			return "redirect:/email/list";
		}
	}
	
	@RequestMapping(value = "/editemail", method = RequestMethod.POST)
	public String editEmail(@Valid @ModelAttribute("email") Email email, BindingResult result,  Model model) {
		String name = authName.getLoginName();
		
		if (result.hasErrors()) {
			model.addAttribute("username", name);
			return "email_edit_form";
		} else {
			model.addAttribute("username", name);
			es.editEmail(email);
			return "redirect:/email/list?edit=true";
		}
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model, Integer offset, Integer maxResults,  @RequestParam(required=false) boolean edit) {
		String name = authName.getLoginName();
		
		if (edit) {
			model.addAttribute("email", es.pageEmailList(offset, maxResults, true));
		} else {
			model.addAttribute("email", es.pageEmailList(offset, maxResults,false));
		}
		
		model.addAttribute("username", name);
		model.addAttribute("count", es.count());
		model.addAttribute("offset", offset);
		return "email_list";
	}

	
}
