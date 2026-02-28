# UC12: Subtraction and Division Operations on Quantity Measurements  
### with Generic `Quantity<U extends IMeasurable>`

---

## Description

UC12 extends the **Quantity Measurement Application** by introducing **subtraction and division** operations on the generic `Quantity<U>` class.

Building on **UC10 (generic quantity model)** and **UC11 (volume support)**, this use case enables complete arithmetic handling of measurements while maintaining:

- Unit safety  
- Cross-unit conversion correctness  
- Immutability  
- SOLID and clean design principles  

All arithmetic operations are performed by converting values to a **common base unit**, ensuring correctness across different units within the same measurement category.

---

## Capabilities Introduced in UC12

UC12 adds the following arithmetic operations:

### 1. Subtraction
- Computes the difference between two quantities
- Supports implicit and explicit target units
- Preserves sign (positive, negative, zero)

### 2. Division
- Computes a **dimensionless ratio**
- Indicates relative magnitude between quantities
- Prevents division by zero

Both operations are restricted to the **same measurement category** (length, weight, volume).

---

## Design Principles Followed

- **Immutability**  
  All operations return new `Quantity` objects. Original objects remain unchanged.

- **Type Safety**  
  Cross-category arithmetic (e.g., length − weight) is strictly prohibited.

- **Base Unit Normalization**  
  Arithmetic is performed after converting operands to their base units.

- **Single Responsibility Principle**  
  - `Quantity` handles arithmetic
  - Units handle conversion logic

- **Backward Compatibility**  
  No changes required for UC10–UC11 code or tests.

---

## Supported Operations by Category

| Category | Equality | Conversion | Addition | Subtraction | Division |
|--------|----------|------------|----------|-------------|----------|
| Length | ✅ | ✅ | ✅ | ✅ | ✅ |
| Weight | ✅ | ✅ | ✅ | ✅ | ✅ |
| Volume | ✅ | ✅ | ✅ | ✅ | ✅ |

---

Key Characteristics of Division

- Result is a dimensionless double
- Units cancel out after base-unit conversion
- Represents a pure ratio, not a measurement


Division Rules

- Operands must belong to the same measurement category
- Division by zero is not allowed
- Division is non-commutative

A.divide(B) ≠ B.divide(A)


Result Interpretation (Division)

- Result > 1.0 → first quantity is larger
- Result < 1.0 → second quantity is larger
- Result = 1.0 → quantities are equivalent


Validation and Error Handling

UC12 enforces strict validation rules:

- Null quantities are not allowed
- Null target units are not allowed
- Cross-category arithmetic is prohibited
- Non-finite numeric values are rejected
- Division by zero is prevented

Invalid operations fail fast with meaningful exceptions.


Test Coverage in UC12

UC12 is validated through unit tests covering:

- Subtraction with same units
- Subtraction with different units
- Explicit target unit subtraction
- Negative subtraction results
- Zero subtraction results
- Division with same units
- Division with different units
- Division results greater than 1, less than 1, and equal to 1
- Division by zero handling
- Cross-category operation prevention
- Immutability verification


Conclusion

UC12 completes the arithmetic model of the Quantity Measurement System by introducing safe and precise subtraction and division.

It strengthens:

- Mathematical correctness
- Architectural scalability
- Clean extensibility
