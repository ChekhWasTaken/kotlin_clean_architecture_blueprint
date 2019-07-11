# Kotlin Clean Architecture project blueprint

This is a blueprint project with implementation of clean architecture in Kotlin for Android.

Following layers are used:

 * data -- entities, repositories and possible errors for operations. this is the inner-most layer. almost all other layers are dependant on it.
 * domain -- here use cases could be found. usecases incorporate the business logic. it is a simple interface with one method called 'execute'. It also has two type parameters, one for request and other for response. it depends on data layer, for entities and repositories.
 * db and api -- these layers are implementation of repositories. first one is local storage using Room, second is remote storage with use of Retrofit as a client.
 * app -- this contains the implementation of UI and coordination of other layers. instantiation of objects and dependency injection is implmemented here.
 
You can use this project as a starting point for your own application or for educational purposes.

Any feedback is appreciated.
