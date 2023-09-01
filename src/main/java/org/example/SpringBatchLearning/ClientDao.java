package org.example.SpringBatchLearning;

import org.example.SpringBatchLearning.model.Client;

import javax.persistence.*;

@Entity
@Table(name = "client")
public class ClientDao extends Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
}
