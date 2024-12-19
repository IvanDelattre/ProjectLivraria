package com.carlosribeiro.service;

import com.carlosribeiro.dao.FaturaDAO;
import com.carlosribeiro.util.FabricaDeDaos;

public class FaturaService {
    FaturaDAO faturaDAO = FabricaDeDaos.getDAO(FaturaDAO.class);
}
