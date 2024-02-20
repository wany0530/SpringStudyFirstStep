package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController
{
    @GetMapping("/") //여기서 "/" 뜻은 localhost:8080 만 url에 입력했을때, 출력되는것을 의미한다.
    public String home()
    {
        return "home";
    }
}
