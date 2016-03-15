# --- !Ups




CREATE table "users"("name" VARCHAR(50),"email" VARCHAR(50),"address" VARCHAR(50) , "mobile" VARCHAR(50), "emergency" VARCHAR(50) ,"id" Serial PRIMARY KEY);
INSERT into "users" values('rishabh','rishabh@gmail.com','kanpur','35555555','98998989',1);
INSERT into "users" values('deepti','deepti@hotmail.com','delhi','3555555555','8010101010',2);




# --- !Downs


DROP TABLE "users";

