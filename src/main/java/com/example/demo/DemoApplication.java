package com.example.demo;

import com.example.demo.kunstobjekt.Kunstobjekt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.index.IndexResolver;
import org.springframework.data.mongodb.core.index.MongoPersistentEntityIndexResolver;
import org.springframework.data.mongodb.core.index.ReactiveIndexOperations;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@EnableReactiveMongoRepositories
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Autowired
	private ReactiveMongoTemplate template;
	@Autowired
	private MongoMappingContext mongoMappingContext;

	@EventListener(ApplicationReadyEvent.class)
	public void initIndicesAfterStartup() {

		ReactiveIndexOperations indexOps = template.indexOps(Kunstobjekt.class);

		IndexResolver resolver = new MongoPersistentEntityIndexResolver(mongoMappingContext);
		resolver.resolveIndexFor(Kunstobjekt.class).forEach(indexOps::ensureIndex);
	}

}
