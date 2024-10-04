document.addEventListener('DOMContentLoaded', async function() {
    const url = new URL(window.location.href);
    const idSetor = url.searchParams.get('id');

    const setorResponse = await fetch(`http://localhost:8080/setor/${idSetor}`, {
        method: 'GET',
        credentials: 'include'
    });
    const setor = await setorResponse.json();
    document.getElementById('setor-nome').textContent = setor.nome;

    await carregarFuncionarios(); 
    await exibirRelatos(); 
});

async function carregarFuncionarios() { 
    const url = new URL(window.location.href);
    const idSetor = url.searchParams.get('id');

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

        const nomeElement = document.createElement('h3'); 
        nomeElement.className = 'nome'; 
        nomeElement.textContent = `Nome: ${funcionario.nome}`; 

        const registroElement = document.createElement('h3'); 
        registroElement.className = 'registro'; 
        registroElement.textContent = `Registro: ${funcionario.registro}`; 

        div.appendChild(nomeElement);
        div.appendChild(registroElement);
        
        listaFuncionarios.appendChild(div);
    });
}

async function exibirRelatos() { 
    const url = new URL(window.location.href);
    const idSetor = url.searchParams.get('id');

    const response = await fetch(`http://localhost:8080/setor/${idSetor}/relatos`, {
        method: 'GET',
        credentials: 'include'
    });
    const relatos = await response.json();
    console.log(relatos);
    const listaRelatos = document.getElementById('lista-relatos');
    listaRelatos.innerHTML = ''; 

    const relatosPorData = {};
    
    relatos.forEach(relato => {
        const dataRelatoUTC = new Date(relato.dataRelato);

        const dataRelatoBrasilia = new Date(dataRelatoUTC.getTime() - 3 * 60 * 60 * 1000); 
        const dataRelato = dataRelatoBrasilia.toLocaleDateString('pt-BR'); 
        if (!relatosPorData[dataRelato]) {
            relatosPorData[dataRelato] = {};
        }
        
        const tipoProblema = relato.problemaTipo;
        if (!relatosPorData[dataRelato][tipoProblema]) {
            relatosPorData[dataRelato][tipoProblema] = 0;
        }
        relatosPorData[dataRelato][tipoProblema]++;
    });

    Object.keys(relatosPorData).forEach(data => {
        const divData = document.createElement('div');
        divData.className = 'card-data'; 

        const tituloData = document.createElement('h3');
        tituloData.textContent = `Data: ${data}`;
        divData.appendChild(tituloData);
        
        const quantidadeRelatos = document.createElement('h4');
        quantidadeRelatos.textContent = `Quantidade de Relatos: ${Object.values(relatosPorData[data]).reduce((a, b) => a + b, 0)}`;
        divData.appendChild(quantidadeRelatos);

        Object.keys(relatosPorData[data]).forEach(tipo => {
            const divTipo = document.createElement('div');
            divTipo.className = 'card-tipo'; 

            const tituloTipo = document.createElement('h4');
            tituloTipo.textContent = `Tipo de Relato: ${tipo} - Quantidade: ${relatosPorData[data][tipo]}`;
            divTipo.appendChild(tituloTipo);

            divData.appendChild(divTipo);
        });

        listaRelatos.appendChild(divData);
    });
}



document.getElementById('cadastro-funcionario').addEventListener('submit', async function(event) {
    event.preventDefault();

    const nome = document.getElementById('nome').value;
    const registro = document.getElementById('registro').value;
    const url = new URL(window.location.href);
    const idSetor = url.searchParams.get('id');

    const novoFuncionario = { nome, registro, id_setor: idSetor };

    const response = await fetch('http://localhost:8080/funcionario', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json; charset=utf-8'
        },
        body: JSON.stringify(novoFuncionario),
        credentials: 'include'
    });

    if (response.ok) {
        document.getElementById('cadastro-funcionario').reset();
        carregarFuncionarios(); 
        closeModal();
    } else {
        console.error('Erro ao cadastrar funcionário:', await response.text());
    }
});

function openModal() {
    document.getElementById('modal').style.display = 'block';
}

function closeModal() {
    document.getElementById('modal').style.display = 'none';
}

document.getElementById('abrir-modal-func').addEventListener('click', openModal);
document.querySelector('.close').addEventListener('click', closeModal);
