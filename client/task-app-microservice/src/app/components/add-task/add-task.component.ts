import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { ApiService } from '../../services/api.service';

@Component({
  selector: 'app-add-task',
  templateUrl: './add-task.component.html',
})
export class AddTaskComponent implements OnInit {
  title = '';
  description = '';
  category: number = 0;
  categories: any[] = [];
  titleError = false; // Error flag for title
  descriptionError = false; // Error flag for description
  categoryError = false; // Error flag for category

  @Input() editingTask: any = null; // Input to receive task data for editing
  @Output() taskAdded = new EventEmitter<void>(); // Event to notify that a task was added or updated
  @Output() formReset = new EventEmitter<void>(); // Event to notify when form is reset

  constructor(private apiService: ApiService) {}

  ngOnInit(): void {
    this.fetchCategories();
  }

  ngOnChanges(): void {
    // If editingTask is provided, populate the form with its data
    if (this.editingTask) {
      this.title = this.editingTask.title;
      this.description = this.editingTask.description;
      this.category = this.editingTask.categoryId;
    }
  }

  // Fetch categories from the API
  fetchCategories(): void {
    this.apiService.getAllCategories().subscribe(
      (response) => {
        this.categories = response;
      },
      (error) => {
        console.error('Error fetching categories', error);
      }
    );
  }

  // Validate form fields
  validateForm(): boolean {
    this.titleError = this.title.trim() === '';
    this.descriptionError = this.description.trim() === '';
    this.categoryError = this.category === 0;

    return !(this.titleError || this.descriptionError || this.categoryError);
  }

  // Add or update a task
  addTask(): void {
    if (!this.validateForm()) {
      return; // Stop if the form is not valid
    }

    const taskData = {
      title: this.title,
      description: this.description,
      categoryId: this.category,
      status: 'Active',
    };

    if (this.editingTask) {
      // Update existing task
      this.apiService.updateTask(this.editingTask.id, taskData).subscribe(
        (response) => {
          alert(response);
          this.resetForm();
          this.taskAdded.emit(); // Notify parent component of task update
        },
        (error) => {
          alert('Failed to update task');
          console.error('Error updating task', error);
        }
      );
    } else {
      // Add new task
      this.apiService.addTask(taskData).subscribe(
        (response) => {
          alert(response);
          this.resetForm();
          this.taskAdded.emit(); // Notify parent component of new task
        },
        (error) => {
          alert('Failed to add task');
          console.error('Error adding task', error);
        }
      );
    }
  }

  // Reset the form fields
  resetForm(): void {
    this.title = '';
    this.description = '';
    this.category = 0; // Adjust according to your default category
    this.editingTask = null; // Reset editing task
    this.formReset.emit(); // Notify the parent component
  }
}
