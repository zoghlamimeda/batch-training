package org.example.job.cursordatasource;

import org.example.SpringBatchLearning.model.Client;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CursorDataSourceWriterConfig {

    @Bean
    public ItemWriter<Client> cursorDataSourceWriter() {
        return items -> items.forEach(System.out::println);
    }

}
