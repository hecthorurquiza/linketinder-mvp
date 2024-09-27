interface ICandidate {
    name: string,
    email: string,
    state: string,
    cep: string,
    description: string,
    competences: string[],
    cpf: string,
    age: number
}

const candidates: ICandidate[] = [
    {
        name: 'John Doe',
        email: 'johndoe@example.com',
        state: 'California',
        cep: '12345-6789',
        description: 'Full-stack developer with 5 years of experience',
        competences: ['JavaScript', 'React', 'Node.js', 'MongoDB'],
        cpf: '123.456.789-01',
        age: 32
    },
    {
        name: 'Maria Silva',
        email: 'mariasilva@example.com',
        state: 'São Paulo',
        cep: '01001-000',
        description: 'Data scientist with 3 years of experience',
        competences: ['Python', 'R', 'Machine Learning', 'Data Visualization'],
        cpf: '987.654.321-09',
        age: 28
    },
    {
        name: 'Pedro Albuquerque',
        email: 'pedroalbuquerque@example.com',
        state: 'Rio de Janeiro',
        cep: '20000-000',
        description: 'DevOps engineer with 4 years of experience',
        competences: ['AWS', 'Docker', 'Kubernetes', 'CI/CD'],
        cpf: '765.432.109-08',
        age: 35
    },
    {
        name: 'Ana Luiza',
        email: 'analuisa@example.com',
        state: 'Minas Gerais',
        cep: '30100-000',
        description: 'UX/UI designer with 2 years of experience',
        competences: ['Sketch', 'Figma', 'Adobe XD', 'User Research'],
        cpf: '543.210.987-65',
        age: 25
    }
];

const applicationsDiv: any = document.getElementById('applications');

function infoCard(description: string, competences: string[]) {
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
            ${competences.join(', ').trim()}
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
    `
}

candidates.map(candidate => applicationsDiv.innerHTML += infoCard(candidate.description, candidate.competences))


const options = {
    series: [
        {
            name: "Quantidade",
            color: "#1A56DB",
            data: [
                { x: "Java", y: 250 },
                { x: "Groovy", y:  90},
                { x: "Python", y: 63 },
                { x: "Angular", y: 110 },
                { x: "Docker", y: 122 },
                { x: "Node.js", y: 170 },
                { x: "Spring", y: 111 },
            ],
        },
    ],
    chart: {
        type: "bar",
        height: "400px",
        fontFamily: "Inter, sans-serif",
        toolbar: {
            show: false,
        },
    },
    plotOptions: {
        bar: {
            horizontal: false,
            columnWidth: "60%",
            borderRadiusApplication: "end",
            borderRadius: 4,
        },
    },
    tooltip: {
        shared: true,
        intersect: false,
        style: {
            fontFamily: "Inter, sans-serif",
        },
    },
    states: {
        hover: {
            filter: {
                type: "darken",
                value: 1,
            },
        },
    },
    stroke: {
        show: true,
        width: 0,
        colors: ["transparent"],
    },
    grid: {
        show: false,
        strokeDashArray: 4,
        padding: {
            left: 2,
            right: 2,
            top: -14
        },
    },
    dataLabels: {
        enabled: false,
    },
    legend: {
        show: false,
    },
    xaxis: {
        floating: false,
        labels: {
            show: true,
            style: {
                fontFamily: "Inter, sans-serif",
                cssClass: 'text-xs font-normal fill-gray-500 dark:fill-gray-400'
            }
        },
        axisBorder: {
            show: false,
        },
        axisTicks: {
            show: false,
        },
    },
    yaxis: {
        show: false,
    },
    fill: {
        opacity: 1,
    },
}

// @ts-ignore
if(document.getElementById("column-chart") && typeof ApexCharts !== 'undefined') {
    // @ts-ignore
    const chart = new ApexCharts(document.getElementById("column-chart"), options);
    chart.render();
}
