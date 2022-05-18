-- This script was generated by a beta version of the ERD tool in pgAdmin 4.
-- Please log an issue at https://redmine.postgresql.org/projects/pgadmin4/issues/new if you find any bugs, including reproduction steps.
BEGIN;


CREATE TABLE IF NOT EXISTS public."Author"
(
    "Full_name" text COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT "Author_pkey" PRIMARY KEY ("Full_name")
);

CREATE TABLE IF NOT EXISTS public."Book"
(
    "Author" text COLLATE pg_catalog."default" NOT NULL,
    "Name" text COLLATE pg_catalog."default" NOT NULL,
    "Genre" text COLLATE pg_catalog."default" NOT NULL,
    "Value" integer NOT NULL,
    book_id integer NOT NULL,
    CONSTRAINT "Book_pkey" PRIMARY KEY (book_id)
);

CREATE TABLE IF NOT EXISTS public."Reader"
(
    "Full_name" text COLLATE pg_catalog."default" NOT NULL,
    "Basket" integer,
    CONSTRAINT "Reader_pkey" PRIMARY KEY ("Full_name")
);

CREATE TABLE IF NOT EXISTS public.genre
(
    "Name" text COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT genre_pkey PRIMARY KEY ("Name")
);

ALTER TABLE IF EXISTS public."Book"
    ADD CONSTRAINT author_fk FOREIGN KEY ("Author")
    REFERENCES public."Author " ("Full_name") MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS public."Book"
    ADD CONSTRAINT genre_fk FOREIGN KEY ("Genre")
    REFERENCES public.genre ("Name") MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS public."Reader"
    ADD CONSTRAINT "Basket_fk" FOREIGN KEY ("Basket")
    REFERENCES public."Book" (book_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;
CREATE INDEX IF NOT EXISTS "Basket"
    ON public."Reader"("Basket");


ALTER TABLE IF EXISTS public."Reader"
    ADD CONSTRAINT "Basket_fk2" FOREIGN KEY ("Basket")
    REFERENCES public."Book" ("Value") MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;
CREATE INDEX IF NOT EXISTS "Basket"
    ON public."Reader"("Basket");

END;