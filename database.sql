--
-- PostgreSQL database dump
--

-- Dumped from database version 9.4.5
-- Dumped by pg_dump version 9.4.5
-- Started on 2016-01-14 11:20:07 COT

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

DROP DATABASE atondb;
--
-- TOC entry 2093 (class 1262 OID 19375)
-- Name: atondb; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE atondb WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'en_US.UTF-8' LC_CTYPE = 'en_US.UTF-8';


ALTER DATABASE atondb OWNER TO postgres;

\connect atondb

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 6 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO postgres;

--
-- TOC entry 2094 (class 0 OID 0)
-- Dependencies: 6
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON SCHEMA public IS 'standard public schema';


--
-- TOC entry 181 (class 3079 OID 11893)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2096 (class 0 OID 0)
-- Dependencies: 181
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

CREATE SEQUENCE SALA_GEN START 1;

CREATE SEQUENCE LAB_GEN START 1;

--
-- TOC entry 172 (class 1259 OID 19519)
-- Name: equipo; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE equipo (
    mac character(17) NOT NULL,
    nombre character varying(15),
    usuario character varying(32),
    password text,
    ip character varying(20),
    sala integer,
    descripcion text
);


ALTER TABLE equipo OWNER TO postgres;

--
-- TOC entry 2097 (class 0 OID 0)
-- Dependencies: 172
-- Name: COLUMN equipo.sala; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN equipo.sala IS '
';


--
-- TOC entry 173 (class 1259 OID 19525)
-- Name: estado; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE estado (
    equipo character(20) NOT NULL,
    fecha date NOT NULL,
    descripcion text
);


ALTER TABLE estado OWNER TO postgres;

--
-- TOC entry 174 (class 1259 OID 19531)
-- Name: laboratorio; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE laboratorio (
    id integer NOT NULL,
    nombre character varying(32),
    ubicacion text,
    administracion text
);


ALTER TABLE laboratorio OWNER TO postgres;

--
-- TOC entry 175 (class 1259 OID 19537)
-- Name: orden; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE orden (
    usuarioweb character varying(32) NOT NULL,
    fecha date NOT NULL,
    eq_sesion character(20),
    fe_sesion date,
    orden text,
    resultado text,
    codigosalida integer
);


ALTER TABLE orden OWNER TO postgres;

--
-- TOC entry 176 (class 1259 OID 19543)
-- Name: sala; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE sala (
    id integer NOT NULL,
    nombre character varying(32),
    mediosaudiovisuales text,
    enseres text,
    laboratorio integer
);


ALTER TABLE sala OWNER TO postgres;

--
-- TOC entry 177 (class 1259 OID 19549)
-- Name: sesion; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE sesion (
    sistemaoperativo character varying(60),
    fecha date NOT NULL,
    usuario character varying(32),
    equipo character(17) NOT NULL,
    activa boolean
);


ALTER TABLE sesion OWNER TO postgres;

--
-- TOC entry 178 (class 1259 OID 19552)
-- Name: sugerencia; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE sugerencia (
    id bigint NOT NULL,
    eq_sesion character(20),
    fe_sesion date,
    sugerencia text
);


ALTER TABLE sugerencia OWNER TO postgres;

--
-- TOC entry 179 (class 1259 OID 19558)
-- Name: rol; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE rol (
    id integer NOT NULL,
    usuarioweb character varying(32) NOT NULL,
    rol character varying
);


ALTER TABLE rol OWNER TO postgres;

--
-- TOC entry 180 (class 1259 OID 19561)
-- Name: usuarioweb; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE usuarioweb (
    usuario character varying(32) NOT NULL,
    password text,
    enabled boolean
);


ALTER TABLE usuarioweb OWNER TO postgres;

--
-- TOC entry 1955 (class 2606 OID 19568)
-- Name: equipo_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY equipo
    ADD CONSTRAINT equipo_pkey PRIMARY KEY (ip);


--
-- TOC entry 1957 (class 2606 OID 19570)
-- Name: estado_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY estado
    ADD CONSTRAINT estado_pkey PRIMARY KEY (equipo, fecha);


