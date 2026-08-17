# Проект по автоматизации тестирования API для сайта [azurewebsites](https://fakerestapi.azurewebsites.net/index.html)
<p align="center"><a href="https://fakerestapi.azurewebsites.net/"><img src="images/screen/azure_screen.png" align="center"  height="150"  alt="reqres"/></a></p>

> Открытый Swagger для API автотестов

## ☑️ Содержание:

- Технологии и инструменты
- Список проверок, реализованных в тестах
- Запуск тестов (сборка в Jenkins) 
- Allure-отчет
- Интеграция с Allure TestOps
- Уведомление в Telegram о результатах прогона тестов


<a id="tools"></a>
## :ballot_box_with_check:Технологии и инструменты:

| Java                                                                                                      | IntelliJ  <br>  Idea                                                                                               | GitHub                                                                                                     | JUnit 5                                                                                                           | Gradle                                                                                                     | Selenide                                                                                                         | Selenoid                                                                                                                  | Allure <br> Report                                                                                                         |  Jenkins                                                                                                        |   Jira                                                                                                              | Telegram                                                                                                            |Allure <br> TestOps                                                                                                          
|:----------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------:|
| <a href="https://www.java.com/"><img src="images/logo/Java.svg" width="50" height="50"  alt="Java"/></a>  | <a href="https://www.jetbrains.com/idea/"><img src="images/logo/Idea.svg" width="50" height="50"  alt="IDEA"/></a> | <a href="https://github.com/"><img src="images/logo/GitHub.svg" width="50" height="50"  alt="Github"/></a> | <a href="https://junit.org/junit5/"><img src="images/logo/Junit5.svg" width="50" height="50"  alt="JUnit 5"/></a> | <a href="https://gradle.org/"><img src="images/logo/Gradle.svg" width="50" height="50"  alt="Gradle"/></a> | <a href="https://selenide.org/"><img src="images/logo/Selenide.svg" width="50" height="50"  alt="Selenide"/></a> | <a href="https://aerokube.com/selenoid/"><img src="images/logo/Selenoid.svg" width="50" height="50"  alt="Selenoid"/></a> | <a href="https://github.com/allure-framework"><img src="images/logo/Allure.svg" width="50" height="50"  alt="Allure"/></a> |<a href="https://www.jenkins.io/"><img src="images/logo/Jenkins.svg" width="50" height="50"  alt="Jenkins"/></a> | <a href="https://www.atlassian.com/software/jira/"><img src="images/logo/Jira.svg" width="50" height="50" alt="Java" title="Java"/></a> | <a href="https://web.telegram.org/"><img src="images/logo/Telegram.svg" width="50" height="50" alt="Telegram"/></a> |<a href="https://qameta.io/"><img src="images/logo/Allure_TO.svg" width="50" height="50" alt="Allure_TO"/></a> |

<a id="cases"></a>
## :ballot_box_with_check: Реализованные проверки:

- Позитивный тест - Успешное добавление записи Activities
- Позитивный тест - Успешное получение записи Activities по существующему id
- Позитивный тест - Успешное получение всех записей Activities
- Позитивный тест - Успешное удаление записи Activities
- Негативный тест - Ошибка при попытке добавления записи Activities с неверным типом данных в completed
- Негативный тест - Ошибка при попытке запроса записи Activities по несуществующему id
- Негативный тест - Ошибка при попытке запроса на несуществующий endpoint Activities
- Негативный тест - Ошибка при попытке удаления записи Activities без id в endpoint


## <img alt="Jenkins" height="25" src="images/logo/Jenkins.svg" width="25"/> Сборка в [Jenkins](https://jenkins.qa.guru/job/Azure_apitests/)

<p align="center">  
<img src="images/screen/jenkins_screen.png" alt="Jenkins" width="950"/>  
</p>



## :ballot_box_with_check: Параметры сборки в Jenkins:

- ENVIRONMENT: DEV/TEST/PRELIVE/PROD
- BASE_URI (ссылка на ресурс)
- COMMENT (комментарий для уведомления телеграмбота)


## <img alt="Allure" height="25" src="images/logo/Allure.svg" width="25"/></a>  <a name="Allure"></a>Allure Report	</a>


## Основная страница отчёта

<p align="center">  
<img src="images/screen/allure_screen.png" width="850">  
</p>  

## Сьюты

<p align="center">  
<img title="Allure Tests" src="images/screen/suits_screen.png" width="850">  
</p>

## Graphs

<p align="center">  
<img title="Allure Tests" src="images/screen/graphs_screen.png" width="850">  
</p>


## <img alt="Allure_TO" height="25" src="images/logo/Allure_TO.svg" width="25"/> </a>Интеграция с Allure TestOps</a>


## Allure TestOps

<p align="center">  
<img title="Allure TestOps Dashboard" src="images/screen/testops_screen.png" width="850">  
</p> 

## Allure TestOps Запуски

<p align="center">  
<img title="Allure TestOps Dashboard" src="images/screen/launches_screen.png" width="850">  
</p> 

## Allure TestOps Дашборды

<p align="center">  
<img title="Allure TestOps Dashboard" src="images/screen/dashboards_screen.png" width="850">  
</p> 


____
## <img alt="Allure" height="25" src="images/logo/Telegram.svg" width="25"/></a> Уведомление в Telegram при помощи бота
____
<p align="center">  
<img title="Allure Overview Dashboard" src="images/screen/telegram_screen.png" width="550">  
</p>