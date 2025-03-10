# Разработка приложения «Управление финансовой системой» 

## Cтек: Kotlin + Jetpack Compose + SqLite (room libary) 

## В проекты также были применены паттерны проектирования MVVM и DI (dagger-hilt library)

### Авторизация и регистрация

Для **авторизации** пользователю необходимо быть зарегистрированным в системе и произвести вход с помощью почты и пароля. Тут имеются проверки на ввод для **корректности данных введённых** от пользователя

![Screenshot](docs/screenshots/1_loginScreen.png)

Для **регистрации** пользователю необходимо ввести ФИО, номер телефона, серию и номер паспорта с идентификационным номером паспорта, почту и пароль

![Screenshot](docs/screenshots/2_regScreen.png)

### Клиент

После авторизации пользователь попадёт в свой аккаунт на **экран профиля**. Снизу имеется BottomNavigarionBar для удобного перемещения по функциональным страничкам пользователя

![Screenshot](docs/screenshots/3_profileScreen.png)

Здесь представлены **банковские счета** пользователя. 
Пользователь также может без труда **создать новый банковский счёт** в любом из предложенных системой банков или же **открыть кредитный счёт** на выбранный срок с процентной ставкой заданной банком

![Screenshot](docs/screenshots/4_clientBankAccount.png)

Обратите внимание на IconButton, который изменил своё состояние. Это всё потому что пользователь может по клику **заморозить счёт** или же обратно перевести его в **активный статус**.
Замороженный счёт не может отправлять денежные средства, только получать.
Но есть нюанс счёт может быть заблокирован менеджером или оператором, но об этом чуть позже.
Лучше взглянуть на ещё один IconButton в виде значка **$** — при нажатии на него откроется **окно для перевода средств** на выбранный банковский счёт.

![Screenshot](docs/screenshots/5_clientBankAccount2.png)

Возле надписи о типе банковского счёта, можно нажать на значок Info, который раскроет **более подробную информацию** о данном банковском счёте

![Screenshot](docs/screenshots/6_clientBankAccountInfo.png)

Тут всё просто клиент может **подавать** или же **отзывать** заявку на свободные **зарплатные проекты**, которые были подтверждены менеджером или оператором финансовой системы

![Screenshot](docs/screenshots/7_clientSalaryProject.png)

### Специалист предприятия

Перейдём к другому пользователю — специалисту предприятия, который выполняет роль бухгалтера компании.
В разделе "Счета", он очень похож на раздел клиента, за исключением того, что **корпорационный счёт** привязан к определённой компании

![Screenshot](docs/screenshots/8_companyBankAccount.png)

А вот в разделе зарплатных проектов поинтереснее, тут можно **создать зарплатный проект**, который позже может обработать менеджер или же оператор (подтвердить или отклонить)

![Screenshot](docs/screenshots/9_companySalaryProject.png)

### Менеджер/Оператор

Рассмотрим только менеджера, так как он имеет все функции оператора и ещё свои.
В разделе "Счета" менеджер может **заблокировать банковский счёт**, после чего клиент не сможет перевести его в первоначальное состояние и тем более отправлять переводы денежных средств.

![Screenshot](docs/screenshots/10_managerBankAccount.png)

Также в этом разделе есть функционал для работы с **кредитными счетами** — менеджер может **отклонить** или **одобрить** их.

![Screenshot](docs/screenshots/11_managerBankAccount.png)

В разделе "переводы" можно наблюдать **все переводы произведённые пользователями** финансовой системы. 
Менеджер обладает правами **отмены переводов**, после чего у перевода изменится статус, а деньги будут возвращены отправителю

![Screenshot](docs/screenshots/12_managerTransfer.png)

Ну тут всё просто, **подтвержение** или **отклонение запросов на зарплатные проекты** от различных компаний

![Screenshot](docs/screenshots/13_managerSalaryProject.png)

### Администратор

Здесь администратор системы может **просматривать логи**, фактически каждое действие которое было совершено любым из пользователей финансовой системы

![Screenshot](docs/screenshots/14_adminActionLog.png)

Кроме того, он может **удалять все данные**, созданные пользователями (банковские счета, переводы, зарплатные проекты)

![Screenshot](docs/screenshots/15_adminDeleter.png)
