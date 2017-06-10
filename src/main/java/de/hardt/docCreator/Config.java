package de.hardt.docCreator;

import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:appl.properties")
public class Config implements EnvironmentAware {

	private static final String ROOTPATH = "rootPath";
	
	/** The env. */
	private static Environment env;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.context.EnvironmentAware#setEnvironment(org.
	 * springframework.core.env.Environment)
	 */
	@Override
	public void setEnvironment(Environment environment) {
		env = environment;
	}

	public String getRootPath() {
		if (env.containsProperty(ROOTPATH)) {
			return env.getProperty(ROOTPATH);
		} else {
			return null;
		}
	}
}