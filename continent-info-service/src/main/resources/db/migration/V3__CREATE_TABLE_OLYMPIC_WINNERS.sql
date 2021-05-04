--uuid-ossp extenssion
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- public.olympic_winners definition
CREATE TABLE IF NOT EXISTS public.olympic_winners (
	guid varchar(36) NOT NULL DEFAULT uuid_generate_v4()::text,
	created_on timestamp(0) NOT NULL DEFAULT now(),
	updated_on timestamp(0) NULL DEFAULT now(),
	deleted bool NULL DEFAULT false,
	full_name varchar(150) NULL,
	age int4 NULL,
	country varchar(100) NOT NULL,
	"year" int4 NOT NULL,
	stat_date date NOT NULL,
	sport varchar(50) NOT NULL,
	gold int4 NULL,
	silver int4 NULL,
	bronze int4 NULL,
	CONSTRAINT olympic_winners_pk PRIMARY KEY (guid)
);