// api.service.ts
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';
import { Task } from '../model/task.model';

@Injectable({
  providedIn: 'root',
})
export class ApiService {
  private apiUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) {}

  // Fetch all tasks
  getAllTasks(): Observable<any> {
    return this.http.get(`${this.apiUrl}/task-service/task/all-tasks`);
  }

  // Search tasks by title only
searchTasks(title: string): Observable<Task[]> {
  return this.http.get<Task[]>(`${this.apiUrl}/task-service/task/search?title=${encodeURIComponent(title)}`);
}

  // Add a new task
  addTask(taskData: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/task-service/task/add-task`, taskData, {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
      }),
      responseType: 'text' as 'json',
    });
  }

  // Update an existing task
  updateTask(taskId: number, taskData: any): Observable<any> {
    return this.http.put(`${this.apiUrl}/task-service/task/update-task/${taskId}`, taskData, {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
      }),
      responseType: 'text' as 'json',
    });
  }

  // Delete a task
  deleteTask(taskId: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/task-service/task/delete-task/${taskId}`, {
      responseType: 'text' as 'json',
    });
  }

  // Fetch all categories
  getAllCategories(): Observable<any> {
    return this.http.get(`${this.apiUrl}/task-service/task/all-categories`);
  }
}
