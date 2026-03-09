package service;

/*
 * =========================================================
 * Equality Service
 * =========================================================
 *
 * This service is responsible for comparing two objects
 * to determine whether they represent equal quantities.
 *
 * =========================================================
 * Design Concept
 * =========================================================
 *
 * Separation of Concerns:
 * The equality logic is separated from the main application
 * to keep the application class cleaner.
 *
 * This also allows equality checks to be reused across
 * different measurement types.
 */
public class EqualityService {

	
    /*
     * Checks whether two objects are equal.
     *
     * Steps:
     * 1. If both references are same → equal
     * 2. If either is null -> not equal
     * 3. If object types differ -> not equal
     * 4. Otherwise delegate to object's equals() method
     *
     * Example:
     * Quantity<LengthUnit> vs Quantity<WeightUnit>
     * -> Not allowed -> returns false
     */
    public boolean areEqual(Object o1, Object o2) {
        if (o1 == o2) return true;
        if (o1 == null || o2 == null) return false;

        if (!o1.getClass().equals(o2.getClass())) {
            return false; // Length != Weight
        }
        return o1.equals(o2);
    }
}
