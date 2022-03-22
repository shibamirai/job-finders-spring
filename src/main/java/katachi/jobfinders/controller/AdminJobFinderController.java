package katachi.jobfinders.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import katachi.jobfinders.domain.model.EmploymentPattern;
import katachi.jobfinders.domain.model.Gender;
import katachi.jobfinders.domain.model.Handicap;
import katachi.jobfinders.domain.model.JobFinder;
import katachi.jobfinders.domain.model.JobFinderSearchParam;
import katachi.jobfinders.domain.model.Paginator;
import katachi.jobfinders.domain.service.JobFinderService;
import katachi.jobfinders.exception.NotFoundException;
import katachi.jobfinders.form.JobFinderForm;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin/job-finders")
public class AdminJobFinderController {

	@Autowired
	JobFinderService jobFinderService;

	@ModelAttribute("avatars")
	String[] avatars() throws IOException {
		File dir = new ClassPathResource("static/avatar").getFile();

		ArrayList<File> avatars = new ArrayList<File>();
		Collections.addAll(
			avatars,
			dir.listFiles(
				file -> file.getName().endsWith(".jpg") ? true : false
			)
		);

		return avatars.stream()
				.map(file -> file.getName())
				.toArray(String[]::new);
	}

	@ModelAttribute("employmentPatterns")
	EmploymentPattern[] employmentPatterns() {
		return EmploymentPattern.values();
	}

	@ModelAttribute("genders")
	Gender[] genders() {
		return Gender.values();
	}

	@ModelAttribute("handicaps")
	Handicap[] handicaps() {
		return Handicap.values();
	}

	@GetMapping
	public String index(Model model, @RequestParam Optional<String> search, @RequestParam Optional<Integer> page) {
		if (page.orElse(1) < 1) {
			log.warn("Invalid parameter. Redirect to /job-finders with no parameter.");
			return "redirect:/admin/job-finders";
		}

		JobFinderSearchParam param = new JobFinderSearchParam();
		search.ifPresent(v -> param.setSearch(v));
		page.ifPresent(v -> param.setPage(v));
		List<JobFinder> jobFinders = jobFinderService.pagenate(param);

		ArrayList<String> query = new ArrayList<>();
		search.ifPresent(v -> query.add("search=" + v));

		Paginator<JobFinder> paginator = new Paginator<JobFinder>(
				jobFinders,
				jobFinderService.getCount(param),
				param.getLimit(),
				param.getPage(),
				"/admin/job-finders",
				query);

		model.addAttribute("jobFinders", paginator);
		model.addAttribute("search", search.orElse(""));

		return "admin/job-finders/index";
	}

	@GetMapping("/create")
	public String show(Model model) {
		if (!model.containsAttribute("jobFinderForm")) {
			model.addAttribute(new JobFinderForm());
		}
		return "admin/job-finders/create";
	}

	@PostMapping
	public String store(@ModelAttribute @Validated JobFinderForm form, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "jobFinderForm", bindingResult);
			redirectAttributes.addFlashAttribute(form);
			return "redirect:/admin/job-finders/create";
		}

		jobFinderService.register(form.toJobFinder());

		redirectAttributes.addFlashAttribute("success", form.getName() + "さんを登録しました");
		return "redirect:/admin/job-finders";
	}

	@GetMapping("/{id}/edit")
	public String edit(@PathVariable int id, Model model) {
		Optional<JobFinder> jobFinder = jobFinderService.getById(id);
		model.addAttribute(jobFinder.orElseThrow(NotFoundException::new));
		if (!model.containsAttribute("jobFinderForm")) {
			model.addAttribute(new JobFinderForm(jobFinder.orElseThrow(NotFoundException::new)));
		}
		return "admin/job-finders/edit";
	}
}
