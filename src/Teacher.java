public class Teacher {
private int id;
private String first_name;
private  String last_name;
private  String class_name;

      public Teacher(String first_name,String last_name){

          this.first_name= first_name;
          this.last_name = last_name;

      }

    public int getId() {
        return id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getClass_name() {
        return class_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setId(int id) {
        this.id = id;

    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", class_name='" + class_name + '\'' +
                '}';
    }
}
