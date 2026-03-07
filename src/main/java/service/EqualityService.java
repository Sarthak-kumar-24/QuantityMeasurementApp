package service;

/**
 * EqualityService Provides equality comparison for measurement objects.
 */
public class EqualityService {

	// Generic equality check.

	 public boolean areEqual(Object first, Object second) {

	        if (first == second) return true;

	        if (first == null || second == null) return false;

	        return first.equals(second);
	    }
}
