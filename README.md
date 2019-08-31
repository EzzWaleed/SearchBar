# Search Bar APP

A simple search bar app built with kotlin using architecture, design and presentation patterns to achieve the five SOLID principles.

## Technical Overview

The app is implemented using , Dagger, Rx2, MVV, Clean Architecture, a lot of design patterns such as factory, builder ,chain of responsibility, repository, observer patterns.


1- Dagger;

	Used to achieve dependency injection by injecting dependencies without making any modules depends on other.

2- RxJava2;

	Responsible for observation and domain operations in domain & data layers.

3- Mvvm;

	Android's ViewModel is used to support orientation changes and reactive programming. This helps in using multiple
	technologies accordingly. LiveData is used in the ViewModels to utilize lifecycle awareness while RxJava is used
	in deeper layers to utilize threading and data operators.

4- Clean Architecture;

	The app is implemented in Clean Architecture to ensures that all dependencies flow 
	inwards towards the Domain Layer. Heavy use of dependency inversion reduce each module scope
	whilst increasing robustness going towards the Domain.

5- Factory & Chain of Responsibility;

    Used to achieve open for extension closed for modification principal to the domain & engine layer,
	you can add a lot of extra functionality without code modification, 
	you will only add additional classes to add new functionality.

6- Repository;

    By using repository pattern the data flow is much more organized by achieving the single entry principle to data layer.

## Additional Note
	
	The functionality is high extensible as mentioned before so much so that the already implemented functionality was written
	after the design was finished to demonstrate how easy it is to add functionality to the search bar, with minimal modification
	and with achieving high modularity.
	
## Installation

Android studio will handle the Installation, the app targets SDK 28 and the latest gradle plugin and wrapper versions
3.5 and 5.6.1 respectively at the time of writing this. Also, it uses AndroidX libraries.
	
## Contributing

The App is open sourced, however, contribution is not currently needed.
	
## License






