Begin transaction;
DECLARE
    cursor_products cursor for
                        select * from products;
FETCH LAST FROM cursor_products;						
FETCH BACKWARD ALL cursor_products;
FETCH LAST FROM cursor_products;
FETCH ABSOLUTE 15 FROM cursor_products;
FETCH ABSOLUTE 7 FROM cursor_products;
FETCH ABSOLUTE 2 FROM cursor_products;
FETCH PRIOR FROM cursor_products;
CLOSE cursor_products;
COMMIT;