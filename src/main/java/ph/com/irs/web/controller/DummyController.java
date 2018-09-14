package ph.com.irs.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ph.com.irs.web.service.RecordGenerationService;

/**
 * Created by julius on 10/09/2018.
 */
@RestController
@RequestMapping("/dummy")
public class DummyController {

  @Autowired
  private RecordGenerationService generationService;

  @GetMapping("/generate")
  public void generateDummyData(@RequestParam(value = "count", required = false) long count) {
    generationService.doGenerate(count);
  }

}
