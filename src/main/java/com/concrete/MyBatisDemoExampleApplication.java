package com.concrete;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.ibatis.annotations.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@SpringBootApplication
public class MyBatisDemoExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyBatisDemoExampleApplication.class, args);
	}

	@Bean
	CommandLineRunner mYBatisDemoExampleRunningData(PersonMapper personMapper) {
		return args -> {

			List<Person> people = Arrays.asList(
					new Person(null, "John", "Wick", 30),
					new Person(null, "John", "McClane", 50),
					new Person(null, "John", "Rambo", 65)
			);

//			people.forEach(p -> {
//				personMapper.insert(p);
//				System.out.println(p);
//			});

			people.forEach(personMapper::insert);

			System.out.println("-------->> SELECT ALL <<--------");
			personMapper.selectAll().forEach(System.out::println);

//			System.out.println("-------->> DELETE BY ID <<--------");
//			personMapper.deleteById(1L);
//			personMapper.selectAll().forEach(System.out::println);

			System.out.println("-------->> SEARCH BY FIRSTNAME <<--------");
			personMapper.search("John", null, 0).forEach(System.out::println);

			System.out.println("-------->> SEARCH BY LASTNAME<<--------");
			personMapper.search(null, "McClane", 0).forEach(System.out::println);

			System.out.println("-------->> SEARCH BY FIRSTNAME AND AGE<<--------");
			personMapper.search("John", null,  65).forEach(System.out::println);

		};

	}
}

@Mapper
interface PersonMapper {

	@Options(useGeneratedKeys = true)
	@Insert("insert into person(firstname, lastname, age) values(#{firstname}, #{lastname}, #{age})")
	void insert(Person person);

	@Select("select * from person")
	Collection<Person> selectAll();

	@Delete("delete from person where id = #{id}")
	void deleteById(long id);

	Collection<Person> search(@Param("firstname") String firstname,
							  @Param("lastname") String lastname,
							  @Param("age") Integer age);
}

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
class Person {

	private Long id;
	private String firstname;
	private String lastname;
	private Integer age;

}
