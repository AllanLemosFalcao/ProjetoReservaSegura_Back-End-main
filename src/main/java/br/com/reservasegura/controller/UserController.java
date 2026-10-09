package br.com.reservasegura.controller;

import br.com.reservasegura.dto.UserProfileDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
// A anotação @CrossOrigin é fundamental para o navegador não bloquear a requisição do Vite!
@CrossOrigin(origins = "http://localhost:5173", maxAge = 3600) 
public class UserController {

    @GetMapping("/perfil")
    public ResponseEntity<UserProfileDTO> getPerfilUsuario() {
        
        // NOTA: Numa fase posterior, deverá injetar o UserService aqui e buscar estes 
        // dados na Base de Dados (PostgreSQL) com base no utilizador logado no JWT.
        // Para já, vamos devolver dados estáticos para garantir que o front-end liga corretamente!
        
        UserProfileDTO perfil = new UserProfileDTO();
        perfil.setNome("Allan Falcão"); // Utilizei o seu nome para testar!
        perfil.setTitulo("Aspirante");
        perfil.setNivel(8);
        perfil.setXpAtual(2450);
        perfil.setXpProximoNivel(3000);
        perfil.setMoedas(1850);
        perfil.setMoedasAcumuladasMes(1850);
        perfil.setSequenciaAtual(7);
        perfil.setRecordeSequencia(14);
        perfil.setTotalConquistas(23);
        perfil.setMissoesCompletas(2);
        perfil.setMissoesTotal(3);
        perfil.setTotalPoupado(26650.00);
        perfil.setMetaMensal(2800.00);

        return ResponseEntity.ok(perfil);
    }
}