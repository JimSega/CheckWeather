CREATE TABLE IF NOT EXISTS public.archive
(
	id integer,
    place integer,
    date_check date,
    humidity double precision CHECK (humidity >= 0 AND humidity <= 100),
    pressure_mb double precision,
    temp_c double precision CHECK (temp_c > -100 AND temp_c < 100),
    time_check time(6) without time zone,
    wind_mph double precision,
    condition_text character varying(50) COLLATE pg_catalog."default",
    location_name character varying(50) COLLATE pg_catalog."default",
    CONSTRAINT archive_pkey PRIMARY KEY (id),
	CONSTRAINT archive_foreign FOREIGN KEY (place)
		REFERENCES place ("id")
		ON DELETE SET NULL
		ON UPDATE CASCADE
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.archive
    OWNER to postgres;