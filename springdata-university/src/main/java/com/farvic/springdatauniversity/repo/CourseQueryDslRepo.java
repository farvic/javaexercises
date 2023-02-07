package com.farvic.springdatauniversity.repo;

import com.farvic.springdatauniversity.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface CourseQueryDslRepo extends JpaRepository<Course, Integer>, QuerydslPredicateExecutor<Course> {
}
