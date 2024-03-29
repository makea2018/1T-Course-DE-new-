INSERT INTO Сотрудники
	(ФИО, "Дата рождения", "Дата начала работы", Должность, "Уровень сотрудника", "Уровень зарплаты", "Идентификатор отдела", "Наличие/отсутствие прав")
VALUES
	('Красный Б.И.', '1979/01/15', '2022/11/04', 'руководитель отдела', 'lead', 455000, 204, FALSE),
	('Зеленая Л.М.', '1984/09/12', '2022/11/04', 'разработчик', 'middle', 175000, 204, TRUE),
	('Крученый И.И.', '1998/11/05', '2022/11/04', 'разработчик', 'junior', 95000, 204, FALSE)
;
	
INSERT INTO Отделы
VALUES
	(204, 'Интеллектуальный анализ данных', 'Красный Б.И.', (SELECT COUNT (Id)
															   FROM Сотрудники
															   WHERE "Идентификатор отдела" = 204))
;

INSERT INTO Кварталы
	(ФИО, квартал1, квартал2, квартал3, квартал4)
VALUES
	('Красный Б.И.', 'A', 'B', 'A', 'A'),
	('Зеленая Л.М.', 'D', 'B', 'B', 'B'),
	('Крученый И.И.', 'B', 'C', 'A', 'A')
;