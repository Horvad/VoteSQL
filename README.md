Project "VoteSQL"
Цель: изучить работу SQL а так же с Сервлетами в JAVA
Приложение предназначено для проведения голосования по двум номинациям:

Номинация Singer - можно проголосовать только за одного исполнителя.
Номинация Genres - можно проголосовать за 3 - 5 жанров.
Также есть возможность просмотреть статистику голосования.

Логическая модель базы данных


Просмотреть список исполнителей:

 (GET) http://host:port/WarFileName/singer
Добавить, обновить, удалить исполнителя:

 (POST)   http://host:port/WarFileName/singer?add=(singer_name) 
 (PUT)    http://host:port/WarFileName/singer?updateId=(singer_id)&newName=(new_singer_name)
 (DELETE) http://host:port/WarFileName/singer?deleteId=(singer_id)
3. Страница жанров
На данной странице предусмотрено выполнение следующих действий:

Просмотреть список жанров:

(GET) http://host:port/WarFileName/genre
Добавить, обновить, удалить жанр:

(POST)   http://host:port/WarFileName/genre?add=(genre_name) 
(PUT)    http://host:port/WarFileName/genre?updateId=(genre_id)&newName=(new_genre_name)
(DELETE) http://host:port/WarFileName/genre?deleteId=(genre_id)
4. Страница голосования
URL "/vote" (1 vote singer, 3-5 votes for genres)

 (POST)  http://host:port/WarFileName/vote?singer=(singer_id)&genre=(genre_id)&genre=(genre_id)&genre=(genre_id)&message=(message)&email=(email)
После отправки голоса пользователь получает на электронную почту информацию о своем выборе жанров и исполнителя.

5. Страница результатов голосования
На данной станице можно увидеть текущие результаты голосования:

(GET) http://host:port/WarFileName/result
