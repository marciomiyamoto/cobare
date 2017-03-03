CREATE TABLE produto (
  id NUMERIC NOT NULL PRIMARY KEY, 
  nome VARCHAR(50), 
  custoUnitario FLOAT, 
  valorVenda FLOAT, 
  dtCadastro DATE, 
  dtValidade DATE);