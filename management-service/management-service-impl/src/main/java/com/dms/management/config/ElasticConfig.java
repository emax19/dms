package com.dms.management.config;

import fr.pilato.elasticsearch.tools.ElasticsearchBeyonder;
import lombok.AllArgsConstructor;
import org.elasticsearch.client.RestClient;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import javax.annotation.PostConstruct;

@AllArgsConstructor
@EnableElasticsearchRepositories
public class ElasticConfig {

	private RestClient restClient;

	@PostConstruct
	public void indexMigration() throws Exception {
		ElasticsearchBeyonder.start(restClient);
	}

}