--
-- TOC entry 1959 (class 2606 OID 19572)
-- Name: id; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY laboratorio
    ADD CONSTRAINT id PRIMARY KEY (id);


--
-- TOC entry 1961 (class 2606 OID 19574)
-- Name: orden_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY orden
    ADD CONSTRAINT orden_pkey PRIMARY KEY (eq_sesion, fecha);


--
-- TOC entry 1963 (class 2606 OID 19576)
-- Name: sala_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY sala
    ADD CONSTRAINT sala_pkey PRIMARY KEY (id);


--
-- TOC entry 1965 (class 2606 OID 19578)
-- Name: sesion_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY sesion
    ADD CONSTRAINT sesion_pkey PRIMARY KEY (equipo, fecha);


--
-- TOC entry 1967 (class 2606 OID 19580)
-- Name: sugerencia_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY sugerencia
    ADD CONSTRAINT sugerencia_pkey PRIMARY KEY (id);


--
-- TOC entry 1969 (class 2606 OID 19582)
-- Name: rol_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY rol
    ADD CONSTRAINT rol_pkey PRIMARY KEY (id);


--
-- TOC entry 1971 (class 2606 OID 19584)
-- Name: usuarioweb_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY usuarioweb
    ADD CONSTRAINT usuarioweb_pkey PRIMARY KEY (usuario);


--
-- TOC entry 1972 (class 2606 OID 19585)
-- Name: equipo_sala_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY equipo
    ADD CONSTRAINT equipo_sala_fkey FOREIGN KEY (sala) REFERENCES sala(id);


--
-- TOC entry 1973 (class 2606 OID 19590)
-- Name: estado_equipo_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY estado
    ADD CONSTRAINT estado_equipo_fkey FOREIGN KEY (equipo) REFERENCES equipo(ip);


--
-- TOC entry 1976 (class 2606 OID 19595)
-- Name: laboratorio; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY sala
    ADD CONSTRAINT laboratorio FOREIGN KEY (laboratorio) REFERENCES laboratorio(id);


--
-- TOC entry 1974 (class 2606 OID 19600)
-- Name: orden_eq_sesion_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY orden
    ADD CONSTRAINT orden_eq_sesion_fkey FOREIGN KEY (eq_sesion, fe_sesion) REFERENCES sesion(equipo, fecha);

ALTER TABLE ONLY orden
    ADD CONSTRAINT orden_equipo_fkey FOREIGN KEY (eq_sesion) REFERENCES equipo(ip);


--
-- TOC entry 1975 (class 2606 OID 19605)
-- Name: orden_usuarioweb_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY orden
    ADD CONSTRAINT orden_usuarioweb_fkey FOREIGN KEY (usuarioweb) REFERENCES usuarioweb(usuario);


--
-- TOC entry 1977 (class 2606 OID 19610)
-- Name: sesion_equipo_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY sesion
    ADD CONSTRAINT sesion_equipo_fkey FOREIGN KEY (equipo) REFERENCES equipo(ip);


--
-- TOC entry 1978 (class 2606 OID 19615)
-- Name: sugerencia_eq_sesion_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY sugerencia
    ADD CONSTRAINT sugerencia_eq_sesion_fkey FOREIGN KEY (eq_sesion, fe_sesion) REFERENCES sesion(equipo, fecha);


--
-- TOC entry 1979 (class 2606 OID 19620)
-- Name: usuarioweb_rol_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY rol
    ADD CONSTRAINT rol_usuarioweb_fkey FOREIGN KEY (usuarioweb) REFERENCES usuarioweb(usuario);


--
-- TOC entry 2095 (class 0 OID 0)
-- Dependencies: 6
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;

INSERT INTO usuarioweb(
            usuario, password, enabled)
    VALUES ('admin', 'admin', TRUE);

INSERT INTO rol(
            id, usuarioweb, rol)
    VALUES (1, 'admin', 'ADMIN');

INSERT INTO rol(
            id, usuarioweb, rol)
    VALUES (2, 'admin', 'DBA');


-- Completed on 2016-01-14 11:20:07 COT

--
-- PostgreSQL database dump complete
--
