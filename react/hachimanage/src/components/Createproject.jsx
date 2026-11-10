import React, { useState, addProject } from "react";
import DatePicker from 'react-datepicker';
import 'react-datepicker/dist/react-datepicker.css';
import axios from 'axios';
import { BRAND } from '../brand';
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
        <div className="flex flex-col gap-[18px] w-[min(100vw-80px,440px)] text-ink">
            <div className="flex items-center gap-[14px]">
                <img src={BRAND.pounce} alt="" className="w-24 h-auto flex-shrink-0" />
                <div>
                    <h2 className="text-[26px] leading-tight">建立專案</h2>
                    <p className="text-sm text-ink-muted">開一個屬於你的專案空間。</p>
                </div>
            </div>
            <label className="hs-field">
                <span className="hs-field__label">專案名稱 Project Title</span>
                <input
                    type="text"
                    value={title}
                    onChange={(e) => setTitle(e.target.value)}
                    className="hs-field__input"
                    placeholder="例如：期末專題"
                />
            </label>
            <div className="grid grid-cols-2 gap-[14px]">
                <div className="hs-field">
                    <span className="hs-field__label">開始 Start date</span>
                    <div className="relative text-ink-muted focus-within:text-ginger-text">
                        <DatePicker
                            selected={startDate}
                            onChange={(date) => setStartDate(date)}
                            className="hs-field__input w-full pl-10"
                            placeholderText="dd/mm/yyyy"
                        />
                        <div className="absolute left-3 top-3 pointer-events-none">
                            <svg className="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg" aria-hidden="true">
                                <path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M8 7V3m8 4V3m-9 8h10M5 21h14a2 2 0 002-2V7a2 2 0 00-2-2H5a2 2 0 00-2 2v12a2 2 0 002 2z" />
                            </svg>
                        </div>
                    </div>
                </div>
                <div className="hs-field">
                    <span className="hs-field__label">結束 End date</span>
                    <div className="relative text-ink-muted focus-within:text-ginger-text">
                        <DatePicker
                            selected={endDate}
                            onChange={(date) => setEndDate(date)}
                            className="hs-field__input w-full pl-10"
                            placeholderText="dd/mm/yyyy"
                        />
                        <div className="absolute left-3 top-3 pointer-events-none">
                            <svg className="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg" aria-hidden="true">
                                <path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M8 7V3m8 4V3m-9 8h10M5 21h14a2 2 0 002-2V7a2 2 0 00-2-2H5a2 2 0 00-2 2v12a2 2 0 002 2z" />
                            </svg>
                        </div>
                    </div>
                </div>
            </div>
            <button onClick={handleCreateProject} className="hs-btn hs-btn--primary w-full">
                建立專案 →
            </button>
        </div>
    );
};

export default CreateProject;
