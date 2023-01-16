#### Dumitru Bianca Andreea, 322CA

# POOTV

### Description

* Implemented a platform for watching movies.

### Structure of the code
* package "actions" contains all the possible user actions organised like this:
    * package "actions_database" has all the actions that modifies the Database from input
    * package "actions_pages" has all the actions that modifies the current user session and user data
  from Database
    * package "factory_design" contains the factory for all actions
    * package "observer_design" has the main classes for this design pattern's implementation
    * package "strategy_design" contains the main classes for this design pattern's implementation
* package "database" :
    * all packages contain the information from input (platform's Database)
* package "input":
    * packages "data" and "actions" has classes for reading from json files
    * "Parsing" is used to parse all the data from input to platform's Database
* package "output":
    * "Printer" creates the messages for printing
* package "pages":
    * package "hierarchy_of_pages" has all the platform's pages:
        * "HomepageLogged" and "HomepageUnlogged" are the main homepages
        * packages "logged_subpages" and "unlogged_subpages" have the specific pages for each Homepage
    * "Page" is the super Class for every page
    * "PageFactory" is the factory of pages
* "Platform" is the main class for organising the interaction between user and pages
* "Main" is handling with the input and output files
### Implementation

* I have tried to simulate an actual user navigation between web pages. Every user has a current session which starts
when the user is logged and ends when the user is unlogged. The specific session interacts with platform's Database
and realizes the actual navigation and history (using a queue for navigation and a stack for history). So, for storing
the user's interactions with the platform I used this session. Also, I created specific classes for web pages
and for actions, strategy pattern representing the bond between every page and its possible action. These are the
concepts of my implementation generally explained, for a more detailed explanation I will describe step by step based
on concrete examples.

### Detailed example
In the beginning I parse the data from input to the server's Database. After I iterate through the actions from
input.

For example, we receive a "change page" command from input. In "Platform", we go to "change page" case where:
  * I add in my navigation queue the page name from input
  * I add in my history stack (for back command) the page name from input
  * I use factory design pattern for pages
  * I solved the particular cases for this command like: in case the user wants to refresh the Logout page, I add to
the navigation "wrong refresh" in order to print error when I call "move()" method; and I set the movie received from
input if the page is "see details"

#### How does move() method work?

Basically, at the beginning I start my iteration between pages from "Page" class (the parent class for all pages).
I verify the peek of the queue to find out which page I should move to. If the name received from input is "logout", I
remove that page from the navigation queue, I create a new "HomepageUnlogged" object and I move to "Logout page". I do
the same thing until I arrive to the wanted page. I verify in every move() method:
* if the "navigation" queue is empty, it means that that page is the wanted one, so I set it in my session as being the
current page: "pageCurr"
* if the peek of the queue has the same name as the page that I am currently on (for example I am on page "login" and
the peek is "login") it means that the user wants to do a refresh to that page
* if the peek is equal with any other available subpages of the page that I am currently on (for example I am on page
"movies" and I want to move to "see details" page) I call navigate method which contains the same actions I explained
above
* if the peek is different then the mentioned cases, I remove the string from navigation and history too and I print
error


After moving to the page the user wanted, let's say that we have an "on page" command. In this case, I parse the action 
and the feature from input to my session. Here I used strategy pattern, where based on the action's feature I created
the specific "Action" object, using  factory pattern too. If after calling "getStrategy" method the result is null, then
that action doesn't exist in my Database, else I execute the action, using "context.executeStrategy()".

Now I arrived in the action classes. Every class contains the method "execute()" which execute the actions and return
1 if the action was executed correctly, -1 if an error occur, 0 (in some particular cases). Based on these values I set
the "Action error" in my session. After that, I call actions() method from the current page, in order to print the correct
message.

Also, "back" and "database" commands are working based on the same logic explained above.

### Design patterns

I already explained before where and for what I used Factory and Strategy design patterns. Now I will explain the rest
of them:
* Singleton -> is used for Printer
* Observer -> is used for notifying all the users subscribed to the genres. In "EventManager" I store all the users
subscribed to a specific genre in a Hashmap. After that, based on event, I notify the users.