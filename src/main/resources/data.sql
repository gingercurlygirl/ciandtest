INSERT INTO users (id, age, username)
VALUES
    (1, 23, 'ivana'),
    (2, 45, 'mark'),
    (3, 15, 'david'),
    (4, 66, 'patrik'),
    (5, 51, 'ema')
;

INSERT INTO todos (id, task, create_date, user_id, completed)
VALUES
    (1, 'Get to the bank', '2025-03-16', 1, false),
    (2, 'Finish this exam on time', '2025-05-17', 2, false),
    (3, 'Buy fishing rod', '2023-02-27', 2, false),
    (4, 'Dinner anniversary', '2012-05-13', 1, false),
    (5, 'Meal prep for the week', '2024-07-11', 3, false),
    (6, 'Do nothing', '2023-05-20', 4, true),
    (7, 'Star wars movie!', '2022-04-03', 4, false)
;
