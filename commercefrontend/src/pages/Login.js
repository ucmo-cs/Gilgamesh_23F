import React, { useState } from 'react';
import { useAuth } from '../components/AuthContext';

const Login = () => {
  const { login } = useAuth();
  const [userId, setUserId] = useState('');
  const [password, setPassword] = useState('');

  const handleLogin = async () => {
    try {
      const response = await fetch('http://localhost:8080/login', {
        method: 'GET',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ userId, password }),
        credentials: 'include',
      });

      if (response.ok) {
        login();
      } else {
        console.error('Login failed');
      }
    } catch (error) {
      console.error('Error during login:', error);
    }
  };

  return (
    <div style={styles.container}>
      <h2 style={styles.header}>Login Page</h2>
      <label style={styles.label}>User ID: </label>
      <input
        type="text"
        value={userId}
        onChange={(e) => setUserId(e.target.value)}
        style={styles.input}
      />
      <br />
      <label style={styles.label}>Password: </label>
      <input
        type="password"
        value={password}
        onChange={(e) => setPassword(e.target.value)}
        style={styles.input}
      />
      <br />
      <button onClick={handleLogin} style={styles.button}>
        Login
      </button>
    </div>
  );
};

const styles = {
  container: {
    display: 'flex',
    flexDirection: 'column',
    alignItems: 'center',
    justifyContent: 'center',
    height: '100vh',
  },
  header: {
    marginBottom: '20px',
  },
  label: {
    marginBottom: '6px',
  },
  input: {
    marginBottom: '10px',
    padding: '8px',
  },
  button: {
    width: '200px',
    padding: '10px',
    backgroundColor: '#006747',
    color: 'white',
    border: 'none',
    borderRadius: '5px',
    cursor: 'pointer',
  },
};

export default Login;
