------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
-- Motivo: Criacao de dados fake para a manipulacao do sistema.
-- Autor : Guilherme Magalhaes
-- Data : 20/10/2014
------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

------------------------------------------------------------------------------- INSERT: TBL_USUARIO ----------------------------------------------------------------------------
INSERT INTO tbl_usuario(
            usuario_id, usuario_nome, usuario_email, usuario_senha)
    VALUES (1, 'Guilherme Magalhaes', 'guiandmag@gmail.com', '123456');

------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------- INSERT: TBL_AUTOR ----------------------------------------------------------------------------
INSERT INTO tbl_autor(
            autor_id, autor_nome)
    VALUES (1, 'Guilherme Magalhaes');
    
INSERT INTO tbl_autor(
            autor_id, autor_nome)
    VALUES (2, 'Antonio Tavares');
    
    
INSERT INTO tbl_autor(
            autor_id, autor_nome)
    VALUES (3, 'Eder Magalhaes');
    
    
INSERT INTO tbl_autor(
            autor_id, autor_nome)
    VALUES (4, 'Carlos Henrique');
    
------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------- INSERT: TBL_FRASE ----------------------------------------------------------------------------
INSERT INTO tbl_frase(
            frase_id, frase_frase, autor_id)
    VALUES (1, 'A mulecagem descende dos safados', 1);
    
INSERT INTO tbl_frase(
            frase_id, frase_frase, autor_id)
    VALUES (2, 'A vespetina situacao do armonico', 2);
    
INSERT INTO tbl_frase(
            frase_id, frase_frase, autor_id)
    VALUES (3, 'Voce eh FDP', 3);
    
INSERT INTO tbl_frase(
            frase_id, frase_frase, autor_id)
    VALUES (4, 'A vida ainda vai te pegar', 4);

------------------------------------------------------------------------------------------------------------------------------------------------------------------------------