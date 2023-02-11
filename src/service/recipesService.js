export async function getRecipes() {
    const response = await fetch('http://localhost:8080/recipes', {
        method: 'GET',
        headers: { 'Content-Type': 'application/json' },
        });
    return response.json();
}