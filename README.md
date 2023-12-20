# Лабораторная работа #1

![GitHub Classroom Workflow](../../workflows/GitHub%20Classroom%20Workflow/badge.svg?branch=master)

## Continuous Integration & Continuous Delivery

### Формулировка

В рамках первой лабораторной работы требуется написать простейшее веб приложение, предоставляющее пользователю набор
операций над сущностью Person. Для этого приложения автоматизировать процесс сборки, тестирования и релиза на Heroku.

Приложение должно реализовать API:

* `GET /persons/{personId}` – информация о человеке;
* `GET /persons` – информация по всем людям;
* `POST /persons` – создание новой записи о человеке;
* `PATCH /persons/{personId}` – обновление существующей записи о человеке;
* `DELETE /persons/{personId}` – удаление записи о человеке.

[Описание API](person-service.yaml) в формате OpenAPI.

### Требования

* Исходный проект хранится на Github. Для сборки использовать
  _только_ [Github Actions](https://docs.github.com/en/actions).
* Запросы / ответы должны быть в формате JSON.
* Если запись по id не найдена, то возвращать HTTP статус 404 Not Found.
* При создании новой записи о человека (метод POST /person) возвращать HTTP статус 201 Created с пустым телом и
  Header `Location: /api/v1/persons/{personId}`, где `personId` – id созданной записи.
* Приложение должно содержать 4-5 unit-тестов на реализованные операции.
* Приложение должно быть завернуто в Docker.
* Деплой на Heroku реализовать средствами GitHub Actions, для деплоя использовать docker. Для деплоя _нельзя_
  использовать Heroku CLI или webhooks.
* В [build.yml](.github/workflows/classroom.yml) дописать шаги на сборку, прогон unit-тестов и деплой на Heroku.
* Приложение должно использовать БД для хранения записей.
* В [[inst][heroku] Lab1.postman_environment.json](postman/%5Binst%5D%5Bheroku%5D%20Lab1.postman_environment.json)
  заменить значение `baseUrl` на адрес развернутого сервиса на Heroku.

### Пояснения

* [Пример](https://github.com/Romanow/person-service) приложения на Kotlin / Spring.
* Для локальной разработки можно использовать Postgres в docker, для этого нужно запустить `docker compose up -d`,
  поднимется контейнер с Postgres 13, будет создана БД `persons` и пользователь `program:test`.
* После успешного деплоя на Heroku, через newman запускаются интеграционные тесты. Интеграционные тесты можно проверить
  локально, для этого нужно импортировать в Postman
  коллекцию [lab1.postman_collection.json](postman/%5Binst%5D%20Lab1.postman_collection.json)]) и
  environment [[local] lab1.postman_environment.json](postman/%5Binst%5D%5Blocal%5D%20Lab1.postman_environment.json).
* Для поиска нужного инструмента для сборки используется [Github Marketplace](https://github.com/marketplace).
* Пояснение как работает [Heroku](https://devcenter.heroku.com/articles/how-heroku-works).
* Для подключения БД на Heroku заходите через Dashboard в раздел Resources и в блоке `Add-ons` ищете Heroku Postgres.
  Для получения адреса, пользователя и пароля переходите в саму БД и выбираете раздел `Settings`
  -> `Database Credentials`.
* ❗Heroku не позволяет регистрировать новых пользователей, поэтому для регистрации используйте VPN.

### Прием задания

1. При получении задания у вас создается fork этого репозитория для вашего пользователя.
2. После того как все тесты успешно завершатся, в Github Classroom на Dashboard будет отмечен успешный прогон тестов.
3. ❗️С конца
   ноября [Heroku убирает Free Plan](https://help.heroku.com/RSBRUH58/removal-of-heroku-free-product-plans-faq),
   останутся только платные подписки. В связи с этим, дедлайн по сдаче ЛР #1 10 ноября. 



### 持续集成和持续交付

#### 表述

第一个实验活动要求编写一个简单的网络应用程序，为用户提供对 "人 "实体的一系列操作。
该应用程序将为用户提供对 "人 "实体的一系列操作。对于这个应用程序，要在 Heroku 上自动完成构建、测试和发布过程。

应用程序应实现以下 API：

* `GET /persons/{personId}`` - 人员信息；
* `GET /persons` - 所有人的信息；
* `POST /persons` - 创建关于某个人的新记录；

* `PATCH /persons/{personId}` - 更新一个人的现有记录；


* `DELETE /persons/{personId}` - 删除个人记录。
  以 OpenAPI 格式提供的[API 描述]（person-service.yaml）。

### 要求

* 源项目存储在 Github 上。要构建，请使用
  _only_ [Github Actions](https://docs.github.com/en/actions)。

* 请求/响应必须是 JSON 格式。

* 如果未找到 id 记录，则返回 HTTP 状态 404 "未找到"。

* 创建新的个人记录（POST /person 方法）时，返回 HTTP 状态 201 Created（已创建），并带有空主体和
  标题 `Location: /api/v1/persons/{personId}`，其中 `personId` 是创建记录的 id。

* 应用程序应包含 4-5 个针对已实现操作的单元测试。

* 应用程序应封装在 Docker 中。

* 使用 GitHub Actions 部署到 Heroku，并使用 docker 进行部署。不能使用 Heroku CLI 或 web 使用 Heroku CLI 或 webhooks。

* 在 [build.yml](.github/workflows/classroom.yml) 中添加构建、运行单元测试和部署到 Heroku 的步骤。

* 应用程序必须使用数据库存储记录。

* 在[[inst][heroku]Lab1.postman_environment.json](postman/%5Binst%5D%5Bheroku%5D%20Lab1.postman_environment.json)中
  用 Heroku 上已部署服务的地址替换 `baseUrl` 值。


#### 说明

* Kotlin / Spring 应用程序的 [示例](https://github.com/Romanow/person-service)。

* 对于本地开发，你可以通过运行 `docker compose up -d` 在 docker 中使用 Postgres、
  这将调出一个装有 Postgres 13 的容器，创建一个 `persons` 数据库和一个 `program:test` 用户。

* 成功部署到 Heroku 后，集成测试将通过 newman 运行。集成测试可以
  导入 Postman 中的 [lab1.postman_
  collection [lab1.postman_collection.json](postman/%5Binst%5D%20Lab1.postman_collection.json)]) 和
  environment [[local] lab1.postman_environment.json](postman/%5Binst%5D%5Blocal%5D%20Lab1.postman_environment.json).

* 使用 [Github Marketplace](https://github.com/marketplace) 找到合适的构建工具。

* 解释[Heroku](https://devcenter.heroku.com/articles/how-heroku-works)如何工作。

* 要在 Heroku 上连接数据库，请通过控制面板进入 "资源"，在 "附加组件 "块中查找 Heroku Postgres。
  要获取地址、用户和密码，请访问数据库本身并选择 "设置 "部分
  -> 数据库凭证"。

* Heroku 不允许注册新用户，因此请使用 VPN 注册。

### 接受任务

1. 当你接受一个作业时，你就为你的用户创建了这个版本库的一个分叉。

2. 一旦所有测试成功完成，Github Classroom 将在仪表板上标记测试运行成功。

3. ❗️С end of
   11月底 [Heroku 删除免费计划](https://help.heroku.com/RSBRUH58/removal-of-heroku-free-product-plans-faq)、
   只保留付费订阅。因此，提交 LR #1 的截止日期为 11 月 10 日。
