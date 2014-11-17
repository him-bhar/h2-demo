The connection url contains the script from which dummy tables and data will be loaded.

Connection Url :- jdbc:h2:mem:sample;INIT=RUNSCRIPT FROM 'classpath:scripts/create.sql'\\;RUNSCRIPT FROM 'classpath:scripts/create_2.sql'