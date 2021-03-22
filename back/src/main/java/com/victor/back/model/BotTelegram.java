package com.victor.back.model;

import org.springframework.stereotype.Component;

@Component
public class BotTelegram {

    private final String botUserName = "CityTaskTelegramBot";

    private final String botToken = "1795626586:AAEFA_jIs93EgWaOfNrYvMh1k_7pE7FZ77A";

    public String getBotUserName() {
        return botUserName;
    }

    public String getBotToken() {
        return botToken;
    }

    private BotTelegram(){}
}
