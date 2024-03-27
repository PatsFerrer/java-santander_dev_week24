package me.dio.sdw24.application;

import me.dio.sdw24.domain.exception.ChampionNotFoundException;
import me.dio.sdw24.domain.model.Champion;
import me.dio.sdw24.domain.ports.ChampionRepository;

import java.util.List;

public record AskChampionUseCase(ChampionRepository repository) {

    public String askChampion(Long championId, String question) {

        Champion champion = repository.findById(championId) //busca o campeão por ID
                .orElseThrow(() -> new ChampionNotFoundException(championId)); // caso ele não exista, lança a excessão que criamos

        // TODO: Evoluir a lógica de negócio para considerar a integração com IAs Generativas.

        return "";
    }


}
