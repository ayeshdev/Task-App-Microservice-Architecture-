// src/app/models/task.model.ts
export interface Task {
  id: number;
  title: string;
  description: string;
  categoryName?: string;
  categoryId: number; // Adjust according to your task structure
  status:string;
  // Add any other relevant properties of the task
}
