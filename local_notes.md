# Xing Challenge

* Git directory is included into the zip. I think is enough to see the commit history.
* For reactive programming, I have used the RxJava2 library. It was my first experience with it. I hope it is ok.
* For the api call, I have used Retrofit2  approach becouse fits quite well with reactive programing. A no-libs approach is not difficult to implement, but I wanted to experience.
* My solution for persistence storage is with SharedPreferences. It is not a good solution for bigger application, but enough for this case.
* I've noticed I have a bug with the persistence approach when load the last data... but I have no time to fix it.

I've really enjoyed coding this challenge. I hope you enjoy too reading my solution.



Feedback

The target of the presenter pattern is to implements the business logic with the data. So, the presenter have the responsability to load the data from the service layer. In the activity, we only deal with the view and handle UI events. Ir orther to process the data that came from events, it is better to create methods into the presenter to handle this data.
It is better to use a deppendency injection engine in orther to separate responsabilities and let this engine to instanciate our buisness logic.

