


  -- Table: public.category

-- DROP TABLE public.category;

CREATE TABLE public.category
(
  id integer NOT NULL,
  name character varying(20),
  CONSTRAINT pk_category PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.category
  OWNER TO postgres;



-- Table: public.product

-- DROP TABLE public.product;

CREATE TABLE public.product
(
  id integer NOT NULL,
  name character varying(20),
  code character varying(10),
  price numeric(20,10),
  category_id integer NOT NULL,
  tags text[] DEFAULT '{}'::text[],
  CONSTRAINT pk_product PRIMARY KEY (id),
  CONSTRAINT product_category_fk FOREIGN KEY (category_id)
      REFERENCES public.category (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.product
  OWNER TO postgres;

-- Index: public.product_tags_idx

-- DROP INDEX public.product_tags_idx;

CREATE INDEX product_tags_idx
  ON public.product
  USING gin
  (tags COLLATE pg_catalog."default");



