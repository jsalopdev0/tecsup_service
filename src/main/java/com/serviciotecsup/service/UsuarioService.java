package com.serviciotecsup.service;

import com.serviciotecsup.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    Usuario guardar(Usuario usuario);
    Optional<Usuario> obtenerPorCodigo(String code);
    List<Usuario> listarTodos();
    void eliminar(String code);
}
