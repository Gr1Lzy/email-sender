# Мікросервіс для відправки електронних листів

Цей мікросервіс призначений для відправки сповіщень електронною поштою, використовуючи дані, отримані асинхронно через Message Broker.

## Інструкції щодо налаштування

1. Створіть файл `.env` в кореневій директорії вашого проекту.

2. Додайте наступні змінні середовища у файл `.env`:

```env
SMTP_HOST=smtp.example.com
SMTP_PORT=587
SMTP_USERNAME=your_username
SMTP_PASSWORD=your_password
KAFKA_BOOTSTRAP_SERVERS=localhost:9092
ELASTICSEARCH_HOST=localhost
ELASTICSEARCH_PORT=9200
