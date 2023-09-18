package com.abrigo.controller;

import com.abrigo.model.AbrigoModel;
import com.abrigo.service.AbrigoService;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;

@WebMvcTest(AbrigoController.class)

class AbrigoControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    AbrigoController abrigoController;

    @MockBean
    AbrigoService abrigoService;

    @BeforeEach
    public void setup() {
        RestAssuredMockMvc.standaloneSetup(this.abrigoController);
    }

    @Test
    public void deveRetornarOk_AoBuscarMoradores() {
        AbrigoModel abrigoModel = new AbrigoModel();
        abrigoModel.setId(1L);
        abrigoModel.setNome("Mário");
        abrigoModel.setDescricao("masculino");
        abrigoModel.setNumeroMorador(1);
        abrigoModel.setArmarioPertences(1);

        Mockito.when(this.abrigoService.buscarPorId(1L)).thenReturn(Optional.of(abrigoModel));

        given()
                .accept(ContentType.JSON)
                .when()
                .get("/abrigo/{id}", 1L)
                .then()
                .statusCode(HttpStatus.OK.value());
    }
}


//