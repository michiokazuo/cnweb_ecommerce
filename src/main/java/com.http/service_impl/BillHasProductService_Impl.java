package com.http.service_impl;

import com.http.dao.BillHasProductDao;
import com.http.dao_impl.BillHasProductDaoImpl;
import com.http.dto.ItemDTO;
import com.http.service.BillHasProductService;

import java.sql.SQLException;
import java.util.List;

public class BillHasProductService_Impl implements BillHasProductService {
    private final BillHasProductDao billHasProductDao = new BillHasProductDaoImpl();

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
