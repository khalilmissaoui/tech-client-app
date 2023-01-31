package com.practice.techclientappointment.repository;

import com.practice.techclientappointment.entity.Address;
import com.practice.techclientappointment.entity.Client;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ClientRepositoryTest {

    @Autowired
    private ClientRepository clientRepository;

    @Test
    public void saveClients() {


        int CREATED_CLIENTS = 20 ;


        for (int i = 0; i < CREATED_CLIENTS; i++) {
            Address address = Address.builder()
                    .city("paris "+ i )
                    .houseNumber(Integer.toString(i))
                    .street("Street "+ i)
                    .build();

            Client student = Client.builder()
                    .type("Agency "+ i)
                    .address(address )
                    .build();

            clientRepository.save(student);
        }


    }
}