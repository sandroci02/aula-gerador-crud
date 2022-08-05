CREATE TABLE public.referencia (
    id integer NOT NULL,
    descricao character varying(255) NOT NULL,
    version integer DEFAULT 1 NOT NULL
);

ALTER TABLE public.referencia OWNER TO postgres;

CREATE SEQUENCE public.referencia_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE public.referencia_id_seq OWNER TO postgres;

ALTER SEQUENCE public.referencia_id_seq OWNED BY public.referencia.id;
