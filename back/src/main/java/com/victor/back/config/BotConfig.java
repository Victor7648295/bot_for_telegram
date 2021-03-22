package com.victor.back.config;

import com.victor.back.api.v1.controller.BotController;
import com.victor.back.model.BotTelegram;
import com.victor.back.service.BotService;
import com.victor.back.service.CityService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

@Configuration
public class BotConfig {

    final BotService botService;

    final BotTelegram botTelegram;

    final CityService cityService;

    public BotConfig(BotTelegram botTelegram, CityService cityService, BotService botService) {
        this.botTelegram = botTelegram;
        this.cityService = cityService;
        this.botService = botService;
    }

    @Bean
    public void TelegramBotApi() throws TelegramApiRequestException {
        TelegramBotsApi botsApi = new TelegramBotsApi();
        botsApi.registerBot(new BotController(botTelegram,cityService,botService));
    }
}
