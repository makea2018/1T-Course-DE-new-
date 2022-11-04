CREATE TABLE "Оценки по кварталам"
(
	Id SERIAL,
	ФИО CHARACTER VARYING,
	"1-й квартал" CHARACTER VARYING (1),
	"2-й квартал" CHARACTER VARYING (1),
	"3-й квартал" CHARACTER VARYING (1),
	"4-й квартал" CHARACTER VARYING (1),
	FOREIGN KEY (Id) REFERENCES Сотрудники (Id)
);

INSERT INTO "Оценки по кварталам"
	("1-й квартал", "2-й квартал", "3-й квартал", "4-й квартал")
VALUES
	('A', 'C', 'B', 'D'),
	('A', 'B', 'D', 'C'),
	('A', 'C', 'C', 'D'),
	('A', 'C', 'B', 'D'),
	('A', 'A', 'B', 'B'),
	('B', 'B', 'B', 'A'),
	('B', 'A', 'B', 'D'),
	('C', 'D', 'B', 'A'),
	('B', 'D', 'B', 'D'),
	('B', 'C', 'B', 'B'),
	('C', 'C', 'C', 'A'),
	('B', 'A', 'B', 'A'),
	('A', 'B', 'B', 'B'),
	('B', 'B', 'B', 'B')
;

UPDATE "Оценки по кварталам"
SET ФИО = (SELECT ФИО FROM Сотрудники WHERE Сотрудники.Id = "Оценки по кварталам".Id)