import React, { useEffect, useState } from 'react';
import {
    List,
    ListItem,
    ListItemText,
    IconButton,
    Checkbox,
    Typography,
    Paper,
    Box,
    TextField,
    Button,
    Dialog,
    DialogTitle,
    DialogContent,
    DialogActions,
} from '@mui/material';
import { Delete as DeleteIcon, Edit as EditIcon } from '@mui/icons-material';
import { Task, taskService } from '../services/taskService';
import { format } from 'date-fns';

export const TaskList: React.FC = () => {
    const [tasks, setTasks] = useState<Task[]>([]);
    const [open, setOpen] = useState(false);
    const [editingTask, setEditingTask] = useState<Task | null>(null);
    const [newTask, setNewTask] = useState<Partial<Task>>({
        title: '',
        description: '',
        dueDate: format(new Date(), "yyyy-MM-dd'T'HH:mm"),
        completed: false,
    });

    const loadTasks = async () => {
        try {
            const data = await taskService.getAllTasks();
            setTasks(data);
        } catch (error) {
            console.error('Error loading tasks:', error);
        }
    };

    useEffect(() => {
        loadTasks();
    }, []);

    const handleCreateTask = async () => {
        try {
            await taskService.createTask(newTask as Task);
            setNewTask({
                title: '',
                description: '',
                dueDate: format(new Date(), "yyyy-MM-dd'T'HH:mm"),
                completed: false,
            });
            loadTasks();
        } catch (error) {
            console.error('Error creating task:', error);
        }
    };

    const handleUpdateTask = async (task: Task) => {
        try {
            await taskService.updateTask(task.id!, task);
            setEditingTask(null);
            loadTasks();
        } catch (error) {
            console.error('Error updating task:', error);
        }
    };

    const handleDeleteTask = async (id: number) => {
        try {
            await taskService.deleteTask(id);
            loadTasks();
        } catch (error) {
            console.error('Error deleting task:', error);
        }
    };

    const handleToggleComplete = async (task: Task) => {
        try {
            await taskService.updateTask(task.id!, {
                ...task,
                completed: !task.completed,
            });
            loadTasks();
        } catch (error) {
            console.error('Error toggling task completion:', error);
        }
    };

    return (
        <Box sx={{ maxWidth: 800, mx: 'auto', p: 3 }}>
            <Paper sx={{ p: 3, mb: 3 }}>
                <Typography variant="h5" gutterBottom>
                    Add New Task
                </Typography>
                <Box sx={{ display: 'flex', flexDirection: 'column', gap: 2 }}>
                    <TextField
                        label="Title"
                        value={newTask.title}
                        onChange={(e) => setNewTask({ ...newTask, title: e.target.value })}
                        fullWidth
                    />
                    <TextField
                        label="Description"
                        value={newTask.description}
                        onChange={(e) => setNewTask({ ...newTask, description: e.target.value })}
                        fullWidth
                        multiline
                        rows={2}
                    />
                    <TextField
                        label="Due Date"
                        type="datetime-local"
                        value={newTask.dueDate}
                        onChange={(e) => setNewTask({ ...newTask, dueDate: e.target.value })}
                        fullWidth
                        InputLabelProps={{ shrink: true }}
                    />
                    <Button
                        variant="contained"
                        onClick={handleCreateTask}
                        disabled={!newTask.title}
                    >
                        Add Task
                    </Button>
                </Box>
            </Paper>

            <List>
                {tasks.map((task) => (
                    <Paper
                        key={task.id}
                        sx={{
                            mb: 2,
                            p: 2,
                            backgroundColor: task.completed ? '#f5f5f5' : 'white',
                        }}
                    >
                        <ListItem
                            secondaryAction={
                                <Box>
                                    <IconButton
                                        edge="end"
                                        onClick={() => setEditingTask(task)}
                                    >
                                        <EditIcon />
                                    </IconButton>
                                    <IconButton
                                        edge="end"
                                        onClick={() => task.id && handleDeleteTask(task.id)}
                                    >
                                        <DeleteIcon />
                                    </IconButton>
                                </Box>
                            }
                        >
                            <Checkbox
                                checked={task.completed}
                                onChange={() => handleToggleComplete(task)}
                            />
                            <ListItemText
                                primary={
                                    <Typography
                                        sx={{
                                            textDecoration: task.completed ? 'line-through' : 'none',
                                        }}
                                    >
                                        {task.title}
                                    </Typography>
                                }
                                secondary={
                                    <>
                                        <Typography variant="body2" color="text.secondary">
                                            {task.description}
                                        </Typography>
                                        <Typography variant="caption" color="text.secondary">
                                            Due: {format(new Date(task.dueDate), 'PPp')}
                                        </Typography>
                                    </>
                                }
                            />
                        </ListItem>
                    </Paper>
                ))}
            </List>

            <Dialog open={!!editingTask} onClose={() => setEditingTask(null)}>
                {editingTask && (
                    <>
                        <DialogTitle>Edit Task</DialogTitle>
                        <DialogContent>
                            <Box sx={{ display: 'flex', flexDirection: 'column', gap: 2, pt: 2 }}>
                                <TextField
                                    label="Title"
                                    value={editingTask.title}
                                    onChange={(e) =>
                                        setEditingTask({ ...editingTask, title: e.target.value })
                                    }
                                    fullWidth
                                />
                                <TextField
                                    label="Description"
                                    value={editingTask.description}
                                    onChange={(e) =>
                                        setEditingTask({
                                            ...editingTask,
                                            description: e.target.value,
                                        })
                                    }
                                    fullWidth
                                    multiline
                                    rows={2}
                                />
                                <TextField
                                    label="Due Date"
                                    type="datetime-local"
                                    value={format(new Date(editingTask.dueDate), "yyyy-MM-dd'T'HH:mm")}
                                    onChange={(e) =>
                                        setEditingTask({
                                            ...editingTask,
                                            dueDate: e.target.value,
                                        })
                                    }
                                    fullWidth
                                    InputLabelProps={{ shrink: true }}
                                />
                            </Box>
                        </DialogContent>
                        <DialogActions>
                            <Button onClick={() => setEditingTask(null)}>Cancel</Button>
                            <Button
                                onClick={() => handleUpdateTask(editingTask)}
                                variant="contained"
                            >
                                Save
                            </Button>
                        </DialogActions>
                    </>
                )}
            </Dialog>
        </Box>
    );
}; 