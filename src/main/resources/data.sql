insert into clients (name, last_name, phone_number, email, default_delivery_address, loyalty_points)
values
  ('Jan', 'Kowalski', '123456789', 'jan@example.com', 'ul. Kwiatowa 1, Gliwice', 50),
  ('Anna', 'Nowak', '987654321', 'anna@example.com', 'ul. Sloneczna 10, Zabrze', 20),
  ('Piotr', 'Wisniewski', '501234567', 'piotr.wisniewski@example.com', 'ul. Mickiewicza 15, Katowice', 120),
  ('Maria', 'Wojcik', '502345678', 'maria.wojcik@example.com', 'ul. 3 Maja 8, Chorzow', 85),
  ('Tomasz', 'Kaminski', '503456789', 'tomasz.kaminski@example.com', 'ul. Rynek 3, Rybnik', 0),
  ('Katarzyna', 'Lewandowska', '504567890', 'katarzyna.lewandowska@example.com', 'ul. Zwyciestwa 22, Gliwice', 200),
  ('Michal', 'Zielinski', '505678901', 'michal.zielinski@example.com', 'ul. Edukacji 4, Tychy', 45),
  ('Ewa', 'Dabrowska', '506789012', 'ewa.dabrowska@example.com', 'ul. Modrzejowska 11, Sosnowiec', 15),
  ('Adam', 'Krawczyk', '507890123', 'adam.krawczyk@example.com', 'ul. Wolnosci 6, Bytom', 310),
  ('Joanna', 'Piotrowska', '508901234', 'joanna.piotrowska@example.com', 'ul. Parkowa 19, Gliwice', 65);

insert into ingredients (name, amount_in_stock, storage_unit)
values
  ('Kurczak', 5000, 'g'),
  ('Maka pszenna', 10000, 'g'),
  ('Pomidor', 3000, 'g'),
  ('Wolowina', 4000, 'g'),
  ('Ziemniak', 8000, 'g'),
  ('Ser bialy', 2500, 'g'),
  ('Ogorek', 2000, 'g'),
  ('Cebula', 3000, 'g');

insert into dishes (name, category, calories, current_price, is_available, is_vegetarian)
values
  ('Zupa pomidorowa', 'SOUP', 280, 18.50, true, true),
  ('Kotlet z kurczaka', 'MAIN_COURSE', 650, 32.50, true, false),
  ('Sernik', 'DESSERT', 420, 16.00, true, true),
  ('Rosol domowy', 'SOUP', 220, 15.00, true, false),
  ('Kotlet schabowy', 'MAIN_COURSE', 720, 34.00, true, false),
  ('Pierogi ruskie', 'MAIN_COURSE', 480, 28.00, true, true),
  ('Salatka grecka', 'APPETIZER', 190, 22.00, true, true),
  ('Nalesniki z serem', 'DESSERT', 380, 19.00, true, true),
  ('Barszcz czerwony', 'SOUP', 180, 14.00, true, true),
  ('Zurek na zakwasie', 'SOUP', 310, 17.00, true, false);

insert into recipes (amount_required, unit, dish_id, ingredient_id)
values
  (
    200.0, 'g',
    (select id from dishes where name = 'Kotlet z kurczaka'),
    (select id from ingredients where name = 'Kurczak')
  ),
  (
    120.0, 'g',
    (select id from dishes where name = 'Zupa pomidorowa'),
    (select id from ingredients where name = 'Pomidor')
  ),
  (
    80.0, 'g',
    (select id from dishes where name = 'Kotlet z kurczaka'),
    (select id from ingredients where name = 'Maka pszenna')
  ),
  (
    150.0, 'g',
    (select id from dishes where name = 'Kotlet schabowy'),
    (select id from ingredients where name = 'Wolowina')
  ),
  (
    100.0, 'g',
    (select id from dishes where name = 'Pierogi ruskie'),
    (select id from ingredients where name = 'Ser bialy')
  ),
  (
    80.0, 'g',
    (select id from dishes where name = 'Salatka grecka'),
    (select id from ingredients where name = 'Ogorek')
  );

