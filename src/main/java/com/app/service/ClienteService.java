package com.app.service;

import com.app.dto.ClienteDTO;
import com.app.model.ClienteEntity;
import com.app.repository.ClienteRepository;
import com.app.security.CustomUserDetails;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log4j2
public class ClienteService implements UserDetailsService {

    @Autowired
    private ClienteRepository clienteRepository;

    public boolean criar(ClienteDTO cliente) {

        ClienteEntity clienteEntity = new ClienteEntity();
        clienteEntity.setUsername(cliente.getUsername());
        clienteEntity.setPassword(cliente.getPassword());
        clienteEntity.setNome(cliente.getNome());
        clienteEntity.setEmail(cliente.getEmail());

        Optional<ClienteEntity> found = clienteRepository
                .findByUsername(clienteEntity.getUsername());
        if (found.isEmpty()) {
            try {
                clienteRepository.save(clienteEntity);
                return true;
            } catch (Exception ex) {
                log.error("Não foi possível criar cliente", ex);
                return false;
            }
        }
        return false;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ClienteEntity clienteEntity = new ClienteEntity();
        clienteEntity.setUsername(username);

        ClienteEntity found = clienteRepository
                .findByUsername(clienteEntity.getUsername())
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado: "));

        return CustomUserDetails.valueOf(found);
    }
}
