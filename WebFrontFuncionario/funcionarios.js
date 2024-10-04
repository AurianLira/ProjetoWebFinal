const params = new URLSearchParams(location.search);
const idSetor = params.get('id');

async function exibirFuncionarios() {
    const response = await fetch(`http://localhost:8080/funcionario/setor/${idSetor}`, {
        method: 'GET',
        credentials: 'include'
    });
    const funcionarios = await response.json();
    const listaFuncionarios = document.getElementById('lista-funcionarios');
    listaFuncionarios.innerHTML = '';

    funcionarios.forEach(funcionario => {
        const div = document.createElement('div');
        div.className = 'card-func';

        const nome = document.createElement('h2');
        nome.textContent = funcionario.nome;

        div.appendChild(nome);
        div.style.cursor = 'pointer'; 
        div.addEventListener('click', () => abrirModalRelato(funcionario));
        listaFuncionarios.appendChild(div);
    });
}

async function abrirModalRelato(funcionario) {
    document.getElementById('funcionario-nome').textContent = `Funcionário: ${funcionario.nome}`;
    await carregarProblemas();
    document.getElementById('modal-relato').style.display = 'block';
    document.selectedFuncionarioId = funcionario.id;
}

async function carregarProblemas() {
    const response = await fetch('http://localhost:8080/problema', {
        method: 'GET',
        credentials: 'include'
    });
    const problemas = await response.json();
    const listaProblemas = document.getElementById('lista-problemas');
    listaProblemas.innerHTML = '';

    problemas.forEach(problema => {
        const button = document.createElement('button');
        button.textContent = problema.tipo; 
        button.onclick = () => salvarRelato(problema);
        listaProblemas.appendChild(button);
    });
}

async function salvarRelato(problema) {
    const funcionarioId = document.selectedFuncionarioId;
    const relato = {
        id_funcionario: funcionarioId,
        id_problema: problema.id,
        data_relato: new Date().toISOString().split('T')[0]
    };

    const response = await fetch('http://localhost:8080/relato', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json; charset=utf-8'
        },
        body: JSON.stringify(relato),
        credentials: 'include'
    });

    if (response.ok) {
        alert('Obrigado, seu relato foi registrado com sucesso!');
        closeModalRelato();
    } else {
        console.error('Erro ao registrar relato:', await response.text());
    }
}

function closeModalRelato() {
    document.getElementById('modal-relato').style.display = 'none';
}

document.querySelector('.close-relato').addEventListener('click', closeModalRelato);

document.addEventListener('DOMContentLoaded', async function() {
    const setorResponse = await fetch(`http://localhost:8080/setor/${idSetor}`, {
        method: 'GET',
        credentials: 'include'
    });
    const setor = await setorResponse.json();
    document.getElementById('setor-nome').textContent = setor.nome;

    exibirFuncionarios();
});
