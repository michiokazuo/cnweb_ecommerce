package com.http.controller.api;

import com.google.gson.Gson;
import com.http.dto.ProductDTO;
import com.http.model.JsonResult;
import com.http.model.Product;
import com.http.service.ProductService;
import com.http.service_impl.ProductService_Impl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductController", value = "/api/product/*")
public class ProductController extends HttpServlet {
    private final ProductService service = new ProductService_Impl();
    private final JsonResult jsonResult = new JsonResult();
    private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        String rs = "";
        int code_resp = HttpServletResponse.SC_OK;

        if (pathInfo == null) {
            code_resp = HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE;
            rs = jsonResult.getResponse("api product: path not found", null);
        } else {
            if (pathInfo.indexOf("/find-all") == 0) {
                List<Product> productList = null;
                try {
                    productList = service.findAll();
                    code_resp = productList != null ? HttpServletResponse.SC_OK : HttpServletResponse.SC_NO_CONTENT;
                } catch (Exception e) {
                    e.printStackTrace();
                    code_resp = HttpServletResponse.SC_BAD_REQUEST;
                }
                rs = jsonResult.getResponse("api product: find all " + code_resp, productList);

            } else if (pathInfo.indexOf("/find-by-id") == 0) {
                Product product = null;
                try {
                    Integer id = Integer.parseInt(req.getParameter("id"));
                    product = service.findById(id);
                    code_resp = product != null ? HttpServletResponse.SC_OK : HttpServletResponse.SC_NO_CONTENT;
                } catch (Exception e) {
                    e.printStackTrace();
                    code_resp = HttpServletResponse.SC_BAD_REQUEST;
                }
                rs = jsonResult.getResponse("api product: find by id " + code_resp, product);

            } else if (pathInfo.indexOf("/get-all-dto") == 0) {
                List<ProductDTO> productDTOS = null;
                try {
                    productDTOS = service.getAllForShow();
                    code_resp = productDTOS != null ? HttpServletResponse.SC_OK : HttpServletResponse.SC_NO_CONTENT;
                } catch (Exception e) {
                    e.printStackTrace();
                    code_resp = HttpServletResponse.SC_BAD_REQUEST;
                }
                rs = jsonResult.getResponse("api product: get all(dto) for show " + code_resp, productDTOS);

            } else if (pathInfo.indexOf("/get-dto-by-id") == 0) {
                ProductDTO productDTO = null;
                try {
                    Integer id = Integer.parseInt(req.getParameter("id"));
                    productDTO = service.getProductDTOById(id);
                    code_resp = productDTO != null ? HttpServletResponse.SC_OK : HttpServletResponse.SC_NO_CONTENT;
                } catch (Exception e) {
                    e.printStackTrace();
                    code_resp = HttpServletResponse.SC_BAD_REQUEST;
                }
                rs = jsonResult.getResponse("api product: find dto by id " + code_resp, productDTO);

            } else {
                code_resp = HttpServletResponse.SC_NOT_FOUND;
                rs = jsonResult.getResponse("api product: URL is not supported", null);
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
            rs = jsonResult.getResponse("api product: path not found", null);
        } else {
            if (pathInfo.indexOf("/insert") == 0) {
                Product new_product = null;
                try {
                    Product product = gson.fromJson(req.getReader(), Product.class);
                    new_product = service.insert(product);
                    code_resp = new_product != null ? HttpServletResponse.SC_OK : HttpServletResponse.SC_NO_CONTENT;
                } catch (Exception e) {
                    e.printStackTrace();
                    code_resp = HttpServletResponse.SC_BAD_REQUEST;
                }
                rs = jsonResult.getResponse("api product: insert " + code_resp, new_product);

            } else if (pathInfo.indexOf("/search") == 0) {
                List<Product> productList = null;
                try {
                    Product product = gson.fromJson(req.getReader(), Product.class);
                    productList = service.search(product);
                    code_resp = productList != null ? HttpServletResponse.SC_OK : HttpServletResponse.SC_NO_CONTENT;
                } catch (Exception e) {
                    e.printStackTrace();
                    code_resp = HttpServletResponse.SC_BAD_REQUEST;
                }
                rs = jsonResult.getResponse("api product: search " + code_resp, productList);

            } else if (pathInfo.indexOf("/search-dto") == 0) {
                List<ProductDTO> productDTOS = null;
                try {
                    Product product = gson.fromJson(req.getReader(), Product.class);
                    productDTOS = service.searchForShow(product);
                    code_resp = productDTOS != null ? HttpServletResponse.SC_OK : HttpServletResponse.SC_NO_CONTENT;
                } catch (Exception e) {
                    e.printStackTrace();
                    code_resp = HttpServletResponse.SC_BAD_REQUEST;
                }
                rs = jsonResult.getResponse("api product: search dto " + code_resp, productDTOS);

            } else {
                code_resp = HttpServletResponse.SC_NOT_FOUND;
                rs = jsonResult.getResponse("api product: URL is not supported", null);
            }
        }

        resp.setStatus(code_resp);
        resp.getWriter().write(rs);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        String rs = "";
        int code_resp = HttpServletResponse.SC_OK;

        if (pathInfo == null) {
            code_resp = HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE;
            rs = jsonResult.getResponse("api product: path not found", null);
        } else {
            if (pathInfo.indexOf("/update") == 0) {
                Product update_product = null;
                try {
                    Product product = gson.fromJson(req.getReader(), Product.class);
                    update_product = service.update(product);
                    code_resp = update_product != null ? HttpServletResponse.SC_OK : HttpServletResponse.SC_NO_CONTENT;
                } catch (Exception e) {
                    e.printStackTrace();
                    code_resp = HttpServletResponse.SC_BAD_REQUEST;
                }
                rs = jsonResult.getResponse("api product: update " + code_resp, update_product);

            } else {
                code_resp = HttpServletResponse.SC_NOT_FOUND;
                rs = jsonResult.getResponse("api product: URL is not supported", null);
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
            rs = jsonResult.getResponse("api product: path not found", null);
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
                rs = jsonResult.getResponse("api product: delete " + code_resp, delete);

            } else {
                code_resp = HttpServletResponse.SC_NOT_FOUND;
                rs = jsonResult.getResponse("api product: URL is not supported", null);
            }
        }

        resp.setStatus(code_resp);
        resp.getWriter().write(rs);
    }
}
