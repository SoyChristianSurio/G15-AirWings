package com.airwings.app.services.boleto;

import com.airwings.app.model.DAO.boleto.BoletoDao;
import com.airwings.app.model.entity.boleto.Boleto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BoletoServiceImpl implements BoletoService {

    @Autowired
    private BoletoDao boletoDao;

    @Override
    @Transactional(readOnly = true)
    public List<Boleto> listaBoleto() {
        return (List<Boleto>) boletoDao.findAll();
    }

    @Override
    @Transactional
    public void guardar(Boleto boleto) {
        boletoDao.save(boleto);
    }

    @Override
    @Transactional
    public void eliminar(Boleto boleto) {
        boletoDao.delete(boleto);
    }

    @Override
    @Transactional(readOnly = true)
    public Boleto encontrarBoleto(Boleto boleto) {
        return boletoDao.findById(boleto.getId()).orElse(null);
    }

}
