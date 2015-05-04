INSERT INTO perfis ( nome, descricao ) VALUES ( "root", "Perfil de adminstrador do sistema");
INSERT INTO permissoes ( nome, descricao ) VALUES ( "all", "Pode fazer tudo no sistema, permissão total");
INSERT INTO perfis_permissoes (perfil_nome, permissao_nome) VALUES ( "root", "all");
INSERT INTO usuarios ( login, senha, cpf, nome, email, perfil_nome ) VALUES ( "admin", "admin", "12345678900", "Guthierrez", "guthi.inf@gmail.com", "root");