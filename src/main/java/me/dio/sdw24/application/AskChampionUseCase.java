package me.dio.sdw24.application;

import me.dio.sdw24.domain.exception.ChampionNotFoundException;
import me.dio.sdw24.domain.model.Champion;
import me.dio.sdw24.domain.ports.ChampionRepository;
import me.dio.sdw24.domain.ports.GenerativeAiService;

public record AskChampionUseCase(ChampionRepository repository, GenerativeAiService genAiService) {

    public String askChampion(Long championId, String question) {

        Champion champion = repository.findById(championId) //busca o campeão por ID
                .orElseThrow(() -> new ChampionNotFoundException(championId)); // caso ele não exista, lança a excessão que criamos

        String context = champion.generateContextByQuestion(question);
        String objective = """
                Atue como um assistente com a habilidade de se comportar como os Campeões do League of Legends (LOL).
                Responda perguntas incorporando a personalidade e estilo de um determinado Campeão.
                Segue a pergunta, o nome do Campeão e sua respectiva lore (história):
                
                """;

        genAiService.generateContent(objective, context);

        return context;
    }


}
