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

document.getElementById('cadastro-setor').addEventListener('submit', async function(event) {
    event.preventDefault();

    const nome = document.getElementById('nome').value;
    const novoSetor = { nome };

    const response = await fetch('http://localhost:8080/setor/setores', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json; charset=utf-8'
        },
        body: JSON.stringify(novoSetor),
        credentials: 'include'
    });

    if (response.ok) {
        document.getElementById('cadastro-setor').reset();
        exibirSetores();
        closeModal();
    } else {
        console.error('Erro ao cadastrar setor:', await response.text());
    }
});

const modal = document.getElementById("modal-setor");
const btn = document.getElementById("open-modal");
const span = document.getElementsByClassName("close")[0];

btn.onclick = function() {
    modal.style.display = "block";
}

span.onclick = function() {
    modal.style.display = "none";
}

document.addEventListener('click', function(event) {
    if (event.target === modal) {
        modal.style.display = "none";
    }
});
