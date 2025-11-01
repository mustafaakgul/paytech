-- Elements: Tables, Sequences, Views, Functions, Triggers, Trigger Functions, Indexes, Constraints, etc.

-- Public Schema Tables
select * from public.accounts order by id desc;
select * from public.transactions order by id desc;
select * from public.users order by id desc;
select * from public.logs order by id desc;

-- Public Schema Sequences
select * from public.accounts_id_seq;
select * from public.transactions_id_seq;

-- Public Schema Views
--select * from public.accounts_view order by id desc;

-- Public Schema Functions
--select * from public.accounts_insert_function(1, 'Test Account', 1000.00);

-- Public Schema Triggers

-- Public Schema Trigger Functions

-- Public Schema Indexes
select * from pg_indexes where schemaname = 'public' and tablename = 'accounts';

-- Public Schema Constraints


select count(*) from public.accounts where user_id = 4 order by id desc;