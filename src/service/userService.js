export async function createUser(data) {
    const response = await fetch('http://localhost:8080/', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ user:data })
        });
    return response.json();
}