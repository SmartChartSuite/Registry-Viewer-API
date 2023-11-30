# Registry-Viewer-API
Registry-Viewer-API provides an API access to the registry data with predefined answer categories.

## How to install and run
```
> mvn clean install
> java -jar target/registry-viewer-api-#.#.#.jar
```

## API Specification
The API specification is available in `http://your.registry-viwer-api.host-url/`

## Authorization
Registry-Viewer-API uses Bearer Authorization in the header. The bearer token will be validated, and its scopes will be evaluated for the access. The token must be generated and signed properly.
