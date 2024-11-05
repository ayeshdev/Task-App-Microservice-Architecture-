import { Component, OnInit } from '@angular/core';
import { ApiService } from '../../services/api.service';
import { Task } from '../../model/task.model'; // Adjust the path as necessary

@Component({
  selector: 'app-task-list',
  templateUrl: './task-list.component.html',
})
export class TaskListComponent implements OnInit {
  tasks: Task[] = [];
  filteredTasks: Task[] = []; // Array to hold filtered tasks
  searchQuery: string = ''; // Search query
  categories: any[] = []; // Array to store categories
  selectedTask: Task | null = null;

  constructor(private apiService: ApiService) {}

  ngOnInit(): void {
    this.loadCategories(); // Load categories on initialization
    this.loadTasks();
  }

  // Load categories from the API
  loadCategories(): void {
    this.apiService.getAllCategories().subscribe(
      (data: any[]) => {
        this.categories = data;
        this.loadTasks(); // Load tasks after categories are loaded
      },
      (error) => {
        console.error('Failed to fetch categories', error);
      }
    );
  }

  // Load tasks from the API
  loadTasks(): void {
    this.apiService.getAllTasks().subscribe(
      (data: Task[]) => {
        this.tasks = data;
        this.filteredTasks = data; // Initially show all tasks

        // Map categoryId to categoryName
        this.tasks.forEach((task) => {
          const category = this.categories.find(
            (c) => c.id === task.categoryId
          );
          task.categoryName = category ? category.name : 'Unknown'; // Set the category name
        });
      },
      (error) => {
        console.error('Failed to fetch tasks', error);
      }
    );
  }

  // Search for tasks based on the title query only
searchTasks(): void {
  this.apiService.searchTasks(this.searchQuery.trim()).subscribe(
    (data: Task[]) => {
      this.filteredTasks = data;
    },
    (error) => {
      console.error('Error searching tasks', error);
    }
  );
}


  // Method to delete a task
  deleteTask(taskId: number): void {
    this.apiService.deleteTask(taskId).subscribe(
      (response) => {
        alert('Task deleted successfully');
        this.loadTasks(); // Reload tasks after deletion
      },
      (error) => {
        alert('Failed to delete task');
        console.error('Error deleting task', error);
      }
    );
  }

  // Set the selected task for editing
  editTask(task: Task): void {
    this.selectedTask = task;
  }

  // Handle the task addition or update event
  onTaskAdded(): void {
    this.loadTasks(); // Reload tasks after addition or update
    this.selectedTask = null; // Clear the selected task after addition or update
  }

  // Reset the selected task when the form is cleared
  onFormReset(): void {
    this.selectedTask = null;
  }
}
