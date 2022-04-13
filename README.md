
ConsoleTwitter 
========================

* Implement a console-based social networking application (similar to Twitter) satisfying the scenarios below.
* Hexagonal architecture

<h2>Scenarios</h2>

 Posting: Alice can publish messages to a personal timeline
 
```
> Alice -> I love the weather today
> Bob -> Damn! We lost!
> Bob -> Good game though.
```
 Reading: Bob can view Alice’s timeline
 
```
> Alice
I love the weather today (5 minutes ago)
> Bob
Good game though. (1 minute ago)
Damn! We lost! (2 minutes ago)
```

 Following: Charlie can subscribe to Alice’s and Bob’s timelines, and view an aggregated list of all subscriptions
 
```
> Charlie -> I'm in New York today! Anyone want to have a coffee?
> Charlie follows Alice
> Charlie wall
Charlie - I'm in New York today! Anyone want to have a coffee? (2 seconds ago)
Alice - I love the weather today (5 minutes ago)

> Charlie follows Bob
> Charlie wall
Charlie - I'm in New York today! Anyone wants to have a coffee? (15 seconds ago)
Bob - Good game though. (1 minute ago)
Bob - Damn! We lost! (2 minutes ago)
Alice - I love the weather today (5 minutes ago)
```

<h2>Details</h2>

* The application must use the console for input and output.
* Users submit commands to the application. 
* There are four commands. “posting”, “reading”, etc. are not part of the commands.
* Commands always start with the user’s name.
* posting: user name -> message
* reading: user name
* following: user name follows another user
* wall: user name wall 

NOTE: Add instructions about how to run the application.

<h2>What we are looking for: </h2>

* Use the language you feel more comfortable.
* Don't use any framework, we want to see you code.
* Pay attention about how your code is organized.
* How you are reflecting the domain in the code.
* We love clean code.
* We don`t think 100% of code coverage is a must, but we love tests.
* We are looking forward to seeing your code and discuss with you your solution.

<h2>Comments from developer </h2>
The implemented approach is based on each user timeline. Each time the wall is requested, the app fetches
every followed user and recreates the wall. This way, the user timeline is displayed very fast because it
does not need to filter, but the wall creation is slower instead. Another solution could be to implement the
wall directly and when the timeline is requested, the wall is filtered by the user. To maintain this second approach
, the app should keep a list of 'followers' for each user, and each time that uses posts, this post has to be
propagated to all his follower walls.

<h3> Tests </h3>
It has been added an unit test for the User class, and an integration test (ServerIt) to test the general functionality.

<h3> Running </h3>

* git clone REPO
* cd CLONED_DIRECTORY
* java -classpath target\classes com.drosa.twitter.ConsoleTwitterApp
