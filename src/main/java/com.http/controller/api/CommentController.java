package com.http.controller.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.http.dto.ProductDTO;
import com.http.model.Comment;
import com.http.model.JsonResult;
import com.http.service.CommentService;
import com.http.service_impl.CommentService_Impl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CommentController", value = "/api/comment/*")
public class CommentController extends HttpServlet {
    private final CommentService service = new CommentService_Impl();
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
            rs = jsonResult.getResponse("api comment: path not found", null);
        } else {
            if (pathInfo.indexOf("/find-all") == 0) {
                List<Comment> commentList = null;
                try {
                    commentList = service.findAll();
                    code_resp = commentList != null ? HttpServletResponse.SC_OK : HttpServletResponse.SC_NO_CONTENT;
                } catch (Exception e) {
                    e.printStackTrace();
                    code_resp = HttpServletResponse.SC_BAD_REQUEST;
                }
                rs = jsonResult.getResponse("api comment: find all " + code_resp, commentList);

            } else if (pathInfo.indexOf("/find-by-id") == 0) {
                Comment comment = null;
                try {
                    Integer id = Integer.parseInt(req.getParameter("id"));
                    comment = service.findById(id);
                    code_resp = comment != null ? HttpServletResponse.SC_OK : HttpServletResponse.SC_NO_CONTENT;
                } catch (Exception e) {
                    e.printStackTrace();
                    code_resp = HttpServletResponse.SC_BAD_REQUEST;
                }
                rs = jsonResult.getResponse("api comment: find by id " + code_resp, comment);

            } else if (pathInfo.indexOf("/get-all-by-product") == 0) {
                List<Comment> commentList = null;
                try {
                    Integer id = Integer.parseInt(req.getParameter("id"));
                    commentList = service.getAllByProduct(id);
                    code_resp = commentList != null ? HttpServletResponse.SC_OK : HttpServletResponse.SC_NO_CONTENT;
                } catch (Exception e) {
                    e.printStackTrace();
                    code_resp = HttpServletResponse.SC_BAD_REQUEST;
                }
                rs = jsonResult.getResponse("api comment: find all " + code_resp, commentList);

            } else if (pathInfo.indexOf("/get-all-by-user") == 0) {
                List<Comment> commentList = null;
                try {
                    Integer id = Integer.parseInt(req.getParameter("id"));
                    commentList = service.getALlByUser(id);
                    code_resp = commentList != null ? HttpServletResponse.SC_OK : HttpServletResponse.SC_NO_CONTENT;
                } catch (Exception e) {
                    e.printStackTrace();
                    code_resp = HttpServletResponse.SC_BAD_REQUEST;
                }
                rs = jsonResult.getResponse("api comment: find all " + code_resp, commentList);

            } else if (pathInfo.indexOf("/get-about-rate") == 0) {
                ProductDTO productDTO = null;
                try {
                    Integer id = Integer.parseInt(req.getParameter("id"));
                    productDTO = service.getAboutRateOfProduct(id);
                    code_resp = productDTO != null ? HttpServletResponse.SC_OK : HttpServletResponse.SC_NO_CONTENT;
                } catch (Exception e) {
                    e.printStackTrace();
                    code_resp = HttpServletResponse.SC_BAD_REQUEST;
                }
                rs = jsonResult.getResponse("api comment: get about rate of product " + code_resp, productDTO);

            } else {
                code_resp = HttpServletResponse.SC_NOT_FOUND;
                rs = jsonResult.getResponse("api comment: URL is not supported", null);
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
            rs = jsonResult.getResponse("api comment: path not found", null);
        } else {
            if (pathInfo.indexOf("/insert") == 0) {
                Comment new_comment = null;
                try {
                    Comment comment = gson.fromJson(req.getReader(), Comment.class);
                    new_comment = service.insert(comment);
                    code_resp = new_comment != null ? HttpServletResponse.SC_OK : HttpServletResponse.SC_NO_CONTENT;
                } catch (Exception e) {
                    e.printStackTrace();
                    code_resp = HttpServletResponse.SC_BAD_REQUEST;
                }
                rs = jsonResult.getResponse("api comment: insert " + code_resp, new_comment);

            } else {
                code_resp = HttpServletResponse.SC_NOT_FOUND;
                rs = jsonResult.getResponse("api comment: URL is not supported", null);
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
            rs = jsonResult.getResponse("api comment: path not found", null);
        } else {
            if (pathInfo.indexOf("/update") == 0) {
                Comment update_comment = null;
                try {
                    Comment comment = gson.fromJson(req.getReader(), Comment.class);
                    update_comment = service.update(comment);
                    code_resp = update_comment != null ? HttpServletResponse.SC_OK : HttpServletResponse.SC_NO_CONTENT;
                } catch (Exception e) {
                    e.printStackTrace();
                    code_resp = HttpServletResponse.SC_BAD_REQUEST;
                }
                rs = jsonResult.getResponse("api comment: update " + code_resp, update_comment);

            } else if (pathInfo.indexOf("/update-rate-by-user") == 0) {
                boolean update = false;
                try {
                    Double rate = Double.valueOf(req.getParameter("rate"));
                    Integer id_user = Integer.parseInt(req.getParameter("id_user"));
                    Integer id_product = Integer.parseInt(req.getParameter("id_product"));
                    update = service.updateRateByUser(rate, id_user, id_product);
                    code_resp = update ? HttpServletResponse.SC_OK : HttpServletResponse.SC_NO_CONTENT;
                } catch (Exception e) {
                    e.printStackTrace();
                    code_resp = HttpServletResponse.SC_BAD_REQUEST;
                }
                rs = jsonResult.getResponse("api comment: update rate by user " + code_resp, update);

            } else {
                code_resp = HttpServletResponse.SC_NOT_FOUND;
                rs = jsonResult.getResponse("api comment: URL is not supported", null);
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
            rs = jsonResult.getResponse("api comment: path not found", null);
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
                rs = jsonResult.getResponse("api comment: delete " + code_resp, delete);

            } else {
                code_resp = HttpServletResponse.SC_NOT_FOUND;
                rs = jsonResult.getResponse("api comment: URL is not supported", null);
            }
        }

        resp.setStatus(code_resp);
        resp.getWriter().write(rs);
    }
}
