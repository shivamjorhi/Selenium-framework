Feature: Validating Place API's

Scenario Outline: Verify if Place is being successfully added
  Given Add PLace Payload with "<name>" "<language>" "<address>"
  When user calls "ADD_PLACE" with "POST" http request
  Then the API call is success with status code 200
  And "status" in response body is "OK"
  And "scope" in response body is "APP"
  And verify place_Id created maps to "<name>" using "GET_PLACE" API

Examples:
  | name    | language | address              |
  | AAhouse | English  | World cross center   |
  | BBhouse | French   | 123, Main Street     |