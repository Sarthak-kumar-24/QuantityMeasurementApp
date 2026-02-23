package service;

public class EqualityService {

    public boolean areEqual(Object o1, Object o2) {
        if (o1 == o2) return true;
        if (o1 == null || o2 == null) return false;

        if (!o1.getClass().equals(o2.getClass())) {
            return false; // Length ≠ Weight
        }
        return o1.equals(o2);
    }
}