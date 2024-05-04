Feature: Validating place API's

	@AddPlace
	Scenario Outline: Verify if place is being successfully added using AddPlaceAPI
		Given add place payload "<name>" "<address>" "<language>"
		When user calls "addPlaceAPI" with "POST" http request
		Then the api call is success with status code 200
		And "status" in response body is "OK"
		And "scope" in response body is "APP"
		And verify "<name>" from addPlaceaPI equals in "getPlaceAPI"
		
		Examples:
			| name      | address      | language |
			| AlluArjun |	JubileeHills | Telugu   |
#			| RamCharan | BanjaraHills | Telugu   |	

	@DeletePl ace
	Scenario: Verify if delete Place functionality is working
		Given delete place payload
		When user calls "deletePlaceAPI" with "DELETE" http request
		Then the api call is success with status code 200
		And "status" in response body is "OK" 