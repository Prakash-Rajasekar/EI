# Astronaut Daily Schedule Organizer

## Overview

The Astronaut Daily Schedule Organizer is a console-based application designed to assist astronauts in managing their daily schedules. The application supports a variety of features for task management, including adding, removing, editing, and viewing tasks. It uses design patterns such as Singleton, Factory, and Observer to ensure efficient and reliable task management.

## Features

### Task Manager Menu
1. **Add Task**: Add a new task with description, start time, end time, and priority level.
2. **Remove Task**: Remove an existing task by description.
3. **Edit Task**: Modify an existing task’s details.
4. **Mark Task as Completed**: Update the status of a task to completed.
5. **View All Tasks**: Display all tasks, showing their details.
6. **View Completed Tasks**: Show only tasks that have been marked as completed.
7. **View Pending Tasks**: Show tasks that are still pending.
8. **View Tasks by Priority**: Filter and display tasks based on priority levels.
9. **View Tasks Sorted by Start Time**: List tasks sorted by their start time.
10. **View Tasks Sorted by Priority**: List tasks sorted by priority levels.
0. **Exit**: Exit the application and serialize the current state.

## Design Patterns Used

1. **Singleton Pattern**: Ensures a single instance of the `ScheduleManager` class to manage all tasks.
2. **Factory Pattern**: Utilizes `TaskFactory` to create `Task` objects.
3. **Observer Pattern**: Notifies users of task conflicts or updates.

## Setup and Usage

### Prerequisites
- Java 20
- Maven

### Build the Project

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/astronaut-daily-schedule-organizer.git
2. cd astronaut-daily-schedule-organizer
3. mvn clean package
4. java -jar target/task-manager-app-1.0-SNAPSHOT.jar

### Error Handling
- Conflicting Tasks: The application will notify users if a new task conflicts with an existing one.
- Invalid Operations: Errors will be reported for invalid task details or operations.
- Code Quality and Best Practices
- The application follows best coding practices and design principles, including SOLID principles and efficient error handling. It is optimized for performance and includes comprehensive logging for tracking usage and errors.

### License
- This project is licensed under the MIT License. See the LICENSE file for details.

### Contact
For any queries, please contact:

**Name**: Prakash Rajasekar
**Email**: prakashrajasekar00@gmail.com