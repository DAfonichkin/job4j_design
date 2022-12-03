create or replace function delete_by_count(d_count integer)
returns void
language 'plpgsql'
as
$$
	BEGIN
		delete from products where count < d_count; 
	END;
$$;
