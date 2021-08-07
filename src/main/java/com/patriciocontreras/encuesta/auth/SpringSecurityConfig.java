package com.patriciocontreras.encuesta.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableGlobalMethodSecurity(securedEnabled = true)
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	
	// al inyectar la interface va abuscar una implementacion concreta que sea de tipo
		//  UserDetailsService y como hay una sola inyecta UsuarioService ,
		// se inyecta mediante genericos
		@Autowired
		private UserDetailsService usuarioService;
		
		// la abnotacion @Bean permite que sea compartido por las clases de Spring
		// se intectara con autowired ya que estara almacenado en el contenedor de spring 
		@Bean
		public BCryptPasswordEncoder passwordEncoder() {
			// se retorna una instacia de BCryptPasswordEncoder
			// luego hay que guardar el objeto que devuelve en uncomponente de spring en un Bean
			
			return new BCryptPasswordEncoder();
		}
		// lo siguiente es registrar en el autentication manager este servicio para 
		// autenticarpara eso sobreescribimos un metodo
	   // Va con @Autowired porque se va inyectar por parametro AuthenticationManagerBuilder auth
	 //el metodo configure no es necesario

		@Override
		@Autowired
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			// ahora con auth se registra UserDetailsService
			//tambien se va a rncriptar la contraseña
			auth.userDetailsService(this.usuarioService).passwordEncoder(passwordEncoder());
		}

		@Bean("authenticationManager")
		@Override
		protected AuthenticationManager authenticationManager() throws Exception {
			return super.authenticationManager();
		}
		
		// Estos son reglas para endpoints por el lado de Spring
		//Por spring hay que deshabilitar CSRF: Cross-site request forgery o falsificacion de peticion
		//en sitios cruzados es para evitar cualquier ataque Cross-site request forgery es para proteger
		// nuestro formulario a traves de un token como en nuestro caso se trabaja separado el frontend con 
		// angular no es necesario tenerlo habilitado esta proteccion de formulario  en spring en el servidor
		// con .and() se vuelve a la configuracion de HttpSecurity
		// con esto deshabilitamos los estados de las sessiones ya que se trabajara con token
		@Override
		public void configure(HttpSecurity http) throws Exception {
			
			http.authorizeRequests()
			.anyRequest().authenticated()
			.and()
			.csrf().disable()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		}
}
