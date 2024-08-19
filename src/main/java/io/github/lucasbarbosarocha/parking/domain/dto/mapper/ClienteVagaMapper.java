package io.github.lucasbarbosarocha.parking.domain.dto.mapper;

import io.github.lucasbarbosarocha.parking.domain.entity.ClienteVaga;
import io.github.lucasbarbosarocha.parking.domain.dto.EstacionamentoCreateDto;
import io.github.lucasbarbosarocha.parking.domain.dto.EstacionamentoResponseDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ClienteVagaMapper {

    public static ClienteVaga toClienteVaga(EstacionamentoCreateDto dto) {
        return new ModelMapper().map(dto, ClienteVaga.class);
    }

    public static EstacionamentoResponseDto toDto(ClienteVaga clienteVaga) {
        return new ModelMapper().map(clienteVaga, EstacionamentoResponseDto.class);
    }
}
