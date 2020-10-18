Feature: Login successful?
Scenario: Log into Dashboard
Given Login page with url "https://www.google.at" is shown
  And today is Sunday
When User with username "admin" and password "pa44w0rd" log into
Then Dashboard is shown