#  Quantity Measurement App

## UC9 – Weight Measurement Equality, Conversion, and Addition  
*(Kilogram, Gram, Pound)*

---

##  Overview

UC9 extends the **Quantity Measurement App** to support a **new measurement category: Weight**.

Building on the scalable architecture introduced in **UC8**, this use case demonstrates that the application can seamlessly handle **multiple, independent measurement categories** without modifying existing code.

Weight measurements operate **independently** of length measurements and support:

- Equality comparison  
- Unit conversion  
- Arithmetic addition  

All weight-related logic mirrors the **design principles, patterns, and structure** used for length measurements, ensuring architectural consistency and maintainability.

---

##  UC9 Objectives

- Introduce **weight as a new measurement category**
- Support multiple weight units with accurate conversion
- Enable equality comparison across different weight units
- Support addition of weight quantities
- Enforce **category type safety** (Length ≠ Weight)
- Reuse UC8’s enum-based conversion architecture
- Preserve all UC1–UC8 functionality without regression

---

##  Preconditions

- UC1–UC8 are fully implemented and passing
- The application supports enum-based unit conversion
- Weight units supported:
  - **KILOGRAM (kg)** – Base unit
  - **GRAM (g)** – 1 kg = 1000 g
  - **POUND (lb)** – 1 lb = 0.453592 kg
- Conversion factors are defined relative to the base unit
- Length and weight are treated as **separate, non-interoperable categories**

---

##  Design Additions in UC9

###  New Components

#### `WeightUnit` (Standalone Enum)

- Defines all supported weight units
- Owns conversion factors relative to **kilogram**
- Provides:
  - Conversion to base unit
  - Conversion from base unit
- Mirrors the design of `LengthUnit` (UC8)

#### `QuantityWeight`

- Immutable value object representing a weight measurement
- Holds:
  - Numeric value
  - `WeightUnit`
- Delegates all conversion logic to `WeightUnit`
- Supports:
  - Equality
  - Conversion
  - Addition

---

##  Architectural Consistency

| Component         | Responsibility                                  |
|------------------|--------------------------------------------------|
| `WeightUnit`      | Weight unit conversion logic                    |
| `QuantityWeight`  | Weight value behavior (equals, add, convert)    |
| `LengthUnit`      | Length unit conversion (unchanged)              |
| `QuantityLength`  | Length value behavior (unchanged)               |
| `EqualityService` | Category-safe equality comparison               |

---

##  Main Flow

### 1️ Equality Comparison

1. User provides two weight values with their respective units
2. Both values are converted to the base unit (kilogram)
3. Converted values are compared using `equals()`
4. Result (`true` / `false`) is returned

---

### 2️ Unit Conversion

1. User provides a weight value, source unit, and target unit
2. Value is converted to base unit (kilogram)
3. Base value is converted to target unit
4. A new `QuantityWeight` object is returned

---

### 3️ Addition Operations

1. Two `QuantityWeight` objects are provided
2. Both values are converted to base unit (kilogram)
3. Values are summed
4. Result is converted to:
   - First operand’s unit (implicit)
   - Or explicitly specified target unit
5. A **new immutable object** is returned

---

##  Supported Operations (UC9)

### Equality

- Same-unit equality  
- Cross-unit equality (kg ↔ g ↔ lb)  
- Reflexive, symmetric, and transitive behavior  

---

### Conversion

- Explicit unit-to-unit conversion
- Base-unit normalization via kilogram
- Precision preserved within floating-point tolerance

---

### Addition

- Implicit target unit (first operand’s unit)
- Explicit target unit support
- Handles zero, negative, and large values correctly

---

- Prevents logical and mathematical errors
- Reinforces strong domain modeling

---

##  Testing Strategy

UC9 introduces **new test cases** while preserving all UC1–UC8 tests.

### Test Coverage Includes

- Kilogram-to-kilogram equality
- Gram-to-gram equality
- Pound-to-pound equality
- Cross-unit equality:
- Kilogram ↔ Gram
- Kilogram ↔ Pound
- Unit conversion accuracy
- Addition with:
- Same units
- Different units
- Explicit target units
- Zero and negative values
- Immutability validation
- Length vs Weight incompatibility
- Hashing consistency with equality

All tests pass without modifying any UC1–UC8 code.

---

##  Concepts Reinforced in UC9

1. **Multiple Measurement Categories**
   - Length and weight coexist independently

2. **Enum-Based Responsibility Assignment**
   - Units handle conversion
   - Quantities handle behavior

3. **Base Unit Normalization**
   - Kilogram chosen as canonical base unit

4. **Category Type Safety**
   - Prevents invalid comparisons at runtime

5. **Immutability**
   - All operations return new objects
   - Thread-safe by design

6. **Architectural Scalability**
   - Pattern directly extensible to:
     - Temperature
     - Volume
     - Time

---

##  Forward Compatibility

With UC9 implemented:

- New **weight units** can be added by extending `WeightUnit`
- New **measurement categories** can be added without refactoring
- Existing length functionality remains untouched
- System continues to scale cleanly and predictably

---

##  Conclusion

UC9 proves that the Quantity Measurement App is no longer limited to a single domain.

It demonstrates:
- Clean separation of measurement categories
- Strong object-oriented design
- Scalable, extensible architecture
- Real-world refactoring maturity

UC9 establishes the foundation for **multi-domain measurement systems** and prepares the application for future enhancements.

---
