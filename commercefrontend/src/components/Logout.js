import React from 'react';
import { useHistory } from 'react-router-dom';
import {Link} from 'react-router-dom';
import { useAuth } from './AuthContext';

function Logout() {
  const history = useHistory();
  const { logout } = useAuth();

  const handleLogout = async () => {
    // Make a request to your backend to log the user out
    try {
      await fetch('http://localhost:8080/logout', {
        method: 'GET', // or the appropriate method for your backend
        credentials: 'include', // include cookies in the request
      });

      logout();
      history.push('/');
    } catch (error) {
      console.error('Logout failed', error);
      // Handle logout failure if needed
    }
  };

  return (
    <Link onClick={handleLogout} className = "nav-link">
      Logout
    </Link>
  );
};

export default Logout;
