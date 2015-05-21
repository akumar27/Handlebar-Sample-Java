package com.voya.controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.jknack.handlebars.Context;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.JsonNodeValueResolver;
import com.github.jknack.handlebars.cache.HighConcurrencyTemplateCache;
import com.github.jknack.handlebars.io.FileTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;
import com.github.jknack.handlebars.springmvc.HandlebarsViewResolver;
import com.voya.helper.CustomHelper;
import com.voya.helper.MessageHelper;

/**
 * @author Amit Kumar
 *
 */
@Controller
public class HomeController {

	private @Autowired ServletContext servletContext;

	@Autowired
	private ApplicationContext appContext;

	@RequestMapping("/home")
	@ResponseBody
	public String testController(HttpServletRequest request,
			HttpServletResponse response) {
		// ModelAndView mav = new ModelAndView("main-layout");

		try {
			String value = "{\"title\": \"First Post\",\"story\": {\"intro\": \"Before the jump\",\"body\": \"After the jump\"}}";
			String path = request.getSession().getServletContext()
					.getRealPath("/WEB-INF/hbs");
			System.out.println("path: " + path);
			ObjectMapper mapper = new ObjectMapper();
			JsonNode jsonNode = mapper.readTree(value);
			TemplateLoader loader = new FileTemplateLoader(path);
			// loader.setPrefix("/WEB-INF/hbs");
			loader.setSuffix(".hbs");
			Handlebars handlebars = new Handlebars(loader);
			// Handlebars handlebars = new Handlebars();
			handlebars.with(new HighConcurrencyTemplateCache());
			Context context = Context.newBuilder(jsonNode)
					.resolver(JsonNodeValueResolver.INSTANCE).build();

			/*
			 * String output = handlebars .compileInline(
			 * "<a href='home'>Home</a><div class=\"entry\"> <h1>{{title}}</h1> {{#with story}}  <div class=\"intro\">{{{intro}}}"
			 * + "</div> <div class=\"body\">{{{body}}}</div>{{/with}}</div>")
			 * .apply(context);
			 */
			String output = handlebars.compile("test").apply(context);
			// mav.addObject("result", output);
			// mav.addObject("jsonObject: " + jsonNode);

			response.setContentType("text/html");
			// response.setCharacterEncoding("UTF-8");
			System.out.println("output: " + output);
			return output;

		}

		catch (Exception e) {
			e.printStackTrace();
		}

		return "";
	}

