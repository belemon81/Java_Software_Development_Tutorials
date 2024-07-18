package lects.lect05.app;

import lects.lect05.ano.DomainConstraint;
import lects.lect05.ano.MethodSpecs;

/**
 * @overview Represents a study course in a university
 * e.g. Physics, Maths, Business, etc.
 * @abstract_properties c.id is integer && c.id > 0 &&
 * c.name != null && c.length <= 50
 */
public class Course {
    @DomainConstraint(type = "Integer", min = 1)
    private int id;

    @DomainConstraint(type = "String", optional = false, length = 10)
    private String name;

    /**
     * Constructor method
     */
    public Course(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // getter and setter methods
    public String getName() {
        return name;
    }

    @MethodSpecs(type = "setter",
            preCondition = "name not empty",
            postCondition = "name attribute is set",
            sideEffects = "none"
    )
    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return "Course:<" + id + "," + name + ">";
    }
}
