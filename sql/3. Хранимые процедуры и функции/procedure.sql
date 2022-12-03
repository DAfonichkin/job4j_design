create or replace procedure delete_by_id(d_id integer)
language 'plpgsql'
as 
$$
	BEGIN
		delete from products where id = d_id; 
	END;
$$;
