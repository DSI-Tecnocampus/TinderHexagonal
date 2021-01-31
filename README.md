# Tinder Hexagonal
An implementation of the Tinder example following the Hexagonal Architecture. You can note that:
* Adapters, Application, Domain and configuration are all in separated modules. In this way the compiler complains if we try to couple in a forbidden direction.
* We kept all the read operation in a single interface (input port). In the future we may decide to split it into different interfaces. Most of these read operations 
don't involve any domain logic at all, thus we could have decided that the web adapter called the persistence adapter directly. (We didn't do that in this code)
* The create a Profile operation is a CRUD operation actually. So, we just used the profileDTO which is kept in a separated package along with the other DTOs
* The like operation is an actual use case and its input port uses a newLikeCommand object. It's an example of full mapping
