package com.serviciotecsup.service;

import com.serviciotecsup.entity.Usuario;
import com.serviciotecsup.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private WebhookService webhookService;

    @Override
    public Usuario guardar(Usuario usuario) {
        Usuario guardado = usuarioRepository.save(usuario);

        // Notifica al backend 8080 (tesisreservatec)
        webhookService.notificarCreacionOActualizacion(guardado);

        return guardado;
    }

    @Override
    public Optional<Usuario> obtenerPorCodigo(String code) {
        return usuarioRepository.findByCode(code);
    }

    @Override
    public void eliminar(String code) {
        usuarioRepository.findByCode(code).ifPresent(usuarioRepository::delete);
    }


    @Override
    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }


}
