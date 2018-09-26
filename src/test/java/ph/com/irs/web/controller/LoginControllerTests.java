package ph.com.irs.web.controller;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isNotNull;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matcher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ph.com.irs.web.dto.BaseResponseDTO;
import ph.com.irs.web.dto.BaseResponseDTO.Status;
import ph.com.irs.web.service.impl.LoginServiceImpl;

/**
 * Created by julius on 21/09/2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LoginControllerTests {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private LoginController loginController;

  @MockBean
  private LoginServiceImpl loginService;

  @Test
  public void testGetAllUniqueDates() throws Exception {

    List<Date> dateList = new ArrayList<>();
    dateList.add(new Date());
    BaseResponseDTO expectedResponse = new BaseResponseDTO();
    expectedResponse.setStatus(Status.SUCCESS);
    expectedResponse.setData(dateList);

    given(loginController.getAllUniqueDates()).willReturn(ResponseEntity.ok(expectedResponse));

    mockMvc.perform(get("/test/dates"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(jsonPath("$.status", is("SUCCESS")))
        .andExpect(jsonPath("$.data", is(notNullValue())));
  }
}
