# Ludonopoly - Board Games Collection

This repository contains two classic board games implemented in Java:
1. **Monopoly Game** - A digital version of the classic Monopoly board game
2. **Snake & Ladder Game** - A traditional Snake and Ladder game with GUI

## VSCode Setup Instructions

### Prerequisites
1. **Java Development Kit (JDK) 17 or higher**
2. **Visual Studio Code**
3. **Maven** (for the Monopoly project)

### Quick Setup with VSCode

#### Option 1: Open Workspace File (Recommended)
1. Open VSCode
2. Go to `File > Open Workspace from File...`
3. Navigate to the repository folder and select `Ludonopoly.code-workspace`
4. VSCode will automatically configure the workspace with proper settings

#### Option 2: Open Folder
1. Open VSCode
2. Go to `File > Open Folder...`
3. Select the root directory of this repository
4. VSCode will automatically detect the Java projects and suggest installing extensions

### Required Extensions
VSCode will automatically suggest installing these extensions when you open the workspace:

- **Extension Pack for Java** - Complete Java development environment
- **Java Dependency Viewer** - Manage Java dependencies
- **Java Test Runner** - Run and debug Java tests
- **Maven for Java** - Maven project support
- **Debugger for Java** - Java debugging capabilities
- **Language Support for Java** - Core Java language features
- **Code Runner** - Quick code execution

### Running the Games

#### Monopoly Game
1. **Using VSCode Run Configuration:**
   - Open the Command Palette (`Ctrl+Shift+P` / `Cmd+Shift+P`)
   - Type "Java: Run and Debug"
   - Select "Launch Monopoly Game" from the list

2. **Using Maven:**
   - Open terminal in VSCode (`Ctrl+`` ` or `View > Terminal`)
   - Navigate to: `cd "ASH2225025M/Monopoly abtahe"`
   - Run: `mvn clean compile exec:java -Dexec.mainClass="monopoly.MonopolyInitGUI"`

3. **Using Code Runner:**
   - Open `MonopolyInitGUI.java`
   - Click the "Run Java" button in the top-right corner

#### Snake & Ladder Game
1. **Using VSCode Run Configuration:**
   - Open the Command Palette (`Ctrl+Shift+P` / `Cmd+Shift+P`)
   - Type "Java: Run and Debug"
   - Select "Launch Snake & Ladder Game" from the list

2. **Using Code Runner:**
   - Open `SnakeLadderGame.java`
   - Click the "Run Code" button in the top-right corner

### Building the Projects

#### Monopoly Project (Maven)
- **Build:** `Ctrl+Shift+P` → "Tasks: Run Task" → "Build Monopoly Project"
- **Test:** `Ctrl+Shift+P` → "Tasks: Run Task" → "Test Monopoly Project"
- **Package:** `Ctrl+Shift+P` → "Tasks: Run Task" → "Package Monopoly Project"

#### Snake & Ladder Project
- **Compile:** `Ctrl+Shift+P` → "Tasks: Run Task" → "Compile Snake & Ladder"

### Debugging
1. Set breakpoints by clicking in the gutter next to line numbers
2. Use the Debug configuration "Debug Monopoly Game" for step-through debugging
3. Use the Debug panel (`Ctrl+Shift+D`) to view variables and call stack

### Project Structure
```
Ludonopoly/
├── .vscode/                 # VSCode configuration files
│   ├── settings.json        # Workspace settings
│   ├── launch.json          # Run/Debug configurations
│   ├── tasks.json           # Build tasks
│   └── extensions.json      # Recommended extensions
├── ASH2225025M/
│   ├── Monopoly abtahe/     # Maven-based Monopoly project
│   │   ├── src/main/java/   # Java source files
│   │   ├── pom.xml          # Maven configuration
│   │   └── target/          # Compiled classes
│   ├── Snake & Ladder/      # Standalone Snake & Ladder project
│   │   ├── SnakeLadderGame.java
│   │   └── board.jpg
│   └── readme.txt           # Original setup instructions
├── Ludonopoly.code-workspace # VSCode workspace file
└── README.md                # This file
```

### Troubleshooting

#### Common Issues:
1. **Java not found:** Ensure JDK 17+ is installed and JAVA_HOME is set
2. **Maven not found:** Install Maven and add it to your PATH
3. **Extensions not working:** Reload VSCode window (`Ctrl+Shift+P` → "Developer: Reload Window")
4. **Build failures:** Clean and rebuild (`Tasks: Run Task` → "Build Monopoly Project")

#### Getting Help:
- Use VSCode's integrated terminal for command-line operations
- Check the Problems panel (`Ctrl+Shift+M`) for compilation errors
- Use the Output panel to view detailed logs from Java Language Server

### Features Available in VSCode:
- ✅ Syntax highlighting and IntelliSense
- ✅ Auto-completion and code suggestions  
- ✅ Error detection and quick fixes
- ✅ Integrated debugging with breakpoints
- ✅ Maven integration for dependency management
- ✅ One-click run configurations for both games
- ✅ Code formatting and organization
- ✅ Integrated terminal for build commands
- ✅ Git integration for version control

Enjoy playing the games and happy coding! 🎲🐍