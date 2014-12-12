------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
-- Motivo : Criacao de tabelas que serao alimentadas para o uso do sistema Carminp
-- Autor  : Guilherme Magalhaes
-- Data   : 17/10/2014
-- Versao : 2.0
------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

------------------------------------------------------------------------------- TABELA: TBL_USUARIO --------------------------------------------------------------------------

CREATE TABLE TBL_USUARIO
(
  usuario_id BIGINT NOT NULL,
  usuario_nome CHARACTER VARYING(150) NOT NULL,
  usuario_email CHARACTER VARYING(100) NOT NULL,
  usuario_senha CHARACTER VARYING(50) NOT NULL,
  usuario_url_foto CHARACTER VARYING(250),
  CONSTRAINT usuario_pk PRIMARY KEY (usuario_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE TBL_USUARIO
  OWNER TO postgres;

COMMENT ON TABLE public.tbl_usuario IS 'Tabela para guardar os dados do usuario';
COMMENT ON COLUMN tbl_usuario.usuario_id IS 'Id do usuario a ser gerado pelo DB';
COMMENT ON COLUMN tbl_usuario.usuario_nome IS 'Nome do usario';
COMMENT ON COLUMN tbl_usuario.usuario_email IS 'Email do usuario';
COMMENT ON COLUMN tbl_usuario.usuario_senha IS 'Senha do usuario';
COMMENT ON COLUMN tbl_usuario.usuario_url_foto IS 'Endereco de url onde esta alocado a foto do usuario';

------------------------------------------------------------------------- INDEX: uk_usuario__usuario_email ------------------------------------------------------------------------

CREATE INDEX uk_usuario__usuario_email
  ON TBL_USUARIO
  USING btree
  (usuario_email COLLATE pg_catalog."default");
  
COMMENT ON INDEX uk_usuario__usuario_email IS 'Email do usuario eh unique no DB';

------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------- TABELA: TBL_AUTOR ----------------------------------------------------------------------------

CREATE TABLE TBL_AUTOR
(
  autor_id BIGINT NOT NULL,
  autor_nome CHARACTER VARYING(50) NOT NULL,
  CONSTRAINT autor_pk PRIMARY KEY (autor_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE TBL_AUTOR
  OWNER TO postgres;

COMMENT ON TABLE public.tbl_autor IS 'Tabela para guardar os dados do autor da frase';
COMMENT ON COLUMN tbl_autor.autor_id IS 'Id do autor a ser gerado pelo DB';
COMMENT ON COLUMN tbl_autor.autor_nome IS 'Nome do autor a ser inserido pelo usuario';

------------------------------------------------------------------------- INDEX: uk_autor__autor_nome ------------------------------------------------------------------------

CREATE INDEX uk_autor__autor_nome
  ON TBL_AUTOR
  USING btree
  (autor_nome COLLATE pg_catalog."default");
  
COMMENT ON INDEX uk_autor__autor_nome IS 'Forçar para o nome do autor ser unique e index';

------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------- TABELA: TBL_FRASE ----------------------------------------------------------------------------
CREATE TYPE RATING AS ENUM ( 'Star1', 'Star2', 'Star3', 'Star4', 'Star5' );

CREATE TABLE TBL_FRASE
(
  frase_id BIGINT NOT NULL,
  frase_frase CHARACTER VARYING(244) NOT NULL,
  frase_avaliacao RATING,
  autor_id BIGINT NOT NULL,
  favorito_id BIGINT,
  CONSTRAINT frase_pk PRIMARY KEY (frase_id),
  CONSTRAINT TBL_AUTOR_FK FOREIGN KEY (autor_id)
      REFERENCES TBL_AUTOR (autor_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT TBL_FAVORITO_FK FOREIGN KEY (favorito_id)
      REFERENCES TBL_FAVORITO (favorito_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE TBL_FRASE
  OWNER TO postgres;

COMMENT ON TABLE public.tbl_frase IS 'Tabela que ira guardar as grases geradas e guardar em seus respectivos autores';
COMMENT ON COLUMN tbl_frase.frase_id IS 'Id da frase a ser gerada pelo DB';
COMMENT ON COLUMN tbl_frase.frase_frase IS 'Frase a ser guardada no DB';
COMMENT ON COLUMN tbl_frase.frase_avaliacao IS 'Coluna que ira guardar o valor de rating das frases.';
COMMENT ON COLUMN tbl_frase.autor_id IS 'Chave que ira fazer FK para a tabela tbl_autor';
COMMENT ON COLUMN tbl_frase.favorito_id IS 'Chave que ira fazer FK para a tabela tbl_favorito';
  
------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------- TABELA: TBL_FAVORITO -------------------------------------------------------------------------

CREATE TABLE TBL_FAVORITO
(
  favorito_id BIGINT NOT NULL,
  favorito_favoritado BOOLEAN NOT NULL DEFAULT FALSE, 
  usuario_id BIGINT, 
  CONSTRAINT favorito_pk PRIMARY KEY (favorito_id),
  CONSTRAINT TBL_USUARIO_FK FOREIGN KEY (usuario_id)
      REFERENCES TBL_USUARIO (usuario_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE TBL_FAVORITO
  OWNER TO postgres;
  
COMMENT ON TABLE public.tbl_favorito IS 'Tabela para fazer intermedio entre usuarios e frases, assim evitando uma cardinalidade de muitos para muitos.';
COMMENT ON COLUMN tbl_favorito.favorito_id IS 'Id da tabela';
COMMENT ON COLUMN tbl_favorito.favorito_favoritado IS 'Coluna que ira guardar se frase foi ou nao favoritado';
COMMENT ON COLUMN tbl_favorito.usuario_id IS 'Chave estrangeira para a tbl_usuario';

------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
-------------------------------------------------------------------------- SEQUENCE: FAVORITO_SEQUENCIA ----------------------------------------------------------------------

CREATE SEQUENCE FAVORITO_SEQUENCE
  INCREMENT 50
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE FAVORITO_SEQUENCE
  OWNER TO postgres;
  
COMMENT ON SEQUENCE FAVORITO_SEQUENCE IS 'Gerar id para o PK da tabela tbl_favorito';

------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
-------------------------------------------------------------------------- SEQUENCE: USUARIO_SEQUENCIA -----------------------------------------------------------------------

CREATE SEQUENCE USUARIO_SEQUENCIA
  INCREMENT 50
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE USUARIO_SEQUENCIA
  OWNER TO postgres;
  
COMMENT ON SEQUENCE USUARIO_SEQUENCIA IS 'Gerar id para o PK da tabela tbl_usuario';

------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
-------------------------------------------------------------------------- SEQUENCE: AUTOR_SEQUENCIA -------------------------------------------------------------------------

CREATE SEQUENCE AUTOR_SEQUENCIA
  INCREMENT 50
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE AUTOR_SEQUENCIA
  OWNER TO postgres;
  
COMMENT ON SEQUENCE AUTOR_SEQUENCIA IS 'Gerar id para o PK da tabela tbl_autor';

------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
-------------------------------------------------------------------------- SEQUENCE: FRASE_SEQUENCIA -------------------------------------------------------------------------
  
CREATE SEQUENCE FRASE_SEQUENCIA
  INCREMENT 50
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE FRASE_SEQUENCIA
  OWNER TO postgres;

COMMENT ON SEQUENCE FRASE_SEQUENCIA IS 'Gerar id para o PK da tabela tbl_frase';
  
------------------------------------------------------------------------------------------------------------------------------------------------------------------------------