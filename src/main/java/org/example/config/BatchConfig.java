package org.example.config;

import org.example.entities.Person;
import org.example.listener.PersonListener;
import org.example.repository.PersonRepository;
import org.example.util.BlankLineRecordSeparatorPolicy;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import java.io.File;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    StepBuilderFactory sbf;

    @Autowired
    JobBuilderFactory jbf;


    //Reader class Object
    @Bean
    public FlatFileItemReader<Person> reader() {

        FlatFileItemReader<Person> reader = new FlatFileItemReader<>();
        // reader.setResource(new ClassPathResource("/invoices.csv"));
        String csvPath = System.getProperty("user.dir") + File.separator + "data" + File.separator + "data.csv";
        reader.setResource(new FileSystemResource(csvPath));
        // reader.setResource(new UrlResource("https://xyz.com/files/invoices.csv"));
        // reader.setLinesToSkip(1);

        reader.setLineMapper(new DefaultLineMapper() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setDelimiter(";");
                setNames("name", "lastName", "firstName", "birthDate", "age", "address", "phone");
            }});

            setFieldSetMapper(new BeanWrapperFieldSetMapper() {{
                setTargetType(Person.class);
            }});
        }});

        reader.setRecordSeparatorPolicy(new BlankLineRecordSeparatorPolicy());

        return reader;
    }

    //Writer class Object
    @Bean
    public ItemWriter<Person> writer() {
        // return new InvoiceItemWriter(); // Using lambda expression code instead of a separate implementation
        return persons -> {
            System.out.println("Saving Invoice Records: " + persons);
            personRepository.saveAll(persons);
        };
    }

    @Bean
    public ItemProcessor<Person, Person> processor() {
        // return new InvoiceProcessor(); // Using lambda expression code instead of a separate implementation
        return person -> {
            person.setAge("27");
            return person;
        };
    }

    @Bean
    public JobExecutionListener listener() {
        return new PersonListener();
    }

    @Bean
    public Step stepA() {
        return sbf.get("stepA")
                .<Person, Person>chunk(2)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build()
                ;
    }

    @Bean
    public Job jobA() {
        return jbf.get("jobA")
                .incrementer(new RunIdIncrementer())
                .listener(listener())
                .start(stepA())
                // .next(stepB())
                // .next(stepC())
                .build()
                ;
    }
}
