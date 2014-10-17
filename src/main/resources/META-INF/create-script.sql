------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
-- Motivo: Criacao de tabelas que serao alimentadas para o uso do sistema Carminp
-- Autor : Guilherme Magalhaes
-- Data : 17/10/2014
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

CREATE TABLE TBL_FRASE
(
  frase_id BIGINT NOT NULL,
  frase_frase CHARACTER VARYING(244) NOT NULL,
  autor_id bigint NOT NULL,
  CONSTRAINT frase_pk PRIMARY KEY (frase_id),
  CONSTRAINT TBL_AUTOR_FK FOREIGN KEY (autor_id)
      REFERENCES TBL_AUTOR (autor_id) MATCH SIMPLE
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
COMMENT ON COLUMN tbl_frase.autor_id IS 'Chave que ira fazer FK para a tabela tbl_autor'
  
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