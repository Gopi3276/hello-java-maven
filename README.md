# hello-java-maven #
##
## Overview
This guide explains how Jenkins was set up inside a Docker container, configured to run a Maven build from a GitHub repository, and how the generated build artifact (`target` folder) was retrieved to the local machine.

---

## Steps Followed

1. **Run Jenkins in a Docker Container**  
   - A Docker container was created from the official Jenkins LTS image.  
   - The Jenkins home directory inside the container was mapped to a local folder to persist Jenkins configuration and job data.  
   - Resource limits were applied to ensure the container used a fixed amount of memory.

2. **Access and Configure the Container**  
   - Connected to the running container's terminal.  
   - Installed Bash for easier shell usage inside the container.

3. **Retrieve Jenkins Admin Password**  
   - Retrieved the initial admin password from the Jenkins container to log into the web interface.

4. **Install Required Jenkins Plugins**  
   - Installed the necessary plugins to work with Java and Maven builds.

5. **Set Up Java and Maven Inside Jenkins**  
   - Installed Java 8 and Maven 3.8 inside the Jenkins container.  
   - Configured Maven in Jenkins' "Global Tool Configuration."

6. **Create a Jenkins Job**  
   - Created a Freestyle project named **HelloWorld**.  
   - Configured the job to pull source code from a GitHub repository.  
   - Added a build step to invoke Maven goals for packaging the project.

7. **Run the Build**  
   - Triggered the build to compile and package the Java application using Maven.  
   - Jenkins generated the `target` folder containing the build artifacts.

8. **Copy Build Artifacts to Local Machine**  
   - Copied the generated `target` folder from the Jenkins container to a local directory for further use.

---

## Dockerfile Configuration

1. Use tomcat:10.1-jdk17 image
2. Copy hello-1.0.war file from target to /usr/local/tomcat/webapps/
3. Expose the port 8080
