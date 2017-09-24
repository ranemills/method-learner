package com.mills.controllers.api;

import com.mills.constants.PageRoutes;
import com.mills.models.PlaceBellNumber;
import com.mills.models.template.MethodModel;
import com.mills.services.MethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.mills.constants.PageRoutes.redirect;

@Controller
@RequestMapping("/api/methods")
public class MethodsController {

    private final MethodService _methodService;

    @Autowired
    public MethodsController(MethodService methodService) {
        _methodService = methodService;
    }

    @PostMapping
    public String addMethod(final MethodModel methodModel, final BindingResult bindingResult, final ModelMap model) {
        if (bindingResult.hasErrors()) {
            for(ObjectError err : bindingResult.getAllErrors())
            {
                System.out.println(err);
            }
            return PageRoutes.METHOD_MANAGEMENT;
        }

        List<PlaceBellNumber> placeBellNumbers = methodModel.getPlaceBells().stream().map(PlaceBellNumber::valueOf).collect(Collectors.toList());

        _methodService.addMethod(methodModel.getMethodName(), placeBellNumbers);

        model.clear();
        return redirect(PageRoutes.METHOD_MANAGEMENT);
    }

}
