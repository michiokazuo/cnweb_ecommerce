package com.http.controller.api;

import com.google.gson.Gson;
import com.http.config.AppConfig;
import com.http.convert.Convert;
import com.http.convert.UserConvert;
import com.http.dto.UserDTO;
import com.http.model.JsonResult;
import com.http.model.User;
import com.http.service.UserService;
import com.http.service_impl.UserServiceImpl;
import com.http.utils.PasswordUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UserController", value = "/api/user/*")
public class UserController extends HttpServlet {
    private final UserService service = new UserServiceImpl();
    private final Convert<User, UserDTO> convert = new UserConvert();
    private final JsonResult jsonResult = new JsonResult();
    private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        String rs = "";
        int code_resp = HttpServletResponse.SC_OK;

        if (pathInfo == null) {
            code_resp = HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE;
            rs = jsonResult.getResponse("api user: path not found", null);
        } else {
            if (pathInfo.indexOf("/find-all") == 0) {
                List<User> userList = null;
                try {
                    userList = service.findAll();
                    code_resp = userList != null ? HttpServletResponse.SC_OK : HttpServletResponse.SC_NO_CONTENT;
                } catch (Exception e) {
                    e.printStackTrace();
                    code_resp = HttpServletResponse.SC_BAD_REQUEST;
                }
                rs = jsonResult.getResponse("api user: find all " + code_resp, userList);

            } else if (pathInfo.indexOf("/find-by-id") == 0) {
                User user = null;
                try {
                    Integer id = Integer.parseInt(req.getParameter("id"));
                    user = service.findById(id);
                    code_resp = user != null ? HttpServletResponse.SC_OK : HttpServletResponse.SC_NO_CONTENT;
                } catch (Exception e) {
                    e.printStackTrace();
                    code_resp = HttpServletResponse.SC_BAD_REQUEST;
                }
                rs = jsonResult.getResponse("api user: find by id " + code_resp, user);

            } else {
                code_resp = HttpServletResponse.SC_NOT_FOUND;
                rs = jsonResult.getResponse("api user: URL is not supported", null);
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
            rs = jsonResult.getResponse("api user: path not found", null);
        } else {
            if (pathInfo.indexOf("/insert") == 0) {
                User new_user = null;
                try {
                    User user = gson.fromJson(req.getReader(), User.class);
                    new_user = service.insert(user);
                    code_resp = new_user != null ? HttpServletResponse.SC_OK : HttpServletResponse.SC_NO_CONTENT;
                    AppConfig.userInSysTem = convert.toDTO(new_user);
                    if (user != null) {
                        Cookie cookie = new Cookie("login-cookie", PasswordUtil.encode(user.getEmail()));
                        cookie.setMaxAge(-1); // until browser shut down
                        resp.addCookie(cookie);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    code_resp = HttpServletResponse.SC_BAD_REQUEST;
                }
                rs = jsonResult.getResponse("api user: insert " + code_resp, new_user);

            } else if (pathInfo.indexOf("/search") == 0) {
                List<User> userList = null;
                try {
                    User user = gson.fromJson(req.getReader(), User.class);
                    userList = service.search(user);
                    code_resp = userList != null ? HttpServletResponse.SC_OK : HttpServletResponse.SC_NO_CONTENT;
                } catch (Exception e) {
                    e.printStackTrace();
                    code_resp = HttpServletResponse.SC_BAD_REQUEST;
                }
                rs = jsonResult.getResponse("api user: search " + code_resp, userList);

            } else {
                code_resp = HttpServletResponse.SC_NOT_FOUND;
                rs = jsonResult.getResponse("api user: URL is not supported", null);
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
            rs = jsonResult.getResponse("api user: path not found", null);
        } else {
            if (pathInfo.indexOf("/update") == 0) {
                User update_user = null;
                try {
                    User user = gson.fromJson(req.getReader(), User.class);
                    update_user = service.update(user);
                    code_resp = update_user != null ? HttpServletResponse.SC_OK : HttpServletResponse.SC_NO_CONTENT;
                    AppConfig.userInSysTem = convert.toDTO(update_user);
                    if (user != null) {
                        Cookie cookie = new Cookie("login-cookie", PasswordUtil.encode(user.getEmail()));
                        cookie.setMaxAge(-1); // until browser shut down
                        resp.addCookie(cookie);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    code_resp = HttpServletResponse.SC_BAD_REQUEST;
                }
                rs = jsonResult.getResponse("api user: update " + code_resp, update_user);

            } else {
                code_resp = HttpServletResponse.SC_NOT_FOUND;
                rs = jsonResult.getResponse("api user: URL is not supported", null);
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
            rs = jsonResult.getResponse("api user: path not found", null);
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
                rs = jsonResult.getResponse("api user: delete " + code_resp, delete);

            } else {
                code_resp = HttpServletResponse.SC_NOT_FOUND;
                rs = jsonResult.getResponse("api user: URL is not supported", null);
            }
        }

        resp.setStatus(code_resp);
        resp.getWriter().write(rs);
    }
}
