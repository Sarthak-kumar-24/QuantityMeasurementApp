package service;

/**
 * EqualityService
 *
 * - Provides a centralized equality check
 * - Ensures category safety (Length ≠ Weight)
 * - Works for legacy wrappers (UC1–UC9)
 * - Works for generic Quantity<U> (UC10)
 */
public class EqualityService {

    public boolean areEqual(Object o1, Object o2) {

        // Same reference
        if (o1 == o2) {
            return true;
        }

        // Null safety
        if (o1 == null || o2 == null) {
            return false;
        }

        /*
         * Category safety:
         * - QuantityLength ≠ QuantityWeight
         * - Quantity<LengthUnit> ≠ Quantity<WeightUnit>
         */
        if (!o1.getClass().equals(o2.getClass())) {
            return false;
        }

        // Delegate to domain-specific equals()
        return o1.equals(o2);
    }
}