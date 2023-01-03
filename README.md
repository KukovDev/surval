# surval
Игра в разработке!
#

### Что нужно для работы с этим проектом:
1. У вас должен быть установлен [Java SE 17](https://www.oracle.com/java/technologies/downloads/#java17) (выберите и установите под свою систему)</br>
2. У вас должен быть установлен Android SDK (очень желательно последней версии). Его можно получить скачав и установив [Android Studio](https://developer.android.com/studio)</br>
3. Android SDK Platform API level должен быть равен 33!</br>
4. Скачайте этот репозиторий, и откройте в [IntelliJ IDEA](https://www.jetbrains.com/idea/download/#section=windows) / [Android Studio](https://developer.android.com/studio)</br>

### Как запустить проект под PC и Android:
1. Для запуска игры на виртуальной машине Android, нажмите на кнопку означающую "пересобрать gradle" и тогда в конфигурации запуска появится модуль для запуска Android проекта:</br>
![](https://user-images.githubusercontent.com/103067811/210158514-6ede1d98-0a82-45a7-93f3-352876b0ca87.png)

2. Для того чтобы запустить проект под PC сделайте это:</br>
![](https://user-images.githubusercontent.com/103067811/210156728-e8a8d1f0-d2fd-4d4e-9a36-5b96d03b88f4.gif)

### Как собрать проект под PC и Android:
1. Чтобы собрать проект под PC сделайте это:</br>
![](https://user-images.githubusercontent.com/103067811/210158434-0ac05cd3-e9d2-498a-b85a-de3c9b77b0c5.gif)
Исполняемый .jar файл найдёте по пути: ```desktop/build/libs/desktop.jar```

2. Чтобы собрать проект под Android сделайте это:</br>
![](https://user-images.githubusercontent.com/103067811/210158591-3b1dc7bc-6764-4dc7-aede-6b0eba31ee90.gif)
Исполняемый .apk файл найдёте по пути: ```android/build/outputs/apk/debug/android-debug.apk```

### Основные папки проекта:
```./core/src/com/kukovdev/surval``` - Место где находится основная часть всего кода игры.</br>
```./core/assets``` - Место где находятся спрайты, текстуры, звуки, и прочий контент используемый в игре.</br>
```./desktop``` - Модуль, отвечающий за сборку и запуск игры на PC.</br>
```./android``` - Модуль, отвечающий за сборку и запуск игры на Android.</br>
