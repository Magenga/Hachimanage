import axios from 'axios';

export const getAllMessages = async (projectId) => {
  const response = await axios.get(`http://localhost:8080/message/project/${projectId}`);
  return response.data;
};
