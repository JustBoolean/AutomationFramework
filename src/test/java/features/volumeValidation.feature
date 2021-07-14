#Basic test to see if a get call to find volumes given a genre/keyword work
Feature: Do you have this volume?
  We want to check what books we have in googlebooks
	
  Scenario Outline: Check if successfully pinged API get volumes
    Given A query parameter "<name>"
    When user calls "volumesAPI" with "GET" request
    Then API call is successful with status code 200

    Examples: 
      |name  		|		
      |quilting |
      |banking	|
      |math			|
