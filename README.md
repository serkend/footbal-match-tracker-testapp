# Footbal Match Tracker Testapp

Приложение для отслеживания футбольных матчей.
Для получения данных о матчах использовал https://soccersapi.com/

Необходимо создать приложение на спортивную тематику, используя один api из списка (можно любой другой, но обязательно на спортивную тематику), либо игру.
Приложение должно быть без ActionBar и состоять минимум из одного activity и трёх фрагментов в нём:

Splash Fragment, либо Fragment с приветствием.
Здесь можно подзагрузить данные из api и сделать общее приветствие, если у вас игра, то просто приветствие.
Fragment с основным контентом.
Здесь показываем список матчей, дополнительно можно сделать внутри фрагменты с игроками и лигами, если в api присутствуют иконки, то подгружаем их через Glide или Coil. Если у вас игра, то показываем основной игровой контент, также можно внутрь включить фрагмент с завершением, где показываются очки и переход на игру заново.
Fragment с webview
Здесь открываем любой сайт и настраиваем работу с OneSignal, переход сюда должен осуществляться через кнопку из одного из первых двух фрагментов.

Дополнительное задание
1. Настроить обработку поворота экрана в activity +

Стек для приложения:
	Kotlin +  
	Retrofit +  
	Glide +  
  	Room +  
  

https://user-images.githubusercontent.com/81919513/213711854-aac2d871-c107-4799-95ff-6600a842a226.mp4

