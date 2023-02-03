package com.practice.techclientappointment.repository;

import com.practice.techclientappointment.entity.Address;
import com.practice.techclientappointment.entity.Client;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ClientRepositoryTest {

    @Autowired
    private ClientRepository clientRepository;

    @BeforeAll
    public void saveClients() {


        int CREATED_CLIENTS = 20 ;


        for (int i = 0; i < CREATED_CLIENTS; i++) {
            Address address = Address.builder()
                    .city("paris "+ i )
                    .houseNumber(Integer.toString(i))
                    .street("Street "+ i)
                    .build();

            Client client = Client.builder()
                    .type("Agency "+ i)
                    .address(address )
                    .build();

            clientRepository.save(client);
        }


    }

    @Test
    public void findClientsByType() {

        String SEARCHED_CLIENT_TYPE="agency 12";

        int listLength = 3 ;
        Slice<Client> slice = null;
        Pageable pageable = PageRequest.of(0, listLength, Sort.by("type"));

        while (true) {
            slice = clientRepository.findAll(pageable);
            List<Client> clientsList = slice.getContent();


            assertTrue(clientsList.size() == listLength );

            Boolean SuscpectedCleint = clientsList.stream().anyMatch(client -> client.getType().equalsIgnoreCase(SEARCHED_CLIENT_TYPE));

            if (SuscpectedCleint){

                log.info("CLIENTS are collected");

                break;
            }

            if (!slice.hasNext()) {
                log.info("END OF LIST OF PAGES");
                break;

            }

            pageable = slice.nextPageable();
        }




    }
}