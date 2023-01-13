select p.name as person, c.name as company 
from person as p
left join company as c
on p.company_id = c.id
where c.id <> 5;