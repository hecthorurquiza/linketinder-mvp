const formCandidate: any = document.querySelector('form');

const candidateNameRegex: RegExp = /^[a-zA-Z\s]+$/;
const candidateEmailRegex: RegExp = /\S+@\w+\.\w{2,6}(\.\w{2})?/i;
const stateRegex: RegExp = /^[A-Z]{2}$/;
const cepRegex: RegExp = /^[0-9]{5}-[0-9]{3}$/;
const cpfRegex: RegExp = /^[0-9]{3}\.?[0-9]{3}\.?[0-9]{3}-?[0-9]{2}$/;
const ageRegex: RegExp = /^[0-9]{1,2}$/;
const passwordRegex: RegExp = /^(?=.*[A-Z])(?=.*[a-z])(?=.*\d)(?=.*[@#$%!^&*]).{8,20}$/;

formCandidate.addEventListener('submit', (e: Event) => {
    e.preventDefault();

    const formData = new FormData(formCandidate);

    const data = {
        username: formData.get('username')?.toString() || '',
        email: formData.get('email')?.toString() || '',
        state: formData.get('state')?.toString() || '',
        cep: formData.get('cep')?.toString() || '',
        cpf: formData.get('cpf')?.toString() || '',
        age: formData.get('age')?.toString() || '',
        password: formData.get('password')?.toString() || '',
        confirm: formData.get('confirm')?.toString() || '',
    };

    if (!candidateNameRegex.test(data.username)) {
        alert('Nome inválido. Por favor, tente novamente.');
        return;
    }

    if (!candidateEmailRegex.test(data.email)) {
        alert('Email inválido. Por favor, tente novamente.');
        return;
    }

    if (!stateRegex.test(data.state)) {
        alert('Estado inválido. Por favor, tente novamente.');
        return;
    }

    if (!cepRegex.test(data.cep)) {
        alert('CEP inválido. Por favor, tente novamente.');
        return;
    }

    if (!cpfRegex.test(data.cpf)) {
        alert('CPF inválido. Por favor, tente novamente.');
        return;
    }

    if (!ageRegex.test(data.age)) {
        alert('Idade inválida. Por favor, tente novamente.');
        return;
    }

    if (!passwordRegex.test(data.password)) {
        alert('Senha inválida. Por favor, tente novamente.');
        return;
    }

    if (data.password !== data.confirm) {
        alert('As senhas precisam ser iguais. Por favor, tente novamente.');
        return;
    }

    console.log(data);
    alert('Candidato cadastrado com sucesso!');

    // Reset form fields
    formCandidate.reset();
});

