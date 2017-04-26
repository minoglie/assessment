package rest.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import rest.bean.DirectoryStatsBean;
import rest.service.DirectoryService;

import java.util.Collection;

@Controller
public class CommandLineController {

    //This controller handles the command line and returns a JSON string
    @RequestMapping(value = "/command-line", method = RequestMethod.GET)
    @ResponseBody
    public String commandLine(@RequestParam(value="path", required=true) String path) {
        Collection<DirectoryStatsBean> directoryStatsBeanCollection = DirectoryService.calculateDirectoryStats(path);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        String arrayToJson = "";
        try {
            arrayToJson = objectMapper.writeValueAsString(directoryStatsBeanCollection);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        
        return arrayToJson;
    }
}
