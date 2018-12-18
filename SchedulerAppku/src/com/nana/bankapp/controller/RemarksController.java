package com.nana.bankapp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nana.bankapp.model.Remarks;
import com.nana.bankapp.services.RemarksService;

@Controller
@RequestMapping("/remark")
public class RemarksController {

	@Autowired
	RemarksService ps;

	@RequestMapping("/add")
	public String newRemarks(Model model) {
		model.addAttribute("remarks", new Remarks());
		return "remarks_add_form";
	}

	@GetMapping("/list")
	public String listRemarks(Model model) {
		List<Remarks> remarks = ps.getRemarks();
		model.addAttribute("remarks", remarks);
		return "remarks_list";
	}

	@GetMapping("/edit")
	public String updateRemarks(@RequestParam("remarksId") String remarksId, Model model) {
		Remarks remarks = ps.getRemarks(remarksId);
		model.addAttribute("remarks", remarks);
		return "remarks_edit_form";
	}

	@GetMapping("/delete")
	public String deleteRemarks(@RequestParam("remarksId") String remarksId, Model model) {
		ps.deleteRemarks(remarksId);
		return "redirect:/remark/list";
	}

	@RequestMapping(value = "/saveremarks", method = RequestMethod.POST)
	public String saveRemarks(@Valid @ModelAttribute("remarks") Remarks remarks, BindingResult result) {
		if (result.hasErrors()) {
			return "remarks_add_form";
		} else {
			ps.saveRemarks(remarks);
			return "redirect:/remark/list";
		}
	}

	@RequestMapping(value = "/editremarks", method = RequestMethod.POST)
	public String editRemarks(@Valid @ModelAttribute("remarks") Remarks remarks, BindingResult result) {
		if (result.hasErrors()) {
			return "remarks_edit_form";
		} else {
			ps.editRemarks(remarks);
			return "redirect:/remark/list";
		}
	}

}
