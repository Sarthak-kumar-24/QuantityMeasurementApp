# UC13: Centralized Arithmetic Logic to Enforce DRY in Quantity Operations

---

## Description

UC13 refactors the arithmetic operations (addition, subtraction, and division) introduced in UC12 to strictly enforce the **DRY (Don’t Repeat Yourself)** principle.

In UC12, each arithmetic method independently implemented validation, unit compatibility checks, base-unit conversion, and error handling, resulting in duplicated logic. UC13 eliminates this duplication by centralizing all arithmetic processing into a single internal helper mechanism inside the `Quantity<U>` class.

The refactoring is **internal only**. Public method signatures, external behavior, and all UC12 test cases remain **unchanged**.

---

## Objectives

- Eliminate code duplication across arithmetic operations  
- Centralize validation and conversion logic  
- Preserve UC12 behavior without modifying public APIs  
- Improve maintainability and scalability  
- Enable easy future arithmetic extensions  

---

## Preconditions

- Generic `Quantity<U extends IMeasurable>` (UC10) is fully implemented  
- Arithmetic operations from UC12 (add, subtract, divide) are functional  
- All unit enums (`LengthUnit`, `WeightUnit`, `VolumeUnit`) implement `IMeasurable`  
- Existing UC12 test cases must pass without modification  
- Public APIs must remain unchanged  

---

## Design Overview

### Central Refactoring Strategy

UC13 introduces:

1. **Private `ArithmeticOperation` Enum**
   - Encapsulates arithmetic behavior
   - Supported operations:
     - `ADD`
     - `SUBTRACT`
     - `DIVIDE`

2. **Centralized Validation Helper**
   - Performs:
     - Null operand checks
     - Cross-category validation
     - Finite numeric validation
     - Target unit validation (when required)
     - Division-by-zero prevention

3. **Central Arithmetic Executor**
   - Converts operands to base unit
   - Executes arithmetic via enum dispatch
   - Converts result back to target unit (if applicable)

---

## Main Flow

### Step 1: Identify Shared Logic
Common across add, subtract, and divide:
- Operand validation
- Unit compatibility checks
- Base-unit normalization
- Arithmetic execution
- Error handling

### Step 2: Introduce ArithmeticOperation Enum
- Eliminates conditional branching
- Enables clean operation dispatch
- Improves extensibility

### Step 3: Central Validation Method
- Single source of validation logic
- Ensures consistent exception behavior

### Step 4: Central Arithmetic Helper
- Converts operands to base units
- Executes arithmetic
- Converts result back to requested unit

### Step 5: Refactor Public Methods
Public APIs delegate internally:
- `add()` → centralized helper using `ADD`
- `subtract()` → centralized helper using `SUBTRACT`
- `divide()` → centralized helper using `DIVIDE`

---

## Behavior Preservation

| Feature | Status |
|------|------|
| Addition | Preserved |
| Subtraction | Preserved |
| Division | Preserved |
| Precision | Preserved |
| Error Handling | Preserved |
| Immutability | Preserved |
| Public APIs | Unchanged |

All UC12 test cases pass without modification.

---

## Cross-Category Safety

- Arithmetic across different measurement categories is prevented
- Validation is centralized and consistently enforced
- Works uniformly for length, weight, and volume

---

## Error Handling Consistency

All arithmetic operations throw:

- `IllegalArgumentException` for:
  - Null operands
  - Cross-category operations
  - Invalid target units
- `ArithmeticException` for:
  - Division by zero

Error messages are consistent across all operations.

---

## Postconditions

- Arithmetic logic is fully centralized
- No duplicated validation or conversion code remains
- Public APIs and external behavior remain unchanged
- UC12 functionality is fully preserved
- Codebase is cleaner, safer, and easier to maintain

---

## Advantages of UC13

- Enforces DRY principle
- Reduces maintenance overhead
- Improves readability and clarity
- Centralizes error handling
- Enables easy future enhancements
- Professional, scalable architecture

---

## Scalability

New arithmetic operations (e.g., multiplication, modulo) can be added by:
- Adding a new enum constant
- Reusing the same centralized helper

No additional duplication required.

---

## Conclusion

UC13 upgrades the Quantity Measurement Application from a working implementation to a **clean, scalable, and maintainable architecture**.

It demonstrates:
- Strong adherence to SOLID and DRY principles
- Safe internal refactoring with backward compatibility
- Robust generic arithmetic design
- Readiness for future expansion

---

✅ UC13 Successfully Implemented  
✅ No changes required in main method or test classes  
✅ All UC12 behavior preserved
