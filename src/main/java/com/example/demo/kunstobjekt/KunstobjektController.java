package com.example.demo.kunstobjekt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/kunstobjekt")
public class KunstobjektController {

    @Autowired
    private KunstobjektRepository repo;

    @Autowired
    private ReactiveMongoTemplate template;

    @GetMapping("/")
    public Flux<Kunstobjekt> getAll(@RequestParam(name = "q", required = false) String queryText) {
        if (queryText == null) {
            return repo.findAll();

        }
        TextCriteria criteria = TextCriteria.forLanguage("de").matchingAny(queryText);
        Query query = TextQuery.queryText(criteria).sortByScore();
        return template.find(query, Kunstobjekt.class);
    }

    @PostMapping("/")
    public Mono<Kunstobjekt> postOne(@RequestBody Kunstobjekt kunstobjekt) {
        return repo.save(kunstobjekt);
    }
}