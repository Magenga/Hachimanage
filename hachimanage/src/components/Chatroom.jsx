import React, { useEffect, useRef, useState } from 'react';
import SockJS from 'sockjs-client';
import { Client } from '@stomp/stompjs';
import axios from 'axios';
import './Chatroom.css';
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
            'Authorization': `Bearer ${token}` 
          }
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
        'Authorization': `Bearer ${token}`
      }
    });

    client.current.onConnect = (frame) => {
      console.log('WebSocket connected:', frame);
      client.current.subscribe(`/topic/${projectId}`, (message) => {
        const receivedMessage = JSON.parse(message.body);
        setMessages((prevMessages) => [...prevMessages, receivedMessage]);
        console.log('Message received:', receivedMessage);
      }, {
        'Authorization': `Bearer ${token}`
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
          'Authorization': `Bearer ${token}` // 添加JWT令牌到消息头部
        }
      });
  
      setNewMessage('');
    } else {
      console.error('WebSocket connection is not established yet or message is empty.');
    }
  };

  return (

    <div>
    <Sidebar />
    <div className="chatroom">
      <h2>Chat Room</h2>
      <div id="messageContainer" className="chat-history">
        {messages.map((msg, index) => (
          <div key={index} className="message">
            {msg.timestamp} - {msg.userAccount}: {msg.content}
          </div>
        ))}
      </div>
      <div className="chat-input">
        <input
          type="text"
          id="messageContent"
          value={newMessage}
          onChange={(e) => setNewMessage(e.target.value)}
          placeholder="Type your message here..."
        />
        <button id="sendMessage" onClick={sendMessage}>
          Send
        </button>
      </div>
    </div>
    </div>
  );
};

export default Chatroom;
