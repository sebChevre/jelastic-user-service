package ch.sebooom.jelastic.usersservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Locale;
import java.util.stream.Collectors;

@Slf4j
@SpringBootApplication
public class UsersServiceApplication {

	private static final String LINE = "***********************************************************************";


	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(UsersServiceApplication.class);
		
		Environment env = app.run(args).getEnvironment();

		logInitApplicationContext(env);
	}

	private static void logInitApplicationContext(Environment env) {

		String externalAdress;

		try {
			externalAdress = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			externalAdress = "Undefined";
		}

		log.info(LINE);
		log.info("            *** Application context configuration ***                   ");
		log.info(LINE);

		log.info("Application '{}' is running!", env.getProperty("spring.application.name"));

		String contextPat = env.getProperty("server.servlet.context-path");
		log.info("Local      : localhost:{}/{}", env.getProperty("server.port"), (contextPat!=null) ? contextPat : "");
		log.info("External  : {}:{} ", externalAdress, env.getProperty("server.port"));
		log.info("Profile(s) : {} ", Arrays.stream(env.getActiveProfiles()).collect(Collectors.joining(",")));

		String httpProxyHost = System.getProperties().getProperty("http.proxyHost");
		if(httpProxyHost != null){
			log.info("HTTP Proxy : {}:{} ", System.getProperties().getProperty("http.proxyHost"),
					 System.getProperties().getProperty("http.proxyPort"));
		}

		String httpsProxyHost = System.getProperties().getProperty("https.proxyHost");
		if(httpsProxyHost != null){
			log.info("HTTPS Proxy: {}:{} ", System.getProperties().getProperty("https.proxyHost"),
					 System.getProperties().getProperty("https.proxyPort"));
		}


		log.info("Non Proxy Host:   {} ", System.getProperties().getProperty("http.nonProxyHosts"));
		log.info("Default Locale:   {}", Locale.getDefault());
		log.info("Default Charset:  {}", Charset.defaultCharset());
		log.info("file.encoding;    {}", System.getProperty("file.encoding"));
		log.info("sun.jnu.encoding: {}", System.getProperty("sun.jnu.encoding"));
		log.info("Default Encoding: {}", getEncoding());
		log.info(LINE);
	}


	private static String getEncoding() {
		final byte[] bytes = {'D'};
		final InputStream inputStream = new ByteArrayInputStream(bytes);
		final InputStreamReader reader = new InputStreamReader(inputStream);
		return reader.getEncoding();
	}

}
