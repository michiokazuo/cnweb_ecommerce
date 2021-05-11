package com.http.dao_impl;

import com.http.dao.BillHasProductDao;
import com.http.dto.ItemDTO;
import com.http.model.MyConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BillHasProductDaoImpl implements BillHasProductDao {
    public static final String NAME_BILL_HAS_PRODUCT = "bill_has_product";
    private final MyConnection connection = new MyConnection();

    @Override
    public ItemDTO getObject(ResultSet resultSet) throws SQLException {
        return null;
    }

    @Override
    public List<ItemDTO> getList(ResultSet resultSet) throws SQLException {
        return null;
    }

    @Override
    public List<ItemDTO> findAll() throws SQLException {
        return null;
    }

    @Override
    public ItemDTO findById(int id) throws SQLException {
        return null;
    }

    @Override
    public ItemDTO insert(ItemDTO itemDTO) throws SQLException {
        return null;
    }

    @Override
    public List<ItemDTO> search(ItemDTO itemDTO) throws SQLException {
        return null;
    }

    @Override
    public ItemDTO update(ItemDTO itemDTO) throws SQLException {
        return null;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        return false;
    }
}
