package com.http.service_impl;

import com.http.dao.BillDao;
import com.http.dao_impl.BillDaoImpl;
import com.http.dto.BillDTO;
import com.http.service.BillService;

import java.sql.SQLException;
import java.util.List;

public class BillService_Impl implements BillService {
    private final BillDao billDao = new BillDaoImpl();

    @Override
    public List<BillDTO> findAll() throws SQLException {
        return null;
    }

    @Override
    public BillDTO findById(int id) throws SQLException {
        return null;
    }

    @Override
    public BillDTO insert(BillDTO billDTO) throws SQLException {
        return null;
    }

    @Override
    public List<BillDTO> search(BillDTO billDTO) throws SQLException {
        return null;
    }

    @Override
    public BillDTO update(BillDTO billDTO) throws SQLException {
        return null;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        return false;
    }
}
