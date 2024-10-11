package alkaz.springwebthree;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Scanner;

@Controller
public class FileAnalyzeController {
    @GetMapping("/analyze")
    public String showAnalyzeForm()
    {return "analyze";}

    @PostMapping("/analyze")
    public String makeAnalyze(@RequestParam(name = "myfile")MultipartFile file,
                              Model model){
        if(file == null || file.isEmpty())
            model.addAttribute("message", "файлов нет");
        else {
            model.addAttribute("message", "был загружен файл " + file.getOriginalFilename());
            model.addAttribute("size", file.getSize());
            try {
                String s = getFirstLine(file);
                model.addAttribute("firstline", s);
            } catch (IOException e) {
                model.addAttribute("erroMessage", e.getMessage());
            }
        }
        return "analyze";
    }

    private String getFirstLine(MultipartFile file) throws IOException {
        Scanner scanner= new Scanner(file.getInputStream());
        if(scanner.hasNext())
            return scanner.nextLine();
        return "ничего не прочиталось";
    }

}
