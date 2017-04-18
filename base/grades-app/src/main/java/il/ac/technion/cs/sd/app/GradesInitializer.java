package il.ac.technion.cs.sd.app;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;

import externelLibraryWrapper.BasicStorage;
import student.Student;
import student.StudentStorage;

/** This class will be instantiated once per test. */
public class GradesInitializer {
  /**
   * Saves the csvData persistently, so that it could be run using GradesRunner.
   * The format of each line of the data is $id,$grade.
   */
	
  public void setup(String csvData) {
	  	Map<String, Boolean> seen = new HashMap<String, Boolean>();
	  
    	List<Student> studentsList = Arrays.asList(csvData.split(System.lineSeparator())).stream().map( s-> Student.fromString(s)).collect(Collectors.toList());
    	List<Student> reverseStudentsList = Lists.reverse(studentsList);
    	new StudentStorage(new BasicStorage()).addMultipleStudents(reverseStudentsList.stream().filter(s -> seen.putIfAbsent(s.getID(), Boolean.TRUE) == null).collect(Collectors.toList()));
  }
}
