package com.http.controller.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.http.dto.BillDTO;
import com.http.model.JsonResult;
import com.http.service.BillService;
import com.http.service_impl.BillService_Impl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "BillController", value = "/api/bill/*")
public class BillController extends HttpServlet {
    private final BillService service = new BillService_Impl();
    private final JsonResult jsonResult = new JsonResult();
    private final Gson gson = new GsonBuilder().create();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        String rs = "";
        int code_resp = HttpServletResponse.SC_OK;

        if (pathInfo == null) {
            code_resp = HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE;
            rs = jsonResult.getResponse("api bill: path not found", null);
        } else {
            if (pathInfo.indexOf("/find-all") == 0) {
                List<BillDTO> billDTOS = null;
                try {
                    billDTOS = service.findAll();
                    code_resp = billDTOS != null ? HttpServletResponse.SC_OK : HttpServletResponse.SC_NO_CONTENT;
                } catch (Exception e) {
                    e.printStackTrace();
                    code_resp = HttpServletResponse.SC_BAD_REQUEST;
                }
                rs = jsonResult.getResponse("api bill: find all " + code_resp, billDTOS);

            } else if (pathInfo.indexOf("/find-by-id") == 0) {
                BillDTO bill = null;
                try {
                    Integer id = Integer.parseInt(req.getParameter("id"));
                    bill = service.findById(id);
                    code_resp = bill != null ? HttpServletResponse.SC_OK : HttpServletResponse.SC_NO_CONTENT;
                } catch (Exception e) {
                    e.printStackTrace();
                    code_resp = HttpServletResponse.SC_BAD_REQUEST;
                }
                rs = jsonResult.getResponse("api bill: find by id " + code_resp, bill);

            } else if (pathInfo.indexOf("/get-all-by-user") == 0) {
                List<BillDTO> billDTOS = null;
                try {
                    Integer id = Integer.parseInt(req.getParameter("id"));
                    billDTOS = service.getAllBillByUser(id);
                    code_resp = billDTOS != null ? HttpServletResponse.SC_OK : HttpServletResponse.SC_NO_CONTENT;
                } catch (Exception e) {
                    e.printStackTrace();
                    code_resp = HttpServletResponse.SC_BAD_REQUEST;
                }
                rs = jsonResult.getResponse("api bill: get all by user " + code_resp, billDTOS);

            } else {
                code_resp = HttpServletResponse.SC_NOT_FOUND;
                rs = jsonResult.getResponse("api bill: URL is not supported", null);
            }
        }

        resp.setStatus(code_resp);
        resp.getWriter().write(rs);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        String rs = "";
        int code_resp = HttpServletResponse.SC_OK;

        if (pathInfo == null) {
            code_resp = HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE;
            rs = jsonResult.getResponse("api bill: path not found", null);
        } else {
            if (pathInfo.indexOf("/insert") == 0) {
                BillDTO new_bill = null;
                try {
                    BillDTO bill = gson.fromJson(req.getReader(), BillDTO.class);
                    new_bill = service.insert(bill);
                    code_resp = new_bill != null ? HttpServletResponse.SC_OK : HttpServletResponse.SC_NO_CONTENT;
                } catch (Exception e) {
                    e.printStackTrace();
                    code_resp = HttpServletResponse.SC_BAD_REQUEST;
                }
                rs = jsonResult.getResponse("api bill: insert " + code_resp, new_bill);

            } else if (pathInfo.indexOf("/accept") == 0) {
                boolean accept = false;
                try {
                    Integer id = Integer.parseInt(req.getParameter("id"));
                    accept = service.acceptBill(id);
                    code_resp = accept ? HttpServletResponse.SC_OK : HttpServletResponse.SC_NO_CONTENT;
                } catch (Exception e) {
                    e.printStackTrace();
                    code_resp = HttpServletResponse.SC_BAD_REQUEST;
                }
                rs = jsonResult.getResponse("api bill: accept bill " + code_resp, accept);

            } else {
                code_resp = HttpServletResponse.SC_NOT_FOUND;
                rs = jsonResult.getResponse("api bill: URL is not supported", null);
            }
        }

        resp.setStatus(code_resp);
        resp.getWriter().write(rs);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        String rs = "";
        int code_resp = HttpServletResponse.SC_OK;

        if (pathInfo == null) {
            code_resp = HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE;
            rs = jsonResult.getResponse("api bill: path not found", null);
        } else {
            if (pathInfo.indexOf("/delete") == 0) {
                boolean delete = false;
                try {
                    Integer id = Integer.parseInt(req.getParameter("id"));
                    delete = service.delete(id);
                    code_resp = delete ? HttpServletResponse.SC_OK : HttpServletResponse.SC_NO_CONTENT;
                } catch (Exception e) {
                    e.printStackTrace();
                    code_resp = HttpServletResponse.SC_BAD_REQUEST;
                }
                rs = jsonResult.getResponse("api bill: delete " + code_resp, delete);

            } else {
                code_resp = HttpServletResponse.SC_NOT_FOUND;
                rs = jsonResult.getResponse("api bill: URL is not supported", null);
            }
        }

        resp.setStatus(code_resp);
        resp.getWriter().write(rs);
    }
}
