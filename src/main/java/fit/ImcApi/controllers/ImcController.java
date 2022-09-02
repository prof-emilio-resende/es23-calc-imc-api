package fit.ImcApi.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fit.ImcApi.models.Person;

@RestController
@CrossOrigin(origins = {"http://127.0.0.1:5500"})
@RequestMapping("/imc")
public class ImcController {
    @GetMapping("/table")
    public Map<String, String> getTable() {
        var map = new HashMap<String, String>();

        map.put("0", "Magreza");
        map.put("18.5", "Normal");
        map.put("24.9", "Sobrepeso");
        map.put("99", "Obesidade");

        return map;
    }

    @PostMapping("/calculate")
    public Person calculateImc(@RequestBody Person person) {
        person.setImc(person.getWeight() / Math.pow(person.getHeight(), 2));
        person.setImcDescription(calculateImcText(person.getImc()));
        
        return person;
    }

    private String calculateImcText(double imc) {
        if (imc <= 18.5) return "magreza";
        if (imc > 18.5 && imc <= 24.9) return "normal";
        if (imc > 24.9 && imc <= 30.0) return "Sobrepeso";
        else return "Obesidade";
    }
}
