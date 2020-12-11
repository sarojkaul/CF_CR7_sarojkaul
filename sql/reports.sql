/*1. show all students of the class 1b, which has the ID=2”*/

SELECT students.id,students.first_name,students.last_name FROM students INNER JOIN enroll ON enroll.fk_student_id = students.id WHERE enroll.fk_class_Id = '2';


/*2.specific class name (aka something like “show all students of the class ‘1b’; I don’t know the students ID”)
*/

/*i reatlly do not understand why are you asking for this becasue in the 1 query its already shown all students without knowing student_Id*/

SELECT students.id,students.first_name,students.last_name FROM students INNER JOIN enroll ON enroll.fk_student_id = students.id WHERE enroll.fk_class_Id = '2';



