package com.victor.back.api.v1.controller;


import com.victor.back.model.BotTelegram;
import com.victor.back.model.City;
import com.victor.back.service.BotService;
import com.victor.back.service.CityService;
import org.springframework.stereotype.Controller;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

@Controller
public class BotController extends TelegramLongPollingBot {

    final BotTelegram botTelegram;

    final CityService cityService;

    final BotService botService;

    public BotController(BotTelegram botTelegram, CityService cityService, BotService botService) {
        this.botTelegram = botTelegram;
        this.cityService = cityService;
        this.botService = botService;
    }

    @Override
    public String getBotUsername() {
        return botTelegram.getBotUserName();
    }

    @Override
    public String getBotToken() {
        return botTelegram.getBotToken();
    }


    @Override
    public void onUpdateReceived(Update update) {
        try {
            if(update.getMessage().getText().equals("/start")){
                SendMessage responseMessage = botService.getStartMessage(update);
                execute(responseMessage);

            }else if (update.getMessage().getText().equals("/cities")){
                Message requestMessage = update.getMessage();
                SendMessage responseMessage = new SendMessage();
                List<City> cities = cityService.getAllCity();
                responseMessage.setChatId(requestMessage.getChatId());
                for(City cities1: cities){
                    responseMessage.setText(cities1.getName());
                    responseMessage.setChatId(requestMessage.getChatId());
                    execute(responseMessage);
                }

            }else if (update.hasMessage() && update.getMessage().hasText()) {
                SendMessage responseMessage = botService.getMessageForTelegram(update);
                execute(responseMessage);
            }

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

}
