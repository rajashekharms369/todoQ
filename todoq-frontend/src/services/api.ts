import axios from 'axios';
import { Task } from '../types/Task';

const API_URL = 'http://localhost:8080/api';

export const api = {
    getAllTasks: async (): Promise<Task[]> => {
        const response = await axios.get(`${API_URL}/tasks`);
        return response.data;
    },

    createTask: async (task: Task): Promise<Task> => {
        const response = await axios.post(`${API_URL}/tasks`, task);
        return response.data;
    },

    updateTask: async (id: number, task: Task): Promise<Task> => {
        const response = await axios.put(`${API_URL}/tasks/${id}`, task);
        return response.data;
    },

    deleteTask: async (id: number): Promise<void> => {
        await axios.delete(`${API_URL}/tasks/${id}`);
    }
}; 