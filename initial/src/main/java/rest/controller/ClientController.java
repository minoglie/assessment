package rest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import rest.bean.DirectoryStatsBean;
import rest.service.DirectoryService;

import java.util.Collection;

@Controller
public class ClientController {

    //This controller handles the client web app and return an html template
    @RequestMapping(value = "/client", method = RequestMethod.GET)
    public String directory(@RequestParam(value="path", required=true) String path, Model model) {
        Collection<DirectoryStatsBean> directoryStatsBeanCollection = DirectoryService.calculateDirectoryStats(path);
        model.addAttribute("directoryStatsBeanCollection", directoryStatsBeanCollection);

        return "directory";
    }

}
