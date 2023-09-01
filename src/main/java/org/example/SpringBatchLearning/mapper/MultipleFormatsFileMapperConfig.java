package org.example.SpringBatchLearning.mapper;

import org.example.SpringBatchLearning.model.Client;
import org.example.SpringBatchLearning.model.Transaction;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.mapping.PatternMatchingCompositeLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class MultipleFormatsFileMapperConfig {

    private LineTokenizer clientLineTokenizer() {
        DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer();
        delimitedLineTokenizer.setNames("name", "nickname", "age", "email", "salaryRange");
        delimitedLineTokenizer.setIncludedFields(1, 2, 3, 4, 5);
        return delimitedLineTokenizer;
    }

    private LineTokenizer transactionLineTokenizer() {
        DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer();
        delimitedLineTokenizer.setNames("id", "description", "value");
        delimitedLineTokenizer.setIncludedFields(1, 2, 3);
        return delimitedLineTokenizer;
    }

    private Map<String, LineTokenizer> tokenizers() {
        Map<String, LineTokenizer> lineTokenizerMap = new HashMap<>();
        lineTokenizerMap.put("0*", clientLineTokenizer());
        lineTokenizerMap.put("1*", transactionLineTokenizer());
        return lineTokenizerMap;
    }

    private FieldSetMapper<Object> fieldSetMapper(Class classReference) {
        BeanWrapperFieldSetMapper<Object> fieldSetMapper = new BeanWrapperFieldSetMapper<Object>();
        fieldSetMapper.setTargetType(classReference);
        return fieldSetMapper;
    }

    private Map<String, FieldSetMapper<Object>> fieldSetMappers() {
        Map<String, FieldSetMapper<Object>> fieldSetMapperMap = new HashMap<>();
        fieldSetMapperMap.put("0*", fieldSetMapper(Client.class));
        fieldSetMapperMap.put("1*", fieldSetMapper(Transaction.class));
        return fieldSetMapperMap;
    }

    @Bean
    public PatternMatchingCompositeLineMapper<Object> lineMapper() {
        PatternMatchingCompositeLineMapper<Object> patternMatchingCompositeLineMapper = new PatternMatchingCompositeLineMapper<Object>();
        patternMatchingCompositeLineMapper.setTokenizers(tokenizers());
        patternMatchingCompositeLineMapper.setFieldSetMappers(fieldSetMappers());
        return patternMatchingCompositeLineMapper;
    }

}
