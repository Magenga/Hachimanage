import React, { useState, addProject } from "react";
import DatePicker from 'react-datepicker';
import 'react-datepicker/dist/react-datepicker.css';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const CreateProject = ({ closeModel, addProject }) => {
    const [startDate, setStartDate] = useState(null);
    const [endDate, setEndDate] = useState(null);
    const [title, setTitle] = useState('');
    const navigate = useNavigate();
    const token = localStorage.getItem('token');
    const account = localStorage.getItem('account');


    const handleCreateProject = async (e) => {
        e.preventDefault();
    
        const token = localStorage.getItem('token'); // 从本地存储获取 JWT 令牌
        console.log('JWT Token:', token); // 打印 token
        
        if (!token) {
            console.error('No token found');
            return;
        }
        
        try {
            const projectData = {
                title,
                startDate: startDate.toISOString(),
                endDate: endDate.toISOString()
            };
            console.log('Project Data:', projectData);
    
            const response = await axios.post('http://localhost:8080/api/project', projectData, {
                headers: {
                    'Account': account,
                    'Authorization': `Bearer ${token}`, // 添加 Authorization 头部
                    'Content-Type': 'application/json'
                }
            });
    
            if (response.status === 201 || response.status === 200) {
                alert('Create succeeded!');
                addProject(response.data.data.id);
                closeModel();
            }
        } catch (error) {
            console.error('There was an error creating the project:', error.response ? error.response.data : error.message);
            alert('Something went wrong');
        }
    };

    return (
        <div>
            <div className="auto bg-gray-100 py-6 flex flex-col justify-center sm:py-12">
                <div className="relative py-3 sm:max-w-xl sm:mx-auto">
                    <div className="relative px-4 py-10 bg-white mx-8 md:mx-0 shadow rounded-3xl sm:p-10">
                        <div className="max-w-md mx-auto">
                            <div className="flex items-center space-x-5">
                                <div className="h-14 w-14 bg-yellow-200 rounded-full flex flex-shrink-0 justify-center items-center text-yellow-500 text-2xl font-mono">i</div>
                                <div className="block pl-2 font-semibold text-xl self-start text-gray-700">
                                    <h2 className="leading-relaxed">Create Project</h2>
                                    <p className="text-sm text-gray-500 font-normal leading-relaxed">Create your own project server.</p>
                                </div>
                            </div>
                            <div className="divide-y divide-gray-200">
                                <div className="py-8 text-base leading-6 space-y-4 text-gray-700 sm:text-lg sm:leading-7">
                                    <div className="flex flex-col">
                                        <label className="leading-loose">Project Title</label>
                                        <input
                                            type="text"
                                            value={title}
                                            onChange={(e) => setTitle(e.target.value)}
                                            className="px-4 py-2 border focus:ring-gray-500 focus:border-gray-900 w-full sm:text-sm border-gray-300 rounded-md focus:outline-none text-gray-600"
                                            placeholder="Event title"
                                        />
                                    </div>
                                    <div className="flex items-center space-x-4">
                                        <div className="flex flex-col">
                                            <label className="leading-loose">Start date</label>
                                            <div className="relative focus-within:text-gray-600 text-gray-400">
                                                <DatePicker
                                                    selected={startDate}
                                                    onChange={(date) => setStartDate(date)}
                                                    className="pr-4 pl-10 py-2 border focus:ring-gray-500 focus:border-gray-900 w-full sm:text-sm border-gray-300 rounded-md focus:outline-none text-gray-600"
                                                    placeholderText="dd/mm/yyyy"
                                                />
                                                <div className="absolute left-3 top-2">
                                                    <svg className="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
                                                        <path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M8 7V3m8 4V3m-9 8h10M5 21h14a2 2 0 002-2V7a2 2 0 00-2-2H5a2 2 0 00-2 2v12a2 2 0 002 2z" />
                                                    </svg>
                                                </div>
                                            </div>
                                        </div>
                                        <div className="flex flex-col">
                                            <label className="leading-loose">End date</label>
                                            <div className="relative focus-within:text-gray-600 text-gray-400">
                                                <DatePicker
                                                    selected={endDate}
                                                    onChange={(date) => setEndDate(date)}
                                                    className="pr-4 pl-10 py-2 border focus:ring-gray-500 focus:border-gray-900 w-full sm:text-sm border-gray-300 rounded-md focus:outline-none text-gray-600"
                                                    placeholderText="dd/mm/yyyy"
                                                />
                                                <div className="absolute left-3 top-2">
                                                    <svg className="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
                                                        <path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M8 7V3m8 4V3m-9 8h10M5 21h14a2 2 0 002-2V7a2 2 0 00-2-2H5a2 2 0 00-2 2v12a2 2 0 002 2z" />
                                                    </svg>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div className="pt-4 flex items-center space-x-4">
                                    <button onClick={handleCreateProject} className="bg-blue-500 flex justify-center items-center w-full text-white px-4 py-3 rounded-md focus:outline-none">
                                        Create
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default CreateProject;
