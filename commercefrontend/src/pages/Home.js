import React, { useEffect, useState} from 'react';
import BookItem from '../components/BookItem';
 
function Home() {

  const centerTextStyle = {
    textAlign: 'center',
  };

  return (
    <div style={centerTextStyle}v>
        <h1>Commerce Bank</h1>
        <h2>Change Management System</h2>
    </div>
  );
}

export default Home;
