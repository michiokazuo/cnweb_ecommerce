package com.http.convert;

import com.http.dao.BillHasProductDao;
import com.http.dao_impl.BillHasProductDaoImpl;
import com.http.dto.BillDTO;
import com.http.model.Bill;
import com.http.model.BillHasProduct;

import java.util.ArrayList;
import java.util.List;

public class BillConvert implements Convert<Bill, BillDTO> {
    private final BillHasProductDao billHasProductDao = new BillHasProductDaoImpl();

    @Override
    public BillDTO toDTO(Bill bill) throws Exception {
        if (bill != null) {
            BillDTO dto = new BillDTO();
            dto.setBill(bill);

            List<BillHasProduct> billHasProducts = billHasProductDao.getListByBill(bill.getId());
            if (billHasProducts != null) {
                dto.setBillHasProducts(billHasProducts);
                dto.setNumbersOfProduct(billHasProducts.size());
                double sum = 0;
                for (BillHasProduct bp : billHasProducts) {
                    sum += bp.getProductPrice() * bp.getQuantity();
                }

                dto.setSumPrice(sum);
                return dto;
            }
        }
        return null;
    }

    @Override
    public List<BillDTO> toDtoList(List<Bill> bills) throws Exception {
        if (bills != null) {
            List<BillDTO> billDTOS = new ArrayList<>();
            for (Bill b : bills) {
                if (b != null)
                    billDTOS.add(toDTO(b));
            }
            return billDTOS.isEmpty() ? null : billDTOS;
        }
        return null;
    }

    @Override
    public Bill toModel(BillDTO billDTO) throws Exception {
        if (billDTO != null && billDTO.getBill() != null) return billDTO.getBill();
        return null;
    }
}
