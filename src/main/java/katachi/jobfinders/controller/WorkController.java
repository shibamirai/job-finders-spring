package katachi.jobfinders.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import katachi.jobfinders.domain.model.JobFinder;
import katachi.jobfinders.domain.service.JobFinderService;
import katachi.jobfinders.domain.service.WorkService;
import katachi.jobfinders.exception.NotFoundException;
import katachi.jobfinders.form.WorkForm;

@Controller
@RequestMapping("/admin/job-finders/{id}/works")
public class WorkController {

	@Autowired
	JobFinderService jobFinderService;

	@Autowired
	WorkService workService;

	@GetMapping("/create")
	public String create(@PathVariable("id") int id, Model model) {
		Optional<JobFinder> jobFinder = jobFinderService.getById(id);
		model.addAttribute(jobFinder.orElseThrow(NotFoundException::new));

		if (!model.containsAttribute("workForm")) {
			model.addAttribute(new WorkForm());
		}
		return "admin/job-finders/edit";
	}

	@PostMapping
	public String store(@PathVariable("id") int id, @ModelAttribute @Validated WorkForm form, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "workForm", bindingResult);
			redirectAttributes.addFlashAttribute(form);
			return "redirect:/admin/job-finders/" + id + "/works/create";
		}

		workService.register(form.toWork(id));

		redirectAttributes.addFlashAttribute("success", "ポートフォリオを追加しました！");
		return "redirect:/admin/job-finders/" + id + "/edit";
	}

	@GetMapping("/{workId}/edit")
	public String edit(@PathVariable("id") int id, @PathVariable("workId") int workId, Model model) {
		Optional<JobFinder> jobFinderOpt = jobFinderService.getById(id);
		JobFinder jobFinder = jobFinderOpt.orElseThrow(NotFoundException::new);
		model.addAttribute(jobFinder);

		if (!model.containsAttribute("workForm")) {
			model.addAttribute(new WorkForm(
				jobFinder.getWork(workId).orElseThrow(NotFoundException::new)
			));
		}
		return "admin/job-finders/edit";
	}

	@PatchMapping("/{workId}")
	public String update(@PathVariable int id, @PathVariable("workId") int workId, @ModelAttribute @Validated WorkForm form, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			Optional<JobFinder> jobFinder = jobFinderService.getById(id);
			redirectAttributes.addFlashAttribute(jobFinder.orElseThrow(NotFoundException::new));
			redirectAttributes.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "workForm", bindingResult);
			redirectAttributes.addFlashAttribute(form);
			return "redirect:/admin/job-finders/" + id + "/works/" + workId + "/edit";
		}

		workService.register(form.toWork(id));

		redirectAttributes.addFlashAttribute("success", "ポートフォリオを更新しました");
		return "redirect:/admin/job-finders/" + id + "/edit";
	}

	@DeleteMapping("/{workId}")
	public String destroy(@PathVariable int id, @PathVariable("workId") int workId, Model model, RedirectAttributes redirectAttributes) {
		Optional<JobFinder> jobFinder = jobFinderService.getById(id);
		model.addAttribute(jobFinder.orElseThrow(NotFoundException::new));

		workService.delete(workId);

		redirectAttributes.addFlashAttribute("success", "ポートフォリオを削除しました");
		return "redirect:/admin/job-finders/" + id + "/edit";
		
	}
}
