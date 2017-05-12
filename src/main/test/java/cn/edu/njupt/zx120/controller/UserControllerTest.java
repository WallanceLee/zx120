package controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * Created by wallance on 3/21/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {
        "classpath:hibernate.cfg.xml",
        "classpath:spring-bean.xml",
        "classpath:spring-dao.xml",
        "classpath:spring-shiro.xml",
        "classpath:spring-web.xml"
})
public class UserControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    private static final MediaType APPLICATION_JSON_UTF8 = new MediaType(
            MediaType.APPLICATION_JSON_UTF8.getType()
    );

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    public void getList() throws Exception {
        this.mockMvc.perform(get("/user/getList")).andDo(print());
    }

    @Test
    public void addNewMember() throws Exception {

    }

    @Test
    public void deleteMember() throws Exception {

    }

    @Test
    public void assignUserRole() throws Exception {

    }

    @Test
    public void login() throws Exception {
        String usernamePassword = "liyanxu:123456";
        this.mockMvc.perform(post("/user/login")
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                                .content("{auth: \"liyanxu:123456\"}")).andDo(print());
    }

}