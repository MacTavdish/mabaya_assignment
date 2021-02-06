# mabaya_assignment
Started coding saturday morning (~ 08:00)
I looked at the PDF and analyzed my actions during friday.

Some assumptions and shortcuts:
1. for "serveAd" - It wasn't exactly clear the difference between bid and price in the context of serving ad.
				   It seemed that each campaign has its own products regardless of category,
				   but in the API descpription I understood that the join should be done on "category" field.
				   I decided to do the join on the category and return the product with the highest price.
				   If it wan't found, I returned just a promoted product (active = true) with highest price.
				   
2. Regarding the modules and class layout - I rounded some corners here, I didn't use interfaces and instead just referenced
											the concrete classes themselved.
											With more time and more complexity - I would create a different "interfaces" modules separate for the services module.
											
3. Testing - I didn't write tests out of time shortage - I think it's required to have unit tests on the services layers at least.

4. DI - I used "Autowired" out of comfort - but I prefer the XML option just because it's better for testing ( my opinion :) )