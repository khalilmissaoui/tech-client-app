package com.practice.techclientappointment.repository;

import com.practice.techclientappointment.entity.Address;
import com.practice.techclientappointment.entity.Client;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Slf4j
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
class ClientRepositoryTest {

    @Autowired
    private ClientRepository clientRepository;

    @BeforeAll
    public void SHOULD_SAVE_CLIENTS_DATA_IN_DB() {


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
    public void SHOULD_RETURN_SLICE_OF_CLIENTS_AND_CHECK_IF_CLIENT_EXIST_BY_TYPE() {
        //GIVEN DATA
        String SEARCHED_CLIENT_TYPE="agency 12";
        int listLength = 3 ;
        Slice<Client> slice = null;
        Pageable pageable = PageRequest.of(0, listLength);


        while (true) {

            // WHEN
            slice = clientRepository.findAll(pageable);
            List<Client> clientsList = slice.getContent();

            //THEN
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