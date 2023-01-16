#### Dumitru Bianca Andreea, 322CA

# POOTV

### Description

* Implemented a platform for watching movies

### The structure of the code
* package "actions" contains all the possible user actions organised like this:
    * package "actions_database" has all the actions that modifies the Database from input
    * package "actions_pages" has all the actions that modifies the current user session and user information
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
### The implementation

* I have tried to simulate an actual user navigation between web pages. Every user has a current session which starts
when the user is logged and ends when the user is unlogged. The specific session interacts with platform's Database
and realizes the actual navigation and history (using a queue for navigation and a stack for history). So, for storing
the user's interactions with the platform I used this session. Also, I created specific classes for web pages
and for actions, strategy pattern representing the bond between every page and its possible action. Theese are the
concepts of my implementation generally explained, for a more detailed explanation I will describe step by step based
on concrete examples.