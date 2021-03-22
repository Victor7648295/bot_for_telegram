package com.victor.back.service.impl;

import com.victor.back.config.Constants;
import com.victor.back.model.City;
import com.victor.back.repository.CityRepository;
import com.victor.back.service.BotService;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Optional;

@Service
public class BotServiceImpl implements BotService {

    final CityRepository repository;

    public BotServiceImpl(CityRepository repository) {
        this.repository = repository;
    }


    @Override
    public String getDescriptionForCity(String name) {
        Optional<City> city = repository.findByName(name);
        if(city.isPresent()){
            return city.get().getDescription();
        }
        return Constants.Phrases.NO_CITY;
    }

    @Override
    public SendMessage getMessageForTelegram(Update update){
        Message requestMessage = update.getMessage();
        String text = update.getMessage().getText();
        String description = getDescriptionForCity(text);
        SendMessage responseMessage = new SendMessage();
        responseMessage.setChatId(requestMessage.getChatId());
        responseMessage.setText(description);
        return responseMessage;
    }

    @Override
    public SendMessage getStartMessage(Update update){
        Message requestMessage = update.getMessage();
        SendMessage responseMessage = new SendMessage();
        responseMessage.setChatId(requestMessage.getChatId());
        responseMessage.setText(Constants.Phrases.START_PHRASES);
        return responseMessage;
    }

}
