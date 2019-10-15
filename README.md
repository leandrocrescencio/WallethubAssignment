#  Wallethub Assignment 
Create a java project using selenium 3 to automate the following scenarios:
### ASSIGNMENT #1:
1. Login to Facebook. Username and Password should be on a
variable we can change.

2. Post a status message "Hello World"

### ASSIGNMENT #2:
Create a light user account in https://wallethub.com/join/light and uncheck the
checkbox to get your free credit score and report.
This is the account you should be using for this test. Then simulate the following actions:

1. Go to this URL: http://wallethub.com/profile/test_insurance_company/

2. On the reviews section of the page, hover over the stars and click on the
fourth star. Your code should actually (a) do the hover and (b) make
sure the stars inside get lit up when you hover over them, then (c) click
on the fourth star. Simply redirecting the WebDriver to the next page
isn’t an option.

3. On the page you get redirected to, click on the Policy dropdown
and change the value to “Health Insurance”.

4. Click on the link “Write a review” and write some random text
(minimum of 200 characters).

5. Press submit.

6. If you are successful, you should see a confirmation screen saying you
have reviewed the institution. You then have to go to your profile
and confirm that the “review feed” got updated.

7. Go to https://wallethub.com/profile/\<username>/reviews/ and
assertTrue() that you can see the review with the text you entered on the
previous page.

**Note**: We expect another tester to pick up your code and continue working
on it with ease. As such, we are interested in the way you code and we
want to see your take on implementing efficient, maintainable and
readable tests. So, use of any existing wrapper or helper framework/library
as a shortcut to achieving the above mentioned goals is discouraged in
favor of your own implementation.