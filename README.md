# Kiwi.com-Test

<img src="https://images.pexels.com/photos/3602269/pexels-photo-3602269.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1" style="width: 50%; height: auto;" />




**Introduction**

This script tests the comparison of flight tickets on Kiwi.com and the booking of an accommodation on Booking.com. The script is written in Java and uses Selenium for web automation.
<br></br>
**Test Structure**

The script consists of the following classes:
- 'WebDriverSingleton' initializes the Selenium webdriver
- 'Guest' creates an object with guest information
- 'TestBase' contains the '@Before' and '@After annotations'
- 'KiwiPage' contains the methods, strings, and web elements used in the Kiwi.com part of the test
- 'KiwiTest' is the main body of the test that carries out testing
<br></br>

**Test Steps**

The test is divided into two parts:
<br></br>
**Part I: Kiwi.com**

The script opens the Kiwi.com homepage and chooses a flight from Paris to Zurich.

The dates are set to departure in 7 days and return in 21 days.

The script adds one checked baggage to the ticket.

The script selects the 3rd flight option.
<br></br>
The first part of the test ends by selecting the flight.


<br></br>
**Part II: Booking.com**

The script switches to the Booking.com homepage and sorts the hotels by 'Top reviewed'.

Using the JavascriptExecutor, the script chooses the 3rd hotel in the list.

The script switches to the hotel details page and chooses the first room option.

The script creates a 'Guest' object and fills in the guest information.
<br></br>
The second part of the test ends before adding payment information.

<br></br>
**Dependencies**

The script requires the following dependencies to be installed:


- Java Development Kit (JDK)

- JUnit

- Selenium WebDriver

- latest version of Chrome and the appropriate WebDriver files in your PATH

<br></br>
**Running the Test**

To run the test, you will need to have a test environment set up with required dependencies. Then, you can run the 'KiwiTest' class to execute the test.

<br></br>
**License**

This project is licensed under the MIT License.








