# Survey
Survey
keytool -genkey -alias tomcat -keyalg RSA --- generate keystore
add keystoreFile="/Users/loiane/.keystore" keystorePass="password" in https setting part
add following in web.xml(dd)
<user-data-constraint>
		<transport-guarantee>CONFIDENTIAL</transport-guarantee>
	</user-data-constraint>