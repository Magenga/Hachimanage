import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import Loginbar from './Loginbar';
import axios from 'axios';

const Login = () => {
  const [account, setAccount] = useState('');
  const [password, setPassword] = useState('');
  const navigate = useNavigate();

  const handleKeyDown = (event) => {
    if (event.key === 'Enter') {
      handleLogin(event);
    }
  };


  const handleLogin = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post('http://localhost:8080/api/login', {
        account,
        password
      });

      if (response.status === 200) {
        const result = response.data;
        alert('Login succeeded!');

				localStorage.setItem('token', result.token);
        localStorage.setItem('account', result.account);
        localStorage.setItem('projectId', 1);
        localStorage.setItem('userId', result.userId);
        console.log(response.token);

				try {
          const getProjectResponse = await axios.get(`http://localhost:8080/api/setProject/${result.userId}`, {
            headers: {
              'Authorization': `Bearer ${result.token}`
            }
          });

          const projectList = getProjectResponse.data.data.map(project => project.id);
          localStorage.setItem('projectList', JSON.stringify(projectList));
          navigate('/dashboard');
        } catch (error) {
          console.error('Error fetching project list:', error);
          alert('Failed to fetch project list, but login succeeded');
          navigate('/dashboard');
        }

      } else {
        alert('Login failed with status: ' + response.status);
      }
    } catch (error) {
      console.error('Login error:', error.response ? error.response.data : error.message);
      alert('Login failed');
    }
  };

  return (
    <div>
      <div className="flex w-screen h-screen">
        <div className="flex flex-col items-center justify-center flex-grow bg-[#33185f]">
          <Loginbar
            account={account}
            setAccount={setAccount}
            password={password}
            setPassword={setPassword}
            handleLogin={handleLogin}
            handleKeyDown={handleKeyDown}
          />
        </div>
      </div>
    </div>
  );
};

export default Login;
