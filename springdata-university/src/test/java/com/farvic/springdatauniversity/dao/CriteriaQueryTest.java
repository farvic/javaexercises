package com.farvic.springdatauniversity.dao;

import com.farvic.springdatauniversity.business.CourseFilter;
import com.farvic.springdatauniversity.business.DynamicQueryService;
import com.farvic.springdatauniversity.business.UniversityService;
import com.farvic.springdatauniversity.domain.Department;
import com.farvic.springdatauniversity.domain.Person;
import com.farvic.springdatauniversity.domain.Staff;
import com.farvic.springdatauniversity.repo.DepartmentRepo;
import com.farvic.springdatauniversity.repo.StaffRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;

import static com.farvic.springdatauniversity.business.CourseFilter.filterBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.data.domain.ExampleMatcher.matching;

/**
 * Test Criteria-based queries
 */
@SpringBootTest
public class CriteriaQueryTest {

        @Autowired
        private DynamicQueryService queryService;
        @Autowired
        private UniversityService universityService;
        @Autowired
        private DepartmentRepo departmentRepo;
        @Autowired
        private StaffRepo staffRepo;

        @Test
        void findByCriteria() {
                UniversityFactory.fillUniversity(universityService);
                Department humanities = departmentRepo
                                .findAll(Example.of(new Department("Humanities", null),
                                                matching().withIgnoreCase()))
                                .stream().findFirst().get();
                Staff professorBlack = staffRepo
                                .findAll(Example.of(new Staff(new Person(null, "Black")),
                                                matching().withIgnoreCase()))
                                .stream().findFirst().get();

                System.out.println('\n' + "*** All Humanities Courses");
                queryAndVerify(filterBy().department(humanities));

                System.out.println('\n' + "*** 4 credit courses");
                queryAndVerify(filterBy().credits(4));

                System.out.println('\n' + "*** Courses taught by Professor Black");
                queryAndVerify(filterBy().instructor(professorBlack));

                System.out.println('\n' + "*** Courses In Humanties, taught by Professor Black, 4 credits");
                queryAndVerify(filterBy()
                                .department(humanities)
                                .credits(4)
                                .instructor(professorBlack));
        }

        private void queryAndVerify(CourseFilter filter) {
                queryService.filterByExample(filter)
                                .forEach(course -> {
                                        filter.getInstructor().ifPresent(i -> assertEquals(i, course.getInstructor()));
                                        filter.getCredits().ifPresent(c -> assertEquals(c, course.getCredits()));
                                        filter.getDepartment()
                                                        .ifPresent(prof -> assertEquals(prof, course.getDepartment()));
                                        System.out.println(course);
                                });
        }
}