insert into orders (order_date_time, order_status, total_price, payment_method, delivery_address, type, client_id)
values
  ('2026-03-05 12:15:00', 'COMPLETED', 50.50, 'CARD', 'ul. Kwiatowa 1, Gliwice', 'DELIVERY',
    (select id from clients where email = 'jan@example.com')),
  ('2026-03-08 18:30:00', 'COMPLETED', 34.00, 'CASH', null, 'DINE_IN',
    (select id from clients where email = 'piotr.wisniewski@example.com')),
  ('2026-03-12 13:00:00', 'COMPLETED', 46.00, 'BLIK', 'ul. 3 Maja 8, Chorzow', 'DELIVERY',
    (select id from clients where email = 'maria.wojcik@example.com')),
  ('2026-03-15 19:45:00', 'COMPLETED', 28.00, 'CARD', null, 'TAKEAWAY',
    (select id from clients where email = 'tomasz.kaminski@example.com')),
  ('2026-03-20 11:20:00', 'COMPLETED', 63.50, 'ONLINE_TRANSFER', 'ul. Zwyciestwa 22, Gliwice', 'DELIVERY',
    (select id from clients where email = 'katarzyna.lewandowska@example.com')),
  ('2026-03-22 14:10:00', 'CANCELLED', 32.50, 'BLIK', null, 'TAKEAWAY',
    (select id from clients where email = 'anna@example.com')),
  ('2026-03-28 17:00:00', 'COMPLETED', 41.00, 'CASH', null, 'DINE_IN',
    (select id from clients where email = 'michal.zielinski@example.com')),
  ('2026-04-02 12:00:00', 'COMPLETED', 66.00, 'CARD', 'ul. Edukacji 4, Tychy', 'DELIVERY',
    (select id from clients where email = 'michal.zielinski@example.com')),
  ('2026-04-07 13:30:00', 'COMPLETED', 36.00, 'BLIK', null, 'TAKEAWAY',
    (select id from clients where email = 'ewa.dabrowska@example.com')),
  ('2026-04-11 20:15:00', 'COMPLETED', 84.00, 'CARD', 'ul. Mickiewicza 15, Katowice', 'DELIVERY',
    (select id from clients where email = 'piotr.wisniewski@example.com')),
  ('2026-04-14 11:45:00', 'COMPLETED', 15.00, 'CASH', null, 'DINE_IN',
    (select id from clients where email = 'joanna.piotrowska@example.com')),
  ('2026-04-18 16:20:00', 'COMPLETED', 56.00, 'ONLINE_TRANSFER', 'ul. Modrzejowska 11, Sosnowiec', 'DELIVERY',
    (select id from clients where email = 'ewa.dabrowska@example.com')),
  ('2026-04-22 12:40:00', 'COMPLETED', 51.00, 'CARD', null, 'TAKEAWAY',
    (select id from clients where email = 'adam.krawczyk@example.com')),
  ('2026-04-25 19:00:00', 'COMPLETED', 72.00, 'BLIK', 'ul. Wolnosci 6, Bytom', 'DELIVERY',
    (select id from clients where email = 'adam.krawczyk@example.com')),
  ('2026-04-29 14:55:00', 'COMPLETED', 44.00, 'CASH', null, 'DINE_IN',
    (select id from clients where email = 'katarzyna.lewandowska@example.com')),
  ('2026-05-03 12:30:00', 'COMPLETED', 60.50, 'CARD', 'ul. Sloneczna 10, Zabrze', 'DELIVERY',
    (select id from clients where email = 'anna@example.com')),
  ('2026-05-08 18:00:00', 'COMPLETED', 34.00, 'BLIK', null, 'TAKEAWAY',
    (select id from clients where email = 'jan@example.com')),
  ('2026-05-12 13:15:00', 'COMPLETED', 84.00, 'ONLINE_TRANSFER', 'ul. Parkowa 19, Gliwice', 'DELIVERY',
    (select id from clients where email = 'joanna.piotrowska@example.com')),
  ('2026-05-16 11:00:00', 'COMPLETED', 28.00, 'CASH', null, 'DINE_IN',
    (select id from clients where email = 'tomasz.kaminski@example.com')),
  ('2026-05-20 15:40:00', 'COMPLETED', 66.50, 'CARD', 'ul. Rynek 3, Rybnik', 'DELIVERY',
    (select id from clients where email = 'tomasz.kaminski@example.com')),
  ('2026-05-24 19:30:00', 'COMPLETED', 38.00, 'BLIK', null, 'TAKEAWAY',
    (select id from clients where email = 'maria.wojcik@example.com')),
  ('2026-05-28 12:00:00', 'COMPLETED', 82.50, 'CARD', 'ul. Mickiewicza 15, Katowice', 'DELIVERY',
    (select id from clients where email = 'piotr.wisniewski@example.com')),
  ('2026-05-28 18:45:00', 'COMPLETED', 48.00, 'CASH', null, 'DINE_IN',
    (select id from clients where email = 'michal.zielinski@example.com')),
  ('2026-05-31 20:00:00', 'COMPLETED', 55.00, 'BLIK', 'ul. Zwyciestwa 22, Gliwice', 'DELIVERY',
    (select id from clients where email = 'katarzyna.lewandowska@example.com')),
  ('2026-06-01 11:30:00', 'COMPLETED', 33.50, 'CARD', null, 'TAKEAWAY',
    (select id from clients where email = 'ewa.dabrowska@example.com')),
  ('2026-06-02 12:00:00', 'NEW', 50.50, 'CARD', 'ul. Kwiatowa 1, Gliwice', 'DELIVERY',
    (select id from clients where email = 'jan@example.com')),
  ('2026-06-02 12:30:00', 'IN_PREPARATION', 32.50, 'BLIK', null, 'TAKEAWAY',
    (select id from clients where email = 'anna@example.com')),
  ('2026-06-05 13:20:00', 'COMPLETED', 76.00, 'ONLINE_TRANSFER', 'ul. Wolnosci 6, Bytom', 'DELIVERY',
    (select id from clients where email = 'adam.krawczyk@example.com')),
  ('2026-06-08 17:15:00', 'READY', 44.00, 'CASH', null, 'DINE_IN',
    (select id from clients where email = 'joanna.piotrowska@example.com')),
  ('2026-06-10 14:00:00', 'COMPLETED', 61.00, 'BLIK', 'ul. 3 Maja 8, Chorzow', 'DELIVERY',
    (select id from clients where email = 'maria.wojcik@example.com')),
  ('2026-06-12 19:45:00', 'IN_PREPARATION', 53.00, 'CARD', null, 'TAKEAWAY',
    (select id from clients where email = 'piotr.wisniewski@example.com')),
  ('2026-06-13 11:10:00', 'NEW', 36.00, 'BLIK', 'ul. Edukacji 4, Tychy', 'DELIVERY',
    (select id from clients where email = 'michal.zielinski@example.com'));

