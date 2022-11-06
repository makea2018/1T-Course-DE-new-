CREATE TABLE Кварталы
(
	Id SERIAL,
	ФИО CHARACTER VARYING,
	"квартал1" CHARACTER VARYING (1),
	"квартал2" CHARACTER VARYING (1),
	"квартал3" CHARACTER VARYING (1),
	"квартал4" CHARACTER VARYING (1),
	FOREIGN KEY (Id) REFERENCES Сотрудники (Id)
);

INSERT INTO Кварталы
	("квартал1", "квартал2", "квартал3", "квартал4")
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

UPDATE Кварталы
SET ФИО = (SELECT ФИО FROM Сотрудники WHERE Сотрудники.Id = Кварталы.Id)