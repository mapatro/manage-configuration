package org.mule.modules.springcloudconfig.config;

import org.mule.api.annotations.Configurable;
import org.mule.api.annotations.components.Configuration;
import org.mule.api.annotations.display.Password;
import org.mule.api.annotations.display.Placement;
import org.mule.api.annotations.param.Default;
import org.mule.api.annotations.param.Optional;

@Configuration(friendlyName = "Spring Cloud Configuration")
public class ConnectorConfig {
	
	/**
	 * The base URL where the Spring Cloud Config API is hosted.
	 */
	@Configurable
	@Default("http://localhost:8888/")
	private String configServerBaseUrl;
	
	/**
	 * The name of the application whose properties will be read. If not specified, mule app name will be
	 * used.
	 */
	@Configurable
	@Optional
	private String applicationName;
	
	/**
	 * The profiles to take into consideration. This is a comma-separated list. If empty, this module
	 * should try to locate spring profiles.
	 */
	@Configurable
	@Optional
	private String profiles;
	
	/**
	 * The tag for the configuration. Useful for versioning.
	 */
	@Configurable
	@Optional
	private String label;
	
	@Configurable
	@Optional
	@Placement(group = "Basic Authentication")
	private String userName;
	
	@Configurable
	@Optional
	@Placement(group = "Basic Authentication")
	@Password
	private String password;
	

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public String getProfiles() {
		return profiles;
	}

	public void setProfiles(String profiles) {
		this.profiles = profiles;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getConfigServerBaseUrl() {
		return configServerBaseUrl;
	}

	public void setConfigServerBaseUrl(String configServerBaseUrl) {
		this.configServerBaseUrl = configServerBaseUrl;
	}
	
	public void setUserName(String userName){
		this.userName = userName;
	}
	public String getUserName(){
		return userName;
	}
	public void setPassword(String password){
		this.password = password;
	}
	public String getPassword(){
		return password;
	}

	@Override
	public String toString() {
		return "ConnectorConfig [configServerBaseUrl=" + configServerBaseUrl + ", applicationName=" + applicationName
				+ ", profiles=" + profiles + ", label=" + label + "]";
	}
	
	
}