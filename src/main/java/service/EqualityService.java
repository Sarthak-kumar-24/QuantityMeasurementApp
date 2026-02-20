package service;

/**
 * EqualityService Provides equality comparison for measurement objects.
 */
public class EqualityService {

	// Generic equality check.

	public boolean areEqual(Object first, Object second) {
		return first != null && first.equals(second);
	}
}
