const formCompany: any = document.querySelector('form');

const companyNameRegex: RegExp = /^[a-zA-Z\s]+$/;
const companyEmailRegex: RegExp = /\S+@\w+\.\w{2,6}(\.\w{2})?/i;
const stateRegex: RegExp = /^[A-Z]{2}$/;
const cepRegex: RegExp = /^[0-9]{5}-[0-9]{3}$/;
const cnpjRegex: RegExp = /^[0-9]{2}\.?[0-9]{3}\.?[0-9]{3}\/0001-[0-9]{2}$/;
const countyRegex: RegExp = /^([A-Z])[a-z]+$/;
const passwordRegex: RegExp = /^(?=.*[A-Z])(?=.*[a-z])(?=.*\d)(?=.*[@#$%!^&*]).{8,20}$/;

formCompany.addEventListener('submit', (e: Event) => {
    e.preventDefault();

    const formData = new FormData(formCompany);

    const data = {
        username: formData.get('username')?.toString() || '',
        email: formData.get('email')?.toString() || '',
        state: formData.get('state')?.toString() || '',
        cep: formData.get('cep')?.toString() || '',
        cnpj: formData.get('cnpj')?.toString() || '',
        country: formData.get('country')?.toString() || '',
        password: formData.get('password')?.toString() || '',
        confirm: formData.get('confirm')?.toString() || '',
    };

    if (!companyNameRegex.test(data.username)) {
        alert('Nome inválido. Por favor, tente novamente.');
        return;
    }

    if (!companyEmailRegex.test(data.email)) {
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

    if (!cnpjRegex.test(data.cnpj)) {
        alert('CNPJ inválido. Por favor, tente novamente.');
        return;
    }

    if (!countyRegex.test(data.country)) {
        alert('País inválido. Por favor, tente novamente.');
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
    alert('Empresa cadastrada com sucesso!');

    // Reset form fields
    formCompany.reset();
});