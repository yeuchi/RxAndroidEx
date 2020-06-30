# RxAndroidEx
Exercise various RxJava operators on Android

Primary principle: Observable -> Observer + Scheduler \
Secondary principle: define onError(), cancel -> unsubscribe()

## User Interface
<img width="584" src="https://user-images.githubusercontent.com/1282659/85935215-f65a6f00-b8b3-11ea-8724-25952f1bb77a.jpg">

## Debounce

## Map, FlatMap
Thanks to mt.uulu <sup>[4]</sup> on Stack overflow for the sample code.

## Timer
Thanks to Gabriel's article <sup>[2]</sup> for demonstrating how to create a linear timer task with RxJava.

## RetroFit + RxAndroid
Thanks to Jessica Thornsby article <sup>[6]</sup> for the operators. \
Identical code as those in NetworkRequestKotlin exercise, working with RxJava library. \
https://github.com/yeuchi/NetworkRequestsKotlin/blob/master/README.md \

<img width="584" alt="Screen Shot 2020-06-27 at 3 16 09 PM" src="https://user-images.githubusercontent.com/1282659/85931324-32c4a580-b889-11ea-8921-062558350419.png">

# Unit Tests
All operators above have most basic 'happy path' test(s).
https://github.com/yeuchi/NetworkRequestsKotlin/tree/master/app/src/androidTest/java/com/ctyeung/networkrequestex

# References

1. Connect to an API With Retrofit, RxJava 2, and Kotlin by Jessica Thornsby, 14 Nov 2018 \
https://code.tutsplus.com/tutorials/connect-to-an-api-with-retrofit-rxjava-2-and-kotlin--cms-32133

2. Fundamentals of RxJava with Kotlin for absolute beginners by Gabriel Leon de Mattos, March 31, 2019 \
https://medium.com/@gabrieldemattosleon/fundamentals-of-rxjava-with-kotlin-for-absolute-beginners-3d811350b701

3. RxBinding by Jake Wharton, Jan 29, 2018 \
https://github.com/JakeWharton/RxBinding

4. When do you use map vs flatMap in RxJava? \
https://stackoverflow.com/questions/22847105/when-do-you-use-map-vs-flatmap-in-rxjava
