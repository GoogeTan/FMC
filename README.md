# FMC

1. [Введение](#введение-)
2. [Примеры](#примеры-)
3. [Архитектурные решения](#архитектурные-решения-)
4. [RoadMap](#roadmap)

## Введение ##
Данный проект призван решить проблему переносимости кода в майнкрафт модах. Зачастую при переносе мода с одной версии игры на другую, даже если бизнесс логика никак не изменилась,
неизменными остаются проценты от старой кодовой базы. Это значительно замедляет скорость разработки и ведёт к новым багам. В итоге авторы предпочитают долго задерживаться на одной версии
и неохотно переходят на другие. Из-за этого существуют такие версии как 1.7.10, 1.12.2, 1.16.5, где есть множество модов, которые мы никогда не увидим на новых версиях.

## Примеры ##
// TODO примеры кода возрастающие по сложности

## Архитектурные решения ##
### Мы используем чистый функциональный стиль
Выбирая между процедурным, объектным и функциональным стилем, мы делаем предпочтение в сторону функционального. Почему? 
* Процедурный код очень трудно поддерживать, на нём практически невозможно делать расширяемые абстракции. 
* Чистый объектный подход в полной мере практически не встречается в природе и в головах программистов. Обычно, это процедурный стиль с классами. Как следствие, мы обладаем всеми теми же проблемами, что и в процедурном, а так же тонну людей, которые видят ситуацию совсем по-разному. Нам этого не надо.
* Функциональный стиль существует в чистом виде, есть множество best-practises, и он понятен людям чаще, чем объектный.
### Мы не используем семейство библиотек cats
"Кошачьи" библиотеки являются очень перегруженными. Мы получаем очень много лишнего и не имеем много нужного. Помимо того, их дизайн не всегда работает лучшим образом с tagless final(например, ApplicativeError/MonadError)
### Мы стараемся избегать наследования
// TODO мотивация
### Мы используем tagless final 
Нам необходимо обобщать совершенно разнородный код с разных версий игры в единую абстракцию. 
Классы типов позволяют это делать в простом и интуитивном стиле. Весь код FMC представляет собой лишь набор готовых кубиков кода. 
Пользователь может выборочно использовать одни и не использовать другие, почти любой код из стандартной библиотеки можно перенести в отдельную 
без потери гибкости. 
### Мы избегаем объектов
// TODO мотивация
### Мы используем FRP
// TODO мотивация
## Roadmap
### 1.0.0 
Этой версии должно быть достаточно, чтобы писать моды средней сложности. Среди возможностей должны быть:
* Создание простых блоков
* Создание блоков-мобов(BlockEntity)
* Создание любых предметов от пустышек до инструментов, батареек, ведёр и других.
* Создание групп предметов(вкладок в креативе)
* Работа с сетью
* Создание эффектов зелий
* Создание звуков
### 1.1.0
Инструментарий для создания статичных моделей блоков и предметов.
### 1.2.0
Инструментарий для создания динамических моделей для мобов(в том числе и блоков-мобов). На данный момент ожидается использование FRP стиля.
### 1.3.0
Инструментарий для создания мобов любой сложности
### 1.4.0
Инструментарий для создания графических интерфейсов любой сложности.
### 1.5.0
Инструментарий для созданий структур любой сложности.
### 1.6.0
Инструментарий для создания биомов любой сложности.
### 1.7.0
Инструментарий для создания миров любой сложности.
### 2.0.0
На данном этапе возможности библиотеки должны быть сравнимы с разработкой через один из низкоуровневых апи(Forge, NeoForge, Fabric и тп)