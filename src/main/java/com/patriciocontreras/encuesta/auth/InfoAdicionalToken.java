package com.patriciocontreras.encuesta.auth;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.patriciocontreras.encuesta.models.entity.Usuario;
import com.patriciocontreras.encuesta.models.services.IUsuarioService;


@Component
public class InfoAdicionalToken  implements TokenEnhancer {
	
	@Autowired
	private IUsuarioService usuarioService;

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		
		Usuario usuario = usuarioService.findByUsername(authentication.getName());
		// se implementara un objeto Map para asignarlo a accessToken
				// sera de tipo String y se guardara cualquier dato generico
				Map<String, Object> info = new HashMap<>();
				info.put("info_adicional", "Hola que tal!:  ".concat(authentication.getName()));
				
				info.put("nombre", usuario.getNombre());
				info.put("apellido", usuario.getApellido());
				// asignar el info a al objeto accessToken
				((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
		return accessToken;
	}

}
