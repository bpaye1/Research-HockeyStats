-- Schedule
INSERT INTO SCHEDULE VALUES(0, 'Summer 2011', 'C League');
INSERT INTO SCHEDULE VALUES(1, 'Winter 2011-2012', 'B League');

-- Players
INSERT INTO PLAYER VALUES(0, 'Ben', 'Joe', 'benjoe@something.com', '1980-01-01', '111 some street', 'Dallas', 'TX', '74444' ,'111-111-1111', 99, 'Boston', 'CENTER', 'ACTIVE');
INSERT INTO PLAYER VALUES(1, 'Rick', 'Hogue', 'rickhogue@something.com', '1980-01-01', '343 some street', 'Dallas', 'TX', '74444' ,'111-111-1111', 88, 'Montreal', 'DEFENSEMEN', 'ACTIVE');
INSERT INTO PLAYER VALUES(2, 'Frank', 'Desrocher', 'fdesrocher@something.com', '1980-01-01', '434 some street', 'Irving', 'TX', '74444' ,'111-111-1111', 1, 'Montreal', 'GOALIE', 'ACTIVE');
INSERT INTO PLAYER VALUES(3, 'Ian', 'Turner', 'iturner@something.com', '1980-01-01', '431 some street', 'Keller', 'TX', '74444' ,'111-111-1111', 8, 'Dallas', 'RIGHT_WING', 'ACTIVE');
INSERT INTO PLAYER VALUES(4, 'Willie', 'Hayes', 'williehayes@something.com', '1980-01-01', '56 some street', 'Richardson', 'TX', '74444' ,'111-111-1111', 22, 'Forth Worth', 'CENTER', 'ACTIVE');

-- Game
INSERT INTO GAME VALUES(0, 0, '2012-05-03', '20:30:00', 'Flyers', 'HOME', 'Frisco Stars Center', 2, null, null);
INSERT INTO GAME VALUES(1, 0, '2012-05-10', '19:30:00', 'Hawks', 'HOME', 'Frisco Stars Center', 0, null, null);
INSERT INTO GAME VALUES(2, 0, '2012-05-17', '21:30:00', 'Habs', 'AWAY', 'Frisco Stars Center', 3, null, null);
INSERT INTO GAME VALUES(3, 0, '2012-05-24', '22:30:00', 'Chiefs', 'AWAY', 'Frisco Stars Center', 4, null, null);
INSERT INTO GAME VALUES(4, 0, '2012-05-31', '20:30:00', 'Rangers', 'HOME', 'Frisco Stars Center', 1, null, null);