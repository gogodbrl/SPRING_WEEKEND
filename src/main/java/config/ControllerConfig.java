package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.infoslab.presentation.AController;
import com.infoslab.presentation.BController;
import com.infoslab.presentation.ExController;

@Configuration
public class ControllerConfig {
	//여기서 autowired를 안쓰고 bean을 써도 잘 되는 이유
	@Bean
	public ExController ExController( ) {
		return new ExController( );
	}
	
	@Bean
	public AController aController() {
		return new AController();
	}
	
	@Bean
	public BController bController() {
		return new BController();
	}
}
