# Coffee Turbo order maker
>`PRACTICE PROJECT` for Googles Udacity Android Developement Course

Coffee Turbo order maker is my practice project for a part of Android Development Scholarship that's focused on getting more experience in working with Java. The aim of this project was to create an app that can be used to order coffee in a care/restaurant. It should have an option to get users name, select number of coffees and have a selection of toppings to choose from. Price calculation should be done automatically and an order summary should be provided which the user can send by email.
----------------------------
The app is buildt based on ImageButtons and checkboxes. Checkboxes flag additional toppings and modify the price calculation. Price once calculated can be displayed in the order summary on screen or sent by email using SENDTO intent with "mailto:" uri. To give the app a bit of extra flavour I designed a custom theme for it and created a custom toast message that matches the theme. Toast message allerts the user if there's an attempt to order less than 1 or more than 100 coffees. 


