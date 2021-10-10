Feature: Validating Add Place Api's

@AddPlace @Regression
Scenario Outline: Verify if place is successfully added using AddPlace API
Given Add Place Payload with "<name>" "<language>" "<address>"
When user calls "AddPlaceAPI" with "POST" HTTP request
Then The API call is success with status code 200
And "status" in response body is "OK"
And "scope" in response body is "APP"
And verify place_id created maps to "<name>" using "getPlaceAPI"

Examples:

|name|language|address|
|ABBhouse|Hindi|World Class|

@DeletePlace @Regression
Scenario: Verify if delete functionality is working fine
Given DeletePlace Payload
When user calls "deletePlaceAPI" with "POST" HTTP request	
Then The API call is success with status code 200
And "status" in response body is "OK"


