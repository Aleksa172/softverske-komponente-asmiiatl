package com.komponente.project;


import com.komponente.project.models.Student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

public class Main {



    public static void main(String[] args) throws IOException {

        // 0 - Odabrati fajl za upis
        // json
        String fajl = "test_obejct2";
        // ili
        // yaml
        // String fajl = "test_obejct_yml.yml";

        /* 1. Odabrati repo */
        DataRepository dataRepository = new DataRepositoryYaml();
        // ili
        // DataRepository dataRepository = new DataRepositoryJson();

        /* 1.1 - Mogucnost otvaranja repoa - sa parametrom foldera */
        // dataRepository.openWarehouse()

        /* Test Case (1) - Maksimalni upis entiteta po fajlu - neophodno je imati fajl sa entitetima */
        // Moze se odabrati "test_obejct2" fajl kao primer
        // dataRepository.setMaxEntitiesPerFile(2);
        // Student s = new Student("e3712636-df13-4678-aaff-2776f46b40ae");
        // s.setName("Pera");
        // dataRepository.save(fajl, s);
        /* Trebalo bi da bude upisan u drugom fajlu */


        /* Test Case (2) - Pravljenje novog studenta */
        // Student s = new Student("e3712636-df13-4678-aaff-2776f46b40ae");
        // s.setName("Pera");
        // dataRepository.save(fajl, s);

        /* Test Case (2) - Ugnjezdeni objekti */
//        Student s = new Student();
//        s.setName("Pera");
//        ArrayList<Student> listaDrugara = new ArrayList<>();
//        Student s2 = new Student(null, "Jova");
//        listaDrugara.add(s2);
//        ArrayList<Student> listaDrugara2 = new ArrayList<>();
//        listaDrugara2.add(s);
//        s2.setDrugari(listaDrugara2);
//        s.setDrugari(listaDrugara);
//        s.setAttribute("najdrugar", s2);
//        dataRepository.save(fajl, s);

        /* Test Case (3) - Pretraga studenta po kriterijumu */
//        HashMap<String,String> testCriteria = new HashMap<>();
//        testCriteria.put("name", "Pera");
//        System.out.println(dataRepository.findByCriteria(fajl, testCriteria, Student.class));


        //Student s = new Student("e3712636-df13-4678-aaff-2776f46b40ae");
        //s.setName("Pera");

        /* Test Case (4) - Prikaz svih entiteta */
         dataRepository.findAll(fajl, Student.class).forEach(System.out::println);


        /* Test Case (5) - Find by Id */
//        Optional.ofNullable(
//                dataRepository.findById("test_obejct2", "e3712636-df13-4678-aaff-2776f46b40ae", Student.class))
//                .ifPresent(System.out::println);

    }
}
