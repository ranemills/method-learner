package com.mills.controllers.layout;

import com.mills.constants.HtmlFiles;
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

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.mills.constants.PageRoutes.redirect;

@Controller
public class MethodsViewController {

    private final MethodService _methodService;

    @Autowired
    public MethodsViewController(MethodService methodService) {
        _methodService = methodService;
    }

    @GetMapping("/methods")
    public String methodManagement(Map<String, Object> model)
    {
        model.put("existingMethods", _methodService.getMethods());
        model.put("method", new MethodModel());

        return HtmlFiles.METHOD_MANAGEMENT;
    }

}
