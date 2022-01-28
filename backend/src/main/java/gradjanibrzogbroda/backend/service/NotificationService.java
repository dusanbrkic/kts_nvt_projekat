package gradjanibrzogbroda.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import gradjanibrzogbroda.backend.dto.Message;

@Service
public class NotificationService {
	
	private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public NotificationService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }
    
    public void newPorudzbinaNotification(Integer porudzbinaId) {
    	Message message = new Message("Porudzbina "+porudzbinaId+" kreirana!");

        messagingTemplate.convertAndSend("/topic/new-porudzbina-notification", message);
    }
    
    public void preuzmiPorudzbinaNotification(Integer porudzbinaId) {
    	Message message = new Message("Porudzbina "+porudzbinaId+" preuzeta!");

        messagingTemplate.convertAndSend("/topic/preuzmi-porudzbina-notification", message);
    }
    
    public void spremiJeloNotifiation(String naziv, Double kolicina, Integer porudzbinaId) {
    	Message message = new Message("Jelo "+naziv+", količina "+kolicina+" iz Porudzbina "+ porudzbinaId + " spremljeno!");

        messagingTemplate.convertAndSend("/topic/spremi-jela-notification", message);
    }
    
    public void spremiPicaNotification(Integer porudzbinaId) {
    	Message message = new Message("Pića iz Porudzbina "+porudzbinaId+" su spremljena!");

        messagingTemplate.convertAndSend("/topic/spremi-pica-notification", message);
    }

}
