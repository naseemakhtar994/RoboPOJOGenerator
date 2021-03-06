package com.robohorse.robopojogenerator.generator;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by vadim on 25.09.16.
 */
public class ClassItem {
    private String annotation;
    private String classAnnotation;
    private String className;
    private String packagePath;
    private Map<String, String> classFields = new HashMap<String, String>();
    private Set<String> classMethods = new HashSet<String>();
    private Set<String> classImports = new HashSet<String>();

    public ClassItem(String className) {
        this.className = className;
    }

    public void setPackagePath(String packagePath) {
        this.packagePath = packagePath;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    public void addClassField(String name, String classType) {
        classFields.put(name, classType);
    }

    public String getClassName() {
        return className;
    }

    public void addClassImport(String value) {
        classImports.add(value);
    }

    public void addClassMethod(String value) {
        classMethods.add(value);
    }

    public Map<String, String> getClassFields() {
        return classFields;
    }

    public void setClassAnnotation(String classAnnotation) {
        this.classAnnotation = classAnnotation;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("package " + packagePath + ";\n");
        for (String string : classImports) {
            stringBuilder.append("\n" + string);
        }
        stringBuilder.append("\n\n");
        if (null != classAnnotation) {
            stringBuilder.append(classAnnotation);
        }
        stringBuilder.append("public class " + className + "{\n");

        for (String objectName : classFields.keySet()) {
            if (null != annotation) {
                stringBuilder.append("\t" + String.format(annotation, objectName, objectName) + "\n");
            }
            stringBuilder.append("\tprivate " + classFields.get(objectName) + " " + objectName + ";\n");
        }

        for (String string : classMethods) {
            stringBuilder.append("\n" + string + "\n");
        }
        stringBuilder.append("\n}");
        return stringBuilder.toString();
    }
}
