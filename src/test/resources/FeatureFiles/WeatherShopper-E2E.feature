Feature: Weather Shopper Platform - Add products, Enter Card details and Pay

  @ShopperE2E
  Scenario: Select a product type based on the weather displayed
    Given User launches the "weatherShopperUrl" site
    When User selects a product type based on the current weather
    Then User gets all the products details
    Given User adds asked products to cart
    Then User should see both products added to cart
    When User clicks on cart button
    Then User should see the added products in cart
    When User submits card details for the correct total amount
    Then User should see the payment success page