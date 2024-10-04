document.addEventListener('DOMContentLoaded', async function() {
    await exibirSetores(); 
});

async function exibirSetores() {
    const response = await fetch('http://localhost:8080/setor/setores', {
        method: 'GET',
        credentials: 'include'
    });
    const setores = await response.json();
    const setoresList = document.getElementById('lista-setores');
    setoresList.innerHTML = '';

    setores.forEach(setor => {
        const card = document.createElement('div');
        card.className = 'card-setor';
        card.innerHTML = `<h2>${setor.nome}</h2>`;
        card.onclick = () => location.href = `index2.html?id=${setor.id}`; 
        setoresList.appendChild(card);
    });
}
