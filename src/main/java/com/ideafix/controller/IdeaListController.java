package com.ideafix.controller;

import com.ideafix.exception.RestException;
import com.ideafix.model.dto.IdeaListDTO;
import com.ideafix.model.pojo.IdeaList;
import com.ideafix.model.security.JwtUser;
import com.ideafix.service.IdeaListService;
import com.ideafix.service.util.ValidationUtil;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.ideafix.model.response.ControllerResponseEntity.*;

@RestController
@RequestMapping("/lists")
public class IdeaListController {
    private IdeaListService ideaListService;

    public IdeaListController(IdeaListService ideaListService) {
        this.ideaListService = ideaListService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Map<String, Object> getAll() {
        return successResponse("data", ideaListService.getAll());
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public Map<String, Object> getByUserId(@RequestParam(value = "id") long userId) {
        return successResponse("data", ideaListService.getByUserId(userId));
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Map<String, Object> getAll(@RequestParam(value = "id") long id) {
        return successResponse("data", ideaListService.getById(id));
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Map<String, Object> create(@RequestBody IdeaListDTO ideaListDTO) throws RestException {
        try {
            JwtUser user = (JwtUser) SecurityContextHolder
                    .getContext()
                    .getAuthentication()
                    .getPrincipal();
            ideaListDTO.setAuthorId(user.getId());
            IdeaList ideaList = ideaListService.create(ideaListDTO);

            return successResponse("data", ideaList);
        } catch (Exception e) {
            throw new RestException(e.getMessage(), e);
        }
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public Map<String, Object> edit(@RequestBody IdeaListDTO ideaListDTO) throws RestException {
        try {
            JwtUser user = (JwtUser) SecurityContextHolder
                    .getContext()
                    .getAuthentication()
                    .getPrincipal();

            ValidationUtil.assertEquals(user.getId(),
                    ideaListDTO.getAuthorId(),
                    "User's and List's of ideas id");
            IdeaList ideaList = ideaListService.edit(ideaListDTO);

            return successResponse("data", ideaList);
        } catch (Exception e) {
            throw new RestException(e.getMessage(), e);
        }
    }

    @RequestMapping(value = "/", method = RequestMethod.DELETE)
    public Map<String, Object> delete(@RequestParam(value = "id") long id) throws RestException {
        try {
            ideaListService.delete(id);

            return emptyResponse();
        } catch (Exception e) {
            throw new RestException(e.getMessage(), e);
        }
    }
}