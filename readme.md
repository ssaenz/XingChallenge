# XING Android Coding Challenge

This challenge would give us an idea about your coding skills. Please send us your solution within 48 hours as a zip file, preferably including a git repository that has a meaningful commit history.

 

## Steps

* Bootstrap a new Android app.
* Request the GitHub API to show XING's public repositories and parse the JSON response.
* Display a list of repositories, each entry should show repo name description login of the owner
* Request only 10 repos at a time. Use an endless list with a load more mechanism. The load more should be triggered when the scrolling is close to reaching the end of the list. Check the pagination documentation.
* Show a light green background if the fork flag is false or missing, a white one otherwise.
* On a long click on a list item show a dialog to ask if go to repository html_url or owner html_url which is opened then in the browser.
 

## Additional notes

* Important for us is code efficiency, following of best practices & code readability.
* Functionality above must be implemented using common architectural patterns (MVC, MVVM or MVP).
* The business logic should be tested by unit tests.
* You can use a reactive approach (Ex. RxJava).
* The App should support orientation changes
* Make sure the app runs on a ICS+ device.
* If your API request limit exceeds, you can generate and use a personal access token here and add ?access_token=<**YOUR_ACCESS_TOKEN**> to the request URLs.
* If you have any final comments about your result please let us know via a file called final_notes.txt
* If you want to use different branches, please make sure that they'll be merged into master branch when you'll finish the task.
 

## Bonus points

* Cache the repos in a persistent storage.
* Provide a comprehensive git history.

