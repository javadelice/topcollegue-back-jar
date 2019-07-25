package com.dev;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.dev.entity.TopCollegue;
import com.dev.persistence.TopCollegueRepository;

@Component
public class StartupDataInit {
	
	@Autowired
	TopCollegueRepository topCollegueRepository;
	
	@EventListener(ContextRefreshedEvent.class)
    public void init() {
		
		topCollegueRepository.save(new TopCollegue("132", "DUPONT", "Jean", "https://randomuser.me/api/portraits/men/41.jpg"));
		topCollegueRepository.save(new TopCollegue("123", "RAY", "John", "https://randomuser.me/api/portraits/men/42.jpg"));
		topCollegueRepository.save(new TopCollegue("111", "SMITH", "Jack", "https://randomuser.me/api/portraits/men/43.jpg"));
		topCollegueRepository.save(new TopCollegue("222", "MARTIN", "Martin", "https://randomuser.me/api/portraits/men/44.jpg"));
		topCollegueRepository.save(new TopCollegue("128", "DUPRE", "Céline", "https://randomuser.me/api/portraits/women/45.jpg"));
		topCollegueRepository.save(new TopCollegue("218", "DUVAL", "Juliette", "https://randomuser.me/api/portraits/women/46.jpg"));
		topCollegueRepository.save(new TopCollegue("112", "DESPRES", "Megane", "https://randomuser.me/api/portraits/women/47.jpg"));
		topCollegueRepository.save(new TopCollegue("107", "DULAC", "Maïté", "https://randomuser.me/api/portraits/women/48.jpg"));
		
	}
}
