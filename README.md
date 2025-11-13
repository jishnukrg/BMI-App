<<<<<<< HEAD

# 📘 **BMI Calculator – Java Swing GUI Application**

## 🧩 Overview  
This project is a **standalone Java Swing application** developed as part of **Module 4 Assignment #1** for *CSC 7435E – Secure DevOps Practices* at **Louisiana State University**.  

The purpose of this app is to **calculate a person’s Body Mass Index (BMI)** based on their weight and height. It provides a clean, user-friendly graphical interface where the user can:
- Input weight and height,  
- Select the preferred measurement units (metric or imperial),  
- Instantly view the calculated BMI value and its category, and  
- See an image corresponding to their BMI category (Underweight, Healthy, or Overweight).  

The app is **fully standalone** – it runs locally, does not require an internet connection, and does not depend on external frameworks beyond the Java Development Kit (JDK).  

---

## ⚙️ **Key Features**
| Feature | Description |
|----------|--------------|
| 🧮 **BMI Calculation** | Calculates BMI using the standard formula `BMI = weight / (height²)` |
| ⚖️ **Unit Selection** | Allows selection between **kg/lb** for weight and **m/ft** for height |
| 🔄 **Automatic Conversion** | Internally converts imperial units (lb/ft) into metric (kg/m) before computation |
| 🧠 **BMI Categories** | Displays user’s BMI classification: Underweight, Healthy, or Overweight |
| 🖼️ **Category Image Display** | Dynamically changes the on-screen image to represent the BMI category |
| 💻 **Standalone Execution** | Runs as a local desktop GUI without external dependencies |
| 🧾 **Simple Design** | Clean, responsive layout using Java Swing with clear labels and intuitive workflow |

---

## 🧮 **BMI Formula Reference**
The Body Mass Index (BMI) is calculated using:
\[
BMI = \frac{weight (kg)}{height (m)^2}
\]

If the user chooses imperial units:
- 1 lb = 0.453592 kg  
- 1 ft = 0.3048 m  

The app automatically performs this conversion before calculating the BMI.

---

## 🧰 **Technology Stack**
| Component | Details |
|------------|----------|
| **Programming Language** | Java |
| **Version Used** | JDK 17 or later |
| **Framework** | Java Swing (for GUI) |
| **IDE / Editor** | Visual Studio Code (recommended) |
| **Dependencies** | None (built-in JDK libraries only) |

---

## 🚀 **How to Run the Application**

### 🖥️ Step 1: Compile the code
Open a terminal (or VS Code terminal) inside the project folder:
```bash
javac BMIApp.java
```

### 🖥️ Step 2: Run the compiled program
```bash
java BMIApp
```

### 🧩 Step 3: Use the GUI
1. Enter your **weight** and select the unit (kg or lb).  
2. Enter your **height** and select the unit (m or ft).  
3. Click **“Calculate BMI.”**  
4. View your BMI value, classification, and related image.

---

## 🧪 **Example Usage**
**Input**  
- Weight = 60 kg  
- Height = 1.70 m  

**Output**  
- BMI = 20.76  
- Category = Healthy  
- Image = Displays the *Healthy* icon  

---

## 📂 **Project Structure**
```
JishnuGanesh_M4Assgn1/
 ├── BMIApp.java          # Main source code file (Java Swing GUI)
 ├── images/
 │     ├── healthy.png        # Shown for “Healthy” BMI
 │     ├── underweight.png    # Shown for “Underweight” BMI
 │     └── overweight.png     # Shown for “Overweight” BMI
 ├── README.md            # Project documentation
 └── .gitignore           # Git configuration (ignores class/IDE files)
```

---

## 🧠 **Program Logic**
1. **Input Handling**  
   - Reads numeric input for weight and height from text fields.  
   - Dropdown menus specify unit types.  
2. **Unit Conversion**  
   - Converts lb → kg and ft → m if needed.  
3. **BMI Computation**  
   - Applies the standard BMI formula.  
4. **Category Classification**  
   - `BMI < 18.5` → Underweight  
   - `18.5 ≤ BMI < 25` → Healthy  
   - `BMI ≥ 25` → Overweight  
5. **Dynamic Image Update**  
   - Displays a category-specific image using `ImageIcon`.  
6. **Error Handling**  
   - Detects invalid or blank input and shows a friendly error message.

---

## 🖼️ **Visual Preview**
| Category | Example Output |
|-----------|----------------|
| Underweight | Displays light/skinny figure icon with “Underweight” text |
| Healthy | Shows fit/normal figure icon with “Healthy” message |
| Overweight | Shows broader figure or caution icon with “Overweight” message |

---

## 💾 **.gitignore Explanation**
```gitignore
*.class        # Ignore compiled class files
.vscode/       # Ignore VS Code settings
.idea/         # Ignore IntelliJ IDEA settings
*.iml          # Ignore IntelliJ project files
```

---

## 🧑‍💻 **Author Information**
**Name:** Jishnu Ganesh  
**Program:** M.S. in Industrial Engineering (IT Concentration)  
**Institution:** Louisiana State University  
**Course:** CSC 7435E – Secure DevOps Practices  
**Assignment:** Module 4 – Assignment #1  
**Date:** November 2025  

---

## 🏁 **Grading Criteria Reference**
| Requirement | How This App Meets It |
|--------------|-----------------------|
| ✅ Code runs with no errors | Tested locally using VS Code and JDK 17 |
| ✅ Reduced to required features | Contains only BMI logic, no extra functions |
| ✅ Documented with original comments | Each method has descriptive headers and inline notes |
| ✅ README in markdown | Explains purpose, features, and usage clearly |

---

## 🧾 **License / Usage**
This project is intended for **academic use only** under Module 4 of CSC 7435E.  
You may reuse the structure or logic for educational purposes with attribution.

---

## 📎 **Repository Link**
When pushed to GitHub, your repo will be:  
👉 [https://github.com/jishnukrg/JishnuGanesh_M4Assgn1](https://github.com/jishnukrg/JishnuGanesh_M4Assgn1)
=======
# JishnuGanesh_M4Assgn1
BMI Application
>>>>>>> 05102732f430f6de5876c4fcde60ef38eaa43e00
