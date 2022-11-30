create view usefull_people_in_project_with_softskills
    as select pr.name project_name, p.id people_id, p.name people_name, ps.name people_softskill 
		from projects pr
		join persons p
		on p.hardskill_id =pr.hardskill_id
		join 
		person_softskills ps 
		on ps.id = p.softskill_id;;
		 
select * from usefull_people_in_project_with_softskills;