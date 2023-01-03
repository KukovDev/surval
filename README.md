# surval
Игра в разработке!
#

### Что нужно для работы с этим проектом:
1. Вы должны владеть основами работы с LibGDX.</br>
2. У вас должен быть установлен [Java SE 17](https://www.oracle.com/java/technologies/downloads/#java17) (выберите и установите под свою систему)</br>
3. У вас должен быть установлен Android SDK (очень желательно последней версии). Его можно получить скачав и установив [Android Studio](https://developer.android.com/studio)</br>
4. Android SDK Platform API level должен быть равен 33!</br>
5. Скачайте этот репозиторий, и откройте в [IntelliJ IDEA](https://www.jetbrains.com/idea/download/#section=windows) / [Android Studio](https://developer.android.com/studio)</br>
6. После того как открыли проект выберите JDK 17:</br>
![](https://user-images.githubusercontent.com/103067811/210389273-19c2f807-914e-48f7-ad0d-b97dcc1c6397.gif)


### Как запустить проект под PC и Android:
1. Для запуска игры на виртуальной машине Android, нажмите на кнопку показанную ниже, и тогда в конфигурации запуска появится модуль для запуска Android проекта:</br>
![](https://user-images.githubusercontent.com/103067811/210390321-4565f979-ba47-44dd-8948-a5827302378a.gif)


2. Для того чтобы запустить проект под PC сделайте это:</br>
![](https://user-images.githubusercontent.com/103067811/210390422-c41051ab-7730-420f-812f-4ab903bf3064.gif)


### Как собрать проект под PC и Android:
1. Чтобы собрать проект под Android сделайте это:</br>
![](https://user-images.githubusercontent.com/103067811/210390539-13acdb19-c139-4316-b134-19d8718cc9b2.gif)
Исполняемый .apk файл найдёте по пути: ```android/build/outputs/apk/debug/android-debug.apk```

2. Чтобы собрать проект под PC сделайте это:</br>
![](https://user-images.githubusercontent.com/103067811/210390513-b2623c05-2016-4d85-a859-11fb665a94dc.gif)
Исполняемый .jar файл найдёте по пути: ```desktop/build/libs/desktop.jar```

### Основные папки проекта:
```./core/src/surval/``` - Место где находится основная часть всего кода игры.</br>
```./core/assets/``` - Место где находятся спрайты, текстуры, звуки, и прочий контент используемый в игре.</br>
```./desktop/``` - Модуль, отвечающий за сборку и запуск игры на PC.</br>
```./android/``` - Модуль, отвечающий за сборку и запуск игры на Android.</br>
