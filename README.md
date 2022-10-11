# MICRO-STREAMING-ANALYTICS

This is Spring boot application that performs readings from Rabbit queue messages and generates statistics data to save them in a Mongo database.

## Starting

This instructions will allow you to configure and start the application.

### Prerequisites
- [Docker Desktop](https://www.docker.com/products/docker-desktop)
- [MongoDB Compass](https://www.mongodb.com/try/download/compass): The GUI for MongoDB
- Internet browser

### Running the app
Before running the app you have to check that docker is running.
Open a terminal and go to the project path and execute the following command:

```
docker-compose up
```

After the app starts, in the Internet browser go to this url:  
- [RabbitMQ](http://localhost:15672/#/queues/%2F/queue1)

then in the Publish message option, insert the next Json text in the Payload field:

```
{
  "version": "1.0.0",
  "datastreams": 
  [
    {
      "id": "temperature",
      "feed": "feed_1",
      "datapoints": [
        {
          "at": 1431602523123,
          "value": 25
        },
        {
          "at": 1431602523123,
          "value": 26
        },
        {
          "at": 1431602523123,
          "value": 27
        }
      ]
    },
    {
      "id": "velocity",
      "feed": "feed_2",
      "datapoints": [
        {
          "at": 1431602523123,
          "value": 35
        },
        {
          "at": 1431602523123,
          "value": 46
        },
        {
          "at": 1431602523123,
          "value": 37
        }
      ]
    }
  ]
}
```

After that, open the MongoDbCompass and check if the save data action was performed:

Connect to the Mongo database 
![img.png](img.png)

Check the records selecting statistics collection from test_db
![img_1.png](img_1.png)