insert into order_items (quantity, price_at_purchase, order_id, dish_id)
values
  (2, 16.00, (select id from orders where order_date_time = '2026-03-05 12:15:00'), (select id from dishes where name = 'Sernik')),
  (1, 18.50, (select id from orders where order_date_time = '2026-03-05 12:15:00'), (select id from dishes where name = 'Zupa pomidorowa')),

  (1, 34.00, (select id from orders where order_date_time = '2026-03-08 18:30:00'), (select id from dishes where name = 'Kotlet schabowy')),

  (1, 28.00, (select id from orders where order_date_time = '2026-03-12 13:00:00'), (select id from dishes where name = 'Pierogi ruskie')),
  (1, 18.00, (select id from orders where order_date_time = '2026-03-12 13:00:00'), (select id from dishes where name = 'Nalesniki z serem')),

  (1, 28.00, (select id from orders where order_date_time = '2026-03-15 19:45:00'), (select id from dishes where name = 'Pierogi ruskie')),

  (1, 32.50, (select id from orders where order_date_time = '2026-03-20 11:20:00'), (select id from dishes where name = 'Kotlet z kurczaka')),
  (1, 17.00, (select id from orders where order_date_time = '2026-03-20 11:20:00'), (select id from dishes where name = 'Zurek na zakwasie')),
  (1, 14.00, (select id from orders where order_date_time = '2026-03-20 11:20:00'), (select id from dishes where name = 'Barszcz czerwony')),

  (1, 32.50, (select id from orders where order_date_time = '2026-03-22 14:10:00'), (select id from dishes where name = 'Kotlet z kurczaka')),

  (1, 22.00, (select id from orders where order_date_time = '2026-03-28 17:00:00'), (select id from dishes where name = 'Salatka grecka')),
  (1, 19.00, (select id from orders where order_date_time = '2026-03-28 17:00:00'), (select id from dishes where name = 'Nalesniki z serem')),

  (1, 34.00, (select id from orders where order_date_time = '2026-04-02 12:00:00'), (select id from dishes where name = 'Kotlet schabowy')),
  (1, 32.00, (select id from orders where order_date_time = '2026-04-02 12:00:00'), (select id from dishes where name = 'Kotlet z kurczaka')),

  (1, 17.00, (select id from orders where order_date_time = '2026-04-07 13:30:00'), (select id from dishes where name = 'Zurek na zakwasie')),
  (1, 19.00, (select id from orders where order_date_time = '2026-04-07 13:30:00'), (select id from dishes where name = 'Nalesniki z serem')),

  (2, 32.50, (select id from orders where order_date_time = '2026-04-11 20:15:00'), (select id from dishes where name = 'Kotlet z kurczaka')),
  (1, 19.00, (select id from orders where order_date_time = '2026-04-11 20:15:00'), (select id from dishes where name = 'Nalesniki z serem')),

  (1, 15.00, (select id from orders where order_date_time = '2026-04-14 11:45:00'), (select id from dishes where name = 'Rosol domowy')),

  (2, 28.00, (select id from orders where order_date_time = '2026-04-18 16:20:00'), (select id from dishes where name = 'Pierogi ruskie')),

  (1, 34.00, (select id from orders where order_date_time = '2026-04-22 12:40:00'), (select id from dishes where name = 'Kotlet schabowy')),
  (1, 17.00, (select id from orders where order_date_time = '2026-04-22 12:40:00'), (select id from dishes where name = 'Zurek na zakwasie')),

  (1, 34.00, (select id from orders where order_date_time = '2026-04-25 19:00:00'), (select id from dishes where name = 'Kotlet schabowy')),
  (1, 22.00, (select id from orders where order_date_time = '2026-04-25 19:00:00'), (select id from dishes where name = 'Salatka grecka')),
  (1, 16.00, (select id from orders where order_date_time = '2026-04-25 19:00:00'), (select id from dishes where name = 'Sernik')),

  (2, 22.00, (select id from orders where order_date_time = '2026-04-29 14:55:00'), (select id from dishes where name = 'Salatka grecka')),

  (1, 32.50, (select id from orders where order_date_time = '2026-05-03 12:30:00'), (select id from dishes where name = 'Kotlet z kurczaka')),
  (1, 28.00, (select id from orders where order_date_time = '2026-05-03 12:30:00'), (select id from dishes where name = 'Pierogi ruskie')),

  (1, 34.00, (select id from orders where order_date_time = '2026-05-08 18:00:00'), (select id from dishes where name = 'Kotlet schabowy')),

  (2, 32.50, (select id from orders where order_date_time = '2026-05-12 13:15:00'), (select id from dishes where name = 'Kotlet z kurczaka')),
  (1, 19.00, (select id from orders where order_date_time = '2026-05-12 13:15:00'), (select id from dishes where name = 'Nalesniki z serem')),

  (1, 28.00, (select id from orders where order_date_time = '2026-05-16 11:00:00'), (select id from dishes where name = 'Pierogi ruskie')),

  (1, 34.00, (select id from orders where order_date_time = '2026-05-20 15:40:00'), (select id from dishes where name = 'Kotlet schabowy')),
  (1, 18.50, (select id from orders where order_date_time = '2026-05-20 15:40:00'), (select id from dishes where name = 'Zupa pomidorowa')),
  (1, 14.00, (select id from orders where order_date_time = '2026-05-20 15:40:00'), (select id from dishes where name = 'Barszcz czerwony')),

  (2, 19.00, (select id from orders where order_date_time = '2026-05-24 19:30:00'), (select id from dishes where name = 'Nalesniki z serem')),

  (1, 28.00, (select id from orders where order_date_time = '2026-05-28 12:00:00'), (select id from dishes where name = 'Pierogi ruskie')),
  (1, 32.50, (select id from orders where order_date_time = '2026-05-28 12:00:00'), (select id from dishes where name = 'Kotlet z kurczaka')),
  (1, 22.00, (select id from orders where order_date_time = '2026-05-28 12:00:00'), (select id from dishes where name = 'Salatka grecka')),

  (1, 34.00, (select id from orders where order_date_time = '2026-05-28 18:45:00'), (select id from dishes where name = 'Kotlet schabowy')),
  (1, 14.00, (select id from orders where order_date_time = '2026-05-28 18:45:00'), (select id from dishes where name = 'Barszcz czerwony')),

  (1, 17.00, (select id from orders where order_date_time = '2026-05-31 20:00:00'), (select id from dishes where name = 'Zurek na zakwasie')),
  (2, 19.00, (select id from orders where order_date_time = '2026-05-31 20:00:00'), (select id from dishes where name = 'Nalesniki z serem')),

  (1, 18.50, (select id from orders where order_date_time = '2026-06-01 11:30:00'), (select id from dishes where name = 'Zupa pomidorowa')),
  (1, 15.00, (select id from orders where order_date_time = '2026-06-01 11:30:00'), (select id from dishes where name = 'Rosol domowy')),

  (2, 16.00, (select id from orders where order_date_time = '2026-06-02 12:00:00'), (select id from dishes where name = 'Sernik')),
  (1, 18.50, (select id from orders where order_date_time = '2026-06-02 12:00:00'), (select id from dishes where name = 'Zupa pomidorowa')),

  (1, 32.50, (select id from orders where order_date_time = '2026-06-02 12:30:00'), (select id from dishes where name = 'Kotlet z kurczaka')),

  (1, 34.00, (select id from orders where order_date_time = '2026-06-05 13:20:00'), (select id from dishes where name = 'Kotlet schabowy')),
  (1, 28.00, (select id from orders where order_date_time = '2026-06-05 13:20:00'), (select id from dishes where name = 'Pierogi ruskie')),
  (1, 14.00, (select id from orders where order_date_time = '2026-06-05 13:20:00'), (select id from dishes where name = 'Barszcz czerwony')),

  (2, 22.00, (select id from orders where order_date_time = '2026-06-08 17:15:00'), (select id from dishes where name = 'Salatka grecka')),

  (1, 28.00, (select id from orders where order_date_time = '2026-06-10 14:00:00'), (select id from dishes where name = 'Pierogi ruskie')),
  (1, 19.00, (select id from orders where order_date_time = '2026-06-10 14:00:00'), (select id from dishes where name = 'Nalesniki z serem')),
  (1, 14.00, (select id from orders where order_date_time = '2026-06-10 14:00:00'), (select id from dishes where name = 'Barszcz czerwony')),

  (1, 34.00, (select id from orders where order_date_time = '2026-06-12 19:45:00'), (select id from dishes where name = 'Kotlet schabowy')),
  (1, 19.00, (select id from orders where order_date_time = '2026-06-12 19:45:00'), (select id from dishes where name = 'Nalesniki z serem')),

  (1, 17.00, (select id from orders where order_date_time = '2026-06-13 11:10:00'), (select id from dishes where name = 'Zurek na zakwasie')),
  (1, 19.00, (select id from orders where order_date_time = '2026-06-13 11:10:00'), (select id from dishes where name = 'Nalesniki z serem'));
