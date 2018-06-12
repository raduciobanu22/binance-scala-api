# Scala Binance API

binance-scala-api is a lightweight Scala library for interacting with the [Binance API](https://www.binance.com/restapipub.html), providing complete API coverage with asynchronous requests and event streaming using WebSockets. It was ported from the Java API.

## Features
* Support for asynchronous REST requests to all [General](https://www.binance.com/restapipub.html#user-content-general-endpoints), [Market Data](https://www.binance.com/restapipub.html#user-content-market-data-endpoints), [Account](https://www.binance.com/restapipub.html#user-content-account-endpoints) endpoints, and [User](https://www.binance.com/restapipub.html#user-content-user-data-stream-endpoints) stream endpoints.
* Support for User Data, AggTrade, Trade, Kline, and Depth event streaming using [Binance WebSocket API](https://www.binance.com/restapipub.html#wss-endpoint).

## Installation
1. Install library into your Maven's local repository by running `sbt publishLocal`
2. Add the following Maven dependency to your project's `build.sbt`:
```
libraryDependencies += "com.olvind" %% "binance-scala-api" % "0.0.1-SNAPSHOT" 
```

## Usage
Have a look at the [examples](https://github.com/oyvindberg/binance-scala-api/tree/master/src/test/java/com/binance/api/examples). 
