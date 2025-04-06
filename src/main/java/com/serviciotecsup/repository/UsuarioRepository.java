package com.serviciotecsup.repository;

import com.serviciotecsup.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByCode(String code);
    boolean existsByEmail(String email);
}
