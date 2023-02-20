const BASE_URL = "http://localhost:8080";

export const userService = {
  register(registerRequest) {
    return fetch(`${BASE_URL}/auth/register`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(registerRequest),
    }).then((response) => {
      if (response.ok) {
        return response.json();
      } else {
        return Promise.reject(new Error("Failed to register"));
      }
    });
  },
};
