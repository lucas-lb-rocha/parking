package io.github.lucasbarbosarocha.parking.domain.dto.mapper;

import io.github.lucasbarbosarocha.parking.domain.entity.Vaga;
import io.github.lucasbarbosarocha.parking.domain.dto.VagaCreateDto;
import io.github.lucasbarbosarocha.parking.domain.dto.VagaResponseDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class VagaMapper {

    public static Vaga toVaga(VagaCreateDto dto) {
        return new ModelMapper().map(dto, Vaga.class);
    }

    public static VagaResponseDto toDto(Vaga vaga) {
        return new ModelMapper().map(vaga, VagaResponseDto.class);
    }
}
