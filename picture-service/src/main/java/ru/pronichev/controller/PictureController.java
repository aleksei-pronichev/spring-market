package ru.pronichev.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.pronichev.exception.PictureServiceException;
import ru.pronichev.service.PictureService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/picture")
public class PictureController {

    private final PictureService pictureService;

    @GetMapping("/{id}")
    public void getByID(@PathVariable("id") Long id, HttpServletResponse response) {
        response.setContentType(pictureService.getPictureContentTypeById(id));
        try {
            response.getOutputStream().write(pictureService.getPictureDataById(id));
        } catch (IOException e) {
            throw new PictureServiceException(e);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteByID(@PathVariable("id") Long id) {
        System.out.println("delete here");
    }
}