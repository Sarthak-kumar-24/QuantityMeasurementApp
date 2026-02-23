# UC14: Temperature Measurement with Selective Arithmetic Support  
### and IMeasurable Refactoring

---

##  Description

UC14 extends the **Quantity Measurement Application** to support **temperature measurements** while preserving correctness and design integrity.

Unlike length, weight, and volume, **temperature does not support full arithmetic operations** in a meaningful way. Operations like multiplication and division on absolute temperatures are mathematically invalid, and even addition/subtraction requires special semantic handling.

UC14 addresses this by **refactoring the `IMeasurable` interface** to support **selective arithmetic operations**, allowing different measurement categories to explicitly declare which arithmetic operations they support.

This use case focuses on **design extensibility and correctness**, not UI expansion.

---

##  Limitations in UC12 / UC13

Before UC14:

1. All units were assumed to support:
   - Addition
   - Subtraction
   - Division
2. Arithmetic constraints were:
   - Not enforced at compile-time
   - Not enforced at validation time
3. Temperature units could not be safely added without:
   - Runtime failures
   - Duplicate validation logic
4. `IMeasurable` violated the **Interface Segregation Principle (ISP)**.

---

##  Goals of UC14

- Allow **measurement categories with restricted arithmetic support**
- Refactor `IMeasurable` to:
  - Provide **default arithmetic support**
  - Allow **opt-out for specific units**
- Ensure:
  - Backward compatibility with UC10–UC13
  - No changes required in existing test cases
  - No changes required in the console application
- Prepare the system for **future temperature input support**

---

##  Supported Operations by Category

| Category      | Equality | Conversion | Addition | Subtraction | Division |
|--------------|----------|------------|----------|-------------|----------|
| Length       | ✅       | ✅         | ✅       | ✅          | ✅       |
| Weight       | ✅       | ✅         | ✅       | ✅          | ✅       |
| Volume       | ✅       | ✅         | ✅       | ✅          | ✅       |
| Temperature  | ✅       | ✅         | ❌       | ❌          | ❌       |

---

# UC14: Temperature Measurement with Selective Arithmetic Support  
### and IMeasurable Refactoring

---

##  Description

UC14 extends the **Quantity Measurement Application** to support **temperature measurements** while preserving correctness and design integrity.

Unlike length, weight, and volume, **temperature does not support full arithmetic operations** in a meaningful way. Operations like multiplication and division on absolute temperatures are mathematically invalid, and even addition/subtraction requires special semantic handling.

UC14 addresses this by **refactoring the `IMeasurable` interface** to support **selective arithmetic operations**, allowing different measurement categories to explicitly declare which arithmetic operations they support.

This use case focuses on **design extensibility and correctness**, not UI expansion.

---

##  Limitations in UC12 / UC13

Before UC14:

1. All units were assumed to support:
   - Addition
   - Subtraction
   - Division
2. Arithmetic constraints were:
   - Not enforced at compile-time
   - Not enforced at validation time
3. Temperature units could not be safely added without:
   - Runtime failures
   - Duplicate validation logic
4. `IMeasurable` violated the **Interface Segregation Principle (ISP)**.

---

##  Goals of UC14

- Allow **measurement categories with restricted arithmetic support**
- Refactor `IMeasurable` to:
  - Provide **default arithmetic support**
  - Allow **opt-out for specific units**
- Ensure:
  - Backward compatibility with UC10–UC13
  - No changes required in existing test cases
  - No changes required in the console application
- Prepare the system for **future temperature input support**

---

##  Supported Operations by Category

| Category      | Equality | Conversion | Addition | Subtraction | Division |
|--------------|----------|------------|----------|-------------|----------|
| Length       | ✅       | ✅         | ✅       | ✅          | ✅       |
| Weight       | ✅       | ✅         | ✅       | ✅          | ✅       |
| Volume       | ✅       | ✅         | ✅       | ✅          | ✅       |
| Temperature  | ✅       | ✅         | ❌       | ❌          | ❌       |

---

##  What UC14 Does NOT Do

❌ Does not add temperature input to the console app
❌ Does not change existing menus
❌ Does not modify previous UC test cases
❌ Does not introduce UI logic

UC14 strictly focuses on core design extensibility.

---

##  Conclusion

UC14 transforms the Quantity Measurement system from a uniform arithmetic model into a capability-aware, extensible architecture.
It ensures mathematical correctness while maintaining backward compatibility and preparing the system for future growth.
