package MyUiRestDbService.controller;

import MyUiRestDbService.dao.StudyGroupRepository;
import MyUiRestDbService.entity.StudyGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Optional;

@RestController
public class StudyGroupController {
    private final StudyGroupRepository studyGroupRepository;

    @Autowired
    public StudyGroupController(StudyGroupRepository studyGroupRepository) {
        this.studyGroupRepository = studyGroupRepository;
    }

    @GetMapping("/groups")
    public ModelAndView getAllGroups() {
        ModelAndView mav = new ModelAndView("list-groups");
        mav.addObject("groups", studyGroupRepository.findAll());
        return mav;
    }

    @GetMapping("/addGroupForm")
    public ModelAndView addGroupForm() {
        ModelAndView mav = new ModelAndView("add-group-form");
        mav.addObject("studyGroup", new StudyGroup());
        return mav;
    }

    @PostMapping("/saveGroup")
    public RedirectView saveGroup(@ModelAttribute StudyGroup studyGroup) {
        studyGroupRepository.save(studyGroup);
        return new RedirectView("groups");
    }

    @GetMapping("/showGroupUpdateForm")
    public ModelAndView showUpdateForm(@RequestParam Long groupId) {
        ModelAndView mav = new ModelAndView("add-group-form");
        Optional<StudyGroup> optionalGroup = studyGroupRepository.findById(groupId);
        StudyGroup studyGroup = new StudyGroup();
        if (optionalGroup.isPresent()) {
            studyGroup = optionalGroup.get();
        }
        mav.addObject("studyGroup", studyGroup);
        return mav;
    }

    @GetMapping("/deleteGroup")
    public RedirectView deleteGroup(@RequestParam Long groupId) {
        studyGroupRepository.deleteById(groupId);
        return new RedirectView("groups");
    }
}
