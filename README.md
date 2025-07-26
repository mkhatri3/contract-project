I used the OpenAPI Generator Maven Plugin to automatically read and parse the contract info, this
defined the structure of the REST API, including endpoints for CRUD operations on /products.

On the provider side, I used the Generator Maven Plugin to generate Java interface and data models
then implemented them in ContractProviderController, which fulfilled the contract by writing in-
memory CRUD logic. This was run on port 8080 and serve all routes under /v1.

On the consumer side, I also used the Generator Maven Plugin to generate a REST client using the
RestTemplate library. I created a class TestApiRunner that uses the generated client (ProductsApi)
to make get and post requests. The consumer connects to the provider via "http://localhost:8080/v1"
