package br.com.fabiodev.todolist.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserRepository userRepository;
 
    @PostMapping("/")
    public ResponseEntity create(@RequestBody UserModel usermodel){
        var user = this.userRepository.findByUsername(usermodel.getUsername());

        if(user != null){
            System.out.println("Já existe este usuário cadastrado.");
            // Mensagem de erro
            // Status Code
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário já existe");
        }
        var userCreated = userRepository.save(usermodel);
        System.out.println(userCreated);
        return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);

    }
    
    @RequestMapping("/teste")
    public String teste(){
        System.out.println("Teste");
        return "Funcionou";
    }
}
