Feature: Retrieve product price

  Background:
    * url baseUrl
    * def servicePath = '/api/v1/product-price'

  Scenario Outline: Test valid product price - <case_name>

    Given path servicePath
    And param brandId = <brandId>
    And param productId = <productId>
    And param date = <date>
    And header Accept = 'application/json'
    When method get
    Then status 200
    And match response == <expected_response>

    Examples:
      | case_name     | brandId | productId | date                  | expected_response
      | 'Test case 1' | 1       | 35455     | '2020-06-14T10:00:00' | { "brandId": 1, "productId": 35455, "priceList": 1, "startDate": "2020-06-14T00:00:00", "endDate": "2020-12-31T23:59:59", "price": 35.50, "currency": "EUR" }
      | 'Test case 2' | 1       | 35455     | '2020-06-14T16:00:00' | { "brandId": 1, "productId": 35455, "priceList": 2, "startDate": "2020-06-14T15:00:00", "endDate": "2020-06-14T18:30:00", "price": 25.45, "currency": "EUR" }
      | 'Test case 3' | 1       | 35455     | '2020-06-14T21:00:00' | { "brandId": 1, "productId": 35455, "priceList": 1, "startDate": "2020-06-14T00:00:00", "endDate": "2020-12-31T23:59:59", "price": 35.50, "currency": "EUR" }
      | 'Test case 4' | 1       | 35455     | '2020-06-15T10:00:00' | { "brandId": 1, "productId": 35455, "priceList": 3, "startDate": "2020-06-15T00:00:00", "endDate": "2020-06-15T11:00:00", "price": 30.50, "currency": "EUR" }
      | 'Test case 5' | 1       | 35455     | '2020-06-16T21:00:00' | { "brandId": 1, "productId": 35455, "priceList": 4, "startDate": "2020-06-15T16:00:00", "endDate": "2020-12-31T23:59:59", "price": 38.95, "currency": "EUR" }


  Scenario Outline: Test invalid input data - <case_name>

    Given path servicePath
    And param brandId = <brandId>
    And param productId = <productId>
    And param date = <date>
    And header Accept = 'application/json'
    When method get
    Then status 400

    Examples:
      | case_name           | brandId | productId | date
      | 'Null brandId'      | null    | 35455     | '2020-06-14T10:00:00'
      | 'Invalid brandId'   | 0       | 35455     | '2020-06-14T16:00:00'
      | 'Null productId'    | 1       | null      | '2020-06-14T21:00:00'
      | 'Invalid productId' | 1       | 0         | '2020-06-15T10:00:00'
      | 'Null date'         | 1       | 35455     | null


  Scenario: Test product price not found

    Given path servicePath
    And param brandId = 1
    And param productId = 35455
    And param date = '2024-06-14T10:00:00'
    And header Accept = 'application/json'
    When method get
    Then status 404
