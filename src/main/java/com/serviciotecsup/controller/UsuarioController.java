package com.serviciotecsup.controller;

import com.serviciotecsup.entity.Usuario;
import com.serviciotecsup.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Usuario> crear(@RequestBody Usuario usuario) {
        return ResponseEntity.ok(usuarioService.guardar(usuario));
    }

    @GetMapping("/{code}")
    public ResponseEntity<Usuario> obtener(@PathVariable String code) {
        return usuarioService.obtenerPorCodigo(code)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Usuario> listar() {
        return usuarioService.listarTodos();
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<Void> eliminar(@PathVariable String code) {
        usuarioService.eliminar(code);
        return ResponseEntity.noContent().build();
    }
}
