package com.deloitte.integ;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.PushCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.NoFilepatternException;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/config")
public class PropertyController {

	
	@RequestMapping(method = RequestMethod.POST)
    public String config(HttpServletRequest req,HttpServletResponse res) {
		 String application = "";
		    String profile = "";
		    String name = "";
		    String value = "";
	        application = req.getParameter("application");
		    profile = req.getParameter("profile");
		    name = req.getParameter("name");
		    value = req.getParameter("value");
	    
		String tempPath = "C:\\gitrepo\\temp\\"+System.currentTimeMillis()+"\\mule-integ-config";
		String propertiesPath = tempPath+"\\"+application+"-"+profile+".properties";
		Git git;
		try {
			git = Git.cloneRepository()
			  .setURI("https://github.houston.softwaregrp.net/Software-Transformation/mule-integ-config")
			  .setDirectory(new File(tempPath))
			  .call();
		} catch (GitAPIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		PropertiesManager pm = new PropertiesManager();
		
		Properties pros = pm.load(propertiesPath);
		pros.put(name, value);
		pm.save(propertiesPath, pros);
		try {
			Git g = Git.open(new File(tempPath));
			g.add().addFilepattern(application+"-"+profile+".properties").call();
			g.commit().setMessage("auto update").call();
			//g.push().call();
			CredentialsProvider cp = new UsernamePasswordCredentialsProvider("fc2c1089979865294d693dbbde7b3e4191a23270", "");
			PushCommand pushCommand = g.push().setCredentialsProvider(cp);
			pushCommand.setRemote( "origin" );
			//pushCommand.setRefSpecs( new RefSpec( "release_2_0_2:release_2_0_2" ) );
			pushCommand.call();
			g.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoFilepatternException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (GitAPIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*try {
			Files.delete(new File(tempPath).toPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
        return "{message:SUCCESS, description:Successfully updated property "+name+"}";
    }
}
