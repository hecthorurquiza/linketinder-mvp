const formCandidate: any = document.querySelector('form');

formCandidate.addEventListener('submit', (e: any) => {
    e.preventDefault();

    const formData = new FormData(formCandidate);

    const data = {
        username: formData.get('username'),
        email: formData.get('email'),
        state: formData.get('state'),
        cep: formData.get('cep'),
        cpf: formData.get('cpf'),
        age: formData.get('age'),
        password: formData.get('password'),
        confirm: formData.get('confirm'),
    };

    console.log(data);
    alert('Candidato cadastrado com sucesso!');
});

