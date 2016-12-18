/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.rgata.ipb13.leha.internettehnology;

import database.dao.DAO;
import database.entity.User;
import database.service.DAOFactory;
import database.service.DataBaseService;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Алексей
 */
@Path("/rest/gate")
public class AuthorizationAndRegistrationService {

    private final DAO<User> dao;
    DAOFactory factory;
    static MessageDigest messageDigest;

    {
        DataBaseService service = DataBaseService.instanceDataBaseService();
        factory = DAOFactory.getInstance(service.getSessionFactory());
        dao = factory.getDaoBuClass(User.class);
    }

    @POST
    @Path("/authorization")
    @Produces(MediaType.APPLICATION_JSON)
    public Response authorizationUser(@FormParam("singUpInfo") String infoJsonString) {

        StringBuilder result = new StringBuilder("{\"authorization\": false");
        try {
            JSONObject jSONObject = new JSONObject(infoJsonString);
            String login = jSONObject.getString("login");
            String password = jSONObject.getString("password");

            if (!isNotNullable(login) || !isNotNullable(password)) {
                result.append("}");
                return Response.ok().entity(result.toString()).build();
            }

            User user = dao.getEntityByStringProperty("login", login);
            if (user == null) {
                result.append(", \"error\":").append("\"Пользователь с таким логином не найден\" }");
                return Response.ok().entity(result.toString()).build();
            }

            if (!user.getPassword().equals(md5Custom(password))) {
                result.append(", \"error\":").append("\"Не верный пароль\" }");
                return Response.ok().entity(result.toString()).build();
            }

            result = new StringBuilder("{\"authorization\": true, ");
            result.append("\"session\": \"").append(md5Custom(login + password)).append("\"}");
            return Response.ok().entity(result.toString()).build();
        } catch (JSONException ex) {
            Logger.getLogger(AuthorizationAndRegistrationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        result.append("}");
        return Response.serverError().entity(result.toString()).build();
    }

    @POST
    @Path("/registration")
    @Produces(MediaType.APPLICATION_JSON)
    public Response registrationUser(@FormParam("singUpInfo") String infoJsonString) {
        JSONObject jSONObject;
        try {
            jSONObject = new JSONObject(infoJsonString);
            String login = jSONObject.getString("login");
            String password = jSONObject.getString("password");
            if (!isNotNullable(login) || !isNotNullable(password)) {
                return Response.ok().entity("{\"error\":\"Не заполнены поля\"}").build();
            }

            User user = dao.getEntityByStringProperty("login", login);
            if (user != null) {
                return Response.ok().entity("{\"error\":\"Пользователь с таким логином существует\"}").build();
            }
            String hashPassword = md5Custom(password);
            user = new User().setLogin(login).setPassword(hashPassword);
            dao.addObject(user);
            return Response.ok().entity("{\"message\":\"Регистрация прошла успешно\"}").build();
        } catch (JSONException ex) {
            Logger.getLogger(AuthorizationAndRegistrationService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return Response.serverError().build();

    }

    private boolean isNotNullable(Object obj) {
        return obj != null && !obj.toString().isEmpty();
    }

    public static String md5Custom(String st) {
        byte[] digest = new byte[0];

        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(st.getBytes());
            digest = messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            // тут можно обработать ошибку
            // возникает она если в передаваемый алгоритм в getInstance(,,,) не существует
            e.printStackTrace();
        }

        BigInteger bigInt = new BigInteger(1, digest);
        String md5Hex = bigInt.toString(16);

        while (md5Hex.length() < 32) {
            md5Hex = "0" + md5Hex;
        }

        return md5Hex;
    }
}
