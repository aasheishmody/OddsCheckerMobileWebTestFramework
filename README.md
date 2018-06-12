# OddsCheckerMobileWebTestFramework

<b><h1>Test Framework to demonstrate parallel run on cloud for both Android and iOS web</h1></b></br>

<b><h1>Steps to run:</h1></b></br>

1)Clone the project.</br>
2)Navigate to the project through command line.</br>
3)Execute the command - <b>mvn clean install -P iOS\ 9.3,cloud,iPhone\ 6\ Simulator,Portrait\ Mode,Production\ Environment</b> </br>
(This will run all the tests parallelly in portrait mode on cloud on the production environment with iOS 9.3 and iPhone 6 Simulator) </br>
4)Check reports - OddsCheckerMobileWebTestFramework/target/cucumber-html-report/cucumber-html-reports/overview-features.html</br></br></br>


<b><h2>Features of the framework </h2></b></br>

<b>1)Can be run on different operating systems. </b></br>
<b>2)Can be run on different versions of the operating systems. </b></br>
<b>3)Can be run on different view modes. </b></br>
<b>4)Can be run on different real devices. </b></br>
<b>5)Can be run on different simulators and emulators. </b></br>
<b>6)Can be run locally or on cloud.</b></br>
<b>7)Can be run on different environments. </b></br>
<b>8)Tests can run parallely through multiple runners and the count can be increased.  </b></br>
<b>9)Takes screenshot of failing scenarios(tests) and embeds it in the test report.  </b></br>
<b>9)All the timeouts are dynamic in nature with regular polling.  </b></br>
<b>10)Its possible to run all the tests at the same time on different devices using the same code base with the help of Jenkins  </b></br>
For example - To elaborate, the existing setup allows you to run the all the tests divided among 5 instances of the same set of maven profiles  </br>
Say you have all the tests running divided among 5 instances of iPhone 6 Simulator on iOS 9.3 on cloud in portrait mode on the production environment. But say at the time
you want to run all the tests divided among 5 instances of Android emulator on Android 7.0 on cloud in portrait mode on the production environment. This could easily be achieved 
with jenkins or another similar tool using the same code base.

