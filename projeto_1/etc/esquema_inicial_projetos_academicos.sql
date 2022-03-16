create schema api_projetos_academicos;

use api_projetos_academicos;

-- --------------------------------------------------------

--
-- Estrutura da tabela `aluno`
--

CREATE TABLE `aluno` (
  `id` int(11) NOT NULL,
  `matricula` varchar(12) NOT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `cpf` varchar(14) DEFAULT NULL,
  `curso` varchar(255) DEFAULT NULL,
  `fk_endereco_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `aluno`
--

INSERT INTO `aluno` (`id`, `matricula`, `nome`, `cpf`, `curso`, `fk_endereco_id`) VALUES
(1, '123456', 'Aluno 1', '956.308.788-05', 'Computação', 1),
(2, '125658', 'Aluno 2', '586.568.598-70', 'Eng. da Computação', 2),
(3, '125658', 'Aluno 3', '123.459.745-52', 'Eng. da Computação', 2);

-- --------------------------------------------------------

--
-- Estrutura da tabela `endereco`
--

CREATE TABLE `endereco` (
  `id` int(11) NOT NULL,
  `rua` varchar(255) NOT NULL,
  `numero` varchar(8) DEFAULT NULL,
  `cep` varchar(14) DEFAULT NULL,
  `cidade` varchar(50) DEFAULT NULL,
  `estado` varchar(50) DEFAULT NULL,
  `pais` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `endereco`
--

INSERT INTO `endereco` (`id`, `rua`, `numero`, `cep`, `cidade`, `estado`, `pais`) VALUES
(1, 'Rua 1', '80', '58.415-200', 'Campina Grande', 'PB', 'BR'),
(2, 'Rua 2', '80', '58.415-200', 'Campina Grande', 'PB', 'BR'),
(3, 'Rua 3', '80', '58.415-200', 'Campina Grande', 'PB', 'BR'),
(4, 'Rua 4', '80', '58.415-200', 'Campina Grande', 'PB', 'BR');

--
-- Índices para tabelas despejadas
--

--
-- Índices para tabela `aluno`
--
ALTER TABLE `aluno`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_endereco` (`fk_endereco_id`);

--
-- Índices para tabela `endereco`
--
ALTER TABLE `endereco`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `aluno`
--
ALTER TABLE `aluno`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de tabela `endereco`
--
ALTER TABLE `endereco`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Restrições para despejos de tabelas
--

--
-- Limitadores para a tabela `aluno`
--
ALTER TABLE `aluno`
  ADD CONSTRAINT `fk_endereco` FOREIGN KEY (`fk_endereco_id`) REFERENCES `endereco` (`id`) ON UPDATE CASCADE ON DELETE CASCADE;
COMMIT;


CREATE TABLE `professor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `matricula` varchar(12) NOT NULL,
  `nome` varchar(200) DEFAULT NULL,
  `curso` varchar(255) DEFAULT NULL,
  `fk_endereco_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`fk_endereco_id`) REFERENCES `endereco` (`id`) ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `professor` (`id`, `matricula`, `nome`, `curso`, `fk_endereco_id`) VALUES
(1, '654321', 'Professor 1', 'Computação', 3),
(2, '125658', 'Professor 2', 'Eng. da Computação', 4);

CREATE TABLE `projeto` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `titulo` varchar(255) DEFAULT NULL,
  `area` varchar(255) DEFAULT NULL,
  `resumo` varchar(3000) DEFAULT NULL,
  `palavraChave1` varchar(50) DEFAULT NULL,
  `palavraChave2` varchar(50) DEFAULT NULL,
  `palavraChave3` varchar(50) DEFAULT NULL,
  `urlDocumento` varchar(255) DEFAULT NULL,
  `fk_professor_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`fk_professor_id`) REFERENCES `professor` (`id`) ON UPDATE CASCADE ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `projeto` (`id`, `titulo`, `area`, `resumo`, `palavraChave1`, `palavraChave2`, `palavraChave3`, `urlDocumento`, `fk_professor_id`) VALUES
(1, 'Psicologia', 'psicologia', 'Estudo em Psicologia', 'Psicologia', null, null, 'www.psicologia.com.br', 1),
(2, 'Inteligência Artificial', 'computação', 'Aplicação de Inteligência Artificial para solução de problemas de classificação', 'Inteligência Artificial', 'problemas de classificação', null, 'www.ia.com.br', 2);

CREATE TABLE `projeto_alunos` (
  `fk_projeto_id` int(11) NOT NULL,
  `fk_aluno_id` int(11) NOT NULL,
  PRIMARY KEY (`fk_projeto_id`, `fk_aluno_id`),
  FOREIGN KEY (`fk_projeto_id`) REFERENCES `projeto` (`id`) ON UPDATE CASCADE ON DELETE CASCADE,
  FOREIGN KEY (`fk_aluno_id`) REFERENCES `aluno` (`id`) ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `projeto_alunos` (`fk_projeto_id`, `fk_aluno_id`) VALUES
(1, 1),
(1, 2),
(2, 3);
