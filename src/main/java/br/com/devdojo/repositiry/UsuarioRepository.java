package br.com.devdojo.repositiry;

import br.com.devdojo.model.Usuario;
import org.apache.juli.logging.Log;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UsuarioRepository extends PagingAndSortingRepository<Usuario, Log> {
    Usuario findByUsername(String username);
}
