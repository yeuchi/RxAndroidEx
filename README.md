# RxAndroidEx
Exercise various RxJava operators on Android

Primary principle: Observable -> Observer + Scheduler \
Secondary principle: define onError(), cancel -> unsubscribe()

## User Interface
<img width="220" src="https://user-images.githubusercontent.com/67604278/86283066-f608ef00-bba5-11ea-9188-c5cdc1cec57e.png">

## CombineLast - Credentials
Similar operators are merge, join, groupJoin.
Emits to observer only when both observables have intial change.
Use map operators for each observable to validate or transform new input.
Thanks to articles by Anupam <sup>[7]</sup> and Anitaa <sup>[6]</sup>.

## Debounce - Wait N seconds
Thanks to Jessica's article <sup>[5]</sup> and demo code. 

## Map, FlatMap - transform or validate data
Thanks to mt.uulu <sup>[4]</sup> on Stack overflow for the sample code. \
<img width="350" alt="Screen Shot 2020-06-30 at 4 59 06 PM" src="https://user-images.githubusercontent.com/67604278/86181114-0e6c0180-baf3-11ea-8d4a-17d01d5f9a32.png">

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

5. Kotlin Reactive Programming for an Android Sign-Up Screen by Jessica Thorns, 6 Aug 2018 \
https://code.tutsplus.com/tutorials/kotlin-reactive-programming-for-an-android-sign-up-screen--cms-31585

6. Exploring RxJava in Android — Operators for Combining Observables by Anitaa Murthy, August 19, 2018
https://proandroiddev.com/exploring-rxjava-in-android-operators-for-combining-observables-25734080f4be

7. RxJava combineLatest, withLatestFrom by Anupam Chugh, JournalDev, 2018-08-07
https://www.journaldev.com/22605/rxjava-combinelatest-withlatestfrom
