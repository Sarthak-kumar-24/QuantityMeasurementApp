# Quantity Measurement App  
## UC6 – Addition of Two Length Units (Same Category)

---

##  Overview

UC6 extends the Quantity Measurement App by introducing **addition operations between two length measurements** belonging to the **same measurement category (Length)**.

This use case builds directly on:
- **UC3** – Generic Quantity model (DRY principle)
- **UC4** – Extended unit support (Feet, Inches, Yards, Centimeters)
- **UC5** – Explicit unit-to-unit conversion API

UC6 enables **arithmetic addition** of two `QuantityLength` objects, even when they are expressed in **different units**, and returns the result in the **unit of the first operand**.

---

##  UC6 Objectives

- Enable arithmetic addition for length quantities
- Support addition across different length units
- Preserve immutability of value objects
- Reuse centralized conversion logic (no duplication)
- Maintain mathematical correctness and precision
- Validate inputs and handle edge cases safely

---

##  Supported Units

- Feet (ft)
- Inches (in)
- Yards (yd)
- Centimeters (cm)

_All units belong to the same measurement category: **Length**._

---

## 🧠 Design Principles Applied

### 1️ Value Object Pattern
- `QuantityLength` represents a physical length
- Immutable: operations return **new instances**
- Encapsulates value + unit + behavior

### 2️ DRY (Don’t Repeat Yourself)
- All conversions normalize via a **single base unit (inches)**
- UC6 reuses UC5 conversion infrastructure

### 3️ Abstraction & Encapsulation
- Conversion logic hidden inside domain objects
- Clients only interact through clean APIs (`add`, `convertTo`)

### 4️ Immutability
- Original operands remain unchanged
- Prevents side effects and improves predictability

---

##  Addition Logic (How UC6 Works)

1. Validate inputs:
   - Operands must not be null
   - Values must be finite numbers
   - Units must be valid length units

2. Convert both operands to the **base unit (inches)**

3. Add the converted values

4. Convert the sum back to the **unit of the first operand**

5. Return a **new `QuantityLength` object**

---

##  Example Scenarios

| Input | Output |
|-----|------|
| `1 ft + 2 ft` | `3 ft` |
| `1 ft + 12 in` | `2 ft` |
| `12 in + 1 ft` | `24 in` |
| `1 yd + 3 ft` | `2 yd` |
| `36 in + 1 yd` | `72 in` |
| `5 ft + 0 in` | `5 ft` |
| `5 ft + (-2 ft)` | `3 ft` |

---

##  Application Flow

1. User provides two values with units
2. Quantities are created using `QuantityLength`
3. Addition is performed using `add()`
4. Result is returned in the unit of the first operand
5. Equality checks can still be performed (UC3+ preserved)

---

##  Test Coverage (UC6)

The UC6 test suite validates:

### ✔ Same-Unit Addition
- Feet + Feet
- Inches + Inches

### ✔ Cross-Unit Addition
- Feet + Inches
- Inches + Feet
- Yards + Feet
- Centimeters + Inches

### ✔ Mathematical Properties
- Commutativity: `a + b = b + a`
- Identity element: adding zero
- Negative value handling

### ✔ Precision & Accuracy
- Floating-point tolerance (epsilon based)
- Round-trip conversion safety

### ✔ Validation & Error Handling
- Null operand rejection
- Invalid input handling
- Large and small value addition

---

##  Concepts Learned in UC6

1. Arithmetic operations on value objects  
2. Unit normalization before arithmetic  
3. Immutability in domain models  
4. Conversion reuse across features  
5. Floating-point precision handling  
6. Mathematical property validation (commutativity)  
7. Clean API design for domain operations  

---

##  Forward Compatibility

With UC6 implemented, the system is now ready for:

- Subtraction, multiplication, division (UC7+)
- Support for additional measurement categories
- Mixed-category validation (length vs weight, etc.)
- Enhanced arithmetic APIs

---

##  Summary

UC6 completes the transition from **simple equality checks** to **full-fledged arithmetic operations** on quantities.

The Quantity Measurement App is now:
- Generic
- Scalable
- Mathematically correct
- Cleanly designed
- Fully test-driven

---
