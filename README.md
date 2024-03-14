# 3commas signal sender

This project is a signal sender for 3commas.io. It is a Spring Boot App that sends signals to 3commas.io using their API to trigger a bot to buy or sell a coin.

## Getting Started

To get started, you need to have a 3commas.io account and a bot created. You will need the bot id and the API key and secret.

You also need to have a binance.com account and an API key and secret.

You will need to have Java 11 and Maven installed.

## Installation

To install the project, you can clone the repository and run the following command:

```
mvn clean install
```

This will install the project and download all the dependencies.

## Configuration

Update the configuration in the application.yml file with your 3commas.io and binance.com API keys and secrets.

You can also tweak the signal sender configuration in the application.yml file.

## Running the app

To run the app, you can use the following command:

```
mvn spring-boot:run
```

## How it works

The app will start and send signals to 3commas.io to buy or sell coins based on the configuration.

Following strategies are already implemented : 
- ADX
- CCI correction
- EMA
- Global extrema
- MACD
- Moving momentum
- Pivot points
- Random
- RSI
- And few custom combinated strategies...

## Disclaimer

This project is for educational purposes only. It is not a financial advice. Use it at your own risk.

This project has been created and was used in 2021. It may not work anymore as 3commas.io and binance.com APIs may have changed.
