package br.com.devdojo.repositiry;

import br.com.devdojo.model.Usuario;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UsuarioRepository extends PagingAndSortingRepository<Usuario, Long> {
    Usuario findByUsername(String username);
}
