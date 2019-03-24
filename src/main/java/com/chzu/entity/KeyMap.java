package com.chzu.entity;

import java.io.Serializable;

public class KeyMap implements Serializable {

    private static final long serialVersionUID = 3176972128965536016L;

    private Integer courseId;

    private Integer studentId;

    public KeyMap() {

    }

    public KeyMap(Integer courseId, Integer studentId) {
        this.courseId = courseId;
        this.studentId = studentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        KeyMap objKey = (KeyMap) o;
        if (courseId.equals(objKey.courseId) &&
                studentId.equals(objKey.studentId)) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {

        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + (courseId == null ? 0 : courseId.hashCode());
        result = PRIME * result + (studentId == null ? 0 : studentId.hashCode());
        return result;

    }
}
