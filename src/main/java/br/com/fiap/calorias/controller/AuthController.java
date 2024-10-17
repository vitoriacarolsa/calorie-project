package br.com.fiap.calorias.controller;

import br.com.fiap.calorias.dto.LoginDTO;
import br.com.fiap.calorias.dto.UsuarioCadastroDTO;
import br.com.fiap.calorias.dto.UsuarioExibicaoDTO;
import br.com.fiap.calorias.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid LoginDTO usuarioDto){
        UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken
                (usuarioDto.email(),usuarioDto.senha());
        Authentication auth = authenticationManager.authenticate(usernamePassword);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity registrar(@RequestBody @Valid UsuarioCadastroDTO usuarioCadastroDTO){
        UsuarioExibicaoDTO usuarioSalvo = null;
        usuarioSalvo = usuarioService.salvarUsuario(usuarioCadastroDTO);
        return ResponseEntity.ok(usuarioSalvo);
    }
}
