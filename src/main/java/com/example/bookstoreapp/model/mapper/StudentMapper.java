package com.example.bookstoreapp.model.mapper;

import com.example.bookstoreapp.model.dto.StudentDto;
import com.example.bookstoreapp.model.dto.response.StudentResponseDto;
import com.example.bookstoreapp.model.entity.Student;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    StudentResponseDto toStudentResponseDto(Student student);

    List<StudentDto> toStudentResponseDtos(List<Student> students);
}
