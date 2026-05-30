INSERT INTO categoria (nome) VALUES ('Lazer');
INSERT INTO categoria (nome) VALUES ('Alimentacao');
INSERT INTO categoria (nome) VALUES ('Supermercado');
INSERT INTO categoria (nome) VALUES ('Farmacia');
INSERT INTO categoria (nome) VALUES ('Outros');

INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) VALUES ('Joao Silva', 'Rua do Abacaxi', '10', null, 'Brasil', '38400-12', 'Uberlandia', 'MG', true);
INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) VALUES ('Maria Rita', 'Rua do Sabia', '110', 'Apto 101', 'Colina', '11400-12', 'Ribeirao Preto', 'SP', true);
INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) VALUES ('Pedro Santos', 'Rua da Bateria', '23', null, 'Morumbi', '54212-12', 'Goiania', 'GO', true);
INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) VALUES ('Ricardo Pereira', 'Rua do Motorista', '123', 'Apto 302', 'Aparecida', '38400-12', 'Salvador', 'BA', true);
INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) VALUES ('Josue Mariano', 'Av Rio Branco', '321', null, 'Jardins', '56400-12', 'Natal', 'RN', true);

INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa) VALUES ('Salario mensal', '2026-06-10', null, 6500.00, 'Pagamento mensal', 'RECEITA', 1, 1);
INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa) VALUES ('Supermercado', '2026-06-12', '2026-06-12', 420.75, 'Compra do mes', 'DESPESA', 3, 2);
INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa) VALUES ('Farmacia', '2026-06-15', null, 89.90, null, 'DESPESA', 4, 3);
INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa) VALUES ('Freelance', '2026-06-20', null, 1200.00, 'Servico extra', 'RECEITA', 5, 4);
