package com.mills.controllers;

import com.mills.constants.HtmlFiles;
import com.mills.constants.PageRoutes;
import com.mills.models.LearningResult;
import com.mills.models.PlaceBell;
import com.mills.models.Rating;
import com.mills.models.template.MethodModel;
import com.mills.models.template.PlaceBellResult;
import com.mills.services.MethodService;
import com.mills.services.PlaceBellService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

import static com.mills.constants.PageRoutes.redirect;

@Controller
public class MainController {

    private final MethodService _methodService;
    private final PlaceBellService _placeBellService;

    @Autowired
    public MainController(MethodService methodService, PlaceBellService placeBellService) {
        _methodService = methodService;
        _placeBellService = placeBellService;
    }

    @GetMapping("/")
    public String welcome(Map<String, Object> model)
    {
        model.put("existingMethods", _methodService.getMethods());
        model.put("method", new MethodModel());

        PlaceBell nextPlaceBell = _placeBellService.getNextPlaceBell();
        model.put("nextPlaceBell", nextPlaceBell);

        PlaceBellResult placeBellResult = new PlaceBellResult();
        if(nextPlaceBell != null) {
            placeBellResult.setPlaceBell(nextPlaceBell.getPlaceBellNumber());
            placeBellResult.setMethodName(nextPlaceBell.getMethodName());
        }
        model.put("placeBellResult", placeBellResult);

        return HtmlFiles.HOME;
    }

    @PostMapping("/result")
    public String addRating(final PlaceBellResult placeBellResult, final BindingResult bindingResult, final ModelMap model) {
        if (bindingResult.hasErrors()) {
            for(ObjectError err : bindingResult.getAllErrors())
            {
                System.out.println(err);
            }
            return HtmlFiles.HOME;
        }

        _placeBellService.addLearningResultForPlaceBell(placeBellResult.getMethodName(), placeBellResult.getPlaceBell(), LearningResult.of(DateTime.now(), Rating.valueOf(placeBellResult.getValue())));

        model.clear();
        return redirect(PageRoutes.HOME);
    }

}

