const formCompanies: any = document.querySelector('form');

formCompanies.addEventListener('submit', (e: any) => {
    e.preventDefault();

    const formData = new FormData(formCompanies);

    const data = {
        username: formData.get('username'),
        email: formData.get('email'),
        state: formData.get('state'),
        cep: formData.get('cep'),
        cpf: formData.get('cnpf'),
        age: formData.get('age'),
        password: formData.get('password'),
        confirm: formData.get('confirm'),
    };

    console.log(data);
    alert("Empresa cadastrada com sucesso!")
});