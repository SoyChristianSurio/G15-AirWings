package com.airwings.app.auth.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.support.SessionFlashMapManager;

import com.airwings.app.model.entity.usuario.Usuario;
import com.airwings.app.services.usuario.UsuarioService;

@Component
public class LoginFailureHandler extends SimpleUrlAuthenticationFailureHandler{

	@Autowired
	UsuarioService usuarioService;
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		SessionFlashMapManager flashMansger = new SessionFlashMapManager();
        FlashMap flash = new FlashMap();
		String username = request.getParameter("username");
        Usuario user = usuarioService.findByUsername(username);
        System.out.println("Entrando a failure: "+username);
        if (user != null) {
        	
            if (!user.getBloqueado()) {
                if (user.getContadorBloqueo() < 2) {
                    user.setContadorBloqueo(user.getContadorBloqueo()+1);
                	usuarioService.save(user);
                	flash.put("warning", "Username o contraseña incorrectos. Intentos Restantes: "+(3-user.getContadorBloqueo()));
                	flashMansger.saveOutputFlashMap(flash, request, response);
                } else {
                    usuarioService.lock(user);
                    flash.put("error", "Su cuenta con username: "+user.getUsername()+", se ha bloqueado por fallar 3 veces el inicio de sesión."
                            + " Revise su email asociado a dicha cuenta");
                	flashMansger.saveOutputFlashMap(flash, request, response);
                    exception = new LockedException("cuenta Bloqueada");
                } 
                
                
            }else if(user.getBloqueado()) {
            	flash.put("error", "Su cuenta con username: "+user.getUsername()+", se ha bloqueado por fallar 3 veces el inicio de sesión."
                    + " Revise su email asociado a dicha cuenta");
            	flashMansger.saveOutputFlashMap(flash, request, response);
            }
       
        }else {
        	flash.put("warning", "La cuenta no existe");
            flashMansger.saveOutputFlashMap(flash, request, response);
        }
        
        super.setDefaultFailureUrl("/login?error");
		
		
		super.onAuthenticationFailure(request, response, exception);
	
	
	}
	
}
