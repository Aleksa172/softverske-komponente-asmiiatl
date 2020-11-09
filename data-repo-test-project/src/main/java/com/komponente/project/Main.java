package com.komponente.project;


import com.komponente.project.models.Student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

public class Main {

    public static void main(String[] args) throws IOException {
        DataRepository dataRepository = new DataRepositoryJson();
        dataRepository.setMaxEntitiesPerFile(20);
        HashMap<String,String> testCriteria = new HashMap<>();
        testCriteria.put("name", "Pera");

        System.out.println(dataRepository.findByCriteria("test_obejct2", testCriteria, Student.class));
        //System.out.println(dataRepository.openWarehouse());

        //Student s = new Student("e3712636-df13-4678-aaff-2776f46b40ae");
        //s.setName("Pera");

        /*
        ArrayList<Student> listaDrugara = new ArrayList<>();
        Student s2 = new Student("e78623876", "Jova");
        listaDrugara.add(s2);
        ArrayList<Student> listaDrugara2 = new ArrayList<>();
        listaDrugara2.add(s);
        s2.setDrugari(listaDrugara2);
        s.setDrugari(listaDrugara);
        s.setAttribute("najdrugar", s2);
        dataRepository.save("test_obejct2", s);*/

/*
        Student s2 = new Student("e78623876", "Jova");
        try {
            dataRepository.save("test_obejct2", s2);
        } catch (Exception e) {
            e.printStackTrace();
        }

 */
        /*dataRepository.findAll("test_obejct2", Student.class).forEach(System.out::println);
        Optional.ofNullable(
                dataRepository.findById("test_obejct2", "e3712636-df13-4678-aaff-2776f46b40ae", Student.class))
                .ifPresent(System.out::println);
        */
    }
}
