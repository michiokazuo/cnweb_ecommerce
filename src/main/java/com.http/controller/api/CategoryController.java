package com.http.controller.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.http.dto.CategoryDTO;
import com.http.model.JsonResult;
import com.http.service.CategoryService;
import com.http.service_impl.CategoryService_Impl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CategoryController", value = "/api/category/*")
public class CategoryController extends HttpServlet {
    private final CategoryService service = new CategoryService_Impl();
    private final JsonResult jsonResult = new JsonResult();
    private final Gson gson = new GsonBuilder()
//            .setDateFormat("MM-dd-yyyy")
            .create();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        String rs = "";
        int code_resp = HttpServletResponse.SC_OK;

        if (pathInfo == null) {
            code_resp = HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE;
            rs = jsonResult.getResponse("api category: path not found", null);
        } else {
            if (pathInfo.indexOf("/find-all") == 0) {
                List<CategoryDTO> categoryList = null;
                try {
                    categoryList = service.findAll();
                    code_resp = categoryList != null ? HttpServletResponse.SC_OK : HttpServletResponse.SC_NO_CONTENT;
                } catch (Exception e) {
                    e.printStackTrace();
                    code_resp = HttpServletResponse.SC_BAD_REQUEST;
                }
                rs = jsonResult.getResponse("api category: find all " + code_resp, categoryList);

            } else if (pathInfo.indexOf("/find-by-id") == 0) {
                CategoryDTO category = null;
                try {
                    Integer id = Integer.parseInt(req.getParameter("id"));
                    category = service.findById(id);
                    code_resp = category != null ? HttpServletResponse.SC_OK : HttpServletResponse.SC_NO_CONTENT;
                } catch (Exception e) {
                    e.printStackTrace();
                    code_resp = HttpServletResponse.SC_BAD_REQUEST;
                }
                rs = jsonResult.getResponse("api category: find by id " + code_resp, category);

            } else {
                code_resp = HttpServletResponse.SC_NOT_FOUND;
                rs = jsonResult.getResponse("api category: URL is not supported", null);
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
            rs = jsonResult.getResponse("api category: path not found", null);
        } else {
            if (pathInfo.indexOf("/insert") == 0) {
                CategoryDTO new_category = null;
                try {
                    CategoryDTO category = gson.fromJson(req.getReader(), CategoryDTO.class);
                    new_category = service.insert(category);
                    code_resp = new_category != null ? HttpServletResponse.SC_OK : HttpServletResponse.SC_NO_CONTENT;
                } catch (Exception e) {
                    e.printStackTrace();
                    code_resp = HttpServletResponse.SC_BAD_REQUEST;
                }
                rs = jsonResult.getResponse("api category: insert " + code_resp, new_category);

            } else if (pathInfo.indexOf("/search") == 0) {
                List<CategoryDTO> categoryList = null;
                try {
                    CategoryDTO category = gson.fromJson(req.getReader(), CategoryDTO.class);
                    categoryList = service.search(category);
                    code_resp = categoryList != null ? HttpServletResponse.SC_OK : HttpServletResponse.SC_NO_CONTENT;
                } catch (Exception e) {
                    e.printStackTrace();
                    code_resp = HttpServletResponse.SC_BAD_REQUEST;
                }
                rs = jsonResult.getResponse("api category: search " + code_resp, categoryList);

            } else {
                code_resp = HttpServletResponse.SC_NOT_FOUND;
                rs = jsonResult.getResponse("api category: URL is not supported", null);
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
            rs = jsonResult.getResponse("api category: path not found", null);
        } else {
            if (pathInfo.indexOf("/update") == 0) {
                CategoryDTO update_category = null;
                try {
                    CategoryDTO category = gson.fromJson(req.getReader(), CategoryDTO.class);
                    update_category = service.update(category);
                    code_resp = update_category != null ? HttpServletResponse.SC_OK : HttpServletResponse.SC_NO_CONTENT;
                } catch (Exception e) {
                    e.printStackTrace();
                    code_resp = HttpServletResponse.SC_BAD_REQUEST;
                }
                rs = jsonResult.getResponse("api category: update " + code_resp, update_category);

            } else {
                code_resp = HttpServletResponse.SC_NOT_FOUND;
                rs = jsonResult.getResponse("api category: URL is not supported", null);
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
            rs = jsonResult.getResponse("api category: path not found", null);
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
                rs = jsonResult.getResponse("api category: delete " + code_resp, delete);

            } else {
                code_resp = HttpServletResponse.SC_NOT_FOUND;
                rs = jsonResult.getResponse("api category: URL is not supported", null);
            }
        }

        resp.setStatus(code_resp);
        resp.getWriter().write(rs);
    }
}
