package com.http.service;

import com.http.dto.BillDTO;

import java.sql.SQLException;
import java.util.List;

public interface BillService extends BaseService<BillDTO> {

    List<BillDTO> getAllBillByUser(Integer id) throws Exception;

    boolean acceptBill(Integer id) throws SQLException;
}
