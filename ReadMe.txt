Libraries Used - 

Green DAO for SQLite Db Management
Rx Java for Reactive Programming
Dagger 2 for Dependency Injection
Retrofit with Rx Java for asynchronus network calls
Glide for image loading
Android Lifecycle  for View Model Support


Languages used - Java, Kotlin, XML, JSON

Architecture - Clean Model-View-View-Model (MVVM)



Flow - 

1. GET call is made to server with the help of Retrofit and data fetched in JSON is serialized with GSON.
2. Data is filtered and marked before saving to the SQLite Db.
3. List is parsed with the help of Rx Java.


