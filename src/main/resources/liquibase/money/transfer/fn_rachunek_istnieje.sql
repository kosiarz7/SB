--liquibase formatted sql

--changeset samoSieZrobilo:5 endDelimiter:"/"
create or replace function fn_rachunek_istnieje (i_nr_rachunku in varchar2) return int is
    l_id_rachunek int := -1;
    
begin
	
    select id_rachunek into l_id_rachunek from rachunki where numer = i_nr_rachunku;
     
    return l_id_rachunek;
   
    exception when others then
        return -1; 
end;
/