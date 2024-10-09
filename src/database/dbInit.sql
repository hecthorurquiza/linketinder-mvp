-- Create the candidates table
CREATE TABLE candidates (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    birthday DATE NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    cpf VARCHAR(14) NOT NULL UNIQUE,
    country VARCHAR(50) NOT NULL,
    cep VARCHAR(10) NOT NULL,
    description TEXT,
    password VARCHAR(255) NOT NULL
);

-- Create the companies table
CREATE TABLE companies (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    cnpj VARCHAR(18) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    description TEXT,
    country VARCHAR(50) NOT NULL,
    cep VARCHAR(10) NOT NULL,
    password VARCHAR(255) NOT NULL
);

-- Create the vacancy table without the foreign key initially
CREATE TABLE vacancies (
   id SERIAL PRIMARY KEY,
   name VARCHAR(100) NOT NULL,
   description TEXT,
   state VARCHAR(50) NOT NULL,
   city VARCHAR(50) NOT NULL
);

-- Add the foreign key to the vacancy table
ALTER TABLE vacancies
ADD COLUMN company_id INT,
ADD CONSTRAINT fk_company
FOREIGN KEY (company_id) REFERENCES companies(id);

-- Add the foreign key to the candidate table
ALTER TABLE candidates
ADD COLUMN vacancy_id INT,
ADD CONSTRAINT fk_company
FOREIGN KEY (vacancy_id) REFERENCES companies(id);


-- Create the competences table
CREATE TABLE competences (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

-- Create the candidate_competence table
CREATE TABLE candidate_competence (
    candidate_id INT NOT NULL,
    competence_id INT NOT NULL,
    PRIMARY KEY (candidate_id, competence_id),
    FOREIGN KEY (candidate_id) REFERENCES candidates(id),
    FOREIGN KEY (competence_id) REFERENCES competences(id)
);

-- Create the vacancy_competence table
CREATE TABLE vacancy_competence (
    vacancy_id INT NOT NULL,
    competence_id INT NOT NULL,
    PRIMARY KEY (vacancy_id, competence_id),
    FOREIGN KEY (vacancy_id) REFERENCES vacancies(id),
    FOREIGN KEY (competence_id) REFERENCES competences(id)
);

INSERT INTO candidates (first_name, last_name, birthday, email, cpf, country, cep, description, password) VALUES
    ('John', 'Doe', '1990-01-01', 'john.doe@example.com', '123.456.789-00', 'USA', '12345-678', 'Experienced software engineer.', 'password123'),
    ('Jane', 'Smith', '1985-05-15', 'jane.smith@example.com', '987.654.321-00', 'Canada', '98765-432', 'Marketing specialist with 10 years of experience.', 'securepassword'),
    ('Alice', 'Johnson', '1992-08-22', 'alice.johnson@example.com', '555.555.555-55', 'UK', '55555-555', 'Graphic designer with a strong portfolio.', 'designpassword'),
    ('Bob', 'Brown', '1988-11-30', 'bob.brown@example.com', '444.444.444-44', 'Australia', '44444-444', 'Project manager with excellent communication skills.', 'projectpassword'),
    ('Emma', 'Wilson', '1995-03-10', 'emma.wilson@example.com', '333.333.333-33', 'Germany', '33333-333', 'Data analyst with expertise in SQL and Python.', 'datapassword');

INSERT INTO companies (name, cnpj, email, description, country, cep, password) VALUES
    ('Tech Innovations', '12.345.678/0001-90', 'tech.innovations@example.com', 'A leading technology company.', 'USA', '12345-678', 'techpassword'),
    ('Marketing Masters', '98.765.432/0001-89', 'marketing.masters@example.com', 'Specializing in digital marketing solutions.', 'Canada', '98765-432', 'marketingpassword'),
    ('Design Studios', '55.555.555/0001-78', 'design.studios@example.com', 'Creative design agency.', 'UK', '55555-555', 'designpassword'),
    ('Project Solutions', '44.444.444/0001-67', 'project.solutions@example.com', 'Expert project management services.', 'Australia', '44444-444', 'projectpassword'),
    ('Data Analytics Corp', '33.333.333/0001-56', 'data.analytics@example.com', 'Providing advanced data analytics services.', 'Germany', '33333-333', 'datapassword');
