# TheCryptoBot
Handy cryptography toolkit on Telegram. https://telegram.me/TheCryptoBot

## Building
TheCryptoBot is written in Java. It uses Spring Boot and Bouncy Castle library for cryptography.
To build this project you have to install maven on your machine and type the following command inside the project directory:

```
mvn package
```

## Running
To run the bot just hit ``` java -jar <yourjarfile>.jar --telegram.bot.token=<token> ```. Make sure you have registered the bot.

https://core.telegram.org/bots#3-how-do-i-create-a-bot

## License
```
Copyright 2016 Dian Aditya

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
