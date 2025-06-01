import React, { useEffect, useState } from 'react';
import { 
    List, 
    ListItem, 
    ListItemText, 
    Checkbox, 
    IconButton, 
    Typography,
    Paper
} from '@mui/material';
import { Delete as DeleteIcon } from '@mui/icons-material';
import { format } from 'date-fns';
import { Task } from '../types/Task';
import { api } from '../services/api';

const TaskList: React.FC = () => {
    const [tasks, setTasks] = useState<Task[]>([]);

    useEffect(() => {
        loadTasks();
    }, []);

    const loadTasks = async () => {
        try {
            const data = await api.getAllTasks();
            setTasks(data);
        } catch (error) {
            console.error('Error loading tasks:', error);
        }
    };

    const handleToggle = async (task: Task) => {
        try {
            const updatedTask = await api.updateTask(task.id!, {
                ...task,
                completed: !task.completed
            });
            setTasks(tasks.map(t => t.id === task.id ? updatedTask : t));
        } catch (error) {
            console.error('Error updating task:', error);
        }
    };

    const handleDelete = async (id: number) => {
        try {
            await api.deleteTask(id);
            setTasks(tasks.filter(task => task.id !== id));
        } catch (error) {
            console.error('Error deleting task:', error);
        }
    };

    return (
        <Paper elevation={3} sx={{ maxWidth: 800, margin: '20px auto', padding: 2 }}>
            <Typography variant="h5" component="h2" gutterBottom>
                Tasks
            </Typography>
            <List>
                {tasks.map((task) => (
                    <ListItem
                        key={task.id}
                        secondaryAction={
                            <IconButton edge="end" onClick={() => handleDelete(task.id!)}>
                                <DeleteIcon />
                            </IconButton>
                        }
                    >
                        <Checkbox
                            edge="start"
                            checked={task.completed}
                            onChange={() => handleToggle(task)}
                        />
                        <ListItemText
                            primary={task.title}
                            secondary={
                                <>
                                    <Typography component="span" variant="body2" color="text.primary">
                                        {task.description}
                                    </Typography>
                                    <br />
                                    Due: {format(new Date(task.dueDate), 'PPP')}
                                </>
                            }
                        />
                    </ListItem>
                ))}
            </List>
        </Paper>
    );
};

export default TaskList; 