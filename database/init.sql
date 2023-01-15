CREATE TABLE public.regiao (
	sigla varchar(2) NOT NULL,
	CONSTRAINT regiao_pk PRIMARY KEY (sigla)
);

CREATE TABLE public.agente (
	codigo int NOT NULL,
	CONSTRAINT agente_pk PRIMARY KEY (codigo)
);

CREATE TABLE public.importacao (
	id serial NOT NULL,
  file_name varchar(255) NOT NULL,
  file_count integer  NOT NULL,
  file_imported_at timestamp with time zone DEFAULT now(),
	CONSTRAINT importacao_pk PRIMARY KEY (id)
);

CREATE TABLE public.importacao_agente (
  id serial NOT NULL,
  importacao_id integer NOT NULL,
  agente_codigo integer NOT NULL,
  data timestamp with time zone NOT NULL,
  CONSTRAINT importacao_agente_pk PRIMARY KEY (id),
  CONSTRAINT importacao_agente_importacao_fk FOREIGN KEY (importacao_id) REFERENCES public.importacao (id),
  CONSTRAINT importacao_agente_agente_fk FOREIGN KEY (agente_codigo) REFERENCES public.agente (codigo)
);

CREATE TABLE public.importacao_agente_regiao (
  id serial NOT NULL,
  importacao_agente_id integer NOT NULL,
  regiao_sigla varchar(2) NOT NULL,
  CONSTRAINT importacao_agente_regiao_pk PRIMARY KEY (id),
  CONSTRAINT importacao_agente_regiao_importacao_agente_fk FOREIGN KEY (importacao_agente_id) REFERENCES public.importacao_agente (id),
  CONSTRAINT importacao_agente_regiao_regiao_fk FOREIGN KEY (regiao_sigla) REFERENCES public.regiao (sigla)
);

CREATE TABLE public.importacao_agente_regiao_detalhe (
  id serial NOT NULL,
  importacao_agente_regiao_id integer NOT NULL,
  compra numeric(10,2) NOT NULL,
  geracao numeric(10,2) NOT NULL,
  CONSTRAINT importacao_agente_regiao_detalhe_pk PRIMARY KEY (id),
  CONSTRAINT importacao_agente_regiao_detalhe_importacao_agente_regiao_fk FOREIGN KEY (importacao_agente_regiao_id) REFERENCES public.importacao_agente_regiao (id)
);