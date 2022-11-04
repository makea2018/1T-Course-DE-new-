CREATE TABLE Отделы (
	"Идентификатор отдела" INT NOT NULL,
	"Название отдела" CHARACTER VARYING(40) NOT NULL,
	"ФИО руководителя" CHARACTER VARYING(20) NOT NULL,
	"Количество сотрудников" INT NOT NULL
);

INSERT INTO Отделы
	("Идентификатор отдела", "Название отдела", "ФИО руководителя", "Количество сотрудников")
VALUES
	(4, 'Бухгалтерский отдел', 'Иванова А.С.', 5),
	(200, 'IT отдел', 'Сидоров В.В.', 9) 