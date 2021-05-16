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
import com.http.service.BillService;

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
        return id != null && id > 0 ? convert.toDTO(billDao.findById(id)) : null;
    }

    @Override
    public BillDTO insert(BillDTO billDTO) throws Exception {
        UserDTO userInSystem = AppConfig.userInSysTem;
        if (billDTO != null && AppConfig.checkUser(userInSystem)) {
            Bill bill = convert.toModel(billDTO);
            if (bill != null && billDTO.getBillHasProducts() != null && userInSystem.getId().equals(bill.getId())) {
                Date date = new Date();
                bill.setCreateDate(date);
                bill.setModifyDate(date);
                bill.setCreateBy(userInSystem.getEmail());
                bill.setModifyBy(userInSystem.getEmail());
                bill.setDeleted(false);
                Bill newBill = billDao.insert(bill);
                billHasProductDao.insertAll(billDTO.getBillHasProducts());

                if (newBill != null) return convert.toDTO(newBill);
            }
        }
        return null;
    }

    @Override
    public List<BillDTO> search(BillDTO billDTO) throws Exception {
        return billDTO != null && billDTO.getBill() != null
                ? convert.toDtoList(billDao.search(billDTO.getBill()))
                : (AppConfig.userInSysTem != null ? getAllBillByUser(AppConfig.userInSysTem.getId()) : null);
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
                        || (userInSystem != null && userInSystem.getId().equals(bill.getUserDTO().getId())))
                        && billDao.delete(id, userInSystem.getEmail(), new Date());
        }
        return false;
    }

    @Override
    public List<BillDTO> getAllBillByUser(Integer id) throws Exception {
        UserDTO userInSystem = AppConfig.userInSysTem;
        if (id != null && id > 0) {
            Bill bill = billDao.findById(id);
            if (AppConfig.checkAdmin(userInSystem)
                    || (userInSystem != null && bill != null && userInSystem.getId().equals(bill.getUserDTO().getId())))
                return convert.toDtoList(billDao.getAllBillByUser(id));
        }
        return null;
    }
}
