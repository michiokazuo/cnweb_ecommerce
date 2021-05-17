package com.http.service_impl;

import com.http.config.AppConfig;
import com.http.convert.BillConvert;
import com.http.convert.Convert;
import com.http.dao.BillDao;
import com.http.dao.BillHasProductDao;
import com.http.dao_impl.BillDaoImpl;
import com.http.dao_impl.BillHasProductDaoImpl;
import com.http.dto.BillDTO;
import com.http.dto.UserDTO;
import com.http.model.Bill;
import com.http.model.BillHasProduct;
import com.http.service.BillService;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class BillService_Impl implements BillService {
    private final BillDao billDao = new BillDaoImpl();
    private final BillHasProductDao billHasProductDao = new BillHasProductDaoImpl();
    private final Convert<Bill, BillDTO> convert = new BillConvert();

    @Override
    public List<BillDTO> findAll() throws Exception {
        if (AppConfig.checkAdmin(AppConfig.userInSysTem))
            return convert.toDtoList(billDao.findAll());
        return null;
    }

    @Override
    public BillDTO findById(Integer id) throws Exception {
        BillDTO dto = id != null && id > 0 ? convert.toDTO(billDao.findById(id)) : null;
        UserDTO userInSystem = AppConfig.userInSysTem;
        if (dto != null)
            if (AppConfig.checkAdmin(userInSystem)
                    || (userInSystem != null && userInSystem.getId().equals(dto.getBill().getUserDTO().getId())))
                return dto;

        return null;
    }

    @Override
    public BillDTO insert(BillDTO billDTO) throws Exception {
        UserDTO userInSystem = AppConfig.userInSysTem;
        if (billDTO != null && AppConfig.checkUser(userInSystem)) {
            Bill bill = convert.toModel(billDTO);
            if (bill != null && billDTO.getBillHasProducts() != null
                    && userInSystem.getId().equals(bill.getUserDTO().getId())) {
                Date date = new Date();
                bill.setCreateDate(date);
                bill.setModifyDate(date);
                bill.setCreateBy(userInSystem.getEmail());
                bill.setModifyBy(userInSystem.getEmail());
                bill.setStatus(0);
                bill.setDeleted(false);
                Bill newBill = billDao.insert(bill);
                List<BillHasProduct> billHasProductDaoS = billDTO.getBillHasProducts();
                for (BillHasProduct billHasProduct : billHasProductDaoS) {
                    billHasProduct.setBillId(newBill.getId());
                }
                billHasProductDao.insertAll(billDTO.getBillHasProducts());

                if (newBill != null) return convert.toDTO(newBill);
            }
        }
        return null;
    }

    @Override
    public List<BillDTO> search(BillDTO billDTO) throws Exception {
        return null;
    }

    @Override
    public BillDTO update(BillDTO billDTO) throws Exception {
        return null;
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        UserDTO userInSystem = AppConfig.userInSysTem;
        if (id != null && id > 0) {
            Bill bill = billDao.findById(id);
            if (bill != null)
                return (AppConfig.checkAdmin(userInSystem)
                        || (userInSystem != null && userInSystem.getId().equals(bill.getUserDTO().getId())
                        && bill.getStatus() == 0))
                        && billDao.delete(id, userInSystem.getEmail(), new Date());
        }
        return false;
    }

    @Override
    public List<BillDTO> getAllBillByUser(Integer id) throws Exception {
        UserDTO userInSystem = AppConfig.userInSysTem;
        if (id != null && id > 0) {
            if (AppConfig.checkAdmin(userInSystem)
                    || (userInSystem != null && userInSystem.getId().equals(id)))
                return convert.toDtoList(billDao.getAllBillByUser(id));
        }
        return null;
    }

    @Override
    public boolean acceptBill(Integer id) throws SQLException {
        UserDTO userInSystem = AppConfig.userInSysTem;
        if (id != null && id > 0)
            return AppConfig.checkAdmin(userInSystem) && billDao.acceptBill(id, userInSystem.getEmail(), new Date());

        return false;
    }
}
