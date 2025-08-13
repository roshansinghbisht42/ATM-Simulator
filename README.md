# ATM Simulator in Java

## 📌 Overview
This project is a simple **ATM (Automated Teller Machine) simulator** built in Java.  
It demonstrates core **Object-Oriented Programming (OOP)** concepts such as:
- **Encapsulation** (private fields, public getters/setters)
- **Abstraction** (separating account logic from ATM operations)

The program allows a user to:
- Withdraw money  
- Deposit money  
- Transfer money between accounts  
- Check account balance  
- Authenticate using account number and PIN  

---

## 🛠 Features
1. **User Authentication**
   - PIN-based login with a maximum of 3 attempts.
   - Prevents unauthorized access.

2. **Withdrawal**
   - Checks for sufficient balance before processing.
   - Rejects invalid amounts.

3. **Deposit**
   - Accepts only positive amounts.

4. **Fund Transfer**
   - Allows transferring money to another account.
   - Validates account existence and balance.

5. **Balance Inquiry**
   - Displays current account balance.

---

## 💻 Technologies Used
- **Java** (Core Java, OOP concepts)
- **Collections Framework** (`HashMap` for storing accounts)
- **Scanner** for user input

---

