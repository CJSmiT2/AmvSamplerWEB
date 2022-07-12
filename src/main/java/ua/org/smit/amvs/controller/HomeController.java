package ua.org.smit.amvs.controller;

import java.io.File;
import java.io.IOException;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import ua.org.smit.amvs.exceptions.BadRequestException;
import static ua.org.smit.amvs.mvcconfig.WebAppConfig.ROOT_DIRECTORY;
import ua.org.smit.amvs.service.AmvSampler;
import ua.org.smit.commontlx.filesystem.FolderCms;
import ua.org.smit.commontlx.web.utilites.grid.GridService;

@Controller
public class HomeController {

    @Autowired
    private AmvSampler amvSampler;

    private final FolderCms posters
            = new FolderCms(ROOT_DIRECTORY + File.separator + "posters");

    @RequestMapping(value = {"/", "/home"})
    public Object home(HttpSession session) {
        ModelAndView model = new ModelAndView("home");
        model.addObject("rows", new GridService(6).makeRows(amvSampler.getGroups()));
        return model;
    }

    @RequestMapping(value = {"/group/{name}"},
            method = {RequestMethod.GET, RequestMethod.HEAD})
    public ModelAndView image(@PathVariable("name") String name) {
        ModelAndView model = new ModelAndView("group");
        model.addObject("group", amvSampler.getSamples(name));
        return model;
    }

    @ResponseBody
    @RequestMapping(value = {"/gif/{groupName}/{fragment}"},
            method = {RequestMethod.GET, RequestMethod.HEAD}, produces = {"image/gif"})
    public Object getFragmentGif(
            @PathVariable("groupName") String groupName,
            @PathVariable("fragment") String fragment,
            HttpSession session) throws IOException {

        return amvSampler.getSamples(groupName).getSample(fragment).getGif().getBytes();
    }

    @ResponseBody
    @RequestMapping(value = {"/mp4/{groupName}/{fragment}"},
            method = {RequestMethod.GET, RequestMethod.HEAD}, produces = {"video/mp4"})
    public Object getFtagmentMp4(
            @PathVariable("groupName") String groupName,
            @PathVariable("fragment") String fragment,
            HttpSession session) throws IOException {

        return amvSampler.getSamples(groupName).getSample(fragment).getMp4().getBytes();
    }

    @ResponseBody
    @RequestMapping(value = {"/poster/{groupName}"},
            method = {RequestMethod.GET, RequestMethod.HEAD}, produces = {"image/jpeg"})
    public Object getPoster(
            @PathVariable("groupName") String groupName) throws IOException {

        try {
            return posters.getByname(groupName + ".jpg").getBytes();
        } catch (RuntimeException ex) {
            throw new BadRequestException("Cant find poster by name '" + groupName + "'");
        }
    }

}
