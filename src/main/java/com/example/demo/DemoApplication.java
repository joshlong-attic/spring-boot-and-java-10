package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Set;

@SpringBootApplication
public class DemoApplication {

	public static class Cat {

		public String name;

		public Cat(String name) {
			this.name = name;
		}
	}

	@RestController
	public static class CatRestController {

		@GetMapping("/cats")
		Set<Cat> cats() {
			return Set.of(new Cat("Felix"), new Cat("Garfield"));
		}
	}

	@RestController
	public static class DukeRestController {

		private final String version, home;

		public DukeRestController(
				@Value("${java.version}") String version,
				@Value("${java.home}") String home) {
			this.version = version;
			this.home = home;
		}

		@GetMapping("/java")
		Map<String, String> java() {
			var msg = "hello, Java " + this.version;
			if (StringUtils.hasText(this.home)) {
				msg += " (running in " + this.home + ")";
			}
			msg += "!";
			return Map.of("message", msg);
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
