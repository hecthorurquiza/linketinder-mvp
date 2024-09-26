interface ICompany {
    name: string
    email: string
    state: string
    cep: string
    description: string
    competences: string[]
    cnpj: string
    country: string
}

const companies: ICompany[] = [
    {
        name: "Tech Innovators",
        email: "info@techinnovators.com",
        state: "California",
        cep: "94016",
        description: "A leading tech company specializing in AI and machine learning.",
        competences: ["AI", "Machine Learning", "Data Science"],
        cnpj: "12345678901234",
        country: "USA"
    },
    {
        name: "Green Leaf Solutions",
        email: "contact@greenleafsolutions.com",
        state: "Washington",
        cep: "98101",
        description: "Providing sustainable and eco-friendly solutions for businesses.",
        competences: ["Sustainability", "Eco-friendly Products", "Consulting"],
        cnpj: "23456789012345",
        country: "USA"
    },
    {
        name: "Data Masters",
        email: "support@datamasters.com",
        state: "New York",
        cep: "10001",
        description: "Experts in data analytics and business intelligence.",
        competences: ["Data Analytics", "Business Intelligence", "Big Data"],
        cnpj: "34567890123456",
        country: "USA"
    },
    {
        name: "Health Care Solutions",
        email: "help@healthcaresolutions.com",
        state: "Texas",
        cep: "75001",
        description: "Innovative healthcare solutions for a healthier future.",
        competences: ["Healthcare", "Medical Technology", "Telemedicine"],
        cnpj: "45678901234567",
        country: "USA"
    },
    {
        name: "EduTech Innovations",
        email: "info@edutechinnovations.com",
        state: "Florida",
        cep: "32003",
        description: "Transforming education through technology.",
        competences: ["Education Technology", "eLearning", "Curriculum Development"],
        cnpj: "56789012345678",
        country: "USA"
    },
    {
        name: "Finance Solutions",
        email: "contact@financesolutions.com",
        state: "Illinois",
        cep: "60601",
        description: "Comprehensive financial services for businesses and individuals.",
        competences: ["Financial Planning", "Investment Management", "Tax Services"],
        cnpj: "67890123456789",
        country: "USA"
    },
    {
        name: "Logistics Masters",
        email: "support@logisticsmasters.com",
        state: "Georgia",
        cep: "30301",
        description: "Efficient logistics and supply chain management solutions.",
        competences: ["Logistics", "Supply Chain Management", "Warehousing"],
        cnpj: "78901234567890",
        country: "USA"
    }
];

const vacanciesDiv: any = document.getElementById('vacancies')
function infoCard(description: string, competences: string[]): string {
    return `
    <div class="relative flex flex-col mt-6 text-gray-700 bg-white shadow-md bg-clip-border rounded-xl w-80">
      <div
        class="relative h-32 mx-4 -mt-6 overflow-hidden text-white shadow-lg bg-clip-border rounded-xl bg-blue-gray-500 shadow-blue-gray-500/40">
        <img
            class="blur-sm"
            src="https://images.unsplash.com/photo-1540553016722-983e48a2cd10?ixlib=rb-1.2.1&amp;ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&amp;auto=format&amp;fit=crop&amp;w=800&amp;q=80"
            alt="card-image" />
      </div>
      <div class="p-6 grow">
        <h5 class="block mb-2 font-sans text-xl antialiased font-semibold leading-snug tracking-normal text-blue-gray-900">
          ${description}
        </h5>
        <p class="block font-sans text-base antialiased font-light leading-relaxed text-inherit max-w-full">
          ${competences.join(", ")}
        </p>
      </div>
      <div class="p-6 pt-0">
        <button
                class="align-middle select-none font-sans font-bold text-center uppercase transition-all disabled:opacity-50 disabled:shadow-none disabled:pointer-events-none text-xs py-3 px-6 rounded-lg bg-gray-900 text-white shadow-md shadow-gray-900/10 hover:shadow-lg hover:shadow-gray-900/20 focus:opacity-[0.85] focus:shadow-none active:opacity-[0.85] active:shadow-none"
                type="button">
          Ler mais
        </button>
      </div>
    </div>
  `;
}

companies.map(company => {
    vacanciesDiv.innerHTML += infoCard(company.name, company.competences);
})
