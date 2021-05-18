package com.http.controller.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.http.config.AppConfig;
import com.http.dao.UserDao;
import com.http.dao_impl.UserDaoImpl;
import com.http.dto.UserDTO;
import com.http.model.JsonResult;
import com.http.model.Role;
import com.http.payload.LoginForm;
import com.http.service.FileService;
import com.http.service.UserService;
import com.http.service_impl.FileService_Impl;
import com.http.service_impl.UserServiceImpl;
import com.http.utils.PasswordUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "IOController", value = "/api/io/*")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 50
        , maxRequestSize = 1024 * 1024 * 50)
public class IOController extends HttpServlet {
    private final UserService userService = new UserServiceImpl();
    private final UserDao userDao = new UserDaoImpl();
    private final FileService fileService = new FileService_Impl();
    private final JsonResult jsonResult = new JsonResult();
    private final Gson gson = new GsonBuilder().create();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        String rs = "";
        int code_resp = -1;

        if (pathInfo == null) {
            code_resp = HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE;
            rs = jsonResult.getResponse("api io: path not found", null);
        } else {
            if (pathInfo.indexOf("/log-out") == 0) {
                Cookie cookie = new Cookie("login-cookie", PasswordUtil.encode("log-out"));
                cookie.setMaxAge(0);// remove cookie
                AppConfig.userInSysTem = null;
                code_resp = HttpServletResponse.SC_OK;
                resp.addCookie(cookie);
//                resp.sendRedirect("/"); // url-home || url-login
            } else if (pathInfo.indexOf("/get-user-in-system") == 0) {
                UserDTO user = null;
                try {
                    user = AppConfig.userInSysTem;
                    code_resp = user != null ? HttpServletResponse.SC_OK : HttpServletResponse.SC_NO_CONTENT;
                } catch (Exception e) {
                    e.printStackTrace();
                    code_resp = HttpServletResponse.SC_BAD_REQUEST;
                }
                rs = jsonResult.getResponse("api user: find by id " + code_resp, user);

            } else if (pathInfo.indexOf("/get-role") == 0) {
                List<Role> roles = null;
                try {
                    roles = userDao.getRole();
                    code_resp = roles != null ? HttpServletResponse.SC_OK : HttpServletResponse.SC_NO_CONTENT;
                } catch (Exception e) {
                    e.printStackTrace();
                    code_resp = HttpServletResponse.SC_BAD_REQUEST;
                }
                rs = jsonResult.getResponse("api io: get role " + code_resp, roles);

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
            rs = jsonResult.getResponse("api io: path not found", null);
        } else {
            if (pathInfo.indexOf("/log-in") == 0) { // thinking...
                UserDTO user = null;
                try {
                    Cookie[] cookies = req.getCookies();
                    boolean check = true;
                    code_resp = HttpServletResponse.SC_UNAUTHORIZED;
                    if (cookies != null) {
                        for (Cookie cookie : cookies) {
                            if (cookie.getName().equals("login-cookie") && cookie.getMaxAge() != 0) {
                                check = false;
                                break;
                            }
                        }
                    }

                    if(check) {
                        LoginForm loginForm = gson.fromJson(req.getReader(), LoginForm.class);
                        user = userService.login(loginForm);
                        code_resp = user != null ? HttpServletResponse.SC_OK : HttpServletResponse.SC_NO_CONTENT;
                        AppConfig.userInSysTem = user;
                        if (user != null) {
                            Cookie cookie = new Cookie("login-cookie", PasswordUtil.encode(user.getEmail()));
                            cookie.setMaxAge(-1);
                            resp.addCookie(cookie);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    code_resp = HttpServletResponse.SC_BAD_REQUEST;
                }
                rs = jsonResult.getResponse("api io: login " + code_resp, user);

            } else if (pathInfo.indexOf("/upload-files") == 0) {
                List<String> files = null;
                try {
                    files = fileService.uploadFiles(req.getParts());
                    code_resp = files != null ? HttpServletResponse.SC_OK : HttpServletResponse.SC_NO_CONTENT;
                } catch (Exception e) {
                    e.printStackTrace();
                    code_resp = HttpServletResponse.SC_BAD_REQUEST;
                }
                rs = jsonResult.getResponse("api io: upload files " + code_resp, files);

            } else {
                code_resp = HttpServletResponse.SC_NOT_FOUND;
                rs = jsonResult.getResponse("api user: URL is not supported", null);
            }
        }

        resp.setStatus(code_resp);
        resp.getWriter().write(rs);
    }
}
