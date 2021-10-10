package ru.adespina.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebInputException;
import ru.adespina.controller.dto.*;
import ru.adespina.emulation.EmulationServiceImpl;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/emulation")
public class EmulationController {

    private final EmulationServiceImpl emulationService;

    public EmulationController(EmulationServiceImpl emulationService) {
        this.emulationService = emulationService;
    }

    private static final String STRUCTURE = "Structure.xml";
    private static final String P_ACT_NUMBER = "number";

    @PostMapping("/create")
    @ResponseBody
    public ResponseEntity<EmulationCreateStructureReply> clientRequest(@RequestBody EmulationCreateStructureRequest request) {
        List<Folder> folderList = spawnObjectForJsonReply(request);
        return new ResponseEntity<>(new EmulationCreateStructureReply(folderList), HttpStatus.OK);
    }

    private String requestValidation(EmulationCreateStructureRequest request) {
        List<Folder> folderList = request.getFolders();
        if (folderList == null || folderList.isEmpty()) {
            throw new ServerWebInputException("Empty folder " + ResponseEntity.badRequest());
        }
        if (folderList.size() > 1) {
            throw new ServerWebInputException("Multiple folders not supported " + ResponseEntity.badRequest());
        }
        Folder folder = folderList.get(0);

        String template = folder.getTemplate();

        if (!template.equals(STRUCTURE)) {
            throw new ServerWebInputException(
                    "Template: " + template + " does not match the pattern 'Structure.xml' " + ResponseEntity.badRequest());
        }

        List<Attrs> attrsList = folder.getAttrs();

        if (attrsList == null || attrsList.isEmpty()) {
            throw new ServerWebInputException("Attrs is empty " + ResponseEntity.badRequest());
        }
        if (attrsList.size() > 1) {
            throw new ServerWebInputException("attrsList length is than one " + ResponseEntity.badRequest());
        }

        Attrs attrs = attrsList.get(0);

        String key = attrs.getAttrStr().getKey();
        String value = attrs.getAttrStr().getValue();

        if (!key.equals(P_ACT_NUMBER)) {
            throw new ServerWebInputException(
                    "Key: " + key + " does not match the pattern 'number' " + ResponseEntity.badRequest());
        }

        if (value.length() < 1) {
            throw new ServerWebInputException("Value length less than one " + ResponseEntity.badRequest());
        }
        return value;
    }

    public List<Folder> spawnObjectForJsonReply(EmulationCreateStructureRequest request) {
        List<Folder> folderList = new ArrayList<>();
        List<Attrs> attrsList = new ArrayList<>();

        Result result = new Result();
        result.setResultCode("0");

        AttrStr attrStr = new AttrStr();
        attrStr.setKey("FolderName");
        attrStr.setValue(request.getFolders().get(0).getAttrs().get(0).getAttrStr().getValue());

        AttrStr attrStr1 = new AttrStr();
        attrStr1.setKey("TemplateId");
        attrStr1.setValue(STRUCTURE);

        AttrStr attrStr2 = new AttrStr();
        attrStr2.setKey("TemplateAttrs");
        attrStr2.setValue(P_ACT_NUMBER + "(STRING)="
                + request.getFolders().get(0).getAttrs().get(0).getAttrStr().getValue() + "|*");

        Attrs attrs = new Attrs();
        attrs.setAttrStr(attrStr);

        Attrs attrs1 = new Attrs();
        attrs1.setAttrStr(attrStr1);

        Attrs attrs2 = new Attrs();
        attrs2.setAttrStr(attrStr2);

        Folder folder = new Folder();
        folder.setTemplate(STRUCTURE);
        folder.setId(emulationService.createFolder(requestValidation(request)));
        folder.setTarget("/Company/Folder");
        folder.setClasz("Folder");
        folder.setResult(result);

        attrsList.add(attrs);
        attrsList.add(attrs1);
        attrsList.add(attrs2);

        folder.setAttrs(attrsList);

        folderList.add(folder);
        return folderList;
    }
}