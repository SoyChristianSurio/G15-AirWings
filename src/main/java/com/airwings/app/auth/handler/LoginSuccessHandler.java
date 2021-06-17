package com.airwings.app.auth.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.support.SessionFlashMapManager;

import com.airwings.app.model.entity.usuario.Usuario;
import com.airwings.app.services.usuario.UsuarioService;

@Component
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler{

	@Autowired
	UsuarioService usuarioService;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		SessionFlashMapManager flashMansger = new SessionFlashMapManager();
        FlashMap flash = new FlashMap();
		String username = request.getParameter("username");
        Usuario user = usuarioService.findByUsername(username);
        System.out.println("Entrando a Success: "+username);
        
        if (user != null) {
        	
            if(user.getBloqueado()) { 
            	user.setContadorBloqueo(0);
            	usuarioService.save(user);
            }
            flash.put("success", "inicio de sesión exitoso");
        	flashMansger.saveOutputFlashMap(flash, request, response);   
        }
		
		super.onAuthenticationSuccess(request, response, authentication);
	}
	
	
	
}
