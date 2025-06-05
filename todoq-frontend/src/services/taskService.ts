import axios from 'axios';

const API_URL = 'http://localhost:8080/api/tasks';

export interface Task {
    id?: number;
    title: string;
    description: string;
    completed: boolean;
    dueDate: string;
    createdAt?: string;
    updatedAt?: string;
}

export const taskService = {
    getAllTasks: async (): Promise<Task[]> => {
        const response = await axios.get(API_URL);
        return response.data;
    },

    getTask: async (id: number): Promise<Task> => {
        const response = await axios.get(`${API_URL}/${id}`);
        return response.data;
    },

    createTask: async (task: Task): Promise<Task> => {
        const response = await axios.post(API_URL, task);
        return response.data;
    },

    updateTask: async (id: number, task: Task): Promise<Task> => {
        const response = await axios.put(`${API_URL}/${id}`, task);
        return response.data;
    },

    deleteTask: async (id: number): Promise<void> => {
        await axios.delete(`${API_URL}/${id}`);
    }
}; 