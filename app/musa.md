###  DATA MANAGEMENT IN COMPOSABLES
- a composable defines the layout of an element in an app screen 
- the composable will contain data that will allow our user to interact with and also views
- Data inside a composable unit is called STATE
- to manage this state android- kotlin utilizes the concept of view mode

## VIEW MODEL
- observe the state update in a composable which determines how a composable is drawn on the android screen
- apart from observing the data it also encapsulates business logic i.e. functions working on the data
  DASHBOARD SCREEN || DASHBOARD SCREEN || DASHBOARD SCREEN a - TodoItemCard x1 a b b c c Todoitem x2

## ROOM DATABASE
steps to work with the room persistace library database library
1. add a library dependency on build gridle
   implementation("androidx.room:room-runtime:2.7.1")
   implementation("androidx.room:room-ktx:2.7.1")
2. create an entity : select the model to and annotatewith the 
@ 






   to add hilt to the project::(dependency)
   a. in the build gradle app level add a hilt dependency 
   plugins{
id("com.google.dagger.hilt.android") version "2.56.1" apply false
   }
dependencies{
implementations("com.google.dagger:hilt-android:2.56.1")
ksp("com.google.dagger:hilt-android-compiler:2.56.1")

### dependency injections
Dependency injections (DI) is a software design pattern that helps manage dependencies between
different components (classes, objects, etc.) in a clean and maintainable way
HILT : Dependency injection manager
MainActivity(launched screen)
    -showcases Dashboard screens
DashboardScreens- lists of todos i.e lazycolumn
                - add to form , alertDialog
DashboardViewmodel - observes changes on data
                   -CRUD provides a data via a repository
TodoRepository     - gets data methods from Dao
                    - and provides it to the VM

steps
1. creates an  application class : this will denote our app as an app:allowing hilt to be used for DI
2. Add the created hilt class in the manifest
3. you need to tell Hilt to provide the repository to the VM, create a module class to provide the info
4. provide hilt in our VM
5. provide hiltViewModel to screen
6. Provide hilt to the launched screen
7. get build - clean projects
8. file - invalidate caches - check all checkboxes
   