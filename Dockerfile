FROM tomcat:10.1-jdk17-temurin

# Remove the default ROOT app (optional but common)
RUN rm -rf /usr/local/tomcat/webapps/ROOT

# Copy your WAR file to Tomcat's webapps directory
COPY target/SpringBoot-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/root.war
