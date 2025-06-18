package org.example.backendproject.purewebsocket;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class htmlController {
  @GetMapping("/")
  public String index() {
    return "redirect:/purechat1.html"; // index.html 파일을 반환
  }
}
