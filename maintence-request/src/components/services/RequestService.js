import axios from 'axios';

const MAINTENANCE_BASE_API_URL = 'http://localhost:8081/api/v1/maintenance';

export function getAllRequests(){
    return axios.get(MAINTENANCE_BASE_API_URL);
}

export function createRequest(maintence){
    return axios.post(MAINTENANCE_BASE_API_URL,maintence);
}

export function getById(id){
    return axios.get(`${MAINTENANCE_BASE_API_URL}/${id}`);
}

export function updateRequest(id, maintence){
    return axios.put(`${MAINTENANCE_BASE_API_URL}/${id}`, maintence);
}

export function deleteRequest(id){
    return axios.delete(`${MAINTENANCE_BASE_API_URL}/${id}`);
}
