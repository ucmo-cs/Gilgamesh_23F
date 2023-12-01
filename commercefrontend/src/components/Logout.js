import React from 'react';
import { useHistory } from 'react-router-dom';
import {Link} from 'react-router-dom';
import { useAuth } from './AuthContext';
import './Header.css';

function Logout() {
  const history = useHistory();
  const { logout } = useAuth();

  const navButtons = {
      backgroundColor: '#f2f2f2',
      border: 'none',
      height: '98px',
      cursor: 'pointer',
      transition: 'background-color 0.3s ease',
      margin: '0px',
    }

  const handleLogout = async () => {

    try {
      await fetch('http://localhost:8080/user/logout', {
        method: 'GET',
        credentials: 'include',
      });

      logout();
      history.push('/');
    } catch (error) {
      console.error('Logout failed', error);
    }
  };

  return (
    <Link onClick={handleLogout} className = "nav-link">
      <button style={navButtons} className = "navButtons">
      Logout
      </button>
    </Link>
  );
};

export default Logout;
