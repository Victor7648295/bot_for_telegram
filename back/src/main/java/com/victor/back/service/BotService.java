package com.victor.back.service;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface BotService {

     String getDescriptionForCity(String city);

     SendMessage getMessageForTelegram(Update update);

     SendMessage getStartMessage(Update update);

}
