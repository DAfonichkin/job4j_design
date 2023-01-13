with people_in_company as 
	(select 
	sum(1) as people_count,
	company_id
	from 
	person
	group by company_id),  
	top_company_id as (
	select p.company_id, p.people_count
	from people_in_company p 
	join (select max(people_in_company.people_count) people_count 
		  from people_in_company) max_people
	on p.people_count = max_people.people_count)
select c.name, t.people_count
from company c
inner join  top_company_id t
on c.id = t.company_id;