import axios from "axios";

const apiClient = axios.create({
    baseURL: "http://localhost:8080", // 백엔드 API의 기본 URL
    headers: {
        "Content-Type": "application/json",
    },
});

export default apiClient;
