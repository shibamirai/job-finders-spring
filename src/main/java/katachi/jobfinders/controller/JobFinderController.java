package katachi.jobfinders.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import katachi.jobfinders.domain.model.EmploymentPattern;
import katachi.jobfinders.domain.model.Gender;
import katachi.jobfinders.domain.model.Handicap;
import katachi.jobfinders.domain.model.JobFinder;
import katachi.jobfinders.domain.model.JobFinderSearchParam;
import katachi.jobfinders.domain.model.Paginator;
import katachi.jobfinders.domain.service.JobFinderService;
import katachi.jobfinders.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/")
public class JobFinderController {

	@Autowired
	JobFinderService jobFinderService;

	@GetMapping
	public String home() {
		return "redirect:/job-finders";
	}

	@GetMapping("job-finders")
	public String index(Model model, @RequestParam Optional<String> search, @RequestParam Optional<Integer> page) {
		if (page.orElse(1) < 1) {
			log.warn("Invalid parameter. Redirect to /job-finders with no parameter.");
			return "redirect:/job-finders";
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
				"/job-finders",
				query);

		model.addAttribute("jobFinders", paginator);

		return "job-finders/index";
	}

	@GetMapping("job-finders/{id}")
	public String show(@PathVariable("id") int id, Model model) {
		Optional<JobFinder> jobFinder = jobFinderService.getById(id);
		model.addAttribute("jobFinder", jobFinder.orElseThrow(NotFoundException::new));

		return "job-finders/show";
	}

	@GetMapping("statistics")
	public String statistics(Model model) {
		List<JobFinder> jobFinders = jobFinderService.getAll();
		model.addAttribute("jobFinders", jobFinders);

		model.addAttribute("genders",
			Stream.of(Gender.values())
				.map(e -> e.getLabel())
				.collect(Collectors.toList())
		);
		model.addAttribute("handicaps",
				Stream.of(Handicap.values())
					.map(e -> e.getLabel())
					.collect(Collectors.toList())
		);
		model.addAttribute("employmentPatterns",
				Stream.of(EmploymentPattern.values())
					.map(e -> e.getLabel())
					.collect(Collectors.toList())
		);

		return "statistics";
	}
}
