\c challenge

-- public.depot definition

-- Drop table

-- DROP TABLE public.depot;

CREATE TABLE public.depot (
	id uuid NOT NULL,
	creation timestamptz(6) NOT NULL,
	last_update timestamptz(6) NOT NULL,
	cap varchar(8) NOT NULL,
	city varchar(32) NOT NULL,
	civic varchar(8) NOT NULL,
	province varchar(32) NOT NULL,
	street varchar(32) NOT NULL,
	latitude float8 NOT NULL,
	longitude float8 NOT NULL,
	warehouse_name varchar(16) NOT NULL,
	CONSTRAINT depot_pkey PRIMARY KEY (id),
	CONSTRAINT uk_e4kdyn3bw9e5ovx1ctrq4tpie UNIQUE (warehouse_name)
);


-- public.package definition

-- Drop table

-- DROP TABLE public.package;

CREATE TABLE public.package (
	id uuid NOT NULL,
	creation timestamptz(6) NOT NULL,
	last_update timestamptz(6) NOT NULL,
	cap varchar(8) NOT NULL,
	city varchar(32) NOT NULL,
	civic varchar(8) NOT NULL,
	province varchar(32) NOT NULL,
	street varchar(32) NOT NULL,
	latitude float8 NOT NULL,
	longitude float8 NOT NULL,
	delivery_date timestamptz(6) NULL,
	notes_for_delivery varchar(32) NULL,
	status varchar(16) NOT NULL,
	CONSTRAINT package_pkey PRIMARY KEY (id),
	CONSTRAINT package_status_check CHECK (((status)::text = ANY ((ARRAY['WAITING'::character varying, 'IN_DELIVERY'::character varying, 'DELIVERED'::character varying, 'LOST'::character varying, 'REJECTED'::character varying])::text[])))
);


-- public.supplier definition

-- Drop table

-- DROP TABLE public.supplier;

CREATE TABLE public.supplier (
	id uuid NOT NULL,
	creation timestamptz(6) NOT NULL,
	last_update timestamptz(6) NOT NULL,
	"name" varchar(16) NOT NULL,
	CONSTRAINT supplier_pkey PRIMARY KEY (id),
	CONSTRAINT uk_c3fclhmodftxk4d0judiafwi3 UNIQUE (name)
);


-- public.orders definition

-- Drop table

-- DROP TABLE public.orders;

CREATE TABLE public.orders (
	id uuid NOT NULL,
	creation timestamptz(6) NOT NULL,
	last_update timestamptz(6) NOT NULL,
	delivery_end timestamptz(6) NULL,
	delivery_start timestamptz(6) NULL,
	notes varchar(64) NULL,
	plan_start timestamptz(6) NULL,
	status varchar(16) NOT NULL,
	depot_id uuid NOT NULL,
	supplier_id uuid NULL,
	CONSTRAINT orders_pkey PRIMARY KEY (id),
	CONSTRAINT orders_status_check CHECK (((status)::text = ANY ((ARRAY['WAITING'::character varying, 'STARTED'::character varying, 'IN_DELIVERY'::character varying, 'DELIVERED'::character varying])::text[]))),
	CONSTRAINT fk1jbovi0pw358h7dxt7dt5u18g FOREIGN KEY (depot_id) REFERENCES public.depot(id),
	CONSTRAINT fksx1o6ggef2tp2583ohnvomxj5 FOREIGN KEY (supplier_id) REFERENCES public.supplier(id)
);


-- public.orders_packages definition

-- Drop table

-- DROP TABLE public.orders_packages;

CREATE TABLE public.orders_packages (
	order_id uuid NOT NULL,
	packages_id uuid NOT NULL,
	CONSTRAINT orders_packages_pkey PRIMARY KEY (order_id, packages_id),
	CONSTRAINT uk_niowwlutit8dr1kw5jq8o7j9h UNIQUE (packages_id),
	CONSTRAINT fkcnv96pcky2wfagkipdtc9rgob FOREIGN KEY (packages_id) REFERENCES public.package(id),
	CONSTRAINT fkgpuatdl8k4fbjd6e3rhvxkeu3 FOREIGN KEY (order_id) REFERENCES public.orders(id)
);