import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Login from './components/Login';
import Dashboard from './components/Dashboard';
import Chatroom from './components/Chatroom'; // 确保这里正确导入

function App() {
  const token = localStorage.getItem('token');
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/dashboard" element={<Dashboard />} />
        <Route path="/chatroom/:projectId" element={<Chatroom />} />
      </Routes>
    </Router>
  );
}

export default App;
