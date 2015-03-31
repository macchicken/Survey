# Survey
Survey 
http://localhost:10080/Survey

keytool -genkey -alias tomcat -keyalg RSA --- generate keystore
add keystoreFile="the path of .keystore file" keystorePass="the password entered when create the .keystore file"
in https setting part of servers.xml that is in the tomcate localserver config folder
add following in web.xml(dd)
<user-data-constraint>
		<transport-guarantee>CONFIDENTIAL</transport-guarantee>
	</user-data-constraint>

run the surveydb.sql in the Db folder to initialise some users data