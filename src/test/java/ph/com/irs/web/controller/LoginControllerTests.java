package ph.com.irs.web.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hamcrest.core.AnyOf;
import org.hamcrest.core.StringContains;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ph.com.irs.web.dto.BaseResponseDTO;
import ph.com.irs.web.dto.BaseResponseDTO.Status;

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
        .andExpect(jsonPath("data", hasSize(1)));


  }

}
