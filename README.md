# 📐 Quantity Measurement App  
## Main Branch – Consolidated README (UC1 → UC14)

---

##  Overview

The **Quantity Measurement App** is a progressively evolved, production-grade system for handling **physical measurements** across multiple domains such as:

- Length  
- Weight  
- Volume  
- Temperature  

The project starts with simple **value equality** and evolves into a **generic, extensible, type-safe, arithmetic-aware measurement framework**, following real-world software engineering principles.

By **UC14**, the system supports:
- Multiple measurement categories
- Cross-unit equality
- Explicit unit conversion
- Arithmetic operations (where mathematically valid)
- Capability-aware arithmetic enforcement
- Clean generic architecture using Java Generics

This repository represents the **final stable architecture**.

---

##  Core Design Principles

Across all UCs, the system consistently applies:

- **Value Object Pattern** – immutable, value-based comparison  
- **DRY (Don’t Repeat Yourself)** – zero duplication across categories  
- **Single Responsibility Principle (SRP)**  
- **Open–Closed Principle (OCP)**  
- **Interface Segregation Principle (ISP)**  
- **Type Safety via Generics**  
- **Base Unit Normalization**  
- **Backward Compatibility at every step**  

---

## 🧩 Evolution Summary by Use Case

---

## UC1 – Feet Equality (Foundation)

- Introduced value-based equality
- Handled:
  - same reference
  - null safety
  - type safety
- Established immutability mindset

 *Baseline correctness established.*

---

## UC2 – Feet & Inches Equality

- Added Inches
- Introduced abstraction
- Removed duplicated equality logic
- Enforced strict same-unit comparison

 *First step toward DRY.*

---

## UC3 – Generic Quantity Model (Length)

- Introduced:
  - `QuantityLength`
  - `LengthUnit` enum
- Enabled cross-unit equality  
  (`1 ft == 12 in`)
- Base unit normalization introduced

 *Architectural turning point.*

---

## UC4 – Extended Length Units

- Added:
  - Yards
  - Centimeters
- Zero changes to business logic
- Enum-only extension

 *Open–Closed Principle validated.*

---

## UC5 – Explicit Unit Conversion

- Public conversion API added
- Two-step conversion:
  - Source → Base
  - Base → Target
- Precision handling introduced

 *Equality evolved into conversion engine.*

---

## UC6 – Addition (Implicit Target Unit)

- Added arithmetic addition
- Cross-unit addition supported
- Result defaults to first operand’s unit
- Immutability preserved

 *System moves from comparison → computation.*

---

## UC7 – Addition with Explicit Target Unit

- Caller specifies result unit
- Same arithmetic, flexible representation
- Full control over output unit

 *API expressiveness improved.*

---

## UC8 – Unit Responsibility Refactor (SRP)

- Moved conversion logic **out of Quantity**
- `LengthUnit` became single source of truth
- `QuantityLength` became a pure value object
- Zero behavior change

 *Architecture cleaned without breaking anything.*

---

## UC9 – Weight Measurement Support

- New category: **Weight**
- Units:
  - Kilogram (base)
  - Gram
  - Pound
- Equality, conversion, addition supported
- Length ≠ Weight enforced

 *Multi-category system achieved.*

---

## UC10 – Generic Quantity with `IMeasurable`

 **Major architectural milestone**

- Removed category-specific quantity classes
- Introduced:
  - `Quantity<U extends IMeasurable>`
  - `IMeasurable` interface
- Length & Weight unified
- Compile-time category safety
- Zero duplication

 *System becomes truly generic.*

---

## UC11 – Volume Measurement Support

- New category: **Volume**
- Units:
  - Litre (base)
  - Millilitre
  - Gallon
- Added using **only a new enum**
- No refactor required

 *Generic architecture proven correct.*

---

## UC12 – Arithmetic Expansion *(Baseline)*

- Introduced:
  - Subtraction
  - Division
- Validation and conversion logic duplicated internally

 *Functional but not DRY.*

---

## UC13 – Centralized Arithmetic Logic (DRY Enforcement)

- Centralized:
  - Validation
  - Conversion
  - Arithmetic execution
- Introduced internal arithmetic dispatcher
- Public APIs unchanged
- UC12 behavior preserved

 *Professional-grade refactor.*

---

## UC14 – Temperature Support with Selective Arithmetic

 **Design maturity milestone**

- New category: **Temperature**
- Units:
  - Celsius
  - Fahrenheit
  - Kelvin
- Equality & conversion supported
- Arithmetic explicitly **disallowed**
- `IMeasurable` refactored to support:
  - Capability-aware arithmetic
- Enforced Interface Segregation Principle

 *Mathematical correctness + architectural integrity.*

---

##  Supported Categories & Capabilities

| Category      | Equality | Conversion | Add | Subtract | Divide |
|--------------|----------|------------|-----|----------|--------|
| Length       | ✅ | ✅ | ✅ | ✅ | ✅ |
| Weight       | ✅ | ✅ | ✅ | ✅ | ✅ |
| Volume       | ✅ | ✅ | ✅ | ✅ | ✅ |
| Temperature  | ✅ | ✅ | ❌ | ❌ | ❌ |

---

##  Testing Strategy

- Tests written per UC
- Existing tests never modified
- Refactors validated via regression
- Coverage includes:
  - Cross-unit equality
  - Precision tolerance
  - Arithmetic laws
  - Category isolation
  - Immutability
  - Error handling

All tests pass on `main`.

---

##  Forward Compatibility

The system now supports:

- Adding a new **unit** → extend enum
- Adding a new **category** → new enum implementing `IMeasurable`
- Adding new **arithmetic operations** → enum-based extension
- Zero refactor of core logic

---

##  Final Summary

From **UC1 to UC14**, this project demonstrates:

- Real-world refactoring discipline
- Clean object-oriented thinking
- Scalable generic design
- Mathematical correctness
- Interview-level architecture maturity

This isn’t just a Quantity Measurement App —  
it’s a **case study in how systems should evolve** 

**Main branch = final, stable, production-ready.**
