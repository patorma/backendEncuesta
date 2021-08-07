package com.patriciocontreras.encuesta.auth;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;




@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
	
	// Este es el servidor de autorizacion
	// aca inyectamos el bean de la clase SpringSecurityConfig llamado
	// passwordEncoder()
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	// Se debe inyectar el AuthenticationManager
		// AutorizationServer lo use en el login
		// con @Qualifier para elegir que bean inyectar
		@Autowired
		@Qualifier("authenticationManager")
		private AuthenticationManager authenticationManager;
		
		@Autowired
		private InfoAdicionalToken infoAdicionalToken;
		
		@Override
		public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
			// se le da acceso total tanto a usuario como usuario anonimo en el login para autenticarse
			// Endpoint de login: /oauth/token/
			//el primero es para generar el token cuando se autentica
			//el segundo es para chequear o validar el token
			// se da el permiso al endpoint que se encarga de validar el token cada vez que queramos acceder a
			// una pagina protegida  tenemos que enviar nuestro token nuestro jwt en la cabecera de nuestra peticion 
			//dentro del authorization
			//endpoint que verific ael token y su firma:/oauth/check_token(.checkTokenAccess(""))
			//estos dos endpoints estan protegidos por autenticacion http Basic
			//(Header Authorization Basic: Client Id + Client secret)
			 // y utilizando las credenciales del cliente de la aplicacion envia el cliente Id con su secreto
			security.tokenKeyAccess("permitAll()")
			.checkTokenAccess("isAuthenticated()");
		}
		
		//2) Hay que configurar nuestros clientes nuestras aplicaciones que van a acceder a nuestro api rest
		// en nuestra aplicacion vamos a tener un solo cliente que seria nuestro frontend osea nuestra aplicacion
		// con Angular en este caso se va a registrar nuestra aplicacion de angular con sus credenciales, tiene
		//doble seguridad por el cliente que se autentifica con el login y por el backend 
		@Override
		public void configure(ClientDetailsServiceConfigurer clients) throws Exception{
			  // configuramos tipo de almacenamiento,luego id del cliente corresponde al username de usuario
			// aca se valida el cliente y backend entrega el token  
			//En resumen: a traves de un refreshToken es un GrantTypes o tipo de concesion
			// podemos obtener un token de acceso completamenrte renovado sin tener que iniciar sesion
			//y se configura ambos tiempos de validez del token lo ue vaa durar el token
			clients.inMemory().withClient("angularapp")
			.secret(passwordEncoder.encode("12345"))
			.scopes("read","write")// ahora se ve el scope o los permisos del clienete
			.authorizedGrantTypes("password","refresh_token")// se refresca el token 
			.accessTokenValiditySeconds(3600)// se indica el tiempo de caducidad el token
			.refreshTokenValiditySeconds(3600);
		}
		
		// 1) Se configura el endpoint del AuthorizationServer se encarga del proceso de autenticacion
		// y de validar el token , cada vez que iniciamos sesion enviamos nuestro usuario y contraseña
		// y si todo sale bien realiza la autenticacion , genera el token se lo entrega al usurio
		// y despues el usuario con ese token puede acceder a las distintas paginas  y recursos de nuestra
		// aplicacion backend para eso se debe validar eso se realiza en endpoints en unas rutas, que maneja 
		// el servidor de autorizacion tanto para el login o autorizacion que genera el token y tambien para el proceso
		// de validacion , validar el token y su firma 
		//  accessTokenConverter almacena datos de autorizacion del usario(maneja datos del token)
		// accessTokenConverter se encarga de traducir los datos del token

		@Override
		public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception{
			// hay que enlazar la informacion que viene por defecto en el token con la adicional
			TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
			tokenEnhancerChain.setTokenEnhancers(Arrays.asList( infoAdicionalToken,accesTokenConverter()));
			
			endpoints.authenticationManager(authenticationManager)
			.tokenStore(tokenStore())// se pasa una instacia del componente que se encarga de almacenar los datos
			.accessTokenConverter(accesTokenConverter())
			.tokenEnhancer(tokenEnhancerChain);
		}
		
		@Bean
		public JwtTokenStore tokenStore() {
			
			return new JwtTokenStore(accesTokenConverter());
		}

		// se anota con @Bean para indicar que se va a crear un componente de spring
		//se importa la implementacion de jwt
		// decodifica y codifica datos del token
		//RSA_PRIVADA 	vamos aa asignar una llave de verificacion
		// el que firma es la clave privada
		//RSA_PUBLICA Y el que valida o verifica es la publica
		@Bean
		public JwtAccessTokenConverter accesTokenConverter() {
			
			JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();   
			jwtAccessTokenConverter.setSigningKey(JwtConfig.RSA_PRIVADA);
			jwtAccessTokenConverter.setVerifierKey(JwtConfig.RSA_PUBLICA);
			  return jwtAccessTokenConverter;
		}
		
}
