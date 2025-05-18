package com.example.ciandtest.service;

import com.example.ciandtest.model.Todo;
import com.example.ciandtest.model.User;
import com.example.ciandtest.model.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "todos", expression = "java(mapStrings(user.getTodos()))")
    public UserDTO userToUserDTO(User user);

    default List<String> mapStrings(List<Todo> todos) {
        if (todos == null || todos.isEmpty()) {
            return new ArrayList<>();
        }
        return todos.stream().map(Todo::getTask).collect(Collectors.toList());
    }
}
