CREATE TABLE endereco(
  id NUMBER(9) NOT NULL PRIMARY KEY,
  dtCadastro DATE NOT NULL,
  logradouro VARCHAR(50),
  numero VARCHAR2(10),
  cep VARCHAR2(9),
  complemento VARCHAR2(50),
  bairro VARCHAR2(30),
  cidade VARCHAR2(30),
  estado VARCHAR2(30)
  );

CREATE TABLE cliente (
  id NUMBER(9) NOT NULL PRIMARY KEY,
  dtCadastro DATE NOT NULL,
  nome VARCHAR2(50),
  cpf VARCHAR2(14),
  idEndereco NUMBER(9),
  CONSTRAINT fkEndereco FOREIGN KEY (idEndereco) REFERENCES endereco(id)
  );
  
