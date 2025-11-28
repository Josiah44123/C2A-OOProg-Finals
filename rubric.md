# Project Assessment Rubric

**Project:** Banking System Application
**Section:** C2A
**Course:** Object Oriented Programming

## Grading Breakdown

### 1. Class Diagram Completeness (25%)
**Score:** 5/5
**Notes:** Excellent class diagram documentation with two versions provided (Class_Diagram_v1.png and Class_Diagram_v2.png) showing iterative refinement. The diagrams clearly show the class hierarchy with Account as the abstract base class and three concrete implementations (CheckingAccount, SavingsAccount, BusinessAccount), along with supporting classes like Client, Transaction, Bank, and InputValidator. Relationships and multiplicities are well-documented.

### 2. Java Program - OOP Concepts (50%)
**Score:** 5/5
**Notes:**
- **Encapsulation (Excellent):** All classes use private fields with public getters/setters. The Account class properly encapsulates balance, transaction history, and account details with controlled access through methods like deposit(), withdraw(), and transfer().
- **Abstraction (Excellent):** Account class is abstract with abstract methods (applyInterest(), applyServiceFee(), getAccountType(), getInterestRate(), getServiceFee()) that subclasses must implement. This enforces a common interface while allowing specialized behavior.
- **Inheritance (Excellent):** Clear inheritance hierarchy with CheckingAccount, SavingsAccount, and BusinessAccount all extending the abstract Account class. Each subclass inherits common functionality while providing specialized implementations.
- **Polymorphism (Excellent):** Method overriding demonstrated throughout - each account type overrides abstract methods with specific implementations. CheckingAccount overrides withdraw() to implement overdraft functionality. The use of @Override annotations shows proper polymorphic behavior.

The project demonstrates all four OOP concepts at a professional level with a well-designed banking system architecture. The code includes proper use of interfaces (Serializable), abstract classes, and inheritance hierarchies.

### 3. Git Usage & Collaboration (15%)
**Score:** 5/5
**Notes:** Excellent collaboration with 56 total commits from 7 contributors: Josiah44123 (36 commits), Lance Derick De Villa (12 commits), Arianna Nicole V. Garcia (3 commits), Miguel Alfonso Pecayo (2 commits), Michael Ong (1 commit), Nicko (1 commit), and copilot-swe-agent[bot] (1 commit). This shows outstanding team participation with one primary developer and strong supporting contributions from other team members. The high commit count indicates iterative development and active version control usage.

### 4. Base Grade (10%)
**Score:** 5/5

### 5. Extra Points (up to 6)

**Features:** 1/5
- Console experience

**Code Quality:** 1.0/1.0
- Variable naming: 0.5/0.5
- Code organization: 0.5/0.5

**Extra Points Total:** +2.0

---

## Final Grade: 100 + 2.0 = **102/100**

*Assessment generated based on project analysis.*
