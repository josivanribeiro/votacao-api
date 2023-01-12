package com.votacaoapi.service;

import com.votacaoapi.dto.PautaDTO;
import com.votacaoapi.dto.SelecaoDTO;
import com.votacaoapi.dto.VotacaoDTO;
import com.votacaoapi.entity.Pauta;
import com.votacaoapi.exception.EntityNotFoundException;
import com.votacaoapi.mapper.PautaMapper;
import com.votacaoapi.repository.PautaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PautaServiceTest {

    @Mock
    private PautaRepository pautaRepository;

    @Mock
    private PautaMapper pautaMapper;

    @InjectMocks
    private PautaService pautaService;

    @Test
    void shouldSavePautaSuccessfully() {
        final VotacaoDTO votacaoDTO = new VotacaoDTO();
        votacaoDTO.setCampo1("Opção 1");
        votacaoDTO.setCampo2(1L);
        votacaoDTO.setIdCampoTexto("Texto");
        votacaoDTO.setIdCampoNumerico("999");
        votacaoDTO.setIdCampoData("01/01/2000");

        final PautaDTO pautaDTO = new PautaDTO();
        pautaDTO.setId("1");
        pautaDTO.setVotos(new ArrayList<>());

        final Pauta pauta = new Pauta();
        pauta.setId("1");
        pauta.setVotos(new ArrayList<>());

        given(pautaMapper.convertToPauta(any())).willAnswer(invocation -> pauta);
        given(pautaMapper.convertToPautaDTO(any())).willAnswer(invocation -> pautaDTO);
        given(pautaRepository.save(ArgumentMatchers.any())).willAnswer(invocation -> invocation.getArgument(0));

        PautaDTO result = pautaService.save(votacaoDTO);

        assertNotNull(result);

        verify(pautaRepository).save(ArgumentMatchers.any());
    }

    @Test
    void shouldThrowErrorWhenSavePautaWithNull() {
        given(pautaRepository.save(null)).willThrow(IllegalArgumentException.class);

        assertThrows(IllegalArgumentException.class, () -> {
            pautaRepository.save(null);
        });

        verify(pautaRepository).save(ArgumentMatchers.any());
    }

    @Test
    void shouldSaveSelectionSuccessfully() throws EntityNotFoundException {
        final SelecaoDTO selecaoDTO = new SelecaoDTO();
        selecaoDTO.setCampo1("1");
        selecaoDTO.setCampo2(34871040089L);
        selecaoDTO.setCampo3("Sim");

        final PautaDTO pautaDTO = new PautaDTO();
        pautaDTO.setId("1");
        pautaDTO.setVotos(new ArrayList<>());

        final Pauta pauta = new Pauta();
        pauta.setId("1");
        pauta.setVotos(new ArrayList<>());

        given(pautaMapper.convertToPauta(any())).willAnswer(invocation -> pauta);
        given(pautaMapper.convertToPautaDTO(any())).willAnswer(invocation -> pautaDTO);
        given(pautaRepository.findById(any())).willAnswer(invocation -> Optional.of(pauta));
        given(pautaRepository.save(ArgumentMatchers.any())).willAnswer(invocation -> invocation.getArgument(0));

        PautaDTO result = pautaService.saveSelection(selecaoDTO);

        assertNotNull(result);

        verify(pautaRepository).findById(ArgumentMatchers.any());
        verify(pautaRepository).save(ArgumentMatchers.any());
    }

    @Test
    void shouldThrowErrorWhenPautaIsNotFound() {
        final Pauta pauta = new Pauta();
        pauta.setId("1");
        pauta.setVotos(new ArrayList<>());

        given(pautaRepository.findById(any())).willThrow(EntityNotFoundException.class);

        assertThrows(EntityNotFoundException.class, () -> {
            pautaRepository.findById(any());
        });

        verify(pautaRepository, never()).save(any());
    }

}
