package tutes.meta;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static tutes.meta.DomainConstraint.Type;

/**
 * A class responsible for capturing data for a Pizza application.
 */
public class DataManager {
    public DataManager() {
    }

    private static boolean isDescendant(Class<?> c1, Class<?> c2) {
        Class<?> sup = c1.getSuperclass();
        if (sup == null)
            return false;
        if (sup == c2) {
            return true;
        } else if (sup != Object.class) {
            return isDescendant(sup, c2);
        } else {
            return false;
        }
    }

    // validate attribute values
    // if ok then create a new instance of c (by the constructor found)
    // else throw exception
    public static Object newInstance(Class<?> c, Object[] attributeVals)
            throws NotPossibleException {

        Object o = null;
        try {
            // create a new object using the default constructor method
            Constructor<?>[] cons = c.getDeclaredConstructors();
            // find the constructor that has the same signature as the attributes specified in values
            Constructor<?> co = null;
            Class<?>[] paramTypes;

            for (Constructor<?> con : cons) {
                co = con;
                paramTypes = co.getParameterTypes();

                if (paramTypes.length == attributeVals.length) {
                    boolean match = true;

                    for (int k = 0; k < paramTypes.length; k++) {
                        Class<?> type = paramTypes[k];
                        Object obj = attributeVals[k];

                        if (obj == null)
                            continue; // consider a match
                        // compare the object type with the parameter type
                        Class<?> oc = obj.getClass();
                        if (!type.equals(oc) && !isDescendant(oc, type)) {
                            match = false;
                            break;
                        }
                    }

                    if (match) {
                        // found the constructor
                        break;
                    }
                }
                co = null;
            }

            if (co == null) {
                throw new NotPossibleException(
                        "DataManager.newInstance: could not find constructor matching the data values");
            }
            // create a new object
            validateDomainAttribute(c, new Object[][]{attributeVals});
            o = co.newInstance(attributeVals);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            // e.printStackTrace();
            throw new NotPossibleException(
                    "DataManager.newInstance: failed to create a new instance for class: "
                            + c.getName());
        }

        return o;
    }


    // return an attribute of c
    private static Field getAttribute(Class<?> c, String name)
            throws NotPossibleException {
        List<Field> fields = getAttributes(c, DomainConstraint.class);
        for (Field field : fields) {
            if (field.getName().equals(name)) {
                return field;
            }
        }
        return null;
    }

    // return the annotated attributes of a class
    public static List<Field> getAttributes(final Class<?> c,
                                            final Class<DomainConstraint>
                                                    annotatedClass) throws NotPossibleException {
        Field[] myFields = c.getDeclaredFields();
        List<Field> _fields = new ArrayList<>();
        Collections.addAll(_fields, myFields);

        Class<?> superClass = c.getSuperclass();
        if (!superClass.equals(Object.class)) {
            List<Field> parentFields = getAttributes(superClass, annotatedClass);
            _fields.addAll(parentFields);
        }
        List<Field> fields = new ArrayList<>();
        Field f;
        for (Field field : _fields) {
            f = field;
            if (f.getAnnotation(annotatedClass) != null) {
                fields.add(f);
            }
        }
        return fields;
    }

    // validate value against the domain constraint of attribute
    public static void validateDomainAttribute(Field f, Object value)
            throws NotPossibleException {
        if (f == null) {
            throw new NullPointerException("field is null");
        }
        DomainConstraint dc = f.getAnnotation(DomainConstraint.class);
        Type type = dc.type();
        Object val = null;

        if (type == Type.String) {
            if (dc.length() > 0) {
                val = value.toString();
                if (((String) val).length() > dc.length())
                    throw new NotPossibleException("validate: value has incorrect length: " + value);
            }
        }

        if (type == Type.UserDefined) {
            Class<?> c = f.getType();
            if (c.getSimpleName().equals("Topping")) {
                List<Field> fields = getAttributes(c, DomainConstraint.class);
                // qty + "@$" + cost + "-" + name;
                String[] strings = value.toString().split("\\@\\$");
                Integer qty = Integer.parseInt(strings[0]);
                strings = ((String) strings[1]).split("-");
                Double cost = Double.parseDouble(strings[0]);
                String name = strings[1];
                validateDomainAttribute(Pizza.Topping.class, new Object[][]{{name, cost, qty}});
            }
        }

        if (type.isNumeric()) {
            if (!(value instanceof Number)) {
                try {
                    switch (type) {
                        case Integer:
                            val = Integer.parseInt(value.toString());
                            break;
                        case Long:
                            val = Long.parseLong(value.toString());
                            break;
                        case Float:
                            val = Float.parseFloat(value.toString());
                            break;
                        case Double:
                            val = Double.parseDouble(value.toString());
                            break;
                    }
                } catch (NumberFormatException e) {
                    throw new NotPossibleException("validate: cannot convert value to type " + type + " :" + value);
                }
            } else {
                val = value;
            }

            if (!Double.isNaN(dc.min())) {
                if (((Number) val).doubleValue() < dc.min())
                    throw new NotPossibleException("validate: value smaller than min: " + value);
            }

            if (!Double.isNaN(dc.max())) {
                if (((Number) val).doubleValue() > dc.max())
                    throw new NotPossibleException("validate: value larger than max: " + value);
            }
        }
    }

    // validate value against the domain constraint of attributes of a class
    public static void validateDomainAttribute(Class<?> c,
                                               Object[][] attributeValPairs) throws NotPossibleException {
        List<Field> fields = getAttributes(c, DomainConstraint.class);
        for (Object[] pairs : attributeValPairs) {
            for (int i = 0; i < pairs.length; i++) {
                validateDomainAttribute(fields.get(i), pairs[i]);
            }
        }
    }

    // validate an input value of an attribute of a class against
    // the attribute's domain constraint
    public static void validateDomainAttribute(Class<?> c, String name, Object value) throws NotPossibleException {
        Field field = getAttribute(c, name);
        validateDomainAttribute(field, value);
    }
}
