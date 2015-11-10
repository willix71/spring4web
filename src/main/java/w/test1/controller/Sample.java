package w.test1.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Sample {

	private static final Logger LOGGER = LoggerFactory.getLogger(Sample.class);
	
	public Sample() {
		LOGGER.info("Sample initialized");
	}
	
	@RequestMapping("/helloWorld")
	public ModelAndView helloWorld() {
		LOGGER.info("Sample called");
		
		String message = "world";
		return new ModelAndView("sample","message", message);
	}
}
