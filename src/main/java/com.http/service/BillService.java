package com.http.service;

import com.http.dto.BillDTO;

import java.util.List;

public interface BillService extends BaseService<BillDTO> {

    List<BillDTO> getAllBillByUser(Integer id) throws Exception;
}
