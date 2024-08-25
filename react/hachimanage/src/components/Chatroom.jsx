import React, { useEffect, useRef, useState } from 'react';
import SockJS from 'sockjs-client';
import { Client } from '@stomp/stompjs';
import axios from 'axios';
import Sidebar from './Sidebar';
import { useParams } from 'react-router-dom';

const Chatroom = () => {
  const [messages, setMessages] = useState([]);
  const [newMessage, setNewMessage] = useState('');
  const client = useRef(null);
  const socket = useRef(null);
  const token = localStorage.getItem('token');
  const account = localStorage.getItem('account');
  const userId = localStorage.getItem('userId');
  const { projectId } = useParams();
  const messageEndRef = useRef(null);

  useEffect(() => {
    if (client.current) {
      if (client.current.connected) {
        client.current.deactivate();
      }
    }

    const fetchHistory = async () => {
      try {
        const response = await axios.get(`http://localhost:8080/message/project/${projectId}`, {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        });
        setMessages(response.data);
      } catch (error) {
        console.error('Error fetching chat history:', error);
      }
    };

    fetchHistory();

    socket.current = new SockJS('http://localhost:8080/ws');
    client.current = new Client({
      webSocketFactory: () => socket.current,
      debug: (str) => {
        console.log(str);
      },
      reconnectDelay: 5000,
      connectHeaders: {
        Authorization: `Bearer ${token}`,
      },
    });

    client.current.onConnect = (frame) => {
      console.log('WebSocket connected:', frame);
      client.current.subscribe(`/topic/${projectId}`, (message) => {
        const receivedMessage = JSON.parse(message.body);
        setMessages((prevMessages) => [...prevMessages, receivedMessage]);
        console.log('Message received:', receivedMessage);
      }, {
        Authorization: `Bearer ${token}`,
      });
    };

    client.current.onStompError = (frame) => {
      console.error('Broker reported error:', frame.headers['message']);
      console.error('Additional details:', frame.body);
    };

    client.current.activate();

    return () => {
      if (client.current.connected) {
        client.current.deactivate();
      } else {
        socket.current.close();
      }
    };
  }, [projectId]);

  useEffect(() => {
    if (messageEndRef.current) {
      messageEndRef.current.scrollIntoView();
    }
  }, [messages]);

  const sendMessage = () => {
    if (client.current && client.current.connected && newMessage.trim() !== '') {
      const chatMessage = {
        userId: userId,
        projectId: projectId,
        userAccount: account,
        content: newMessage,
        type: 'CHAT',
      };

      console.log('Sending message:', chatMessage);

      client.current.publish({
        destination: `/app/chat.sendMessage/${projectId}`,
        body: JSON.stringify(chatMessage),
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });

      setNewMessage('');
    } else {
      console.error('WebSocket connection is not established yet or message is empty.');
    }
  };

  return (
    <div className="flex h-screen antialiased text-gray-800 bg-[#3A3A3A]">
      <Sidebar />
      <div className="w-40 bg-[#3A3A3A]"></div>
      <div className="flex flex-col flex-auto h-full p-6 bg-[#3A3A3A]">
        <div className="flex flex-col flex-grow rounded-2xl bg-gray-100 p-4 chat-container bg-[#575757] overflow-y-auto">
          {messages.map((msg, index) => (
            <ChatMessage
              key={index}
              message={msg.content}
              account={msg.userAccount}
              time={msg.timestamp}
              isOwnMessage={account === msg.userAccount}
              avatarUrl={`https://api.dicebear.com/9.x/adventurer/svg?seed=${msg.userAccount}`}
            />
          ))}
          <div ref={messageEndRef} />
        </div>
        <div className="flex flex-row items-center h-16 mt-4 bg-[#575757] w-full px-4 rounded-xl">
          <div className="flex-grow">
            <div className="relative w-full">
              <input
                type="text"
                value={newMessage}
                onChange={(e) => setNewMessage(e.target.value)}
                placeholder="Type here to chat."
                onKeyDown={(e) => e.key === 'Enter' && sendMessage()}
                className="flex w-full rounded-xl focus:outline-none pl-4 h-10 bg-[#575757] text-white caret-white"
              />
              <button onClick={sendMessage} className="absolute flex items-center justify-center h-full w-12 right-0 top-0 text-gray-400 hover:text-gray-600">
                <svg className="w-4 h-4 transform rotate-45 -mt-px" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
                  <path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M12 19l9 2-9-18-9 18 9-2zm0 0v-8"></path>
                </svg>
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

const ChatMessage = ({ message, account, time, avatarUrl, isOwnMessage }) => (
  <div className={`col-start-1 col-end-13 p-3 ${isOwnMessage ? 'justify-end' : 'justify-start'} flex`}>
    <div className={`flex items-center ${isOwnMessage ? 'flex-row-reverse' : ''}`}>
      <div className="flex items-center justify-center h-10 w-10 rounded-full bg-indigo-500 flex-shrink-0">
        <img src={avatarUrl} alt="Avatar" className="h-full w-full rounded-full" />
      </div>
      <div className={`relative ml-3 text-sm w-full ${isOwnMessage ? 'text-right' : ''}`}>
        <div className="font-bold text-white">{account}</div>
        <div className='text-white'>{message}</div>
        <div className="text-xs text-gray-500">{time}</div>
      </div>
    </div>
  </div>
);

export default Chatroom;