	@RequestMapping("/option1")
	@ResponseBody
	public String option1Controller(
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "template", required = false) String template,
			@RequestParam(value = "locale", required = false) String locale) {
		// ModelAndView mav = new ModelAndView("main-layout");
		String templateToRender = "example-module-a";
		if (template != null) {
			templateToRender = template;
		}
		String jsonFileName = "en.json";
		if ("sp".equalsIgnoreCase(locale)) {
			jsonFileName = "sp.json";
		}
		try {

			InputStream inputStream = null;
			StringBuilder sb2 = new StringBuilder();
			String line;
			try {
				inputStream = servletContext
						.getResourceAsStream("/WEB-INF/config/"
								+ templateToRender + "/" + jsonFileName);
				BufferedReader bufferedReader = new BufferedReader(
						new InputStreamReader(inputStream));

				while ((line = bufferedReader.readLine()) != null) {
					sb2.append(line);
				}
			} finally {
				if (inputStream != null) {
					inputStream.close();
				}
			}
			//option1
			String value = sb2.toString(); // "{\"title\": \"First Post\",\"story\": {\"intro\": \"Before the jump\",\"body\": \"After the jump\"}}";

			String path = request.getSession().getServletContext()
					.getRealPath("/WEB-INF/hbs/option1");
			System.out.println("path: " + path);
			ObjectMapper mapper = new ObjectMapper();
			JsonNode jsonNode = mapper.readTree(value);
			TemplateLoader loader = new FileTemplateLoader(path);
			// loader.setPrefix("/WEB-INF/hbs");
			loader.setSuffix(".hbs");
			Handlebars handlebars = new Handlebars(loader);

			handlebars.with(new HighConcurrencyTemplateCache());
			Context context = Context.newBuilder(jsonNode)
					.resolver(JsonNodeValueResolver.INSTANCE).build();
			//option1
			/*
			 * String output = handlebars .compileInline(
			 * "<a href='home'>Home</a><div class=\"entry\"> <h1>{{title}}</h1> {{#with story}}  <div class=\"intro\">{{{intro}}}"
			 * + "</div> <div class=\"body\">{{{body}}}</div>{{/with}}</div>")
			 * .apply(context);
			 */
			String output2 = handlebars.compile(templateToRender)
					.apply(context);
			String output = handlebars.compile("main-layout").apply(context);

			// mav.addObject("result", output);
			// mav.addObject("jsonObject: " + jsonNode);

			response.setContentType("text/html");
			// response.setCharacterEncoding("UTF-8");
			System.out.println("output1: " + output);
			System.out.println("output2: " + output2);
			System.out.println("value: " + jsonNode.toString());

			StringBuffer sb = new StringBuffer(output).append(output2);
			return sb.toString();

		}

		catch (Exception e) {
			e.printStackTrace();
		}

		return "";
	}

	@RequestMapping("/test")
	@ResponseBody
	public String option2Controller(
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "template", required = false) String template,
			@RequestParam(value = "locale", required = false) String locale) {
		// ModelAndView mav = new ModelAndView("main-layout");
		String templateToRender = "example-module-a";
		if (template != null) {
			templateToRender = template;
		}
		String jsonFileName = "en.json";
		if ("sp".equalsIgnoreCase(locale)) {
			jsonFileName = "sp.json";
		}
		try {
			// test
			InputStream inputStream = null;
			StringBuilder sb2 = new StringBuilder();
			String line;
			try {
				inputStream = servletContext
						.getResourceAsStream("/WEB-INF/config/"
								+ templateToRender + "/" + jsonFileName);
				BufferedReader bufferedReader = new BufferedReader(
						new InputStreamReader(inputStream));

				while ((line = bufferedReader.readLine()) != null) {
					sb2.append(line);
				}
			} finally {
				if (inputStream != null) {
					inputStream.close();
				}
			}
			//test
			String value = sb2.toString(); // "{\"title\": \"First Post\",\"story\": {\"intro\": \"Before the jump\",\"body\": \"After the jump\"}}";

			String path = request.getSession().getServletContext()
					.getRealPath("/WEB-INF/hbs");
			System.out.println("path: " + path);
			ObjectMapper mapper = new ObjectMapper();
			JsonNode jsonNode = mapper.readTree(value);
			TemplateLoader loader = new FileTemplateLoader(path);
			// loader.setPrefix("/WEB-INF/hbs");
			loader.setSuffix(".hbs");
			Handlebars handlebars = new Handlebars(loader);

			handlebars.with(new HighConcurrencyTemplateCache());
			Context context = Context.newBuilder(jsonNode)
					.resolver(JsonNodeValueResolver.INSTANCE).build();

			/*
			 * String output = handlebars .compileInline(
			 * "<a href='home'>Home</a><div class=\"entry\"> <h1>{{title}}</h1> {{#with story}}  <div class=\"intro\">{{{intro}}}"
			 * + "</div> <div class=\"body\">{{{body}}}</div>{{/with}}</div>")
			 * .apply(context);
			 */
			// String output2 =
			// handlebars.compile(templateToRender).apply(context);
		//	String output = handlebars.compile("main-layout2").apply(context);
			HandlebarsViewResolver handlebarsViewResolver = (HandlebarsViewResolver)appContext.getAutowireCapableBeanFactory().getBean("viewResolver");
			//Handlebars handlebars = handlebarsViewResolver.getHandlebars();
			System.out.println("object " + handlebarsViewResolver);
			// mav.addObject("result", output);
			// mav.addObject("jsonObject: " + jsonNode);

			response.setContentType("text/html");
			// response.setCharacterEncoding("UTF-8");
		//	System.out.println("output1: " + output);
			// System.out.println("output2: " + output2);
			System.out.println("value: " + jsonNode.toString());

			// StringBuffer sb = new StringBuffer(output).append(output2);
			return "";

		}

		catch (Exception e) {
			e.printStackTrace();
		}

		return "";
	}

	@RequestMapping(value = "/option2")
	public ModelAndView embeddedController(HttpServletRequest request,
			@RequestParam(value = "template", required = false) String template,
			@RequestParam(value = "locale", required = false) String locale) {
		ModelAndView mav = new ModelAndView("option2/main-layout2");
		try {
			
			//String templateToRender = "example-module-a";
			if (template == null) {
				template = "example-module-a";;
			}
			String jsonFileName = "en.json";
			if ("sp".equalsIgnoreCase(locale)) {
				jsonFileName = "sp.json";
			}
			
			InputStream inputStream = null;
			StringBuilder sb2 = new StringBuilder();
			String line;
			try {
				inputStream = servletContext
						.getResourceAsStream("/WEB-INF/config/"
								+ template + "/" + jsonFileName);
				BufferedReader bufferedReader = new BufferedReader(
						new InputStreamReader(inputStream));

				while ((line = bufferedReader.readLine()) != null) {
					sb2.append(line);
				}
			} finally {
				if (inputStream != null) {
					inputStream.close();
				}
			}
			
			String value = sb2.toString();

			ObjectMapper mapper = new ObjectMapper();
			JsonNode jsonNode = mapper.readTree(value);
			
			HandlebarsViewResolver handlebarsViewResolver = (HandlebarsViewResolver)appContext.getAutowireCapableBeanFactory().getBean("viewResolver");
			Handlebars handlebars = handlebarsViewResolver.getHandlebars();
			
			MessageHelper messageHelper = new MessageHelper();
			handlebars.registerHelpers(messageHelper);
			CustomHelper customHelper = new CustomHelper();
			handlebars.registerHelpers(customHelper);
			mav.addObject("config" , jsonNode);
			handlebars.with(new HighConcurrencyTemplateCache());
			mav.addObject("template", "example-module-a2");
			
		}

		catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}
}
