import React from 'react';
import { CssBaseline, Container, AppBar, Toolbar, Typography, ThemeProvider, createTheme } from '@mui/material';
import { TaskList } from './components/TaskList';

const theme = createTheme({
  palette: {
    primary: {
      main: '#1976d2',
    },
    background: {
      default: '#f5f5f5',
    },
  },
});

function App() {
  return (
    <ThemeProvider theme={theme}>
      <CssBaseline />
      <AppBar position="static">
        <Toolbar>
          <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
            TodoQ
          </Typography>
        </Toolbar>
      </AppBar>
      <Container>
        <TaskList />
      </Container>
    </ThemeProvider>
  );
}

export default App;
