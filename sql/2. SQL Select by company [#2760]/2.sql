SELECT cmp.name, count(pr.company_id) 
from person pr
join company cmp
on pr.company_id = cmp.id
GROUP by cmp.name 
having count(pr.company_id) = (
	select count(pr.name) 
	from person pr
	GROUP by pr.company_id 
	order by count(pr.name) desc limit 1)