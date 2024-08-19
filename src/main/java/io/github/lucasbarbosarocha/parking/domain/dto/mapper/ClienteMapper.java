package io.github.lucasbarbosarocha.parking.domain.dto.mapper;

import io.github.lucasbarbosarocha.parking.domain.entity.Cliente;
import io.github.lucasbarbosarocha.parking.domain.dto.ClienteCreateDto;
import io.github.lucasbarbosarocha.parking.domain.dto.ClienteResponseDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ClienteMapper {

    public static Cliente toCliente(ClienteCreateDto dto) {
        return new ModelMapper().map(dto, Cliente.class);
    }

    public static ClienteResponseDto toDto(Cliente cliente) {
        return new ModelMapper().map(cliente, ClienteResponseDto.class);
    }
}
