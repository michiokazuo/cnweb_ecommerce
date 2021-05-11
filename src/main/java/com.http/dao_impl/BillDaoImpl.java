package com.http.dao_impl;

import com.http.dao.BillDao;
import com.http.dto.BillDTO;
import com.http.model.MyConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BillDaoImpl implements BillDao {
    public static final String NAME_BILL = "bill";
    private final MyConnection connection = new MyConnection();

    @Override
    public BillDTO getObject(ResultSet resultSet) throws SQLException {
        return null;
    }

    @Override
    public List<BillDTO> getList(ResultSet resultSet) throws SQLException {
        return null;
    }

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
